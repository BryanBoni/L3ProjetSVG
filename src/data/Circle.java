package data;

import Maths.Vector2f;
import java.awt.Color;
import java.awt.Graphics2D;
import view.CanvasPanel;

public class Circle implements IDrawableSVG {

	private Vector2f m_position = new Vector2f(0, 0);
	private float m_radius = 0;
	private Color m_strokeColor = Color.BLACK;
	private Color m_fillColor = null;

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

	public void setStrokeColor(Color strokeColor) {
		m_strokeColor = strokeColor;
	}

	public void setFillColor(Color fillColor) {
		m_fillColor = fillColor;
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
			g.fillOval(x, y, d, d);
		}
		if (m_strokeColor != null) {
			g.setColor(m_strokeColor);
			g.drawOval(x, y, d, d);
		}
	}
}
