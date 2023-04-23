package management.network;

import exceptions.WrongTurnException;
import io.*;
import management.control.Position;
import management.desk.SideColor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkHandler {
    private static final int SERVER_PORT = 1234;

    public static void start() {
        ObjectInputStream input;
        ObjectOutputStream output;
        try {
            Socket socket = new Socket("localhost", SERVER_PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            initializePlayer(input);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                requestHandling(output, input);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void initializePlayer(ObjectInputStream input) throws IOException, ClassNotFoundException {
        SidePayload sideSetter = (SidePayload) input.readObject();
        final SideColor playerSide = sideSetter.side();
        S2CPayload received = (S2CPayload) input.readObject();
        System.out.println(received.data());
        if (playerSide == SideColor.BLACK) {
            received = (S2CPayload) input.readObject();
            System.out.println(received.data());
        }

    }

    private static void requestHandling(ObjectOutputStream output, ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.println("Enter turn:");
        Position position = InputHandler.inputTurn();
        output.writeObject(new C2SPayload(position));
        S2CPayload received = (S2CPayload) input.readObject();
        if (received.type() == PayloadType.WARNING) {
            System.out.println(received.data());
        } else if (received.type() == PayloadType.EVOLVE) {
            while (true) {
                try {
                    System.out.print("Enter figure:");
                    output.writeObject(new C2SEvolvePayload(InputHandler.inputFigure()));
                    S2CPayload evolvedReceive = (S2CPayload) input.readObject();
                    System.out.println(evolvedReceive.data());
                    break;
                } catch (WrongTurnException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println(received.data());
            received = (S2CPayload) input.readObject();
            System.out.println(received.data());
        }
    }
}
