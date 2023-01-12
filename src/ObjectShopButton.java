import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ObjectShopButton 
{
	private Object object;

	private int x;
	private int y;
	private int width;
	private int height;

	private Font dogicaPixelBold;

	private BufferedImage button;
	private BufferedImage coin;
	
	private Color brown;
	private Color tan;
	private Color black;
	
	private boolean hovered;

	public ObjectShopButton(int x, int y, int width, int height, Object object) {
		this.x = x;
		this.y = y;
	
		this.width = width;
		this.height = height;
		
		this.object = object;
		this.hovered = false;

		brown = new Color(63, 38, 49);
		tan = new Color(189, 108, 74);
		black = new Color(20, 20, 20);
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);

			coin = ImageIO.read(getClass().getResourceAsStream("icons/coin.png"));
			button = ImageIO.read(getClass().getResourceAsStream("ui/menu_button.png"));
		} 
		catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (GameJPanel.getMouseX() > x && GameJPanel.getMouseX() < x + width && GameJPanel.getMouseY() > y
				&& GameJPanel.getMouseY() < y + height) {
			hovered = true;
		} 
		else {
			hovered = false;
		}
	}

	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		g.setFont(dogicaPixelBold);
		
		// Draw shop button
		g.setColor(new Color(189, 108, 74, 100));
		g.fillRect(x + 2, y + 2, width - 4, height - 4);
		
		g.drawImage(button, x, y, null);
		
		if (object != null) {
			// Display item icon
			g.drawImage(object.getIcon(), x + 10, y + 175, width - 20, width - 20, null);

			// Display item name
			g.setFont(dogicaPixelBold);
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 20F));
			
			g.setColor(brown);

			String name = object.getName().substring(0, object.getName().indexOf(' ')).toUpperCase();

			g.drawString(name, (int) ((x + width / 2) - (g.getFont().getStringBounds(name, frc).getWidth() / 2)),
					y + 100);
			
			name = object.getName().substring(object.getName().indexOf(' ') + 1).toUpperCase();
			
			g.drawString(name, (int) ((x + width / 2) - (g.getFont().getStringBounds(name, frc).getWidth() / 2)),
					y + 125);

			// Display item cost
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 18F));

			String cost;
			if (object.getCost() == 0) {
				cost = "FREE";
				
				g.drawString(cost,
						(int) ((x + width / 2) - (g.getFont().getStringBounds(cost, frc).getWidth() / 2)), y + 440);
			} 
			else {
				cost = String.valueOf(object.getCost());
				
				g.drawImage(coin, (int) ((x + width / 2) - (g.getFont().getStringBounds(cost, frc).getWidth() / 2)) - 16,
						y + 420, 22, 22, null);
				g.drawString(cost,
						(int) ((x + width / 2) - ((g.getFont().getStringBounds(cost, frc).getWidth() - 27) / 2)), y + 440);
			}

			
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && hovered && object != null) {
			GameScreenManager.getInstance().addScreen(new ShopItemScreen(object));
			GameScreenManager.getInstance().clearFirst();
		}
	}
}
