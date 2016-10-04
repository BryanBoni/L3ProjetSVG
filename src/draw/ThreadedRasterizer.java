package draw;

import Maths.Vector2f;
import java.util.ArrayList;

public class ThreadedRasterizer extends Thread {

    private final ArrayList<Vector2f> m_stops;
    private final float m_progression;
    private final float m_variation;

    public ThreadedRasterizer(ArrayList<Vector2f> stops, float progression, float variation) {
        m_stops = stops;
        m_progression = progression;
        m_variation = variation;
    }

    @Override
    public void run() {
        interpolateThread(m_stops, m_progression, m_variation);
    }

    protected void interpolateThread(ArrayList<Vector2f> existingStops, float progression, float variation) {
        ArrayList<Vector2f> stops = new ArrayList<>();
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
                    ThreadedRasterizer threadA = new ThreadedRasterizer(m_stops, progression + variation, variation);
                    ThreadedRasterizer threadB = new ThreadedRasterizer(m_stops, progression - variation, variation);
                    threadA.start();
                    threadB.start();
                    threadA.join();
                    threadB.join();

                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            } // else do nothing and die.
        }
    }

}
