package data;

import java.util.ArrayList;


/**
 *
 * @author p1402828
 */
public class Path implements IDrawableSVG{
    
    public Path() {
        
    }
    /**
     * 
     * @param line 
     */
    public void addLine(Line line) {
        m_elements.add(line);
    }
    /**
     * 
     * @param curve 
     */
    public void addCurve(Curve curve) {
        m_elements.add(curve);
    }
    
    private ArrayList<Line> m_elements;
}
