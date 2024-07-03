package ProjectFile;

import MyButtons.MyDraw;
import MyButtons.MyFile;
import MyDrawComponent.PanelDraw;

import javax.swing.*;
import java.awt.*;

public class Project {
    public static void main(String[] args)
    {
        new Project();
    }

    public static JLabel statusBasedPaint;
    public static JFrame jFrame;

    public Project()
    {
        SwingUtilities.invokeLater(() -> createProject());

    }

    protected void createProject(){
        jFrame = new JFrame();

        jFrame.setVisible(true);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyFile file = new MyFile();
        MyDraw draw = new MyDraw();

        JToolBar jToolBar = new JToolBar();
        statusBasedPaint = MyDraw.statusPaint;
        jToolBar.add(statusBasedPaint);
        jToolBar.setLayout(new BoxLayout(jToolBar,BoxLayout.X_AXIS)); // wszystko poziomo
        jToolBar.add(Box.createHorizontalGlue()); // wszystko do prawej strony
        jToolBar.setFloatable(false);
        jToolBar.add(MyFile.statusFile);

        jFrame.add(jToolBar, BorderLayout.SOUTH);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(file);
        jMenuBar.add(draw);
        jFrame.setJMenuBar(jMenuBar);

        PanelDraw myPanel = new PanelDraw();
        file.panelDrawClass = myPanel;
        draw.panelDrawClass = myPanel;
        jFrame.add(myPanel);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 350, dimension.height/2 - 270, 700, 500);

        jFrame.setTitle("Simple Draw");


    }
}