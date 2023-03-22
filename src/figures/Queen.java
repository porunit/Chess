package figures;

import deskmanagement.SideColor;

public class Queen extends Chessman {
    public Queen(SideColor color) {
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♛';
        } else if (side == SideColor.BLACK) {
            SIGN = '♕';
        }
    }
}
