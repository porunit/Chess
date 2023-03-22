package figures;

import deskmanagement.SideColor;

/**
 * Король
 */
public class King extends Chessman {
    public King(SideColor color, int x1, int y1) {
        super(x1, y1);
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♚';
        } else if (side == SideColor.BLACK) {
            SIGN = '♔';
        }
    }
}
