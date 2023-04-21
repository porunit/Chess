package figures;

import exceptions.WrongTurnException;
import management.control.Position;
import management.desk.SideColor;
import management.desk.Table;

import java.util.ArrayList;
import java.util.List;

public class King extends Chessman {
    private boolean isMoved = false;
    private boolean isChecked = false;

    public King(SideColor color, int y1, int x1) {
        super(y1, x1);
        side = color;
        name = "King";
        if (side == SideColor.WHITE) {
            SIGN = '♚';
        } else if (side == SideColor.BLACK) {
            SIGN = '♔';
        }
    }

    @Override
    public boolean isDirectionPossible(int endY, int endX) {

        int currX = x;
        int currY = y;

        if (Math.abs(endX - currX) > 1 || Math.abs(endY - currY) > 1) {
            return false;
        }

        Chessman destPiece = Table.getField(endX, endY);
        return destPiece == null || destPiece.getSide() != this.getSide();
    }

    public boolean isUnderAttack() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Chessman piece = Table.getField(i, j);
                if (piece != null && piece.getSide() != this.side) {
                    if (piece.isDirectionPossible(y, x)) {
                        isChecked = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public List<Position> getPossiblePositions() throws WrongTurnException {
        List<Position> possiblePositions = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = x + dx;
                int newY = y + dy;

                if (Table.isValidField(newY, newX)) {
                    if (isDirectionPossible(newY, newX)) {
                        possiblePositions.add(new Position(x, y, newX, newY));
                    }
                }
            }
        }
        return possiblePositions;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public void setCoordinates(int y, int x) {
        super.setCoordinates(y, x);
        isMoved = true;
    }

    public void setHasMoved() {
        this.isMoved = true;
    }
}
