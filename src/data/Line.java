package data;

import Maths.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author p1402828
 */
public class Line implements IDrawableSVG {

    protected ArrayList<Vector2f> m_stops;

    public Line() {
        m_stops = new ArrayList<Vector2f>();
    }

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
    public void draw() {

    }

    public Vector2f getPoint(float progression) {
        return interpolate(m_stops, progression);
    }

    protected Vector2f interpolate(ArrayList<Vector2f> existingStops, float progression) {
        float reversedProgression = 1 - progression;
        ArrayList<Vector2f> stops = new ArrayList<Vector2f>();
        for (int i = 1; i < existingStops.size(); i++) {
            Vector2f a = existingStops.get((i-1)).scale(reversedProgression);
            Vector2f b = existingStops.get((i)).scale(progression);
            stops.add(a.add(b));
        }
        if(stops.size()<1)
            return interpolate(stops, progression);
        return stops.get(0);
    }
}
