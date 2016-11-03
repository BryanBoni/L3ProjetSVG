package data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Class representing a path red from a SVG file. It can contains Lines ("L" or
 * * "l") and Curves ("C" or "c"). Some style can be defined, such as line
 * width, line color...
 */
public class Path implements IDrawableSVG {

    private final ArrayList<Line> m_elements = new ArrayList<>();
    private Color m_strokeColor = Color.BLACK;
	private float m_strokeWidth = 1;

    /**
     * Add a Line to be rendered with the Path.
     *
     * @param line
     */
    public void addLine(Line line) {
        m_elements.add(line);
    }

    /**
     * Add a Curve to be rendered with the Path.
     *
     * @param curve
     */
    public void addCurve(Curve curve) {
        m_elements.add(curve);
    }

    public ArrayList<Line> getElements() {
        return m_elements;
    }

    /**
     * @return the m_strokeColor
     */
    public Color getStrokeColor() {
        return m_strokeColor;
    }

    /**
     * @param strokeColor the m_strokeColor to set
     */
    public void setStrokeColor(Color strokeColor) {
        this.m_strokeColor = strokeColor;
    }

    /**
     * @return the m_strokeWidth
     */
    public float getStrokeWidth() {
        return m_strokeWidth;
    }

    /**
     * @param strokeWidth the m_strokeWidth to set
     */
    public void setStrokeWidth(float strokeWidth) {
        this.m_strokeWidth = strokeWidth;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(m_strokeColor);
		STROKE_WIDTH = m_strokeWidth;
        for (Line l : m_elements) {
            l.render(g);
        }
    }
	
	public static float STROKE_WIDTH = 1;

}
