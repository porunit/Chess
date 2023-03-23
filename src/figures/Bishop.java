package figures;

import deskmanagement.SideColor;
import deskmanagement.Table;

public class Bishop extends Chessman {

    public Bishop(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Bishop";
        if (side == SideColor.WHITE) {
            SIGN = '♝';
        } else if (side == SideColor.BLACK) {
            SIGN = '♗';
        }
    }

    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int deltaX = endX - x;
        int deltaY = endY - y;

        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            return false;
        }

        int xDir = deltaX > 0 ? 1 : -1;
        int yDir = deltaY > 0 ? 1 : -1;

        for (int i = 1; i <= Math.abs(deltaX); i++) {
            int currX = x + i * xDir;
            int currY = y + i * yDir;

            if (Table.getField(currX, currY) != null && Table.getField(currX, currY).getSide() == this.side) {
                return false;
            }
        }
        return true;
    }

}

