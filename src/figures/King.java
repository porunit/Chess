package figures;

import deskmanagement.SideColor;

/**
 * Король
 */
public class King extends Chessman {
    public King(SideColor color) {
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♚';
        } else if (side == SideColor.BLACK) {
            SIGN = '♔';
        }
    }
}
