import io.C2SPayload;
import io.InputHandler;
import io.S2CPayload;
import management.control.Position;
import management.desk.SideColor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to server");
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        S2CPayload sideSetter = (S2CPayload) input.readObject();
        final SideColor playerSide = sideSetter.sideToSet();

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        S2CPayload received = (S2CPayload) input.readObject();
        System.out.println(received.board());
        if (playerSide == SideColor.BLACK) {
            received = (S2CPayload) input.readObject();
            System.out.println(received.board());
        }
        while (true) {
            System.out.print("Enter turn: ");
            Position position = InputHandler.inputTurn();
            output.writeObject(new C2SPayload(position));
            received = (S2CPayload) input.readObject();
            if (received.board() == null) {
                System.out.println(received.message());
            } else {
                System.out.println(received.board());
                received = (S2CPayload) input.readObject();
                System.out.println(received.board());
            }
        }
    }
}
