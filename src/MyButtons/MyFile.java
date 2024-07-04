package MyButtons;

import MyDrawComponent.FragmentsInterface;
import MyDrawComponent.PanelDraw;
import ProjectFile.Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class MyFile extends JMenu implements ActionListener {
    private final JMenuItem open, save, saveAs, quit;
    public PanelDraw panelDrawClass;
    public File fileBased;
    public static JLabel statusFile;

    public MyFile() {
        super("File");

        this.open = new JMenuItem("Open");
        this.save = new JMenuItem("Save");
        this.saveAs = new JMenuItem("Save As");
        this.quit = new JMenuItem("Quit");


        statusFile = new JLabel("New");


        this.setMnemonic(KeyEvent.VK_F);

        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);

        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);

        saveAs.setMnemonic(KeyEvent.VK_A);
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        saveAs.addActionListener(this);

        quit.setMnemonic(KeyEvent.VK_Q);
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        quit.addActionListener(this);

        this.add(open);
        this.add(save);
        this.add(saveAs);
        this.addSeparator();
        this.add(quit);


    }

//    SHO

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open){
            JFileChooser jFileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter(".spo", "spo");
            jFileChooser.setFileFilter(filter);

            int i = jFileChooser.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream =  new ObjectInputStream(fileInputStream);
                    PanelDraw.fragmentsList = (ArrayList<FragmentsInterface>) objectInputStream.readObject();
                }catch (ClassNotFoundException exception){
                    exception.printStackTrace();
                }catch (IOException exception){
                    exception.printStackTrace();
                }
                fileBased = file;
                statusFile.setText("Saved");
                Project.jFrame.setTitle("Simple Draw: " + file.getName());
                PanelDraw.saved = true;
                MyDraw.statusPaint.setText(Project.statusBasedPaint.getText());

            }
            panelDrawClass.repaint();
        } else if (e.getSource() == save){
            if (fileBased == null){
                saveAs();
            }else {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileBased);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(PanelDraw.fragmentsList);

                    MyFile.statusFile.setText("Saved");

                } catch (IOException exception){
                    exception.printStackTrace();
                }

            }


        } else if (e.getSource() == saveAs){
            saveAs();
        } else if (e.getSource() == quit) {
            int option = JOptionPane.showConfirmDialog(null,"Czy chcesz opuscić aplikacje?",
                    "Tak",  JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){

                if (fileBased == null){
                    saveAs();
                }else {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(fileBased);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(PanelDraw.fragmentsList);

                        MyFile.statusFile.setText("Saved");

                    } catch (IOException exception){
                        exception.printStackTrace();
                    }

                }

                System.exit(0);
            }

        }

    }

    public void saveAs(){
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".spo", "spo");
        jFileChooser.setFileFilter(filter);
        int i = jFileChooser.showSaveDialog(this);
        if (i == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".spo")) {
                file = new File(file.getAbsolutePath() + ".spo");
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(PanelDraw.fragmentsList);
                System.out.println("ArrayList saved to file: " + file.getAbsolutePath());
                Project.jFrame.setTitle("Simple Draw: " + file.getName());
                PanelDraw.saved = true;
                statusFile.setText("Saved");
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
    }
}
