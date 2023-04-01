package management.control;

import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import figures.Chessman;
import figures.King;
import figures.Pawn;
import figures.Rook;
import io.InputHandler;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

public class TurnHandler {
    private static final King whiteKing = Table.getKing(SideColor.WHITE);
    private static final King blackKing = Table.getKing(SideColor.BLACK);
    private static boolean wasCastle = false;

    public static void process(SideColor turnColor, Position position) throws IllegalTurnException, WrongTurnException {
        assert position != null;
        Chessman figure = Table.getField(position.getStartX(), position.getStartY());
        if (figure == null) {
            throw new WrongTurnException("Empty cell");
        }
        if (figure.getSide() != turnColor) {
            throw new IllegalTurnException("Now turn " + turnColor.toString());
        }
        if (!wasCastle && figure instanceof King) {
            castleHandler(figure, position);
            if (figure.getX() == position.getEndX()) {
                wasCastle = true;
            }
        } else if (!isValidTurn(figure, position)) {
            throw new IllegalTurnException(figure.getName() + " cant move in that way");
        }
        Table.moveTo(figure, position.getEndX(), position.getEndY());
        if (whiteKing.isUnderAttack() && turnColor == SideColor.WHITE ||
                blackKing.isUnderAttack() && turnColor == SideColor.BLACK) {
            Table.moveTo(figure, position.getStartX(), position.getStartY());
            throw new IllegalTurnException(turnColor + " King under check");
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
        FigureToEvolve figureToEvolve = InputHandler.inputFigure();
        Table.pawnEvolve((Pawn) figure, figureToEvolve);
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

    private static void castle(Chessman king, int endX, int endY) throws IllegalTurnException {

        if (((King) king).isMoved()) {
            throw new IllegalTurnException("Can't castle: King has already moved");
        }
        if (((King) king).isChecked()) {
            throw new IllegalTurnException("Can't castle: King has already checked");
        }

        int direction = endX - king.getX();

        if (Math.abs(direction) > 2) {
            throw new IllegalTurnException("Can't castle: King can't move that far");
        }

        int startX, startY, endRookX, endRookY;
        if (direction < 0) {
            startX = 0;
            startY = king.getY();
            endRookX = king.getX() - 1;
        } else {
            startX = 7;
            startY = king.getY();
            endRookX = king.getX() + 1;
        }
        endRookY = startY;

        Chessman rook = Table.getField(startX, startY);
        if (!(rook instanceof Rook)) {
            throw new IllegalTurnException("Can't castle: No rook in position");
        }

        if (((Rook) rook).isMoved()) {
            throw new IllegalTurnException("Can't castle: Rook has already moved");
        }

        int start = Math.max(king.getX(), startX);
        int end = Math.min(king.getX(), startX);
        for (int i = start + 1; i < end; i++) {
            if (Table.getField(i, king.getY()) != null) {
                throw new IllegalTurnException("Can't castle: " + Table.getField(i, king.getY()).getName() + " in the way ");
            }
        }

        Table.moveTo(king, endX, endY);
        Table.moveTo(rook, endRookX, endRookY);

        ((King) king).setHasMoved();
        ((Rook) rook).setHasMoved();
    }

    private static void castleHandler(Chessman king, Position position) throws IllegalTurnException {
        if (Table.isFieldOccupied(king.getSide(), position.getEndX(), position.getEndY())) {
            return;
        }
        if (!(king instanceof King)) {
            return;
        }
        if (Math.abs(position.getEndX() - position.getStartX()) != 2) {
            return;
        }
        castle(king, position.getEndX(), position.getEndY());
    }
}