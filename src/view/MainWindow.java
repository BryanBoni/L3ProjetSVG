package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import parser.Parser;
import parser.Path;
import parser.SVG;

public class MainWindow extends JFrame {

    private final CanvasPanel m_panelCanvas;
    private javax.swing.JMenuItem m_FileChooser;
    private javax.swing.JMenu m_FileMenu;
    private javax.swing.JMenu m_jMenu2;
    private javax.swing.JMenuBar m_jMenuBar1;
    private javax.swing.JPanel m_postionPanel;
    private javax.swing.JPanel m_toolsPanel;
    private static javax.swing.JLabel m_position;

    public MainWindow() {
        super();
        m_panelCanvas = new CanvasPanel("L3SVG.svg");
        initComponents();
    }

    /**
     *
     */
    private void initComponents() {

        m_toolsPanel = new JPanel();
        m_postionPanel = new JPanel();
        m_jMenuBar1 = new JMenuBar();
        m_FileMenu = new JMenu();
        m_jMenu2 = new JMenu();
        m_FileChooser = new JMenuItem();
        m_position = new javax.swing.JLabel();

        setCursor(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("I wanna move it move it");

        m_panelCanvas.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        m_panelCanvas.setDoubleBuffered(false);
        m_panelCanvas.setRequestFocusEnabled(false);
        m_panelCanvas.setLayout(null);

        GroupLayout m_panelCanvasLayout = new GroupLayout(m_panelCanvas);
        m_panelCanvas.setLayout(m_panelCanvasLayout);

        //Setting the size of a the canvas
        m_panelCanvasLayout.setHorizontalGroup(
                m_panelCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 500, Short.MAX_VALUE));
        m_panelCanvasLayout.setVerticalGroup(
                m_panelCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 500, Short.MAX_VALUE));

        m_toolsPanel.setBorder(BorderFactory.createEtchedBorder());

        GroupLayout toolsPanelLayout = new GroupLayout(m_toolsPanel);
        m_toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 137, Short.MAX_VALUE)
        );
        toolsPanelLayout.setVerticalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 496, Short.MAX_VALUE)
        );

        m_position.setText("X: Y:");

        m_postionPanel.setBorder(BorderFactory.createEtchedBorder());

        GroupLayout postionPanelLayout = new GroupLayout(m_postionPanel);
        m_postionPanel.setLayout(postionPanelLayout);
        postionPanelLayout.setHorizontalGroup(postionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, postionPanelLayout.createSequentialGroup()
                        .addContainerGap(292, Short.MAX_VALUE)
                        .addComponent(m_position, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        //.addGap(0, 0, Short.MAX_VALUE)
        );
        postionPanelLayout.setVerticalGroup(postionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, postionPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m_position)
                        .addContainerGap())
        //.addGap(0, 30, Short.MAX_VALUE)
        );

        m_FileMenu.setText("File");

        m_FileChooser.setText("Chose File");
        m_FileChooser.addActionListener(this::m_FileChooserActionPerformed);
        m_FileMenu.add(m_FileChooser);

        m_jMenuBar1.add(m_FileMenu);

        m_jMenu2.setText("Edit");
        m_jMenuBar1.add(m_jMenu2);

        setJMenuBar(m_jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(m_toolsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_panelCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addComponent(m_postionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(m_panelCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(m_toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_postionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This function is a listener for the FileChooser item, use to retreve the
     * path of a file from a repository and call a function to parse this file.
     *
     * @param evt
     */
    private void m_FileChooserActionPerformed(ActionEvent evt) {

        Path path;
        JFileChooser fc = new JFileChooser();
        String filePath;
        int returnVal = fc.showOpenDialog(MainWindow.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //filePath = file.getAbsolutePath();
            filePath = file.getPath();
            System.out.println(filePath + "\n");

            //redraw
            Parser parser = new Parser(filePath);
            SVG svg = parser.parse();
            path = svg.getPathList().get(0);
            m_panelCanvas.setM_p(path);
            m_panelCanvas.repaintImage();

        }
    }

    /**
     * Display constantly the position of the mouse on the canvasPanel.
     *
     * @param mouseX
     * @param mouseY
     */
    public static void changeLabelPosition(int mouseX, int mouseY) {
        m_position.setText("X:" + mouseX + "Y:" + mouseY);
    }

}
