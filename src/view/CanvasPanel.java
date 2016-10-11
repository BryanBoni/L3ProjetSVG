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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import parser.Path;

/**
 * This class is used to create a canvas.
 *
 */
public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    public static CanvasPanel currentCanvas;
    private BufferedImage m_canvasImage;
    private SimpleDrawer m_simpDraw;
    private ArrayList<Path> m_pathList;

    // rendering context
    public static float zoom = 1;
    private static int translateX = 0;
    private static int translateY = 0;

    // input context
    public static int mouseX = 0;
    public static int mouseY = 0;
    private static boolean isMousePressed = false;

    /**
     * The constructor of the CanvasPanel, used when a default SVG file is
     * define at the begining.
     *
     * @param pathUrl
     */
    public CanvasPanel(String pathUrl) {
        super();
        m_pathList = new ArrayList<>();
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
        addMouseWheelListener(this);
        currentCanvas = this;
        isMousePressed = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println("paintComponent");
        super.paintComponent(g);
        resetImage(g);
        g.translate(translateX, translateY);
        //Rasterizer.renderPath(m_pathList, g);

        for (Path p : m_pathList) {
            g.setColor(p.getStroke());
            for (Line l : p.getElements()) {
                for (float t = 0.01f; t < 1; t += 0.01f) {
                    Vector2f a = l.getPoint(t - 0.01f);
                    Vector2f b = l.getPoint(t);

                    g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);

                }
            }
        }
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
     * Used to reset the position of the canvas himself.
     */
    public void resetPostion() {
        translateX = 0;
        translateY = 0;
        repaint();
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (!isMousePressed) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
        MainWindow.changeLabelPosition(translateX + mouseX, translateY + mouseY);
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
        int deltaX, deltaY;
        deltaX = e.getX() - mouseX;
        deltaY = e.getY() - mouseY;
        mouseX += deltaX;
        mouseY += deltaY;
        translateX += deltaX;
        translateY += deltaY;

        MainWindow.changeLabelPosition(translateX + mouseX, translateY + mouseY);
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
    }

    /**
     * Call when the right click of the mouse is pressed, change the state of
     * stayPressed to false and change the position of the canvas right after a
     * drag operation.
     *
     * @param e : cath the even of a mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        float step = 0.5f;
        System.out.println(e.getPreciseWheelRotation() + "\n");

        if (e.getPreciseWheelRotation() == -1.0 && zoom <= 4.5f) {//zoom +
            zoom += step;
        } else if (e.getPreciseWheelRotation() == 1.0 && zoom >= 1.0f) {//zoom -
            zoom -= step;
        }
        
        MainWindow.changeFieldZoom(zoom);
        
    }

    /**
     * Use this function to modify the path list variable of an SVG file for the
     * canvas panel.
     *
     * @param m_p
     */
    public void setPathList(ArrayList<Path> pathList) {
        m_pathList = pathList;
    }

    /**
     * unused here.
     *
     * @param e : cath the even of a mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    /**
     * unused here.
     *
     * @param e : cath the even of a mouse.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        MainWindow.changeLabelPosition(translateX + mouseX, translateY + mouseY);
    }

    /**
     * unused here.
     *
     * @param e : cath the even of a mouse.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        MainWindow.changeLabelPosition(translateX, translateY);
    }

}
