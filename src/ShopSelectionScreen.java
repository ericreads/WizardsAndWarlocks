import java.awt.Color;
import java.awt.Font;
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
	private ObjectShopButton[][] itemButtons;
	private BufferedImage starterWandSprite;
	private BufferedImage spellSlingerSprite;
	private BufferedImage spellSprayerSprite;
	private BufferedImage starterWandIcon;
	private BufferedImage spellSlingerIcon;
	private BufferedImage spellSprayerIcon;
	private Button backButton;
	private Font buttonFont;
	private static int dimensions = 100;
	@Override
	public void initialize() {
		buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		backButton = new BackButton(20, 25, "Back", buttonFont, Color.black, Color.red);
		try {
			//Wand Sprites
			starterWandSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127(rotated).png"));
			spellSlingerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130(rotated).png"));
			spellSprayerSprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129(rotated).png"));
			
			//Wand Icons
			starterWandIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0127.png"));
			spellSlingerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0130.png"));
			spellSprayerIcon = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_0129.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		purchasableItems = new Object[] {new StarterWand(0, 0, starterWandSprite, starterWandIcon), new SpellSlinger(0, 0, spellSlingerSprite, spellSlingerIcon), new SpellSprayer(0, 0, spellSprayerSprite, spellSprayerIcon)};
		itemButtons = new ObjectShopButton[680/(dimensions+10)-1][1200/(dimensions+10)];
		int k = 0;
		for(int i = 0; i < itemButtons.length; i++)
		{
			for(int j = 0; j < itemButtons[i].length; j++)
			{
				if(k < purchasableItems.length)
					itemButtons[i][j] = new ObjectShopButton(j*(dimensions+10)+80, i*(dimensions+10)+40, dimensions, purchasableItems[k]);
				else
					itemButtons[i][j] = new ObjectShopButton(j*(dimensions+10)+80, i*(dimensions+10)+40, dimensions, null);
				k++;
			}
		}
		SaveManager.getInstance().refreshVals();
	}

	@Override
	public void update(int deltaTime) {
		for(int i = 0; i < itemButtons.length; i++)
		{
			for(int j = 0; j < itemButtons[i].length; j++)
			{
				itemButtons[i][j].update();
			}
		}
		backButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		backButton.draw(g);
		g.setFont(buttonFont);
		g.setColor(Color.black);
		AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
		g.drawString("$"+SaveManager.getInstance().getMoney(), (int)(1100-buttonFont.getStringBounds("$"+SaveManager.getInstance().getMoney(), frc).getX()), 25);
		for(int i = 0; i < itemButtons.length; i++)
		{
			for(int j = 0; j < itemButtons[i].length; j++)
			{
				itemButtons[i][j].draw(g);
			}
		}
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
		for(int i = 0; i < itemButtons.length; i++)
		{
			for(int j = 0; j < itemButtons[i].length; j++)
			{
				itemButtons[i][j].mouseClicked(e);
			}
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
