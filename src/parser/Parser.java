package parser;

import data.Curve;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

    private Document doc;

    public Parser(String filePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(filePath);
            doc = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public SVG parse() {
        if (doc != null) {
            SVG svg = new SVG();
                       
            // Parsing path
            
            NodeList nodeList = doc.getElementsByTagName("path");
            
            for(int i=0;i<nodeList.getLength();i++) {
                
                Path path = new Path();
                
                Node node = nodeList.item(i);
                
                // Parsing style
                
                if(node.getAttributes().getNamedItem("style") != null) {
                    String style = node.getAttributes().getNamedItem("style").getNodeValue();
                    for(String s : style.split(";")) {
                        if(s.contains("stroke:")) {
                            path.setStroke(Color.decode(s.replace("stroke:","")));
                        }
                    }
                }
                
                // String to be scanned to find the pattern.
                String d = node.getAttributes().getNamedItem("d").getNodeValue();
                
                String pattern = "[a-z][^a-z]*";

                // Create a Pattern object.
                Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

                // Now create matcher object.
                Matcher m = r.matcher(d);
                float previousX = 0f;
                float previousY = 0f;
                
                while (m.find()) {
                    String match = m.group(0);
                    
                    if(match.startsWith("m") || match.startsWith("M")) {
                        String coord = match.replace("m ", "").replace("M ", "");
                        previousX = Float.parseFloat(coord.split(",")[0]);
                        previousY = Float.parseFloat(coord.split(",")[1]);
                    }
                    
                    else if(match.startsWith("c") || match.startsWith("C")) {
                        String coord = match.replace("c ", "").replace("C ", "");
                        Curve curve = new Curve();
                        for(String s : coord.split(" ")) {
                           if(curve.getStops().isEmpty()) {
                               curve.addStop(previousX, previousY);
                           }                           
                           previousX = Float.parseFloat(s.split(",")[0]);
                           previousY = Float.parseFloat(s.split(",")[1]);
                           
                           curve.addStop(previousX, previousY);
                        }
                        path.addCurve(curve);
                    }
                }
                
                svg.getPathList().add(path);
            }         
            
            return svg;
        }
        else {
            return null;
        }
        
    }

}
