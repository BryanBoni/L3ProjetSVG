package data;

import java.awt.Font;
import java.awt.Graphics2D;
import view.CanvasPanel;

/**
 * Class that describes text objects that have to be drawn on the canvas.
 *
 * @author ANTOINE
 */
public class Text extends DrawableSVG {

	private int m_fontSize; // size of the font
	private String m_text; // text displayed
	private Font m_font; // font used

	/**
	 * Set the size for the font to be rendered in.
	 *
	 * @param fontSize Size of the font.
	 */
	public void setFontSize(int fontSize) {
		m_fontSize = fontSize;
	}

	/**
	 * Set the text content of the object
	 *
	 * @param text Text to be displayed.
	 */
	public void setText(String text) {
		m_text = text;
	}

	/**
	 * Set the font in wich the text will be rendered.
	 *
	 * @param font Font of the text.
	 */
	public void setFont(Font font) {
		m_font = font;
	}

	@Override
	public void render(Graphics2D g) {
		//g.setFont(m_font);
		float zoom = CanvasPanel.zoom;
		int x, y;
		x = (int) (m_position.x * zoom);
		y = (int) (m_position.y * zoom);
		g.drawString(m_text, x, y);
	}
}
