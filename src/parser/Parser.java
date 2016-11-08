package parser;

import data.Circle;
import data.Path;
import data.Curve;
import data.Image;
import data.Point;
import java.awt.Color;
import data.Rectangle;
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
			// Parsing nodes
			NodeList nodeList = null;
			nodeList = doc.getElementsByTagName("g");
			if (nodeList.getLength() > 0) { // if exist "g" node (from Inkscape)
				loadNodeList(nodeList.item(0).getChildNodes(), svg);
			} else {
				loadNodeList(doc.getFirstChild().getChildNodes(), svg);
			}

			svg.addDrawable(new Point()); // origin
			
			return svg;
		} else {
			return null;
		}

	}

	public void loadNodeList(NodeList nodeList, SVG svg) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals("circle")) {
					loadNodeCircle(node, svg);
				}
				if (node.getNodeName().equals("rect")) {
					loadNodeRect(node, svg);
				}
				if (node.getNodeName().equals("path")) {
					loadNodePath(node, svg);
				}
				if (node.getNodeName().equals("image")) {
					loadNodeImage(node, svg);
				}
			}
		}
	}

	public void loadNodeCircle(Node node, SVG svg) {
		Circle circle = new Circle();

		if (node.getAttributes().getNamedItem("style") != null) {
			String style = node.getAttributes().getNamedItem("style").getNodeValue();
			for (String s : style.split(";")) {
				if (s.contains("stroke:")) {
					circle.setStrokeColor(Color.decode(s.replace("stroke:", "")));
				}
				if (s.contains("fill:")) {
					circle.setFillColor(Color.decode(s.replace("fill:", "")));
				}
			}
		}

		float cx = Float.parseFloat(node.getAttributes().getNamedItem("cx").getNodeValue());
		float cy = Float.parseFloat(node.getAttributes().getNamedItem("cy").getNodeValue());
		float r = Float.parseFloat(node.getAttributes().getNamedItem("r").getNodeValue());
		circle.setPosition(cx, cy);
		circle.setRadius(r);

		svg.addDrawable(circle);
	}

	public void loadNodeRect(Node node, SVG svg) {
		Rectangle rect = new Rectangle();

		if (node.getAttributes().getNamedItem("style") != null) {
			String style = node.getAttributes().getNamedItem("style").getNodeValue();
			for (String s : style.split(";")) {
				if (s.contains("stroke:")) {
					rect.setStrokeColor(Color.decode(s.replace("stroke:", "")));
				}
				if (s.contains("fill:")) {
					rect.setFillColor(Color.decode(s.replace("fill:", "")));
				}
			}
		}

		float x = Float.parseFloat(node.getAttributes().getNamedItem("x").getNodeValue());
		float y = Float.parseFloat(node.getAttributes().getNamedItem("y").getNodeValue());
		float width = Float.parseFloat(node.getAttributes().getNamedItem("width").getNodeValue());
		float height = Float.parseFloat(node.getAttributes().getNamedItem("height").getNodeValue());
		rect.setPosition(x, y);
		rect.setSize(width, height);

		svg.addDrawable(rect);
	}

	public void loadNodePath(Node node, SVG svg) {
		Path path = new Path();

		// Parsing style
		if (node.getAttributes().getNamedItem("style") != null) {
			String style = node.getAttributes().getNamedItem("style").getNodeValue();
			for (String s : style.split(";")) {
				if (s.contains("stroke:")) {
					path.setStrokeColor(Color.decode(s.replace("stroke:", "")));
				}
				if (s.contains("stroke-width:")) {
					path.setStrokeWidth(Float.parseFloat(s.replace("stroke-width:", "")));
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

			if (match.startsWith("m") || match.startsWith("M")) {
				String coord = match.replace("m ", "").replace("M ", "");
				previousX = Float.parseFloat(coord.split(",")[0]);
				previousY = Float.parseFloat(coord.split(",")[1]);
			} else if (match.startsWith("c") || match.startsWith("C")) {
				String coord = match.replace("c ", "").replace("C ", "");
				Curve curve = new Curve();
				for (String s : coord.split(" ")) {
					if (curve.getStops().isEmpty()) {
						curve.addStop(previousX, previousY);
					}
					previousX = Float.parseFloat(s.split(",")[0]);
					previousY = Float.parseFloat(s.split(",")[1]);

					curve.addStop(previousX, previousY);
				}
				path.addCurve(curve);
			}
		}

		svg.addDrawable(path);
	}

	public void loadNodeImage(Node node, SVG svg) {
		Image image = new Image();
		System.out.println("loading image node");
		float x = Float.parseFloat(node.getAttributes().getNamedItem("x").getNodeValue());
		float y = Float.parseFloat(node.getAttributes().getNamedItem("y").getNodeValue());
		float width = Float.parseFloat(node.getAttributes().getNamedItem("width").getNodeValue());
		float height = Float.parseFloat(node.getAttributes().getNamedItem("height").getNodeValue());
		String xlinkHref = node.getAttributes().getNamedItem("xlink:href").getNodeValue();
		image.setPosition(x, y);
		image.setSize(width, height);
		image.setXlinkHref(xlinkHref);

		svg.addDrawable(image);
	}

}
