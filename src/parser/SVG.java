package parser;

import java.util.ArrayList;

public class SVG {
    
    private final ArrayList<Path> m_pathList;
    
    public SVG() {
        m_pathList = new ArrayList<>();
    }
    
    public ArrayList<Path> getPathList() {
        return m_pathList;
    }
}
