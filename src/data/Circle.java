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
	public void setPosition(float x, float y) {
		m_position.x = x;
		m_position.y = y;
	}
	public void setRadius(float radius) {
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
		x = (int)((m_position.x - m_radius) * zoom);
		y = (int)((m_position.y - m_radius) * zoom);
		r = (int)(m_radius * zoom);
		g.drawOval(x, y, r*2, r*2);
	}
}
