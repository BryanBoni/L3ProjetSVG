package data;

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

/**
 * Class that holds datas for images files that have to be rendered on the
 * canvas.
 *
 * @author ANTOINE
 */
public class Image extends DrawableSVG {

	private String m_xlinkHref = ""; // either a path or a bunch of data
	private boolean m_isXlinkHrefPath = false; // defines if the picture is path located or data described
	private java.awt.Image m_image = null; // data of the image once loaded

	public void setXlinkHref(String xlinkHref) {
		m_xlinkHref = xlinkHref;
		if (!m_xlinkHref.contains(":")) { // local relative file
			m_isXlinkHrefPath = true;
			System.out.println("loading image file...");
			try {
				File imageFile = new File(m_xlinkHref);
				m_image = ImageIO.read(imageFile);
				System.out.println("Image loaded (" + m_xlinkHref + ")");
			} catch (Exception e) {
				m_image = null;
				System.out.println("Impossible to load image! (" + m_xlinkHref + ")");
			}
		} else { // url
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
	}

	@Override
	public void render(Graphics2D g) {
//		float entropie = 50;
//		m_position = m_position.add(new Vector2f((float) Math.random() * entropie - entropie/2, (float) Math.random() * entropie - entropie/2));
//		m_size = m_size.add(new Vector2f((float) Math.random() * entropie - entropie/2, (float) Math.random() * entropie - entropie/2));

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
					x, y, x + w, y + h,
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
					x, y, x + w, y + h,
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
