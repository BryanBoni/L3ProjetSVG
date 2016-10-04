/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import Maths.Vector2f;
import java.util.ArrayList;

public class ThreadedRastirizer extends Thread {

	private ArrayList<Vector2f> m_stops;
	private float m_progression;
	private float m_variation;

	public ThreadedRastirizer(ArrayList<Vector2f> stops, float progression, float variation) {
		m_stops = stops;
		m_progression = progression;
		m_variation = variation;
	}

	@Override
	public void run() {
		interpolateThread(m_stops, m_progression, m_variation);
	}

	protected void interpolateThread(ArrayList<Vector2f> existingStops, float progression, float variation) {
		ArrayList<Vector2f> stops = new ArrayList<Vector2f>();
		float reversedProgression = 1 - progression;
		for (int i = 1; i < existingStops.size(); i++) {
			Vector2f a = existingStops.get((i - 1)).scale(reversedProgression);
			Vector2f b = existingStops.get((i)).scale(progression);
			stops.add(a.add(b));
		}
		if (stops.size() > 1) {
			interpolateThread(stops, progression, variation);
		} else {
			Vector2f position = stops.get(0); // point to print
			if (Rasterizer.rasterize(position)) { // if point was'nt drawed yet, continue to interpolate
				try {
					variation = variation / 2;
					ThreadedRastirizer threadA = new ThreadedRastirizer(m_stops, progression + variation, variation);
					ThreadedRastirizer threadB = new ThreadedRastirizer(m_stops, progression - variation, variation);
					threadA.start();
					threadB.start();
					threadA.join();
					threadB.join();
				} catch (InterruptedException ex) {
					// show "error multithreading application!"
				}
			} // else do nothing and die.
		}
	}

}
