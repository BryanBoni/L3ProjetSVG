/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private CanvasPanel m_panelCanvas;

	public MainWindow() {
		super();
		m_panelCanvas = new CanvasPanel();
		initCompenents();
                
	}

	private void initCompenents() {
		setContentPane(m_panelCanvas);
		setCursor(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("SVG");
		getContentPane().setLayout(null);
		m_panelCanvas.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		m_panelCanvas.setDoubleBuffered(false);
		m_panelCanvas.setRequestFocusEnabled(false);
		m_panelCanvas.setLayout(null);
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void resetImage() {
		m_panelCanvas.resetImage();
	}

	public void drawPoint(int x, int y, int r, int g, int b) {
		m_panelCanvas.drawPoint(x, y, r, g, b);
	}

	public void showImage() {
		m_panelCanvas.showImage();
	}
}
