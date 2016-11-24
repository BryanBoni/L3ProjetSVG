package parser;

import java.util.ArrayList;
import data.DrawableSVG;

public class SVG {

    private final ArrayList<DrawableSVG> m_drawableList;

    public SVG() {
        m_drawableList = new ArrayList<>();
    }

    public ArrayList<DrawableSVG> getDrawableList() {
        return m_drawableList;
    }
	
	public void addDrawable(DrawableSVG drawable) {
		m_drawableList.add(drawable);
	}
}
