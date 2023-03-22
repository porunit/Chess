package figures;

import deskmanagement.SideColor;

/**
 * Ладья <bald>|</bald>
 */
public class Rook extends Chessman {

    public Rook(SideColor color) {
        side = color;
        if (side == SideColor.WHITE) {
            SIGN = '♜';
        } else if (side == SideColor.BLACK) {
            SIGN = '♖';
        }
    }

}
