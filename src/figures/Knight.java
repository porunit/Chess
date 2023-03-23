package figures;

import deskmanagement.SideColor;
import deskmanagement.Table;

public class Knight extends Chessman {

    public Knight(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Knight";
        if (side == SideColor.WHITE) {
            SIGN = '♞';
        } else if (side == SideColor.BLACK) {
            SIGN = '♘';
        }
    }


    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int deltaX = endX - x;
        int deltaY = endY - y;

        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) {
            return Table.getField(endX, endY) == null || Table.getField(endX, endY).getSide() != this.side;
        }
        return false;
    }
}
