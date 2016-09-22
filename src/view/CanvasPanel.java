/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Maths.Vector2f;
import Maths.Vector3f;
import data.Curve;
import draw.SimpleDrawer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

	BufferedImage m_canvasImage;
	SimpleDrawer m_simpDraw;
	Curve m_c;

	public CanvasPanel() {
		super();
		m_c = new Curve();
		m_c.addStop(5, 5);
		m_c.addStop(250, 300);
		m_c.addStop(250, 5);
		m_c.addStop(495, 300);
		setBackground(Color.gray);
		setSize(200, 200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		resetImage();
		for (float t = 0; t < 1; t += 0.001f) {
			Vector2f pos = m_c.getPoint(t);
			System.out.println(Math.round(pos.x) +" "+Math.round(pos.y));
			drawPoint(Math.round(pos.x), Math.round(pos.y), 0, 0, 0);
		}
		showImage();
	}

	public void resetImage() {
		m_canvasImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = m_canvasImage.getGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 500, 500);
	}

	public void drawPoint(int x, int y, int r, int g, int b) {
		m_simpDraw = new SimpleDrawer(m_canvasImage);
		m_simpDraw.drawPixel(new Vector2f(x, y), new Vector3f(r, g, b));
	}

	public void showImage() {
		Graphics g = this.getGraphics();
		g.drawImage(m_canvasImage, 0, 0, 500, 500, null);
	}
}
