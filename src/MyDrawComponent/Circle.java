package MyDrawComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

public class Circle implements FragmentsInterface, Serializable {

    private static final long serialVersionUID = 1L;

    public int x, y, radius;
    public Color color;

    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }


    @Override
    public void drawFragment(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius,
                radius * 2, radius * 2);
    }

    @Override
    public boolean checkingArea(int x, int y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }
}
