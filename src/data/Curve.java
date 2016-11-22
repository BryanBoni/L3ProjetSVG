package data;

import Maths.Vector2f;

public class Curve extends Line {

    /**
     * Add a new stop to the Curve. A curve is only 4 points, so we can't add
     * more than 4 stops.
     *
     * @param x The abstract x position in world cooridinate for the stop.
     * @param y The abstract y position in world cooridinate for the stop.
     */
    @Override
    public void addStop(float x, float y) {
        if (m_stops.size() < 4) {
            m_stops.add(new Vector2f(x, y));
        }
    }

    /**
     * Add a new stop to the Line. A curve is only 4 points, so we can't add
     * more than 4 stops.
     *
     * @param position Vector representing the abstract position in world
     * cordinate for the stop.
     */
    @Override
    public void addStop(Vector2f position) {
        if (m_stops.size() < 4) {
            m_stops.add(position);
        }
    }

}
