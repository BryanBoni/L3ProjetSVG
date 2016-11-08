package parser;

import data.IDrawableSVG;
import java.util.ArrayList;

public class SVG {

    private final ArrayList<IDrawableSVG> m_drawableList;

    public SVG() {
        m_drawableList = new ArrayList<>();
    }

    public ArrayList<IDrawableSVG> getDrawableList() {
        return m_drawableList;
    }
	
	public void addDrawable(IDrawableSVG drawable) {
		m_drawableList.add(drawable);
	}
}
