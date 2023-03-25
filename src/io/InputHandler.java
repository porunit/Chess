package io;

import exceptions.WrongTurnException;
import management.control.FigureToSwap;
import management.control.Position;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char START_LETTER = 'a';

    public static Position inputTurn() throws WrongTurnException {
        while (true) {
            try {
                String turn = scanner.nextLine();
                if (turn == null) {
                    throw new WrongTurnException("Wrong turn format");
                }
                turn = turn.replace(" ", "").toLowerCase();
                if (turn.length() != 4) {
                    throw new WrongTurnException("Wrong turn format");
                }

                int[] xY = new int[4];
                for (int i = 0; i < 4; i++) {
                    if (i % 2 != 0) {
                        xY[i] = turn.charAt(i) - '0';
                    } else {
                        xY[i] = InputHandler.convertToPosition(turn.charAt(i));
                    }
                }
                return new Position(xY[0], xY[1] - 1, xY[2], xY[3] - 1);
            } catch (WrongTurnException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static FigureToSwap inputFigure() throws WrongTurnException {
        var figureType = scanner.next().toUpperCase();
        FigureToSwap figureToSwap;
        try {
            figureToSwap = FigureToSwap.valueOf(figureType);
        } catch (IllegalArgumentException e) {
            throw new WrongTurnException("No such figure");
        }
        return figureToSwap;
    }

    private static int convertToPosition(char letter) {
        return letter - START_LETTER;
    }
}
