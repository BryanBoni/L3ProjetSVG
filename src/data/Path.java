package data;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import view.CanvasPanel;
import static view.CanvasPanel.minZoom;

/**
 * Class representing a path red from a SVG file. It can contains Lines ("L" or
 * "l") and Curves ("C" or "c"). Some style can be defined, such as line width,
 * line color...
 */
public class Path extends DrawableSVG {

	private final ArrayList<Line> m_elements = new ArrayList<>();
	private Color m_strokeColor = Color.BLACK;
	private float m_strokeWidth = 1;
	private AlphaComposite m_strokeOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	//private AlphaComposite m_fillOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);

	/**
	 * Add a Line to be rendered with the Path.
	 *
	 * @param line
	 */
	public void addLine(Line line) {
		m_elements.add(line);
	}

	/**
	 * Add a Curve to be rendered with the Path.
	 *
	 * @param curve
	 */
	public void addCurve(Curve curve) {
		m_elements.add(curve);
	}

	public ArrayList<Line> getElements() {
		return m_elements;
	}

	public void setStrokeColor(Color strokeColor) {
		this.m_strokeColor = strokeColor;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.m_strokeWidth = strokeWidth;
	}

	public void setStrokeOpacity(float opacity) {
		m_strokeOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
	}

	//public void setFillOpacity(float opacity) {
	//	m_fillOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
	//}
	@Override
	public void preDraw() {
		m_limits.x = getMinX() - m_strokeWidth / 2;
		m_limits.y = getMinY() - m_strokeWidth / 2;
		m_limits.z = getMaxX() + m_strokeWidth / 2;
		m_limits.w = getMaxY() + m_strokeWidth / 2;
		m_position.x = m_limits.x;
		m_position.y = m_limits.y;
		m_size.x = Math.abs(m_limits.z - m_limits.x);
		m_size.y = Math.abs(m_limits.w - m_limits.y);
		double surface = m_size.x * m_size.y;
		float ratio = minZoom * PRE_RENDERING_RATIO;
		m_bufferedImage = new BufferedImage(Math.round(m_size.x * ratio), Math.round(m_size.y * ratio), BufferedImage.TYPE_INT_ARGB);
		Graphics2D layerGraphics = (Graphics2D) m_bufferedImage.createGraphics();
		//layerGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		//layerGraphics.setColor(Color.BLACK);
		//layerGraphics.fillRect(0, 0, m_bufferedImage.getWidth(), m_bufferedImage.getHeight());
		//layerGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		layerGraphics.setColor(m_strokeColor);
		layerGraphics.setRenderingHints(RENDRING_HINTS);
		layerGraphics.translate(ratio * -(m_position.x + m_strokeWidth / 2), ratio * -(m_position.y + m_strokeWidth / 2));
		STROKE_WIDTH = m_strokeWidth;
		for (Line l : m_elements) {
			l.m_drawSteps = 1 / surface;
			l.render(layerGraphics);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if (m_bufferedImage == null) {
			preDraw();
		}
		/*BufferedImage layer = new BufferedImage(g.getClipBounds().width, g.getClipBounds().height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D layerGraphics = (Graphics2D) layer.createGraphics();
		layerGraphics.transform(g.getTransform());
		layerGraphics.setColor(m_strokeColor);
		layerGraphics.setRenderingHints(g.getRenderingHints());
		STROKE_WIDTH = m_strokeWidth;
		for (Line l : m_elements) {
			l.render(layerGraphics);
		}
		try {
			g.transform(layerGraphics.getTransform().createInverse());
			g.drawImage(layer, null, 0, 0);
			g.transform(layerGraphics.getTransform());
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		float zoom = CanvasPanel.zoom;
		int x, y, w, h;

		x = Math.round(m_position.x * zoom);
		y = Math.round(m_position.y * zoom);
		w = Math.round(m_size.x * zoom);
		h = Math.round(m_size.y * zoom);
		if (m_bufferedImage != null) {
			g.drawImage(m_bufferedImage,
					x, y, x + w, y + h,
					0, 0, m_bufferedImage.getWidth(), m_bufferedImage.getHeight(),
					null);
		}
	}

	public static float STROKE_WIDTH = 1;

	public float getMinX() {
		float min = m_elements.get(0).getMinX();
		for (Line stop : m_elements) {
			if (stop.getMinX() < min) {
				min = stop.getMinX();
			}
		}
		return min;
	}

	public float getMaxX() {
		float max = m_elements.get(0).getMaxX();
		for (Line stop : m_elements) {
			if (stop.getMaxX() > max) {
				max = stop.getMaxX();
			}
		}
		return max;
	}

	public float getMinY() {
		float min = m_elements.get(0).getMinY();
		for (Line stop : m_elements) {
			if (stop.getMinY() < min) {
				min = stop.getMinY();
			}
		}
		return min;
	}

	public float getMaxY() {
		float max = m_elements.get(0).getMaxY();
		for (Line stop : m_elements) {
			if (stop.getMaxY() > max) {
				max = stop.getMaxY();
			}
		}
		return max;
	}

}
