package l3projetsvg;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import view.MainWindow;

public class L3ProjetSVG {

    public static void main(String[] args) {
        System.out.println("L3ProjetSVG");

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()/*UIManager.getSystemLookAndFeelClassName()*/);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.out);
        }

        UIManager.put("PopupMenu.border", new LineBorder(new Color(0,0,0)));
        UIManager.put("MenuBar.border", new LineBorder(new Color(52,52,52)));
        
        new MainWindow("L3SVG.svg").setVisible(true);
    }
}
