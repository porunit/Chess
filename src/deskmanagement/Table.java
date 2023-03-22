package deskmanagement;

import figures.*;

public class Table {
    private static final Chessman[][] board = new Chessman[8][8];

    static {
        board[0][0] = new Rook(SideColor.WHITE);
        board[0][7] = new Rook(SideColor.WHITE);
        board[7][7] = new Rook(SideColor.BLACK);
        board[7][0] = new Rook(SideColor.BLACK);
        for (int x = 0; x < 8; x++) {
            board[1][x] = new Pawn(SideColor.WHITE);
        }
        for (int x = 0; x < 8; x++) {
            board[6][x] = new Pawn(SideColor.BLACK);
        }
        board[0][1] = new Knight(SideColor.WHITE);
        board[0][6] = new Knight(SideColor.WHITE);
        board[7][1] = new Knight(SideColor.BLACK);
        board[7][6] = new Knight(SideColor.BLACK);

        board[0][2] = new Bishop(SideColor.WHITE);
        board[0][5] = new Bishop(SideColor.WHITE);
        board[7][5] = new Bishop(SideColor.BLACK);
        board[7][2] = new Bishop(SideColor.BLACK);

        board[7][3] = new Queen(SideColor.BLACK);
        board[0][3] = new Queen(SideColor.WHITE);

        board[0][4] = new King(SideColor.WHITE);
        board[7][4] = new King(SideColor.BLACK);
    }


    public static Chessman getChessman(int x, int y) {
        return board[y][x];
    }

    public static void moveTo(int startX, int startY, int endX, int endY) {
        var tmp = board[startY][startX];
        board[endY][endX] = tmp;
        board[startY][startX] = null;
    }
}
