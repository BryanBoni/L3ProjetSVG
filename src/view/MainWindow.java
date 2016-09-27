/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.DebugGraphics;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
        m_panelCanvas = new CanvasPanel();
        initCompenents();

    }

    private void initCompenents() {
        
        toolsPanel = new javax.swing.JPanel();
        postionPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_FileMenu = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        m_FileChooser = new javax.swing.JMenuItem();
        
        setCursor(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SVG Editor");
  
        
        m_panelCanvas.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        m_panelCanvas.setDoubleBuffered(false);
        m_panelCanvas.setRequestFocusEnabled(false);
        m_panelCanvas.setLayout(null);

        javax.swing.GroupLayout m_panelCanvasLayout = new javax.swing.GroupLayout(m_panelCanvas);
        m_panelCanvas.setLayout(m_panelCanvasLayout);
        
        m_panelCanvasLayout.setHorizontalGroup(
                m_panelCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 500, Short.MAX_VALUE)
        );
        
        m_panelCanvasLayout.setVerticalGroup(
                m_panelCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 500, Short.MAX_VALUE)
        );

        toolsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout toolsPanelLayout = new javax.swing.GroupLayout(toolsPanel);
        toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
                toolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 137, Short.MAX_VALUE)
        );
        toolsPanelLayout.setVerticalGroup(
                toolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 496, Short.MAX_VALUE)
        );

        postionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout postionPanelLayout = new javax.swing.GroupLayout(postionPanel);
        postionPanel.setLayout(postionPanelLayout);
        postionPanelLayout.setHorizontalGroup(
                postionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        postionPanelLayout.setVerticalGroup(
                postionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 30, Short.MAX_VALUE)
        );

        m_FileMenu.setText("File");
        
        m_FileChooser.setText("Chose File");
        m_FileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_FileChooserActionPerformed(evt);
            }
        });
        m_FileMenu.add(m_FileChooser);
        
        jMenuBar1.add(m_FileMenu);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(toolsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_panelCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(postionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(m_panelCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(toolsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        
        //setContentPane(m_panelCanvas);
        
        //getContentPane().setLayout(null);
        
        //setSize(500, 500);
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
    
    private void m_FileChooserActionPerformed(java.awt.event.ActionEvent evt) {
        /*
        This function is a listener for the FileChooser item,
        use to retreve the path of a file from a repository and 
        call a function to parse this file
        */
        JFileChooser fc = new JFileChooser();
        String filePath;
        int returnVal = fc.showOpenDialog(MainWindow.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filePath = file.getAbsolutePath();
            System.out.println(filePath + "\n");
            //call Parser
            }
        }
    }

