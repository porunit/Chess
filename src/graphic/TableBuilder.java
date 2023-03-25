package graphic;

import figures.Chessman;
import management.desk.Table;

public class TableBuilder {

    public void drawBoard() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                Chessman figure = Table.getField(j, i);
                if (figure != null)
                    System.out.print(figure.getSIGN() + "  ");
                else System.out.print("+  ");
            }
            System.out.println();
        }
        System.out.print("  a  b  c  d  e  f  g  h\n");
        System.out.println();
    }
}

