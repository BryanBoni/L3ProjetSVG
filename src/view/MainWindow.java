/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private CanvasPanel m_panelCanvas;
    private javax.swing.JMenuItem m_FileChooser;
    private javax.swing.JMenu m_FileMenu;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel postionPanel;
    private javax.swing.JPanel toolsPanel;

    public MainWindow() {
        super();
        m_panelCanvas = new CanvasPanel("L3SVG.svg");
        initCompenents();

    }

    private void initCompenents() {
        
        toolsPanel = new JPanel();
        postionPanel = new JPanel();
        jMenuBar1 = new JMenuBar();
        m_FileMenu = new JMenu();
        jMenu2 = new JMenu();
        m_FileChooser = new JMenuItem();
        
        setCursor(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SVG Editor");
  
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

        toolsPanel.setBorder(BorderFactory.createEtchedBorder());

        GroupLayout toolsPanelLayout = new GroupLayout(toolsPanel);
        toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 137, Short.MAX_VALUE)
        );
        toolsPanelLayout.setVerticalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 496, Short.MAX_VALUE)
        );

        postionPanel.setBorder(BorderFactory.createEtchedBorder());

        GroupLayout postionPanelLayout = new GroupLayout(postionPanel);
        postionPanel.setLayout(postionPanelLayout);
        postionPanelLayout.setHorizontalGroup(
                postionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        postionPanelLayout.setVerticalGroup(
                postionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 30, Short.MAX_VALUE)
        );

        m_FileMenu.setText("File");
        
        m_FileChooser.setText("Chose File");
        m_FileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                m_FileChooserActionPerformed(evt);
            }
        });
        m_FileMenu.add(m_FileChooser);
        
        jMenuBar1.add(m_FileMenu);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(toolsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_panelCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addComponent(postionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(m_panelCanvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void resetImage() {
        m_panelCanvas.resetImage();
    }

    public void drawPoint(int x, int y, int r, int g, int b) {
        m_panelCanvas.drawPoint(x, y, r, g, b);
    }

    public void showImage() {
        m_panelCanvas.showImage();
    }
    
    private void m_FileChooserActionPerformed(ActionEvent evt) {
        /*
        This function is a listener for the FileChooser item,
        use to retreve the path of a file from a repository and 
        call a function to parse this file
        */
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
            
            m_panelCanvas.m_p = path;
            
            m_panelCanvas.repaint();
            /*initCompenents();
            
            showImage();*/
        }
    }
}

