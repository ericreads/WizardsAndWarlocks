import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.*;

import javax.imageio.ImageIO;

public class WinScreen extends GameScreen
{
	private Font dogicaPixelBold;
	private Font dogicaPixel;
	private Font buttonFont;
	
	private Button mainMenuButton;
	private Button exitButton;
	
	private Color brown, black, tan, red;

	private BufferedImage background;
	private int frames = 0;

	@Override
	public void initialize() 
	{
		try
		{
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			is = getClass().getResourceAsStream("/fonts/dogicapixel.ttf");
			dogicaPixel = Font.createFont(Font.TRUETYPE_FONT, is);
			
			background = ImageIO.read(getClass().getResourceAsStream("/ui/main_menu.png"));
		}
		catch (IOException | FontFormatException e)
		{
			System.out.println(e.toString());
		}
		
		AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
		
		brown = new Color(63, 38, 49);
		black = new Color(35, 35, 35);
		tan = new Color(207, 130, 84);
		red = new Color(232, 69, 55);
		
		buttonFont = dogicaPixelBold.deriveFont(Font.PLAIN, 36F);
		
		mainMenuButton = new MainMenuButton(1265 / 2 - (int) buttonFont.getStringBounds("MAIN MENU", frc).getWidth() / 2, 425,
				"MAIN MENU", buttonFont, black, brown);
		exitButton = new ExitButton(1265 / 2 - (int) buttonFont.getStringBounds("EXIT", frc).getWidth() / 2, 500,
				"EXIT", buttonFont, black, red);
	}

	@Override
	public void update(int deltaTime) {
		if (frames >= 360) {
			frames = 0;
		} else {
			frames++;
		}
		
		mainMenuButton.update();
		exitButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// Draw background
		g.drawImage(background, 0, 0, null);

		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		g.setFont(dogicaPixelBold);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 56F));

		// Display title
		String title = "You Won!";

		g.setColor(brown);
		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2, 300);
		
		// Draw buttons
		mainMenuButton.draw(g);
		exitButton.draw(g);
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
		mainMenuButton.mouseClicked(e);
		exitButton.mouseClicked(e);
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
