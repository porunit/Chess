package figures;

import deskmanagement.SideColor;
import deskmanagement.Table;

public class Queen extends Chessman {
    public Queen(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Queen";
        if (side == SideColor.WHITE) {
            SIGN = '♛';
        } else if (side == SideColor.BLACK) {
            SIGN = '♕';
        }
    }


    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int deltaX = endX - x;
        int deltaY = endY - y;

        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            int xDir = deltaX > 0 ? 1 : -1;
            int yDir = deltaY > 0 ? 1 : -1;

            for (int i = 1; i < Math.abs(deltaX); i++) {
                int currX = x + i * xDir;
                int currY = y + i * yDir;

                if (Table.getField(currX, currY) != null && Table.getField(currX, currY).getSide() == this.side) {
                    return false;
                }
            }
            return true;
        } else if (deltaX == 0 || deltaY == 0) {
            int currX = this.getX();
            int currY = this.getY();

            if (endX != currX && endY != currY) {
                return false;
            }

            int dx = 0;
            int dy = 0;
            if (endX != currX) {
                dx = (endX - currX) / Math.abs(endX - currX);
            }
            if (endY != currY) {
                dy = (endY - currY) / Math.abs(endY - currY);
            }
            int x = currX + dx;
            int y = currY + dy;
            while (x != endX || y != endY) {
                Chessman piece = Table.getField(x, y);
                if (piece != null) {
                    return false;
                }
                x += dx;
                y += dy;
            }

            return true;
        } else {
            return false;
        }
    }
}
