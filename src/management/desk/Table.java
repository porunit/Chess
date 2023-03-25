package management.desk;

import figures.*;
import management.control.FigureToSwap;

import java.util.ArrayList;

public class Table {
    private static final Chessman[][] board = new Chessman[8][8];
    private static final King whiteKing;
    private static final King blackKing;

    static {
        whiteKing = new King(SideColor.WHITE, 0, 4);
        blackKing = new King(SideColor.BLACK, 7, 4);
        board[0][0] = new Rook(SideColor.WHITE, 0, 0);
        board[0][7] = new Rook(SideColor.WHITE, 0, 7);
        board[7][7] = new Rook(SideColor.BLACK, 7, 7);
        board[7][0] = new Rook(SideColor.BLACK, 7, 0);
        for (int x = 0; x < 8; x++) {
            board[1][x] = new Pawn(SideColor.WHITE, 1, x);
        }
        for (int x = 0; x < 8; x++) {
            board[6][x] = new Pawn(SideColor.BLACK, 6, x);
        }
        board[0][1] = new Knight(SideColor.WHITE, 0, 1);
        board[0][6] = new Knight(SideColor.WHITE, 0, 6);
        board[7][1] = new Knight(SideColor.BLACK, 7, 1);
        board[7][6] = new Knight(SideColor.BLACK, 7, 6);

        board[0][2] = new Bishop(SideColor.WHITE, 0, 2);
        board[0][5] = new Bishop(SideColor.WHITE, 0, 5);
        board[7][5] = new Bishop(SideColor.BLACK, 7, 5);
        board[7][2] = new Bishop(SideColor.BLACK, 7, 2);

        board[7][3] = new Queen(SideColor.BLACK, 7, 3);
        board[0][3] = new Queen(SideColor.WHITE, 0, 3);

        board[0][4] = whiteKing;
        board[7][4] = blackKing;
    }


    public static Chessman getField(int x, int y) {
        return board[y][x];
    }

    public static void moveTo(Chessman chessman, int endX, int endY) {
        board[endY][endX] = chessman;
        board[chessman.getY()][chessman.getX()] = null;
        chessman.setY(endY);
        chessman.setX(endX);
    }

    public static King getKing(SideColor color) {
        return color == SideColor.WHITE ? whiteKing : blackKing;
    }

    public static void pawnEvolve(Pawn pawn, FigureToSwap figureType) {
        switch (figureType) {
            case QUEEN -> board[pawn.getY()][pawn.getX()] = new Queen(pawn.getSide(), pawn.getY(), pawn.getX());
            case ROOK -> board[pawn.getY()][pawn.getX()] = new Rook(pawn.getSide(), pawn.getY(), pawn.getX());
            case BISHOP -> board[pawn.getY()][pawn.getX()] = new Bishop(pawn.getSide(), pawn.getY(), pawn.getX());
            case KNIGHT -> board[pawn.getY()][pawn.getX()] = new Knight(pawn.getSide(), pawn.getY(), pawn.getX());
        }
    }

    public static ArrayList<Chessman> getChessmans(SideColor side) {
        ArrayList<Chessman> figures = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Chessman Chessman = Table.getField(i, j);

                if (Chessman != null && Chessman.getSide() == side) {
                    figures.add(Chessman);
                }
            }
        }

        return figures;
    }

    public static void setField(int x, int y, Chessman piece) {
        board[y][x] = piece;
    }

    public static boolean isValidField(int i, int j) {
        return i >= 0 && i < 8 && j >= 0 && j < 8;
    }

}
