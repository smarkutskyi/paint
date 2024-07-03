package MyDrawComponent;

import MyButtons.MyDraw;
import MyButtons.MyFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PanelDraw extends JPanel {
    public static List<FragmentsInterface> fragmentsList = new ArrayList<>();
    public static boolean startCircle = false;
    public static boolean startSquare = false;
    public static boolean startPen = false;
    public static boolean startClear = false;
    public static boolean colorChooser = true;
    public static boolean pressed_D = false;
    public static boolean saved = false;
    public static int option;
    private int oldX,oldY;


    public PanelDraw() {
//        this.setMinimumSize(new Dimension(400, 200));
        this.setPreferredSize(new Dimension(10000, 10000));
//        this.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        this.setFocusable(true);
        this.setBackground(Color.white);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1 && getMousePosition() != null && startCircle) {
                    fragmentsList.add(new Circle((int) getMousePosition().getX(), (int) getMousePosition().getY(),
                            50, getRandomColor()));
                    if (saved) {
                        MyFile.statusFile.setText("Modified");
                    }
                    MyDraw.statusPaint.setText("Circle");
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_F1 && getMousePosition() != null && startSquare) {
                    fragmentsList.add(new Square((int) getMousePosition().getX(), (int) getMousePosition().getY(),
                            50, getRandomColor()));
                    if (saved) {
                        MyFile.statusFile.setText("Modified");
                    }
                    MyDraw.statusPaint.setText("Square");
                    repaint();
//                } else if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown() && e.isShiftDown()) {
//                    repaint(); // Żeby odrazu zostaw wyciszczony JComponent
                } else if (e.getKeyCode() == KeyEvent.VK_D ) {
                    pressed_D = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) pressed_D = false;
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (startClear) repaint();
                if (pressed_D) {
                    for (int i = fragmentsList.size()-1; i >= 0; i--){
                        FragmentsInterface f = fragmentsList.get(i);
                        if (f.checkingArea(e.getX(), e.getY())){
                            int option = JOptionPane.showConfirmDialog(null,
                                    "Czy chcesz usunać ten element?", "Tak", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                fragmentsList.remove(i);
                                if (saved) {
                                    MyFile.statusFile.setText("Modified");
                                }
                                repaint();
                            }
                        }
                    }
                    pressed_D = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (startPen) {
                    oldX = e.getX();
                    oldY = e.getY();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (startPen) {
                    fragmentsList.add(new Pen(oldX, oldY, e.getX(), e.getY()));
                    repaint();
                }
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (startPen) {
//                    System.out.println("lol");
                    if (colorChooser) {           // Żeby koło albo kwadrat nie nadawali na początku ramdomny kolor
                        Pen.COLOR = Color.BLACK;
                    }

                    fragmentsList.add(new Pen(oldX, oldY, e.getX(), e.getY())); // Dokładnie tutaj tworzymy pierwszy ob

                    repaint();
                    oldX = e.getX();
                    oldY = e.getY();
                    if (saved) {
                        MyFile.statusFile.setText("Modified");
                    }
                    MyDraw.statusPaint.setText("Pen");
                }
            }

        });

        this.setLayout(new BorderLayout());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setForeground(Color.white);
        if (startClear) {
            clearAll();
        }
        g.setColor(Color.BLACK);
        for (FragmentsInterface f : fragmentsList){
            f.drawFragment(g);
        }

    }

    public void clearAll() {

        if (option == JOptionPane.YES_OPTION) {
            this.setBackground(Color.white);
            fragmentsList.clear();
            PanelDraw.startClear = false;
            if (saved) {
                MyFile.statusFile.setText("Modified");
            }
        }
        repaint();


    }

    private static Color getRandomColor() {
        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(256);
        int g = randomGenerator.nextInt(256);
        int b = randomGenerator.nextInt(256);
        return new Color(r, g, b);
    }


}
