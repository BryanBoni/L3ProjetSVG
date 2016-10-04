package parser;

import data.Curve;
import data.IDrawableSVG;
import data.Line;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Class representing a path red from a SVG file. 
 * It can contains Lines ("L" or * "l") and Curves ("C" or "c"). 
 * Some style can be defined, such as line width, line color...
 */
public class Path implements IDrawableSVG {

    private final ArrayList<Line> m_elements;
    private Color m_stroke;

    public Path() {
        m_elements = new ArrayList<>();
    }

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

    @Override
    public void draw() {
        m_elements.stream().forEach((l) -> {
            l.draw();
        });
    }

    /**
     * @return the m_stroke
     */
    public Color getStroke() {
        return m_stroke;
    }

    /**
     * @param m_stroke the m_stroke to set
     */
    public void setStroke(Color m_stroke) {
        this.m_stroke = m_stroke;
    }

}
