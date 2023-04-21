package figures;

import exceptions.WrongTurnException;
import management.control.Position;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Chessman {
    public Pawn(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Pawn";
        if (side == SideColor.WHITE) {
            SIGN = '♟';
        } else if (side == SideColor.BLACK) {
            SIGN = '♙';
        }
    }

    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int differenceY = endY - y;
        int differenceX = endX - x;
        boolean isCorrect = false;
        if (canAttack(endY, endX)) {
            return true;
        }
        if (y == 6 || y == 1) {
            if (Table.getField(x, y + 1) == null && side == SideColor.WHITE) {
                isCorrect = differenceY == 2;
            } else if (side == SideColor.BLACK && Table.getField(x, y - 1) == null) {
                isCorrect = differenceY == -2;
            }
        }
        if (!isCorrect)
            isCorrect = differenceY == 1 && side == SideColor.WHITE || differenceY == -1 && side == SideColor.BLACK;

        return (isCorrect && Table.getField(endX, endY) == null) && differenceX == 0;
    }

    public boolean canEvolve() {
        return y == 7 && side == SideColor.WHITE ||
                y == 0 && side == SideColor.BLACK;
    }

    private boolean canAttack(int endY, int endX) {
        int currX = this.getX();
        int currY = this.getY();

        int dy = endY - currY;
        int dx = Math.abs(endX - currX);
        if ((dx == 1) && (dy == this.getDirection())) {
            Chessman destPiece = Table.getField(endX, endY);
            return destPiece != null && destPiece.getSide() != this.getSide();
        }
        return false;
    }

    private int getDirection() {
        int direction;
        if (this.getSide() == SideColor.WHITE) {
            direction = 1;
        } else {
            direction = -1;
        }
        return direction;
    }

    public List<Position> getPossiblePositions() throws WrongTurnException {
        List<Position> positions = new ArrayList<>();
        int currX = getX();
        int currY = getY();

        for (int i = -1; i <= 1; i += 2) {
            int endX = currX + i;
            int endY = currY + getDirection() * i;
            if (Table.isValidField(endX, endY) && Table.getField(endX, endY) != null && Table.getField(endX, endY).getSide() != getSide()) {
                positions.add(new Position(currX, currY, endX, endY));
            }
        }

        int endY = currY + getDirection();

        if (Table.isValidField(currX, endY) && Table.getField(currX, endY) == null) {
            positions.add(new Position(currX, currY, currX, endY));
        }

        if (isStartingPosition() && Table.getField(currX, currY + getDirection()) == null && Table.getField(currX, currY + 2 * getDirection()) == null) {
            positions.add(new Position(currX, currY, currX, currY + 2 * getDirection()));
        }

        return positions;
    }

    private boolean isStartingPosition() {
        return y == 6 && side == SideColor.BLACK || y == 1 && side == SideColor.WHITE;
    }
}
