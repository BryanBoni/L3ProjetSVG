package l3projetsvg;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.MainWindow;

public class L3ProjetSVG {

    public static void main(String[] args) {
        System.out.println("L3ProjetSVG");

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()/*UIManager.getSystemLookAndFeelClassName()*/);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.out);
        }
        
        new MainWindow().setVisible(true);
    }
}
