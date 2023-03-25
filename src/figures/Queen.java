package figures;

import exceptions.WrongTurnException;
import management.control.Position;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

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
            if (Table.getField(endX, endY) != null && Table.getField(endX, endY).getSide() == side) {
                return false;
            }

            int xDir = deltaX > 0 ? 1 : -1;
            int yDir = deltaY > 0 ? 1 : -1;

            for (int i = 1; i < Math.abs(deltaX); i++) {
                int currX = x + i * xDir;
                int currY = y + i * yDir;

                if (Table.getField(currX, currY) != null) {
                    return false;
                }
            }
            return true;
        } else if (deltaX == 0 || deltaY == 0) {
            int currX = x;
            int currY = y;

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

    @Override
    public List<Position> getPossiblePositions() throws WrongTurnException {
        List<Position> positions = new ArrayList<>();

        // Check all possible positions in diagonal directions
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && isDirectionPossible(y + i, x + i)) {
                positions.add(new Position(x, y, x + i, y + i));
            }
            if (x + i <= 7 && y - i >= 0 && isDirectionPossible(y - i, x + i)) {
                positions.add(new Position(x, y, x + i, y - i));
            }
            if (x - i >= 0 && y + i <= 7 && isDirectionPossible(y + i, x - i)) {
                positions.add(new Position(x, y, x - i, y + i));
            }
            if (x - i >= 0 && y - i >= 0 && isDirectionPossible(y - i, x - i)) {
                positions.add(new Position(x, y, x - i, y - i));
            }
        }

        // Check all possible positions in horizontal and vertical directions
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && isDirectionPossible(y, x + i)) {
                positions.add(new Position(x, y, x + i, y));
            }
            if (x - i >= 0 && isDirectionPossible(y, x - i)) {
                positions.add(new Position(x, y, x - i, y));
            }
            if (y + i <= 7 && isDirectionPossible(y + i, x)) {
                positions.add(new Position(x, y, x, y + i));
            }
            if (y - i >= 0 && isDirectionPossible(y - i, x)) {
                positions.add(new Position(x, y, x, y - i));
            }
        }

        return positions;
    }
}
