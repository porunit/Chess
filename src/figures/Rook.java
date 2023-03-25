package figures;

import exceptions.WrongTurnException;
import management.control.Position;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;

public class Rook extends Chessman {

    public Rook(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "Rook";
        if (side == SideColor.WHITE) {
            SIGN = '♜';
        } else if (side == SideColor.BLACK) {
            SIGN = '♖';
        }
    }

    @Override
    public void setCoordinates(int y, int x) {
        super.setCoordinates(y, x);
    }


    @Override
    public boolean isDirectionPossible(int endY, int endX) {
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
    }

    @Override
    public ArrayList<Position> getPossiblePositions() throws WrongTurnException {
        ArrayList<Position> possiblePositions = new ArrayList<>();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < 8 && j >= 0 && j < 8 && (i != x || j != y)) {
                    if (Table.getField(i, j) == null || Table.getField(i, j).getSide() != this.side) {
                        possiblePositions.add(new Position(x, y, i, j));
                    }
                }
            }
        }

        return possiblePositions;
    }
}
