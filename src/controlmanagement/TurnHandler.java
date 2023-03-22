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
        Chessman figure = Table.getChessman(position.getStartX(), position.getStartY());
        if (figure == null) {
            throw new WrongTurnException("Empty cell");
        }
        if (figure.getSide() != turnColor) {
            throw new IllegalTurnException("Now turn " + turnColor.toString());
        }
        Table.moveTo(figure, position.getEND_X(), position.getEND_Y());
    }

}
