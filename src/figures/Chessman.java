package figures;

import deskmanagement.SideColor;

public abstract class Chessman {
    char SIGN;
    SideColor side;
    int x;
    int y;

    public Chessman(int y1, int x1) {
        x = x1;
        y = y1;
    }

    public char getSIGN() {
        return SIGN;
    }

    public SideColor getSide() {
        return side;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
