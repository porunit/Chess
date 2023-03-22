package figures;

import deskmanagement.SideColor;

/**
 * Пешка
 */
public class Pawn extends Chessman {
    public Pawn(SideColor color) {
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♟';
        } else if (side == SideColor.BLACK) {
            SIGN = '♙';
        }
    }
}
