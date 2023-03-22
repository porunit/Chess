package io;

import exceptions.WrongTurnException;

public class Position {
    private final int START_X;
    private final int START_Y;
    private final int END_X;
    private final int END_Y;

    public Position(int startX, int startY, int endX, int endY) throws WrongTurnException {
        if (isCorrect(startX) || isCorrect(startY) || isCorrect(endX) || isCorrect(endY)) {
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

    public int getEND_X() {
        return END_X;
    }

    public int getEND_Y() {
        return END_Y;
    }

    private boolean isCorrect(int coordinate) {
        return (coordinate < 0 || coordinate > 8);
    }
}
