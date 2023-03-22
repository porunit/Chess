package controlmanagement;

import deskmanagement.SideColor;
import deskmanagement.Table;
import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import io.Position;

public class TurnHandler {
    public static void process(SideColor turnColor) throws IllegalTurnException {
        Position position = null;
        try {
            position = InputHandler.inputTurn();
        } catch (WrongTurnException e) {
            System.out.println(e.getMessage());
        }
        assert position != null;
        if (Table.getChessman(position.getStartX(), position.getStartY()).getSide() != turnColor) {
            throw new IllegalTurnException("Now turn " + turnColor.toString());
        }
        Table.moveTo(position.getStartX(), position.getStartY(), position.getEND_X(), position.getEND_Y());
    }

}
