package data;

import java.awt.Graphics2D;
import view.CanvasPanel;

/**
 * Class that represents circle objects that need to be drawn on the canvas.
 *
 * @author ANTOINE
 */
public class Circle extends DrawableSVG {

	private float m_radius = 0; // circle radius

	/**
	 * Set the radius of the circle.
	 *
	 * @param radius Radius of the circle.
	 */
	public void setRadius(float radius) {
		m_radius = radius;
	}

	@Override
	public void render(Graphics2D g) {
		float zoom = CanvasPanel.zoom;
		int x, y, d;
		x = Math.round((m_position.x - m_radius) * zoom);
		y = Math.round((m_position.y - m_radius) * zoom);
		d = Math.round(m_radius * 2 * zoom);
		if (m_fillColor != null) {
			g.setColor(m_fillColor);
			g.setComposite(m_fillOpacity);
			g.fillOval(x, y, d, d);
		}
		if (m_strokeColor != null) {
			g.setColor(m_strokeColor);
			g.setComposite(m_strokeOpacity);
			g.drawOval(x, y, d, d);
		}
	}
}
