/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

	BufferedImage m_canvasImage;

	public CanvasPanel() {
		super();
		setBackground(Color.gray);
		setSize(200, 200);
		resetImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		showImage();
	}

	public void resetImage() {
		m_canvasImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = m_canvasImage.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 500, 500);
	}

	public void drawPoint(int x, int y) {
		/*Graphics2D g2 = (Graphics2D) m_canvasImage.getGraphics();
		g2.setStroke(new BasicStroke(5));
		g2.setColor(Color.black);
		g2.drawLine(x, y, x + 5, y + 5);*/
		m_canvasImage.setRGB(x, y, Color.black.getRGB());
	}

	public void showImage() {
		Graphics g = this.getGraphics();
		g.drawImage(m_canvasImage, 0, 0, 500, 500, null);
	}
}
