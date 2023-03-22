package figures;

import deskmanagement.SideColor;

/**
 * Слон
 */
public class Bishop extends Chessman {

    public Bishop(SideColor color, int x1, int y1) {
        super(x1, y1);
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♝';
        } else if (side == SideColor.BLACK) {
            SIGN = '♗';
        }
    }
}
