package figures;

import exceptions.WrongTurnException;
import management.control.Position;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Chessman {

    public Knight(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Knight";
        if (side == SideColor.WHITE) {
            SIGN = '♞';
        } else if (side == SideColor.BLACK) {
            SIGN = '♘';
        }
    }


    @Override
    public boolean isDirectionPossible(int endY, int endX) {
        int deltaX = endX - x;
        int deltaY = endY - y;

        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) {
            return Table.getField(endX, endY) == null || Table.getField(endX, endY).getSide() != this.side;
        }
        return false;
    }

    @Override
    public List<Position> getPossiblePositions() throws WrongTurnException {
        List<Position> possiblePositions = new ArrayList<>();
        int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

        for (int i = 0; i < dx.length; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8 && (Table.getField(newX, newY) == null || Table.getField(newX, newY).getSide() != side)) {
                possiblePositions.add(new Position(x, y, newX, newY));
            }
        }
        return possiblePositions;
    }
}
