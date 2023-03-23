package controlmanagement;

import deskmanagement.SideColor;
import deskmanagement.Table;
import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import figures.Chessman;
import io.Position;

public class TurnHandler {
    public static void process(SideColor turnColor) throws IllegalTurnException, WrongTurnException {
        Position position = null;
        try {
            position = InputHandler.inputTurn();
        } catch (WrongTurnException e) {
            System.out.println(e.getMessage());
        }
        assert position != null;
        Chessman figure = Table.getField(position.getStartX(), position.getStartY());
        if (figure == null) {
            throw new WrongTurnException("Empty cell");
        }
        if (figure.getSide() != turnColor) {
            throw new IllegalTurnException("Now turn " + turnColor.toString());
        }
        if (!isValidTurn(figure, position)) {
            throw new IllegalTurnException(figure.getName() + " cant move in that way");
        }
        Table.moveTo(figure, position.getEND_X(), position.getEND_Y());
    }

    private static boolean isValidTurn(Chessman figure, Position position) {
        Chessman endField = Table.getField(position.getEND_X(), position.getEND_Y());

        if (endField != null && endField.getSide() == figure.getSide()) {
            return false;
        }
        return figure.isDirectionPossible(position.getEND_Y(), position.getEND_X());
    }
}
