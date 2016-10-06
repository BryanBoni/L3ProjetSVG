package view;

import Maths.Vector2f;
import Maths.Vector3f;
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

/**
 * This class is used to create a canvas.
 *
 */
public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener {

    public static CanvasPanel currentCanvas;
    private BufferedImage m_canvasImage;
    private SimpleDrawer m_simpDraw;
    private Path m_p;

    private boolean m_stayPressed;
    private boolean m_isDragged;

    public static int mouseX;
    public static int mouseY;
    private static int mouseInitX;
    private static int mouseInitY;
    private static int mouseDeltaX;
    private static int mouseDeltaY;
    private static int positionCanvasX;
    private static int positionCanvasY;

    /**
     * The constructor of the CanvasPanel, used when a default SVG file is
     * define at the begining.
     *
     * @param pathUrl
     */
    public CanvasPanel(String pathUrl) {
        super();
        Parser parser = new Parser(pathUrl);
        SVG svg = parser.parse();
        m_p = svg.getPathList().get(0);
        initComponents();
    }

    /**
     * The constructor of the CanvasPanel.
     */
    public CanvasPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.gray);
        setSize(500, 500);
        addMouseMotionListener(this);
        addMouseListener(this);
        currentCanvas = this;
        m_stayPressed = false;
        m_isDragged = false;
        mouseDeltaX = 0;
        mouseDeltaY = 0;
        positionCanvasX = 0;
        positionCanvasY = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println("paintComponent");
        super.paintComponent(g);
        resetImage(g);
        g.translate(positionCanvasX + mouseDeltaX, positionCanvasY + mouseDeltaY);
        g.setColor(Color.BLUE);
        m_p.getElements().stream().forEach((l) -> {
            //System.out.println("paintComponent un elem");
            for (float t = 0.01f; t < 1; t += 0.01f) {
                Vector2f a = l.getPoint(t - 0.01f);
                Vector2f b = l.getPoint(t);

                g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);

            }
        });
    }

    /**
     *
     * @param g
     */
    public void resetImage(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
    }

    /**
     *
     *
     * @param x
     * @param y
     * @param r
     * @param g
     * @param b
     */
    public void drawPoint(int x, int y, int r, int g, int b) {
        m_simpDraw = new SimpleDrawer(m_canvasImage);
        m_simpDraw.drawPixel(new Vector2f(x, y), new Vector3f(r, g, b));
    }

    /**
     * Used to outsource the repaint method.
     */
    public void repaintImage() {
        repaint();
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved" + e);

        if (m_stayPressed == false) {
            mouseInitX = e.getXOnScreen();
            mouseInitY = e.getYOnScreen();
        }

        MainWindow.changeLabelPosition(e.getX(), e.getY());
    }

    /**
     *
     * Call when the right click of the mouse is pressed and exit when it's
     * released, used to change the position of the picture on the canvas.
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse moved" + e);
        m_isDragged = true;

        mouseX = e.getXOnScreen();
        mouseY = e.getYOnScreen();

        mouseDeltaX = mouseX - mouseInitX;
        mouseDeltaY = mouseY - mouseInitY;

        //System.out.println("Mouse delta X: " + m_mouseDeltaX + " Y: " + m_mouseDeltaY);
        MainWindow.changeLabelPosition(e.getX(), e.getY());
        repaint();
    }

    /**
     * Call when the right click of the mouse is pressed, Change the state of
     * stayPressed to true.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        m_stayPressed = true;
    }

    /**
     * Call when the right click of the mouse is pressed, change the state of
     * stayPressed to false and change the position of the canvas right after a
     * drag operation.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        m_stayPressed = false;
        if (m_isDragged == true) {
            positionCanvasX += mouseDeltaX;
            positionCanvasY += mouseDeltaY;
            m_isDragged = false;
        }

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
     * unused here.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * unused here.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * unused here.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
