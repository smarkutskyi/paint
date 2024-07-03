package MyDrawComponent;

import java.awt.*;
import java.io.Serializable;

public class Pen implements FragmentsInterface, Serializable {
    private static final long serialVersionUID = 1L;
    public int x1,y1,x2,y2;
    public Color color;

    public static Color COLOR;

    public Pen(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        color = COLOR;

    }

    @Override
    public void drawFragment(Graphics g) {

        g.setColor(color);
        g.drawLine(x1 , y1, x2 , y2);

    }

    @Override
    public boolean checkingArea(int x, int y) {
        return false;
    }
}
