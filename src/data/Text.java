/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Maths.Vector2f;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import view.CanvasPanel;

public class Text implements IDrawableSVG {

	private Vector2f m_position;
	private int m_fontSize;
	private Color m_color;
	private String m_text;
	private Font m_font;

	public void setPosition(Vector2f position) {
		m_position = position;
	}
	public void setPosition(int x, int y) {
		m_position.x = x;
		m_position.y = y;
	}
	public void setFontSize(int fontSize) {
		m_fontSize = fontSize;
	}
	public void setColor(Color color) {
		m_color = color;
	}
	public void setText(String text) {
		m_text = text;
	}
	public void setFont(Font font) {
		m_font = font;
	}
	
	@Override
	public void render(Graphics g) {
		//g.setFont(m_font);
		float zoom = CanvasPanel.zoom;
		int x, y;
		x = (int) (m_position.x * zoom);
		y = (int) (m_position.y * zoom);
		g.drawString(m_text, x, y);
	}
}
