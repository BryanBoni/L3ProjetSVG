package data;

import Maths.Vector2f;
import draw.ThreadedRasterizer;
import java.awt.Graphics2D;
import java.util.ArrayList;
import view.CanvasPanel;

public class Line extends DrawableSVG {

	protected ArrayList<Vector2f> m_stops = new ArrayList<>();

	/**
	 * Add a new stop to the Line. A line is only 2 points, so we can't add more
	 * than 2 stops.
	 *
	 * @param x The abstract x position in world cooridinate for the stop.
	 * @param y The abstract y position in world cooridinate for the stop.
	 */
	public void addStop(float x, float y) {
		if (m_stops.size() < 2) {
			m_stops.add(new Vector2f(x, y));
		}
	}

	/**
	 * Add a new stop to the Line. A line is only 2 points, so we can't add more
	 * than 2.
	 *
	 * @param position Vector representing the abstract position in world
	 * cordinate for the stop.
	 */
	public void addStop(Vector2f position) {
		if (m_stops.size() < 2) {
			m_stops.add(position);
		}
	}

	@Override
	public void render(Graphics2D g) {
		//float entropie = 50;
		//for (int i = 0; i < m_stops.size(); i++) {
		//	m_stops.set(i, m_stops.get(i).add(new Vector2f((float) Math.random() * entropie - entropie/2, (float) Math.random() * entropie - entropie/2)));
		//}
		float zoom = CanvasPanel.minZoom * PRE_RENDERING_RATIO;
		double step = m_drawSteps;
		int size = Math.round(Path.STROKE_WIDTH * zoom);

		for (double t = 0; t <= 1; t += step) {
			Vector2f point = getPoint(t).scale(zoom);
			g.fillOval(Math.round(point.x), Math.round(point.y), size, size);
		}
		Vector2f point = m_stops.get(m_stops.size()-1).scale(zoom);
		g.fillOval(Math.round(point.x), Math.round(point.y), size, size);
	}

	public ArrayList<Vector2f> getStops() {
		return m_stops;
	}

	public Vector2f getPoint(double progression) {
		return interpolate(m_stops, (float)progression);
	}

	protected Vector2f interpolate(ArrayList<Vector2f> existingStops, float progression) {
		float reversedProgression = 1 - progression;
		ArrayList<Vector2f> stops = new ArrayList<>();
		for (int i = 1; i < existingStops.size(); i++) {
			Vector2f a = existingStops.get((i - 1)).scale(reversedProgression);
			Vector2f b = existingStops.get((i)).scale(progression);
			stops.add(a.add(b));
		}
		if (stops.size() > 1) {
			return interpolate(stops, progression);
		}
		return stops.get(0);
	}

	public void rasterizeThread() {
		try {
			ThreadedRasterizer thread = new ThreadedRasterizer(m_stops, 0.5f, 0.5f);
			System.out.println("Start thread.");
			thread.start();
			thread.join();
			System.out.println("Thread finished.");
		} catch (InterruptedException e) {
			e.printStackTrace(System.out);
		}
	}

	public float getMinX() {
		float min = m_stops.get(0).x;
		for (Vector2f stop : m_stops) {
			if (stop.x < min) {
				min = stop.x;
			}
		}
		return min;
	}

	public float getMaxX() {
		float max = m_stops.get(0).x;
		for (Vector2f stop : m_stops) {
			if (stop.x > max) {
				max = stop.x;
			}
		}
		return max;
	}

	public float getMinY() {
		float min = m_stops.get(0).y;
		for (Vector2f stop : m_stops) {
			if (stop.y < min) {
				min = stop.y;
			}
		}
		return min;
	}

	public float getMaxY() {
		float max = m_stops.get(0).y;
		for (Vector2f stop : m_stops) {
			if (stop.y > max) {
				max = stop.y;
			}
		}
		return max;
	}
}
