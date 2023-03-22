package figures;

import deskmanagement.SideColor;

/**
 * Конь
 */
public class Knight extends Chessman {

    public Knight(SideColor color) {
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♞';
        } else if (side == SideColor.BLACK) {
            SIGN = '♘';
        }
    }
}
