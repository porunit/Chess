package figures;

import exceptions.WrongTurnException;
import management.control.Position;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Chessman {

    public Bishop(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Bishop";
        if (side == SideColor.WHITE) {
            SIGN = '♝';
        } else if (side == SideColor.BLACK) {
            SIGN = '♗';
        }
    }

    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int deltaX = endX - x;
        int deltaY = endY - y;

        if (Math.abs(deltaX) != Math.abs(deltaY)) {
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
    }

    @Override
    public List<Position> getPossiblePositions() throws WrongTurnException {
        List<Position> possiblePositions = new ArrayList<>();

        for (int destX = 0; destX < 8; destX++) {
            int destY = destX;

            if (isDirectionPossible(destY, destX)) {
                Position position = new Position(x, y, destX, destY);
                possiblePositions.add(position);
            }

            destY = 8 - destX - 1;

            if (isDirectionPossible(destY, destX)) {
                Position position = new Position(x, y, destX, destY);
                possiblePositions.add(position);
            }
        }

        return possiblePositions;
    }
}

