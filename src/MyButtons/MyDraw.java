package MyButtons;

import MyDrawComponent.PanelDraw;
import MyDrawComponent.Pen;
import ProjectFile.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyDraw extends JMenu implements ActionListener {

    private JMenuItem  color, clear;
    public PanelDraw panelDrawClass;
    public static JLabel statusPaint;



    public MyDraw(){
        super("Draw");

        this.color = new JMenuItem("Color");
        this.clear = new JMenuItem("Clear");
        if (Project.statusBasedPaint == null){              //był problem że to się zmieniało po otwieraniu pliku
            statusPaint = new JLabel("Circle");       //na Circle, czyli domyslną wartosć :(
        }else{
            statusPaint = new JLabel(Project.statusBasedPaint.getText());
        }

        this.setMnemonic(KeyEvent.VK_D);

        color.setMnemonic(KeyEvent.VK_C);
        color.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                KeyEvent.ALT_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        color.addActionListener(this);

        clear.setMnemonic(KeyEvent.VK_C);
        clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        clear.addActionListener(this);

        MyFigure figure = new MyFigure();

        this.add(figure);
        this.add(color);
        this.addSeparator();
        this.add(clear);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == color){
            Color colorSet = JColorChooser.showDialog(null, "Choose color", Color.black);
            PanelDraw.colorChooser = false;
            Pen.COLOR = colorSet;
        } else if (e.getSource() == clear) {
            int option = JOptionPane.showConfirmDialog(null,
                "Czy chcesz wszystko usunać?", "Tak", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                PanelDraw.option = option;
                PanelDraw.startClear = true;
                panelDrawClass.repaint();
            }
        }
    }
}
