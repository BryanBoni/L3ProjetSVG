package view;

import Maths.Vector2f;
import Maths.Vector3f;
import data.IDrawableSVG;
import draw.SimpleDrawer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class is used to create a canvas.
 *
 */
public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    public static CanvasPanel currentCanvas;
    private BufferedImage m_canvasImage;
    private SimpleDrawer m_simpDraw;
    private ArrayList<IDrawableSVG> m_drawableList;

    // rendering context
    public static float zoom = 1;
    private static int translateX = 0;
    private static int translateY = 0;

    // input context
    public static int mouseX = 0;
    public static int mouseY = 0;
    private static boolean isMousePressed = false;

    //init
    private boolean isEnd = false; // delimiter

    /**
     * The constructor of the CanvasPanel.
     */
    public CanvasPanel() {
        super();
        m_drawableList = new ArrayList<>();
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
        super.paintComponent(g);
        resetImage(g);
        g.translate(translateX, translateY);

        for (IDrawableSVG drawable : m_drawableList) {
            drawable.render((Graphics2D) g);
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
     * Use this function to modify the path list variable of an SVG file for the
     * canvas panel.
     *
     * @param drawableList
     */
    public void setDrawableList(ArrayList<IDrawableSVG> drawableList) {
        m_drawableList = drawableList;
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //todo make the zoom on the position of the mousse or the center.

        int diffX, diffY;
        diffX = Math.abs(translateX - e.getX());
        diffY = Math.abs(translateY - e.getY());

        float oldScale = zoom;

        if (e.getPreciseWheelRotation() < 0) {//zoom +
            zoom = Math.min(zoom * 2f, 32.0f);

            //get the diff of the old and new position.
            int newDiffX = (int) (((diffX / oldScale) * zoom));
            int newDiffY = (int) (((diffY / oldScale) * zoom));

            translateX -= (newDiffX - diffX);
            translateY -= (newDiffY - diffY);

        } else if (e.getPreciseWheelRotation() > 0) {//zoom -
            zoom = Math.max(zoom * 0.5f, 0.25f);

            int newDiffX = (int) (((diffX / oldScale) * zoom));
            int newDiffY = (int) (((diffY / oldScale) * zoom));
            /*
                translateX += newDiffX;
                translateY += newDiffY;
             */
            translateX -= (newDiffX - diffX);
            translateY -= (newDiffY - diffY);

        }

        // System.out.println(zoom + " , " + currentScale);
        repaint();

        MainWindow.changeFieldZoom(zoom);

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
