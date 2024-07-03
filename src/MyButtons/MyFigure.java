package MyButtons;

import MyDrawComponent.PanelDraw;

import javax.swing.*;
import java.awt.event.*;


public class MyFigure extends JMenu implements ActionListener{
    private JRadioButtonMenuItem circle, square, pen;

    public MyFigure(){
        super("Figure");

        circle = new JRadioButtonMenuItem("Circle");
        square = new JRadioButtonMenuItem("Square");
        pen = new JRadioButtonMenuItem("Pen");

        this.setMnemonic(KeyEvent.VK_F);
        circle.setMnemonic(KeyEvent.VK_C);
        circle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        circle.addActionListener(this);

        circle.setSelected(true);
        PanelDraw.startCircle = true;


        square.setMnemonic(KeyEvent.VK_S);
        square.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        square.addActionListener(this);

        pen.setMnemonic(KeyEvent.VK_P);
        pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        pen.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(circle);
        group.add(square);
        group.add(pen);

        this.add(circle);
        this.add(square);
        this.add(pen);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == circle){
//            System.out.println("lol");
            PanelDraw.startCircle = true;
            PanelDraw.startSquare = false;
            PanelDraw.startPen = false;
            MyDraw.statusPaint.setText("Circle");

        } else if (e.getSource() == square) {
            PanelDraw.startSquare = true;
            PanelDraw.startCircle = false;
            PanelDraw.startPen = false;
            MyDraw.statusPaint.setText("Square");

        } else if (e.getSource() == pen) {
            PanelDraw.startPen = true;
            PanelDraw.startCircle = false;
            PanelDraw.startSquare = false;
            MyDraw.statusPaint.setText("Pen");

        }
    }
}
