package figures;

import deskmanagement.SideColor;
import deskmanagement.Table;

/**
 * Пешка
 */
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
            isCorrect = differenceY == 2 && side == SideColor.WHITE || differenceY == -2 && side == SideColor.BLACK;
        }
        if (!isCorrect)
            isCorrect = differenceY == 1 && side == SideColor.WHITE || differenceY == -1 && side == SideColor.BLACK;

        return (isCorrect && Table.getField(endX, endY) == null) && differenceX == 0;
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
}
