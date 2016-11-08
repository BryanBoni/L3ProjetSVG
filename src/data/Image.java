package data;

import Maths.Vector2f;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.imageio.ImageIO;
import view.CanvasPanel;

public class Image implements IDrawableSVG {

	private Vector2f m_position = new Vector2f(0, 0);
	private Vector2f m_size = new Vector2f(0, 0);
	private String m_xlinkHref = "";
	private boolean m_isXlinkHrefPath = false;
	private java.awt.Image m_image = null;

	public void setPosition(Vector2f position) {
		m_position = position;
	}

	public void setPosition(float x, float y) {
		m_position.x = x;
		m_position.y = y;
	}

	public void setSize(Vector2f size) {
		m_size = size;
	}

	public void setSize(float width, float height) {
		m_size.x = width;
		m_size.y = height;
	}

	public void setXlinkHref(String xlinkHref) {
		m_xlinkHref = xlinkHref;
		String start = m_xlinkHref.substring(0, m_xlinkHref.indexOf(":"));
		m_isXlinkHrefPath = start.equals("file");
		System.out.println(start + " ? " + "file = " + m_isXlinkHrefPath);
		if (m_isXlinkHrefPath) {
			System.out.println("loading image file...");
			try {
				String path = m_xlinkHref.replace("file:///", "");
				URL url = new URL(m_xlinkHref);
				File imageFile = new File(url.toURI());
				m_image = ImageIO.read(imageFile);
				System.out.println("Image loaded (" + url.toURI() + ")");
			} catch (Exception e) {
				m_image = null;
				System.out.println("Impossible to load image! (" + m_xlinkHref + ")");
			}
		} else {
			System.out.println("loading embadded image...");
			try {
				String dataStr = m_xlinkHref.substring(m_xlinkHref.indexOf(",") + 1);
				Base64.Decoder decoder = Base64.getMimeDecoder();
				byte data[] = decoder.decode(dataStr.getBytes(StandardCharsets.UTF_8));
				InputStream in = new ByteArrayInputStream(data);
				m_image = ImageIO.read(in);
				System.out.println("Image loaded");
			} catch (Exception e) {
				m_image = null;
				System.out.println("Impossible to load image!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		if (m_isXlinkHrefPath) {
			renderImageLink(g);
		} else {
			renderImageEmbed(g);
		}
	}

	public void renderImageLink(Graphics2D g) {
		float zoom = CanvasPanel.zoom;
		int x, y, w, h;

		x = Math.round(m_position.x * zoom);
		y = Math.round(m_position.y * zoom);
		w = Math.round(m_size.x * zoom);
		h = Math.round(m_size.y * zoom);
		if (m_image != null) {
			g.drawImage(m_image,
					x, y, x+w, y+h,
					0, 0, m_image.getWidth(null), m_image.getHeight(null),
					null);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, w, h);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
	}

	public void renderImageEmbed(Graphics2D g) {
		float zoom = CanvasPanel.zoom;
		int x, y, w, h;

		x = Math.round(m_position.x * zoom);
		y = Math.round(m_position.y * zoom);
		w = Math.round(m_size.x * zoom);
		h = Math.round(m_size.y * zoom);
		if (m_image != null) {
			g.drawImage(m_image,
					x, y, x+w, y+h,
					0, 0, m_image.getWidth(null), m_image.getHeight(null),
					null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, w, h);
		}
		g.setColor(Color.BLUE);
		g.drawRect(x, y, w, h);
	}

}
