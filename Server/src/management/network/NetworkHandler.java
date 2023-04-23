package management.network;

import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import graphic.TableBuilder;
import io.C2SPayload;
import io.S2CPayload;
import io.SidePayload;
import management.control.Position;
import management.control.TurnHandler;
import management.desk.SideColor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class NetworkHandler {
    private static final TableBuilder builder;
    private static SideColor sideTurn;
    private static boolean isRunning;

    static {
        builder = new TableBuilder();
        sideTurn = SideColor.WHITE;
        isRunning = true;
    }

    public static void start() {
        Socket player1;
        Socket player2;
        ObjectOutputStream player1Output;
        ObjectInputStream player1Input;
        ObjectOutputStream player2Output;
        ObjectInputStream player2Input;

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            player1 = serverSocket.accept();
            player1Output = new ObjectOutputStream(player1.getOutputStream());
            player1Output.writeObject(new SidePayload(SideColor.WHITE));
            player1Input = new ObjectInputStream(player1.getInputStream());
            player2 = serverSocket.accept();
            player2Output = new ObjectOutputStream(player2.getOutputStream());
            player2Output.writeObject(new SidePayload(SideColor.BLACK));
            player2Input = new ObjectInputStream(player2.getInputStream());
            player1Output.writeObject(new S2CPayload(builder.drawWhiteBoardString(), PayloadType.APPROVE));
            player2Output.writeObject(new S2CPayload(builder.drawWhiteBoardString(), PayloadType.APPROVE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (isRunning) {
            try {
                if (sideTurn == SideColor.WHITE) {
                    handleRequest(player1Input, player1Output, player2Output);
                } else {
                    handleRequest(player2Input, player2Output, player1Output);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void handleRequest(ObjectInputStream fromPlayer, ObjectOutputStream toCurrentPlayer,
                                      ObjectOutputStream toOtherPlayer) throws IOException, ClassNotFoundException {
        var payload = (C2SPayload) fromPlayer.readObject();
        var position = payload.position();
        TurnHandler.setStreams(toCurrentPlayer, fromPlayer);
        S2CPayload message = validate(position);
        if (message == null || message.type() == PayloadType.APPROVE) {
            S2CPayload toClient = new S2CPayload(
                    builder.drawWhiteBoardString(),
                    PayloadType.APPROVE);
            toCurrentPlayer.writeObject(toClient);
            toOtherPlayer.writeObject(toClient);

        } else if (message.type() == PayloadType.MATE) {
            S2CPayload toClient = new S2CPayload(
                    builder.drawWhiteBoardString() + message.data(),
                    PayloadType.APPROVE);
            toCurrentPlayer.writeObject(toClient);
            toOtherPlayer.writeObject(toClient);
        } else {
            toCurrentPlayer.writeObject(message);
        }
    }

    private static S2CPayload validate(Position position) {
        try {
            TurnHandler.process(sideTurn, Objects.requireNonNull(position));
        } catch (IllegalTurnException | WrongTurnException e) {
            return new S2CPayload(e.getMessage(), PayloadType.WARNING);
        }
        if (sideTurn == SideColor.WHITE) {
            sideTurn = SideColor.BLACK;
        } else {
            sideTurn = SideColor.WHITE;
        }
        try {
            if (TurnHandler.isMate()) {
                isRunning = false;
                return new S2CPayload("\n" + sideTurn + " WIN", PayloadType.MATE);
            }
        } catch (WrongTurnException e) {
            return new S2CPayload(e.getMessage(), PayloadType.WARNING);

        }
        return null;
    }
}
