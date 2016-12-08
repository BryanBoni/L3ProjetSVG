package data;

import java.awt.Graphics2D;
import view.CanvasPanel;

/**
 * Class that describe a rectangle object that has to be drawn on the canvas.
 *
 * @author ANTOINE
 */
public class Rectangle extends DrawableSVG {

	@Override
	public void render(Graphics2D g) {
		float zoom = CanvasPanel.zoom;
		int x, y, w, h;

		x = Math.round(m_position.x * zoom);
		y = Math.round(m_position.y * zoom);
		w = Math.round(m_size.x * zoom);
		h = Math.round(m_size.y * zoom);
		if (m_fillColor != null) {
			g.setColor(m_fillColor);
			g.fillRect(x, y, w, h);
		}
		if (m_strokeColor != null) {
			g.setColor(m_strokeColor);
			g.drawRect(x, y, w, h);
		}
	}

}
