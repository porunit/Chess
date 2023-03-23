package figures;

import deskmanagement.SideColor;
import deskmanagement.Table;

/**
 * Король
 */
public class King extends Chessman {
    public King(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "King";
        if (side == SideColor.WHITE) {
            SIGN = '♚';
        } else if (side == SideColor.BLACK) {
            SIGN = '♔';
        }
    }

    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int currX = x;
        int currY = y;

        if (Math.abs(endX - currX) > 1 || Math.abs(endY - currY) > 1) {
            return false;
        }

        Chessman destPiece = Table.getField(endX, endY);
        if (destPiece != null && destPiece.getSide() == this.getSide()) {
            return false;
        }

        return !isUnderAttack(endY, endX);
    }

    private boolean isUnderAttack(int endY, int endX) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Chessman piece = Table.getField(i, j);
                if (piece != null && piece.getSide() != this.side) {
                    if (piece.isDirectionPossible(endY, endX)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
