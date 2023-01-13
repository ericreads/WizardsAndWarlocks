import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ShopItemScreen extends GameScreen {
	private Object object;
	private Button buyButton;
	private Button backButton;
	private boolean buyable;

	private BufferedImage background;
	private BufferedImage border;
	private BufferedImage coin;

	private Font buttonFont;
	private Font dogicaPixelBold;
	private Font dogicaPixel;

	private Color black;
	private Color brown;
	private Color red;
	private Color lightBrown;

	public ShopItemScreen(Object object) {
		this.object = object;
	}

	@Override
	public void initialize() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);

			is = getClass().getResourceAsStream("/fonts/dogicapixel.ttf");
			dogicaPixel = Font.createFont(Font.TRUETYPE_FONT, is);

			background = ImageIO.read(getClass().getResourceAsStream("ui/background.png"));
			border = ImageIO.read(getClass().getResourceAsStream("ui/shop_item_border.png"));
			coin = ImageIO.read(getClass().getResourceAsStream("icons/coin.png"));
		} catch (IOException | FontFormatException e) {
			System.out.println(e.toString());
		}

		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		black = new Color(20, 20, 20);
		brown = new Color(63, 38, 49);
		red = new Color(232, 69, 55);
		lightBrown = new Color(197, 119, 82);

		buttonFont = dogicaPixelBold.deriveFont(Font.PLAIN, 20F);
		backButton = new BackButton(30, 45, "BACK", buttonFont, brown, red);

		if ((object.getName().equals("Spell Slinger") && SaveManager.getInstance().hasSpellSlinger())
				|| (object.getName().equals("Spell Sprayer") && SaveManager.getInstance().hasSpellSprayer())
				|| object.getName().equals("Starter Wand")) {
			buyButton = new BuyButton(
					(int) (1264 / 2 - buttonFont.getStringBounds("ALREADY OWNED", frc).getWidth() / 2), 590,
					"ALREADY OWNED", buttonFont, brown, brown);
			buyable = false;
		} else if (object.getName().equals("Health Upgrade") && SaveManager.getInstance().getPlayerHealth() >= 175) {
			buyButton = new BuyButton(
					(int) (1264 / 2 - buttonFont.getStringBounds("MAXIMUM HEALTH REACHED", frc).getWidth() / 2), 590,
					"MAXIMUM HEALTH REACHED", buttonFont, brown, brown);
			buyable = false;
		} else if (object.getCost() > SaveManager.getInstance().getMoney()) {
			buyButton = new BuyButton(1264 / 2, 590, object, buttonFont, lightBrown, lightBrown);
			buyable = false;
		} else {
			buyButton = new BuyButton(1264 / 2, 590, object, buttonFont, brown, lightBrown);
			buyable = true;
		}
	}

	@Override
	public void update(int deltaTime) {
		backButton.update();
		buyButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		// Draw background
		g.drawImage(background, 0, 0, null);
		g.drawImage(border, 30, 70, null);
		g.setColor(new Color(189, 108, 74, 100));
		g.fillRect(32, 72, border.getWidth() - 4, border.getHeight() - 4);

		g.setFont(dogicaPixelBold);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 20F));
		g.setColor(brown);

		// Display money
		String money = String.valueOf(SaveManager.getInstance().getMoney());
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 18F));

		g.drawImage(coin, (int) (1190 - g.getFont().getStringBounds(money, frc).getWidth()), 25, 25, 25, null);
		g.drawString(money, (int) (1220 - g.getFont().getStringBounds(money, frc).getWidth()), 45); // right align

		// Display item name
		g.setColor(brown);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 48F));

		g.drawString(object.getName(),
				(int) (1280 / 2 - g.getFont().getStringBounds(object.getName().toUpperCase(), frc).getWidth() / 2) + 4,
				150);

		// Display item description
		g.setColor(black);
		g.setFont(dogicaPixel);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 20F));

		String[] lines = object.getDescription().split("\n");

		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], 1280 / 12, 300 + (g.getFontMetrics().getHeight() + 20) * i);
		}

		g.drawImage(object.getIcon(), (1280 / 3) * 2, 175, 300, 300, null);
		backButton.draw(g);
		buyButton.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		backButton.mouseClicked(e);
		if (buyable)
			buyButton.mouseClicked(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
