package management.control;

import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import figures.Chessman;
import figures.King;
import figures.Pawn;
import io.InputHandler;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

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
        if (whiteKing.isUnderAttack() && turnColor == SideColor.WHITE ||
                blackKing.isUnderAttack() && turnColor == SideColor.BLACK) {
            Table.moveTo(figure, position.getStartX(), position.getStartY());
            throw new IllegalTurnException(turnColor + " King under shah");
        }
        try {
            pawnEvolve(figure);
        } catch (WrongTurnException e) {
            System.out.print(e.getMessage());
        }

    }

    private static void pawnEvolve(Chessman figure) throws WrongTurnException {
        if (!(figure instanceof Pawn && ((Pawn) figure).canEvolve())) {
            return;
        }
        System.out.print("Enter figure to swap pawn on: ");
        FigureToSwap figureToSwap = InputHandler.inputFigure();
        Table.pawnEvolve((Pawn) figure, figureToSwap);
    }

    private static boolean isValidTurn(Chessman figure, Position position) {
        Chessman endField = Table.getField(position.getEndX(), position.getEndY());

        if (endField != null && endField.getSide() == figure.getSide()) {
            return false;
        }
        return figure.isDirectionPossible(position.getEndY(), position.getEndX());
    }

    public static boolean isCheckmate(King king) throws WrongTurnException {
        if (!king.isUnderAttack()) {
            System.out.println("it not mate because King not under attack");
            return false;
        }
        ArrayList<Chessman> chessmen = Table.getChessmans(king.getSide());
        for (Chessman chessman : chessmen) {
            List<Position> positions = chessman.getPossiblePositions();
            for (Position position : positions) {
                Chessman oldField = Table.getField(position.getEndX(), position.getEndY());
                Table.moveTo(chessman, position.getEndX(), position.getEndY());
                if (!king.isUnderAttack()) {
                    Table.moveTo(chessman, position.getStartX(), position.getStartY());
                    Table.setField(position.getEndX(), position.getEndY(), oldField);
                    Table.setField(position.getStartX(), position.getStartY(), chessman);
                    chessman.setY(position.getStartY());
                    chessman.setX(position.getStartX());
                    return false;
                }
                Table.moveTo(chessman, position.getStartX(), position.getStartY());
                Table.setField(position.getEndX(), position.getEndY(), oldField);
                Table.setField(position.getStartX(), position.getStartY(), chessman);
                chessman.setY(position.getStartY());
                chessman.setX(position.getStartX());
            }
        }
        return true;
    }

    public static boolean isMate() throws WrongTurnException {
        return isCheckmate(whiteKing) || isCheckmate(blackKing);
    }
}
