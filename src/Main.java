import exceptions.IllegalTurnException;
import exceptions.WrongTurnException;
import graphic.TableBuilder;
import management.control.TurnHandler;
import management.desk.SideColor;

public class Main {
    public static void main(String[] args) {
        TableBuilder builder = new TableBuilder();
        SideColor sideTurn = SideColor.WHITE;
        builder.drawBoard();
        while (true) {
            System.out.print("Enter turn(first coordinate & second coordinate): ");
            try {
                TurnHandler.process(sideTurn);
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