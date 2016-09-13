package data;

/**
 *
 * @author p1402828
 */
public class Curve extends Line implements IDrawableSVG {

    public Curve() {
        super();
    }

    /**
     * Add a new stop to the Curve.
     * A curve is only 4 points, so we can't add more than 4 stops.
     * @param x The abstract x position in world cooridinate for the stop.
     * @param y The abstract y position in world cooridinate for the stop.
     */
    @Override
    public void addStop(float x, float y) {
        //if(m_stops.length() <4)
        //m_stops.add(new Vector2f(x,y));
    }

}
