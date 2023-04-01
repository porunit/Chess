import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import graphic.TableBuilder;
import io.InputHandler;
import management.control.Position;
import management.control.TurnHandler;
import management.desk.SideColor;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        TableBuilder builder = new TableBuilder();
        SideColor sideTurn = SideColor.WHITE;
        builder.drawBoard();
        while (true) {
            System.out.println();
            System.out.print("Enter turn(first coordinate & second coordinate): ");
            Position position;
            position = InputHandler.inputTurn();
            try {
                TurnHandler.process(sideTurn, Objects.requireNonNull(position));
            } catch (IllegalTurnException | WrongTurnException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (sideTurn == SideColor.WHITE) {
                sideTurn = SideColor.BLACK;
            } else {
                sideTurn = SideColor.WHITE;
            }
            System.out.println();
            builder.drawBoard();
            try {
                if (TurnHandler.isMate()) {
                    System.out.print(sideTurn + " Win");
                    break;
                }
            } catch (WrongTurnException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}