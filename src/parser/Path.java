package parser;

import data.Curve;
import data.IDrawableSVG;
import data.Line;
import java.util.ArrayList;


/**
 * Class representing a path red from a SVG file.
 * It can contains Lines ("L" or "l") and Curves ("C" ou "c").
 * Some style can be defined, such as line width, line color...
 * @author p1402828
 */
public class Path implements IDrawableSVG {
    
    private ArrayList<Line> m_elements;
    
    public Path() {
        m_elements = new ArrayList<>();
    }
    /**
     * Add a Line to be rendered with the Path.
     * @param line 
     */
    public void addLine(Line line) {
        m_elements.add(line);
    }
    
    /**
     * Add a Curve to be rendered with the Path.
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
        for(Line l:m_elements)
            l.draw();
    } 
    
}