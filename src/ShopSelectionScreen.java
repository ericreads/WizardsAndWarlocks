import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class ShopSelectionScreen extends GameScreen {
	private Object[] purchasableItems;
	private ObjectShopButton[] itemButtons;

	private BufferedImage starterWandSprite;
	private BufferedImage spellSlingerSprite;
	private BufferedImage spellSprayerSprite;
	private BufferedImage starterWandIcon;
	private BufferedImage spellSlingerIcon;
	private BufferedImage spellSprayerIcon;
	private BufferedImage coin;
	private BufferedImage background;

	private Button backButton;
	private Font dogicaPixelBold;
	private Font buttonFont;
	private Color brown;
	private Color red;
	private static int width = 215;
	private static int height = 550;

	@Override
	public void initialize() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);

			// Wand Sprites
			starterWandSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127(rotated).png"));
			spellSlingerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130(rotated).png"));
			spellSprayerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129(rotated).png"));

			// Wand Icons
			starterWandIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127.png"));
			spellSlingerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130.png"));
			spellSprayerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129.png"));

			// Coin icon
			coin = ImageIO.read(getClass().getResourceAsStream("icons/coin.png"));

			// Background
			background = ImageIO.read(getClass().getResourceAsStream("ui/store_background.png"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		purchasableItems = new Object[] { new StarterWand(0, 0, starterWandSprite, starterWandIcon),
				new SpellSprayer(0, 0, spellSprayerSprite, spellSprayerIcon),
				new SpellSlinger(0, 0, spellSlingerSprite, spellSlingerIcon)};
		itemButtons = new ObjectShopButton[5];

		int j = 0;

		for (int i = 0; i < itemButtons.length; i++) {
			if (j < purchasableItems.length) {
				itemButtons[i] = new ObjectShopButton(i * (width + 30) + 30, 75, width, height, purchasableItems[j]);
			} else {
				itemButtons[i] = new ObjectShopButton(i * (width + 30) + 30, 75, width, height, null);
			}
			j++;
		}

		brown = new Color(63, 38, 49);
		red = new Color(232, 69, 55);

		buttonFont = dogicaPixelBold.deriveFont(Font.PLAIN, 20F);
		backButton = new BackButton(30, 45, "BACK", buttonFont, brown, red);

		SaveManager.getInstance().refreshVals();
	}

	@Override
	public void update(int deltaTime) {
		for (int i = 0; i < itemButtons.length; i++) {
			itemButtons[i].update();
		}
		backButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		// Draw background
		g.drawImage(background, 0, 0, null);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 20F));
		backButton.draw(g);

		buttonFont = dogicaPixelBold.deriveFont(Font.PLAIN, 16F);

		g.setFont(buttonFont);

		for (int i = 0; i < itemButtons.length; i++) {
			itemButtons[i].draw(g);
		}

		// Header
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 28F));
		g.setColor(brown);
		g.drawString("STORE", (int) (1265 / 2 - g.getFont().getStringBounds("STORE", frc).getWidth() / 2), 50);

		// Display money
		String money = String.valueOf(SaveManager.getInstance().getMoney());
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 18F));

		g.drawImage(coin, (int) (1130 - dogicaPixelBold.getStringBounds(money, frc).getWidth()), 25, 25, 25, null);
		g.drawString(money, (int) (1160 - dogicaPixelBold.getStringBounds(money, frc).getWidth()), 45); // right align
																										// text
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
		for (int i = 0; i < itemButtons.length; i++) {
			itemButtons[i].mouseClicked(e);
		}
		backButton.mouseClicked(e);
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
