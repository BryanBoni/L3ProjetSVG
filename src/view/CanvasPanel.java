package view;

import Maths.Vector2f;
import Maths.Vector3f;
import data.DrawableSVG;
import static data.DrawableSVG.RENDRING_HINTS;
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
 * This class is used to create a canvas from an SVG file.
 *
 */
public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    public static CanvasPanel currentCanvas;
    private BufferedImage m_canvasImage;
    private ArrayList<DrawableSVG> m_drawableList;

    // rendering context
    public static float zoom = 1;
    public static float minZoom = 16;
    public static float maxZoom = 0.25f;
    public static int translateX = 0;
    public static int translateY = 0;

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

    /**
     * initiate the properties and components of the new canvas panel.
     */
    private void initComponents() {
        setBackground(Color.gray);
        setSize(500, 500);
        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
        currentCanvas = this;
        isMousePressed = false;
    }

    /**
     * Used to redraw/update constantly the canvas panel.
     * 
     * @param g containt the current properties of the canvas.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        resetImage(g);
        g.translate(translateX, translateY);
        ((Graphics2D) g).setRenderingHints(RENDRING_HINTS);
        for (DrawableSVG drawable : m_drawableList) {
            drawable.render((Graphics2D) g);
        }
    }

    /**
     * Redraw an empty canvas.
     * 
     * @param g containt the current properties of the canvas.
     */
    public void resetImage(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
    }

    /**
     * Used to outsource the repaint method.
     */
    public void repaintImage() {
        repaint();
    }

    /**
     * Used to reset the position of the canvas panel.
     */
    public void resetPostion() {
        translateX = 0;
        translateY = 0;
        repaint();
    }

    /**
     * Used to give the current position of the mouse.
     * 
     * @param e event catch when the user mouve his mouse.
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
     * Call when the right click of the mouse is pressed and exit when it's
     * released, used to change the position of the picture on the canvas.
     *
     * @param e event catch when the user click on the mouse button and this one stay pressed.
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
     * Unused here, implement only because of the MouseListener interface.
     *
     * @param e event catch when a mouse button is pressed.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * On call, change the value of isMousePressed.
     *
     * @param e event catch when a mouse button is released.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    /**
     * Use this function to modify the path list variable of an SVG file for the
     * canvas panel.
     *
     * @param drawableList the new pathList table.
     */
    public void setDrawableList(ArrayList<DrawableSVG> drawableList) {
        m_drawableList = drawableList;
    }

    /**
     * Used to change the zoom of the canvas panel when the user move the wheel of his mouse,
     * The origin of the zoom is relative to the mouse.
     * 
     * 
     * @param e event catch when a mouse wheel is mouved.
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

            translateX -= (newDiffX - diffX);
            translateY -= (newDiffY - diffY);

        }

        repaint();

        MainWindow.changeFieldZoom(zoom);

    }

    /**
     * change the boolean isMousePressed to true if the mouse is pressed.
     *
     * @param e event catch when a mouse button is released.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    /**
     * Used to change the label position relative to the mouse on the canvas.
     *
     * @param e even catch of a mouse when it entered the canvas panel.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        MainWindow.changeLabelPosition(translateX + mouseX, translateY + mouseY);
    }

    /**
     * Used to change the label position to the translate X and Y.
     *
     * @param e even catch of a mouse when it quite the canvas panel.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        MainWindow.changeLabelPosition(translateX, translateY);
    }
}
