package graphic;

import deskmanagement.Table;
import figures.Chessman;

public class TableBuilder {

    public void drawBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Chessman figure = Table.getChessman(j, i);
                if (figure != null)
                    System.out.print(figure.getSIGN() + "  ");
                else System.out.print("+  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

