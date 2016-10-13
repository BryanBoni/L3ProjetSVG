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

public class Rectangle implements IDrawableSVG {

	private Vector2f m_position = new Vector2f(0,0);
	private Vector2f m_size = new Vector2f(0,0);
	private Color m_color = Color.BLACK;

	@Override
	public void render(Graphics g) {
		g.setColor(m_color);
		float zoom = CanvasPanel.zoom;
		int x, y, w, h;
		x = (int)(m_position.x * zoom);
		y = (int)(m_position.y * zoom);
		w = (int)(m_size.x * zoom);
		h = (int)(m_size.y * zoom);
		g.drawRect(x, y, w, h);
	}
}
