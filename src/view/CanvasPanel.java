/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Maths.Vector2f;
import Maths.Vector3f;
import data.Line;
import draw.SimpleDrawer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import parser.Parser;
import parser.Path;
import parser.SVG;

public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener {
    
    public static CanvasPanel currentCanvas;
    private BufferedImage m_canvasImage;
    private SimpleDrawer m_simpDraw;
    private Path m_p;
    private boolean m_stayPressed;
    public static int mouseX, mouseY;
    private static int mouseInitX, mouseInitY, mouseDeltaX, mouseDeltaY, positionCanvasX, positionCanvasY;

    public CanvasPanel(String pathUrl) {
        super();
        Parser parser = new Parser(pathUrl);
        SVG svg = parser.parse();
        m_p = svg.getPathList().get(0);

        setBackground(Color.gray);
        setSize(500, 500);

        addMouseMotionListener(this);
        addMouseListener(this);
        m_stayPressed = false;
        
        currentCanvas= this;
        
        mouseDeltaX = 0;
        mouseDeltaY = 0;
        positionCanvasX = 0; 
        positionCanvasY = 0;
    }

    public CanvasPanel() {
        super();

        setBackground(Color.gray);
        setSize(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println("paintComponent");
        super.paintComponent(g);
        resetImage(g);
        g.translate(positionCanvasX + mouseDeltaX, positionCanvasY + mouseDeltaY);
        g.setColor(Color.red);
        for (Line l : m_p.getElements()) {
            //System.out.println("paintComponent un elem");

            for (float t = 0.01f; t < 1; t += 0.01f) {
                Vector2f a = l.getPoint(t - 0.01f);
                Vector2f b = l.getPoint(t);

                g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);

            }
        }
    }

    public void resetImage(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
    }

    public void drawPoint(int x, int y, int r, int g, int b) {
        m_simpDraw = new SimpleDrawer(m_canvasImage);
        m_simpDraw.drawPixel(new Vector2f(x, y), new Vector3f(r, g, b));
    }

    /**
     * Used to outsource the repaint method
     */
    public void repaintImage() {
        repaint();
    }

    
    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved" + e);
        
        if (m_stayPressed == false) {
            mouseInitX = e.getX();
            mouseInitY = e.getY();
        }

        MainWindow.changeLabelPosition(e.getX(), e.getY());       
    }

    /**
     * Unused here
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse moved" + e);
        mouseX = e.getX();
        mouseY = e.getY();
        
        mouseDeltaX = mouseX - mouseInitX;
        mouseDeltaY = mouseY - mouseInitY;
        
          
        //System.out.println("Mouse delta X: " + mouseDeltaX + " Y: " + mouseDeltaY);
        
        MainWindow.changeLabelPosition(e.getX(), e.getY());  
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        m_stayPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        m_stayPressed = false;
        
        positionCanvasX += mouseDeltaX;
        positionCanvasY += mouseDeltaY;
        
    }

    /**
     * Use this function to modify the path list variable of an SVG file for the
     * canvas panel.
     *
     * @param m_p
     */
    public void setM_p(Path m_p) {
        this.m_p = m_p;
    }

    /**
     * unused here
     * 
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * unused here
     * 
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * unused here
     * 
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}
