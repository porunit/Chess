package management.control;

import exceptions.WrongTurnException;

import java.io.Serializable;

public class Position implements Serializable {
    private final int START_X;
    private final int START_Y;
    private final int END_X;
    private final int END_Y;

    public Position(int startX, int startY, int endX, int endY) throws WrongTurnException {
        if (isCorrectX(startX) || isCorrectY(startY) || isCorrectX(endX) || isCorrectY(endY)) {
            throw new WrongTurnException("Wrong turn format");
        }
        this.START_X = startX;
        this.START_Y = startY;
        this.END_X = endX;
        this.END_Y = endY;
    }

    public int getStartX() {
        return START_X;
    }

    public int getStartY() {
        return START_Y;
    }

    public int getEndX() {
        return END_X;
    }

    public int getEndY() {
        return END_Y;
    }

    private boolean isCorrectX(int coordinate) {
        return (coordinate < 0 || coordinate > 8);
    }

    private boolean isCorrectY(int coordinate) {
        return (coordinate < 0 || coordinate >= 8);
    }
}
