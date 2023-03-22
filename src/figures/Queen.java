package figures;

import deskmanagement.SideColor;

public class Queen extends Chessman {
    public Queen(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♛';
        } else if (side == SideColor.BLACK) {
            SIGN = '♕';
        }
    }
}
