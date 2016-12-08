package data;

import Maths.Vector2f;
import Maths.Vector4f;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Common template for all objetcs that have to be drawn on the canvas.
 *
 * @author ANTOINE
 */
public class DrawableSVG {

	// size and position datas
	protected AlphaComposite m_fillOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1); // inner fill opacity
	protected Vector4f m_limits = new Vector4f(); // stores the coordinates of the outer limits of the object
	protected Vector2f m_size = new Vector2f(); // stores the width and height for non-vectorial objects
	// color datas
	protected Vector2f m_position = new Vector2f(); // x an y positions of the object, where it needs to be drawn on the canvas
	protected Color m_strokeColor = Color.BLACK; // outer line color
	protected Color m_fillColor = Color.BLACK; // inner fill color
	protected AlphaComposite m_strokeOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1); // outer line opacity
	// pre-render datas
	protected double m_drawSteps = 0.01f; // for vectorial objects, hints how many points have to be rendered
	protected BufferedImage m_bufferedImage = null; // for pre-drawn objects, stores the high resolution render
	public static float PRE_RENDERING_RATIO = 2; // higher means better quatity and longer rendering time
	public static RenderingHints RENDRING_HINTS = initRenderingHint(); // hints for the rendering behavior

	/**
	 * Creates all hints that define how the canvas must behaves.
	 *
	 * @return Hints parameters.
	 */
	public static RenderingHints initRenderingHint() {
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.add(new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		rh.add(new RenderingHints(
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
		rh.add(new RenderingHints(
				RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_SPEED));
		return rh;
	}

	/**
	 * Common interface that renders an object on the canvas.
	 *
	 * @param g The Graphics2D of the canvas on wich we want to render the
	 * object.
	 */
	public void render(Graphics2D g) {

	}

	/**
	 * Some object need a pre-rendering routine, because they can't be drawn
	 * every frame. They will be drawn once in high resolution and scaled on the
	 * screen instead.
	 */
	public void preDraw() {

	}

	/**
	 * Set the position of a non-vectorial object.
	 *
	 * @param position Vector(x,y) of the object.
	 */
	public void setPosition(Vector2f position) {
		m_position = position;
	}

	/**
	 * Set the position of a non-vectorial object.
	 *
	 * @param x Position X of the object.
	 * @param y Position Y of the object.
	 */
	public void setPosition(float x, float y) {
		m_position.x = x;
		m_position.y = y;
	}

	/**
	 * Set the size of a non-vectorial object.
	 *
	 * @param size Vector(w,h) of the object
	 */
	public void setSize(Vector2f size) {
		m_size = size;
	}

	/**
	 * Set the size of a non-vectorial object.
	 *
	 * @param width Size of the object.
	 * @param height Size of the object.
	 */
	public void setSize(float width, float height) {
		m_size.x = width;
		m_size.y = height;
	}

	/**
	 * Set the color of the outer line.
	 *
	 * @param strokeColor Outer line color.
	 */
	public void setStrokeColor(Color strokeColor) {
		m_strokeColor = strokeColor;
	}

	/**
	 * Set the color of the inner fill.
	 *
	 * @param fillColor Inner fill color.
	 */
	public void setFillColor(Color fillColor) {
		m_fillColor = fillColor;
	}

	/**
	 * Set the opacity for the outer line.
	 *
	 * @param opacity Outer line opacity.
	 */
	public void setStrokeOpacity(float opacity) {
		m_strokeOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
	}

	/**
	 * Set the opacity for the inner fill.
	 *
	 * @param opacity Inner fill opacity.
	 */
	public void setFillOpacity(float opacity) {
		m_fillOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
	}

}
