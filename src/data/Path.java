package data;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class representing a path red from a SVG file. 
 * It can contains Lines ("L" or * "l") and Curves ("C" or "c"). 
 * Some style can be defined, such as line width, line color...
 */
public class Path implements IDrawableSVG {

    private final ArrayList<Line> m_elements = new ArrayList<>();
    private Color m_strokeColor = Color.BLACK;


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
    public Color getStroke() {
        return m_strokeColor;
    }

    /**
     * @param m_stroke the m_strokeColor to set
     */
    public void setStroke(Color m_stroke) {
        this.m_strokeColor = m_stroke;
    }
	
	@Override
	public void render(Graphics g) {
		g.setColor(m_strokeColor);
		for(Line l: m_elements) {
            l.render(g);
		}
	}

}