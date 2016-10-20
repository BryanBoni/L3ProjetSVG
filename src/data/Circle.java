package data;

import Maths.Vector2f;
import java.awt.Color;
import java.awt.Graphics2D;
import view.CanvasPanel;

public class Circle implements IDrawableSVG {

	private Vector2f m_position = new Vector2f(0, 0);
	private float m_radius = 0;
	private Color m_colorStroke = Color.BLACK;
	private Color m_colorFill = null;

	public void setPosition(Vector2f position) {
		m_position = position;
	}

	public void setPosition(float x, float y) {
		m_position.x = x;
		m_position.y = y;
	}

	public void setRadius(float radius) {
		m_radius = radius;
	}

	public void setColorStroke(Color colorStroke) {
		m_colorStroke = colorStroke;
	}

	public void setColorFill(Color colorFill) {
		m_colorFill = colorFill;
	}

	@Override
	public void render(Graphics2D g) {
		float zoom = CanvasPanel.zoom;
		int x, y, d;
		x = Math.round((m_position.x - m_radius) * zoom);
		y = Math.round((m_position.y - m_radius) * zoom);
		d = Math.round(m_radius * 2 * zoom);
		if (m_colorFill != null) {
			g.setColor(m_colorFill);
			g.fillOval(x, y, d, d);
		}
		if (m_colorStroke != null) {
			g.setColor(m_colorStroke);
			g.drawOval(x, y, d, d);
		}
	}
}
