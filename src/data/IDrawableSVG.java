package data;

import java.awt.Graphics2D;

public interface IDrawableSVG {
	public static float DRAW_STEP = 0.0005f;
    public void render(Graphics2D g);
}
