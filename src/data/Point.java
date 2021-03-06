package data;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Class that is used to display a red cross specific coordinates. Useful for
 * debuging purposes.
 *
 * @author ANTOINE
 */
public class Point extends DrawableSVG {

	@Override
	public void render(Graphics2D g) {
		int size = Math.round(5);
		g.setColor(Color.red);
		g.drawLine(-size, size, size, -size);
		g.drawLine(-size, -size, size, size);
	}
}
