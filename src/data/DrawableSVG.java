package data;

import Maths.Vector2f;
import Maths.Vector4f;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class DrawableSVG {
	public Vector4f m_limits = new Vector4f();
	public Vector2f m_size = new Vector2f();
	public Vector2f m_position = new Vector2f();
	public double m_drawSteps = 0.01f;
	public static float PRE_RENDERING_RATIO = 1;
	public static RenderingHints RENDRING_HINTS = initRenderingHint();
	public BufferedImage m_bufferedImage = null;
	
	public static RenderingHints initRenderingHint() {
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.add(new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		rh.add(new RenderingHints(
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
		rh.add(new RenderingHints(
				RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_SPEED));
		return rh;
	}
	
    public void render(Graphics2D g) {
		
	}
	
	public void preDraw() {
		
	}
	
	// buffer rendu tous zoom (tab[])
	// surface bezier * CONSTANTE / LARGEUR => nombre de points à rendre
	// 
}
