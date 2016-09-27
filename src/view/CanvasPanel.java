/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Maths.Vector2f;
import Maths.Vector3f;
import data.Line;
import draw.SimpleDrawer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import parser.Parser;
import parser.Path;
import parser.SVG;

public class CanvasPanel extends JPanel {

    BufferedImage m_canvasImage;
    SimpleDrawer m_simpDraw;
    Path m_p;

    public CanvasPanel() {
        super();
        Parser parser = new Parser("L3SVG.svg");
        SVG svg = parser.parse();

        m_p = svg.getPathList().get(0);

        setBackground(Color.gray);
        setSize(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        resetImage();
        for(Line l: m_p.getElements()) {
            for (float t = 0; t < 1; t += 0.001f) {
            Vector2f pos = l.getPoint(t);
            drawPoint(Math.round(pos.x), Math.round(pos.y), 0, 0, 0);
            }
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
