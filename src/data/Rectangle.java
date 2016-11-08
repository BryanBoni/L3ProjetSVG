package data;

import Maths.Vector2f;
import java.awt.Color;
import java.awt.Graphics2D;
import view.CanvasPanel;

public class Rectangle implements IDrawableSVG {

	private Vector2f m_position = new Vector2f(0, 0);
	private Vector2f m_size = new Vector2f(0, 0);
	private Color m_strokeColor = Color.BLACK;
	private Color m_fillColor = null;

	public void setPosition(Vector2f position) {
		m_position = position;
	}

	public void setPosition(float x, float y) {
		m_position.x = x;
		m_position.y = y;
	}

	public void setSize(Vector2f size) {
		m_size = size;
	}

	public void setSize(float width, float height) {
		m_size.x = width;
		m_size.y = height;
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
