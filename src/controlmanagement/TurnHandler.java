package controlmanagement;

import deskmanagement.SideColor;
import deskmanagement.Table;
import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import figures.Chessman;
import figures.King;
import io.Position;

public class TurnHandler {
    private static final King whiteKing = Table.getKing(SideColor.WHITE);
    private static final King blackKing = Table.getKing(SideColor.BLACK);

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
        Table.moveTo(figure, position.getEndX(), position.getEndY());
        if (whiteKing.isUnderAttack(whiteKing.getY(), whiteKing.getX()) && turnColor == SideColor.WHITE ||
                blackKing.isUnderAttack(blackKing.getY(), blackKing.getX()) && turnColor == SideColor.BLACK) {
            Table.moveTo(figure, position.getStartX(), position.getStartY());
            throw new IllegalTurnException(turnColor + " King under shah");
        }
    }

    private static boolean isValidTurn(Chessman figure, Position position) {
        Chessman endField = Table.getField(position.getEndX(), position.getEndY());

        if (endField != null && endField.getSide() == figure.getSide()) {
            return false;
        }
        return figure.isDirectionPossible(position.getEndY(), position.getEndX());
    }
}
