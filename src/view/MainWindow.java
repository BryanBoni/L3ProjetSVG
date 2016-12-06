package view;

import data.DrawableSVG;
import data.SaveState;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DebugGraphics;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import parser.SVG;
import parser.Parser;

/**
 * This class is used to create a new window for the program.
 *
 * @author BryanBoni.
 */
public final class MainWindow extends JFrame {

    //Window components.
    private final CanvasPanel m_panelCanvas;
    private JMenuItem m_FileChooser;
    private JMenuItem m_themesMenuItem;
    private JMenu m_FileMenu;
    private JMenu m_edit;
    private JMenu m_toolsMenu;
    private JMenuBar m_jMenuBar1;
    private JPanel m_infoPanel;
    private JPanel m_toolsPanel;
    private JButton m_resetButton;
    private JLabel m_zoomLabel;
    private JTabbedPane m_multiCanvas;
    private int m_NbFenetres;
    private int m_currentPane;

    public static MainWindow currentWindow;
    private static JTextField zoomField;
    private static JLabel position;
    private static String curTheme;
    private static WindowPreferences pref;

    //Memory variables
    private ArrayList<CanvasPanel> m_canvasList; //Stock all canvas loaded.
    private ArrayList<SaveState> m_statList;//TODO : use it !

    private File m_currentDir = null;

