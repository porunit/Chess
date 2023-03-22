package figures;

import deskmanagement.SideColor;

/**
 * Слон
 */
public class Bishop extends Chessman {

    public Bishop(SideColor color) {
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♝';
        } else if (side == SideColor.BLACK) {
            SIGN = '♗';
        }
    }
}
