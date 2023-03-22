package figures;

import deskmanagement.SideColor;

/**
 * Конь
 */
public class Knight extends Chessman {

    public Knight(SideColor color, int x1, int y1) {
        super(x1, y1);
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♞';
        } else if (side == SideColor.BLACK) {
            SIGN = '♘';
        }
    }
}