    public MainWindow() {
        super();
        m_NbFenetres = 0;
        m_canvasList = new ArrayList<>();
        m_panelCanvas = new CanvasPanel();

        pref = new WindowPreferences(new Color(52, 52, 52), new Color(255, 255, 255), Color.BLACK, new Color(255, 255, 255), new Color(52, 52, 52));
        curTheme = "Dark";

        initComponents();
        applyPreferences();

        //add a listener for when we change pane.
        m_multiCanvas.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    System.out.println("Selected paneNo : " + pane.getSelectedIndex());
                    m_multiCanvas.setComponentAt(m_currentPane, null);
                    m_multiCanvas.setComponentAt(pane.getSelectedIndex(), m_canvasList.get(pane.getSelectedIndex()));
                    m_currentPane = pane.getSelectedIndex();
                }
            }
        });

    }

    /**
     * Intialise all the components of the main window, It create & init all the
     * needed componenent for our SVG reader : - a canvas panel, - a bar menu, -
     * a tool panel and its components, - a info panel and its components.
     *
     */
    private void initComponents() {
        UIManager.put("PopupMenu.border", new LineBorder(WindowPreferences.getBorderColor()));
        //UIManager.put("MenuBar.border", new LineBorder(WindowPreferences.getBorderColor()));

        m_toolsPanel = new JPanel();
        m_infoPanel = new JPanel();
        m_jMenuBar1 = new JMenuBar();
        m_FileMenu = new JMenu();
        m_edit = new JMenu();
        m_toolsMenu = new JMenu();
        m_themesMenuItem = new JMenuItem();
        m_FileChooser = new JMenuItem();
        position = new JLabel();
        m_resetButton = new JButton();
        m_zoomLabel = new JLabel();
        zoomField = new JTextField();
        m_multiCanvas = new JTabbedPane();

        setCursor(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
                m_panelCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE));

        //Set the Multi canvas pane.
        addPane(0, "NewFile", m_panelCanvas);

        m_resetButton.setText("reset position");
        m_resetButton.addActionListener(this::m_resetButtonActionPerformed);

        //m_toolsPanel.setBorder(BorderFactory.createEtchedBorder());
        GroupLayout toolsPanelLayout = new GroupLayout(m_toolsPanel);
        m_toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(m_resetButton, GroupLayout.Alignment.CENTER)
                        .addGap(0, 137, Short.MAX_VALUE)
        );
        toolsPanelLayout.setVerticalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(m_resetButton, GroupLayout.Alignment.TRAILING)
                        .addGap(0, 520, Short.MAX_VALUE)
        );

        position.setText("X: Y:");
        m_zoomLabel.setText("Zoom :");

        zoomField.setEditable(false);
        zoomField.setText("100%");

        GroupLayout postionPanelLayout = new GroupLayout(m_infoPanel);
        m_infoPanel.setLayout(postionPanelLayout);

        postionPanelLayout.setHorizontalGroup(postionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, postionPanelLayout.createSequentialGroup()
                        .addContainerGap(10, Short.MAX_VALUE)
                        .addComponent(m_zoomLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                        .addComponent(position, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );

        postionPanelLayout.setVerticalGroup(postionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, postionPanelLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(postionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(m_zoomLabel)
                                .addComponent(zoomField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(position))
                        .addContainerGap())
        );

        m_FileMenu.setText("File");

        m_FileChooser.setText("Chose File");
        m_FileChooser.addActionListener(this::m_FileChooserActionPerformed);
        m_FileMenu.add(m_FileChooser);

        m_jMenuBar1.add(m_FileMenu);

        m_edit.setText("Edit");
        m_jMenuBar1.add(m_edit);

        m_toolsMenu.setText("Tools");

        m_themesMenuItem.setText("Themes");
        m_themesMenuItem.addActionListener(this::m_themesMenuItemActionPerformed);
        m_toolsMenu.add(m_themesMenuItem);

        m_jMenuBar1.add(m_toolsMenu);

        setJMenuBar(m_jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(m_toolsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //.addComponent(m_panelCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_multiCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addComponent(m_infoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                //.addComponent(m_panelCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(m_multiCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(m_toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_infoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();

        setResizable(false);
        setLocationRelativeTo(null);

        currentWindow = this;
    }

    /**
     * Used to create a new pane in the tabbed pane
     *
     * @param index
     * @param title
     * @param panel
     */
    private void addPane(int index, String title, CanvasPanel panel) {
        //Set the Multi canvas pane.
        m_multiCanvas.addTab(title, panel);

        JPanel pnlTab = new JPanel(new GridBagLayout());

        JLabel titleLabl = new JLabel(title);

        JButton closeBtn = new JButton("X");
        closeBtn.setBackground(Color.red);
        closeBtn.setForeground(Color.white);

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_multiCanvas.remove(panel);
                m_canvasList.remove(panel);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(titleLabl, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(closeBtn, gbc);

        m_multiCanvas.setTabComponentAt(index, pnlTab);
    }

    /**
     * Used when a pane content is modified.
     *
     * @param index
     * @param title
     * @param panel
     */
    private void modifPane(int index, String title, CanvasPanel panel) {

        JPanel pnlTab = new JPanel(new GridBagLayout());

        JLabel titleLabl = new JLabel(title);

        JButton closeBtn = new JButton("X");
        closeBtn.setBackground(Color.red);
        closeBtn.setForeground(Color.white);

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_multiCanvas.remove(index);
                m_canvasList.remove(panel);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(titleLabl, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(closeBtn, gbc);

        m_multiCanvas.setTabComponentAt(index, pnlTab);
    }

    /**
     * This function is a listener for the FileChooser item, use to retreve the
     * path of a file from a repository and call a function to parse this file,
     *
     * It open a new SVG file and add it to the multiCanvas Tab.
     *
     * @param evt
     */
    private void m_FileChooserActionPerformed(ActionEvent evt) {
        if (m_NbFenetres == 0) {
            JFileChooser fc = new JFileChooser();

            if (m_currentDir != null) {
                fc.setCurrentDirectory(m_currentDir);
            } else {
                fc.setCurrentDirectory(new File("."));
            }
            String filePath;
            int returnVal = fc.showOpenDialog(MainWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filePath = file.getPath();
                System.out.println(filePath + "\n");

                //redraw
				// move to a new thread
				modifLoadingBar(0);
                Parser parser = new Parser(filePath);
                SVG svg = parser.parse();
				float progressStep = 1 / svg.getDrawableList().size();
				for(int i = 0; i < svg.getDrawableList().size(); i++) {
					DrawableSVG d = svg.getDrawableList().get(i);
					d.preDraw();
					modifLoadingBar(Math.round(i * progressStep));
				}
                m_panelCanvas.setDrawableList(svg.getDrawableList());
                m_panelCanvas.repaintImage();
				modifLoadingBar(100);
                setTitle(file.getName());
                m_multiCanvas.setTitleAt(m_NbFenetres, file.getName());
                m_currentDir = file;
                modifPane(m_NbFenetres, file.getName(), m_panelCanvas);
                m_canvasList.add(m_NbFenetres, m_panelCanvas);
            }

        } else { //Create a new tab with a choosen file.
            int oldPane = m_multiCanvas.getSelectedIndex();

            CanvasPanel canvas = new CanvasPanel();
            JFileChooser fc = new JFileChooser();

            if (m_currentDir != null) {
                fc.setCurrentDirectory(m_currentDir);
            } else {
                fc.setCurrentDirectory(new File("."));
            }

            String filePath;
            int returnVal = fc.showOpenDialog(MainWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filePath = file.getPath();
                System.out.println(filePath + "\n");

                //redraw
                Parser parser = new Parser(filePath);
                SVG svg = parser.parse();
                canvas.setDrawableList(svg.getDrawableList());
                canvas.repaintImage();
                setTitle(file.getName());
                m_currentDir = file;

                canvas.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
                canvas.setDoubleBuffered(false);
                canvas.setRequestFocusEnabled(false);
                canvas.setLayout(null);

                GroupLayout m_panelCanvasLayout = new GroupLayout(canvas);
                canvas.setLayout(m_panelCanvasLayout);

                //Setting the size of a the canvas
                m_panelCanvasLayout.setHorizontalGroup(
                        m_panelCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 500, Short.MAX_VALUE));

                m_panelCanvasLayout.setVerticalGroup(
                        m_panelCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 500, Short.MAX_VALUE));

                //adding a canvas to the table of canvas.
                addPane(m_NbFenetres, file.getName(), null);

                m_canvasList.add(m_NbFenetres, canvas);

            }
        }
        m_NbFenetres += 1;
    }

    /**
     * On call, reset the position of the panel m_panelCanvas.
     *
     * @param evt
     */
    private void m_resetButtonActionPerformed(ActionEvent evt) {
        m_panelCanvas.resetPostion();
    }

    /**
     * Used to open a new jDialog where the user can modify the theme of the
     * interface.
     *
     * @param evt
     */
    public void m_themesMenuItemActionPerformed(ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace(System.out);
                }
                ThemeEditor themeEditDialog = new ThemeEditor(currentWindow, true, curTheme);

                themeEditDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                });
                themeEditDialog.setVisible(true);
            }
        });
    }

    /**
     * Used to apply a new theme for the interface.
     */
    public void applyPreferences() {

        getContentPane().setBackground(WindowPreferences.getM_backgroundColor());

        m_toolsPanel.setBackground(WindowPreferences.getM_backgroundColor());
        m_toolsPanel.setForeground(WindowPreferences.getM_textColor());

        m_infoPanel.setBackground(WindowPreferences.getM_backgroundColor());
        m_infoPanel.setForeground(WindowPreferences.getM_textColor());

        m_jMenuBar1.setBackground(WindowPreferences.getM_backgroundColor());
        m_jMenuBar1.setForeground(WindowPreferences.getM_textColor());

        m_FileMenu.setBackground(WindowPreferences.getM_backgroundColor());
        m_FileMenu.setForeground(WindowPreferences.getM_textColor());

        m_edit.setBackground(WindowPreferences.getM_backgroundColor());
        m_edit.setForeground(WindowPreferences.getM_textColor());

        m_toolsMenu.setBackground(WindowPreferences.getM_backgroundColor());
        m_toolsMenu.setForeground(WindowPreferences.getM_textColor());

        m_FileChooser.setBackground(WindowPreferences.getM_backgroundColor());
        m_FileChooser.setForeground(WindowPreferences.getM_textColor());

        m_themesMenuItem.setBackground(WindowPreferences.getM_backgroundColor());
        m_themesMenuItem.setForeground(WindowPreferences.getM_textColor());

        position.setBackground(WindowPreferences.getM_backgroundColor());
        position.setForeground(WindowPreferences.getM_textColor());

        m_resetButton.setBackground(WindowPreferences.getM_buttonBackgoundColor());
        m_resetButton.setForeground(WindowPreferences.getM_buttonColor());

        m_zoomLabel.setForeground(WindowPreferences.getM_textColor());

        zoomField.setBackground(WindowPreferences.getM_backgroundColor());
        zoomField.setForeground(WindowPreferences.getM_textColor());

        m_multiCanvas.setBackground(WindowPreferences.getM_backgroundColor());
    }

    /**
     * Display constantly the position of the mouse on the canvasPanel.
     *
     * @param mouseX
     * @param mouseY
     */
    public static void changeLabelPosition(int mouseX, int mouseY) {
        position.setText("X:" + mouseX + " Y:" + mouseY);
    }

    /**
     * initiate the text for the postition label.
     */
    public static void changeLabelPosition() {
        position.setText("X: " + " Y:");
    }

    /**
     * Used to change the zoom label to the new zoom given.
     *
     * @param zoom
     */
    public static void changeFieldZoom(float zoom) {
        zoom = zoom * 100;
        String zoomText = String.valueOf(zoom);
        if (zoomText.contains(".")) {
            zoomText = zoomText.substring(0, zoomText.indexOf(".") + Math.min(zoomText.length() - zoomText.indexOf("."), 3));
        }
        zoomField.setText(zoomText + " %");
    }

}
