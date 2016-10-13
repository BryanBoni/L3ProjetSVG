package draw;

import Maths.Vector2f;
import data.Line;
import java.awt.Graphics;
import java.util.Collection;
import data.Path;
import view.CanvasPanel;

public class Rasterizer {

	// static
	static private float[][] m_pixels;
	static private int m_width, m_height;
	static private CanvasPanel m_canvas;
	
	static public void renderPath(Collection<Path> pSet, Graphics g) {
		for(Path p: pSet) {
			renderPath(p, g);
		}
	}

	static public void renderPath(Path p, Graphics g) {
		// clean pixel array
		prepareRender(g.getClipBounds().width, g.getClipBounds().height);
		
		// compute all pixel
		for (Line l : p.getElements()) {
			drawLine(l, g);
		}

		// send pixels to display area
		for (int x = 0; x < m_width; x++) {
			for (int y = 0; y < m_height; y++) {
				if (m_pixels[x][y] > 0) {
					g.setColor(p.getStroke());
					g.drawRect(x, y, 0, 0);
				}
			}
		}
	}

	/**
	 * Reset the drawing context using the current area size. Must be called
	 * before any drawLine calls.
	 *
	 * @param drawingAreaWidth the width of the drawing area
	 * @param drawingAreaHeight the height of the drawing area
	 */
	static public void prepareRender(int drawingAreaWidth, int drawingAreaHeight) {
		m_width = drawingAreaWidth;
		m_height = drawingAreaHeight;
		m_pixels = new float[m_width][m_height];
	}

	/**
	 * Starts the rasterizing process for an object. Will clean m_pixels array
	 * and start the recursive position approximation. Will then send the pixels
	 * for rendering.
	 *
	 * @param line
	 * @param g
	 */
	static public void drawLine(Line line, Graphics g) {

		// clean pixel array
		for (int x = 0; x < m_width; x++) {
			for (int y = 0; y < m_height; y++) {
				m_pixels[x][y] = 0;
			}
		}
		System.out.println("before rast ");

		// start recursive approximation
		line.rasterizeThread();
		System.out.println("after rast");
	}

	/**
	 * Transform a point into a pixel to insert it in the matrice. The return
	 * value is used to know if the recursive calls should continue interpolate
	 * or should stop.
	 *
	 * @param position the real coordinates of the point to be drawn
	 * @return false if the pixel was already drawed, true if not
	 */
	static public boolean rasterize(Vector2f position) {
		int lineWidth = 1;
		int X = (int) Math.floor(position.x);
		int Y = (int) Math.floor(position.y);
		boolean overWrite = false;
		for (int x = X - lineWidth; x < X + lineWidth + 1; x++) {
			for (int y = Y - lineWidth; y < Y + lineWidth + 1; y++) {
				float weight = clamp(0, 1, position.subtract(new Vector2f(x, y)).length() * lineWidth);
				if (m_pixels[x][y] < weight) {
					m_pixels[x][y] = weight;
					overWrite = true; // not drawed yet, continue recursion
				}
			}
		}
		return overWrite;
	}

	/**
	 * Returns the value clamped between min and max.
	 *
	 * @param min The minimum value that will return the function.
	 * @param max The maximum value that will return the function.
	 * @param value The value to be clamped.
	 * @return
	 */
	public static float clamp(float min, float max, float value) {
		if (value > max) {
			return max;
		}
		if (value < min) {
			return min;
		}
		return value;
	}

}
