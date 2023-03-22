import controlmanagement.TurnHandler;
import deskmanagement.SideColor;
import exceptions.IllegalTurnException;
import graphic.TableBuilder;

public class Main {
    public static void main(String[] args) {
        TableBuilder builder = new TableBuilder();
        builder.drawBoard();
        SideColor sideTurn = SideColor.WHITE;
        while (true) {
            try {
                TurnHandler.process(sideTurn);
            } catch (IllegalTurnException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (sideTurn == SideColor.WHITE) {
                sideTurn = SideColor.BLACK;
            } else {
                sideTurn = SideColor.WHITE;
            }
            builder.drawBoard();
        }
    }
}