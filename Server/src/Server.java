import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import graphic.TableBuilder;
import io.C2SPayload;
import io.S2CPayload;
import management.control.Position;
import management.control.TurnHandler;
import management.desk.SideColor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    static SideColor sideTurn = SideColor.WHITE;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);

        Socket player1 = serverSocket.accept();
        ObjectOutputStream output = new ObjectOutputStream(player1.getOutputStream());

        output.writeObject(new S2CPayload(null, null, SideColor.WHITE));
        ObjectInputStream input = new ObjectInputStream(player1.getInputStream());

        Socket player2 = serverSocket.accept();
        ObjectOutputStream output2 = new ObjectOutputStream(player2.getOutputStream());

        output2.writeObject(new S2CPayload(null, null, SideColor.BLACK));
        ObjectInputStream input2 = new ObjectInputStream(player2.getInputStream());

        TableBuilder builder = new TableBuilder();

        output.writeObject(new S2CPayload(null, builder.drawWhiteBoardString(), null));
        output2.writeObject(new S2CPayload(null, builder.drawBlackBoardString(), null));

        while (true) {
            Position position;
            if (sideTurn == SideColor.WHITE) {
                var payload = (C2SPayload) input.readObject();
                position = payload.position();
                String message = validate(position, builder);
                if (message == null) {

                    output.writeObject(new S2CPayload(
                            null,
                            builder.drawWhiteBoardString(),
                            null
                    ));
                    output2.writeObject(new S2CPayload(
                            null,
                            builder.drawBlackBoardString(),
                            null
                    ));
                } else {
                    S2CPayload toClient = new S2CPayload(message, null, null);
                    output.writeObject(toClient);
                }

            } else {
                var payload = (C2SPayload) input2.readObject();
                position = payload.position();
                String message = validate(position, builder);
                if (message == null) {
                    output.writeObject(new S2CPayload(
                            null,
                            builder.drawWhiteBoardString(),
                            null
                    ));
                    output2.writeObject(new S2CPayload(
                            null,
                            builder.drawBlackBoardString(),
                            null
                    ));
                } else {
                    S2CPayload toClient = new S2CPayload(message, null, null);
                    output2.writeObject(toClient);
                }
            }
        }
    }

    private static String validate(Position position, TableBuilder builder) {
        try {
            TurnHandler.process(sideTurn, Objects.requireNonNull(position));
        } catch (IllegalTurnException | WrongTurnException e) {
            return e.getMessage();
        }
        if (sideTurn == SideColor.WHITE) {
            sideTurn = SideColor.BLACK;
        } else {
            sideTurn = SideColor.WHITE;
        }
        builder.drawBoard();
        try {
            if (TurnHandler.isMate()) {
                return sideTurn + " Win";
            }
        } catch (WrongTurnException e) {
            return e.getMessage();
        }
        return null;
    }
}
