package figures;

import deskmanagement.SideColor;
import deskmanagement.Table;

/**
 * Ладья <bald>|</bald>
 */
public class Rook extends Chessman {

    public Rook(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Rook";
        if (side == SideColor.WHITE) {
            SIGN = '♜';
        } else if (side == SideColor.BLACK) {
            SIGN = '♖';
        }
    }

    @Override
    public void setCoordinates(int y, int x) {
        super.setCoordinates(y, x);
    }


    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int currX = this.getX();
        int currY = this.getY();

        if (endX != currX && endY != currY) {
            return false;
        }

        int dx = 0;
        int dy = 0;
        if (endX != currX) {
            dx = (endX - currX) / Math.abs(endX - currX);
        }
        if (endY != currY) {
            dy = (endY - currY) / Math.abs(endY - currY);
        }
        int x = currX + dx;
        int y = currY + dy;
        while (x != endX || y != endY) {
            Chessman piece = Table.getField(x, y);
            if (piece != null) {
                return false;
            }
            x += dx;
            y += dy;
        }

        return true;
    }

}
