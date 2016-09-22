package draw;

import Maths.Vector2f;
import data.Line;

/**
 *
 * @author p1402828
 */
public class Rasterizer {

	// static
	static private float[][] m_pixels;
	static private int m_width, m_height;

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
	 */
	static public void drawLine(Line line) {
		// clean pixel array
		for (int x = 0; x < m_width; x++) {
			for (int y = 0; y < m_height; y++) {
				m_pixels[x][y] = -1;
			}
		}
		// start recursive approximation

		// send pixels for render
	}

	/**
	 * Transform a point into a pixel to insert it in the matrice. The return
	 * value is used to know if the recursive calls should continue interpolate
	 * or should stop.
	 *
	 * @param position the real coordinates of the point to be drawn
	 * @return false if the pixel was already drawed, true if not
	 */
	static private boolean rasterize(Vector2f position) {
		//int x1 = (int)Math.floor(position.x);
		//int y1 = (int)Math.floor(position.y);
		//int x2 = (int)Math.ceil(position.x);
		//int y2 = (int)Math.ceil(position.y);

		int x = (int) Math.round(position.x);
		int y = (int) Math.round(position.y);

		if (m_pixels[x][y] < 0) {
			m_pixels[x][y] = 1;
			return true; // not drawed yet, continue recursion
		}
		return false; // already drawed, stop recursion
	}

}
