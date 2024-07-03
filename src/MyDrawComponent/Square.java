package MyDrawComponent;

import java.awt.*;
import java.io.Serializable;

public class Square  implements FragmentsInterface, Serializable {
    private static final long serialVersionUID = 1L;
    public int x, y;
    public int side;
    public Color color;

    public Square(int x, int y, int side, Color color) {
        this.x = x;
        this.y = y;
        this.side = side;
        this.color = color;
    }

    @Override
    public void drawFragment(Graphics g) {
        g.setColor(color);
        g.fillRect(x - side, y - side,
                side * 2, side * 2);
    }

    @Override
    public boolean checkingArea(int x, int y) {
        int halfSide = side / 2;
        int leftX = this.x - halfSide;
        int rightX = this.x + halfSide;
        int topY = this.y - halfSide;
        int bottomY = this.y + halfSide;

        return x >= leftX && x <= rightX && y >= topY && y <= bottomY;
    }
}
