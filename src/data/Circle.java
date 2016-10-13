/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import Maths.Vector2f;
import java.awt.Color;
import java.awt.Graphics;
import view.CanvasPanel;

public class Circle implements IDrawableSVG {
	private Vector2f m_position = new Vector2f(0,0);
	private float m_radius = 0;
    private Color m_color = Color.BLACK;

	public void setPosition(Vector2f position) {
		m_position = position;
	}
	public void setPosition(int x, int y) {
		m_position.x = x;
		m_position.y = y;
	}
	public void setRadius(int radius) {
		m_radius = radius;
	}
	public void setColor(Color color) {
		m_color = color;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(m_color);
		float zoom = CanvasPanel.zoom;
		int x, y, r;
		x = (int)(m_position.x * zoom);
		y = (int)(m_position.y * zoom);
		r = (int)(m_radius * zoom);
		g.drawOval(x, y, r, r);
	}
}
