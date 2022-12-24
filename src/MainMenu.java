import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class MainMenu extends GameScreen {

	private Button exitButton;
	private Button playButton;
	private Button shopButton;
	
	private Font buttonFont;
	private Font dogicaPixelBold;
	
	private int y = 230;
	private int frames = 0;

	private BufferedImage background;
	
	private Color brown, tan, black, red;
	
	@Override
	public void initialize() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			background = ImageIO.read(getClass().getResourceAsStream("/ui/main_menu.png"));
		} 
		catch (FontFormatException | IOException e) {
			System.out.println(e.toString());
		}

		buttonFont = dogicaPixelBold.deriveFont(Font.PLAIN, 36F);
	
		brown = new Color(63, 38, 49);
		tan = new Color(207, 130, 84);
		black = new Color(35, 35, 35);
		red = new Color(232, 69, 55);
		
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		
		exitButton = new ExitButton(1265 / 2 - (int) buttonFont.getStringBounds("EXIT", frc).getWidth() / 2, 550,
				"EXIT", buttonFont, black, red);
		shopButton = new ShopButton(1265 / 2 - (int) buttonFont.getStringBounds("SHOP", frc).getWidth() / 2, 475,
				"SHOP", buttonFont, black, brown);
		playButton = new PlayButton(1265 / 2 - (int) buttonFont.getStringBounds("PLAY", frc).getWidth() / 2, 400,
				"PLAY", buttonFont, black, brown);
		
		SaveManager.getInstance().refreshVals();
	}

	@Override
	public void update(int deltaTime) {
		exitButton.update();
		shopButton.update();
		playButton.update();

		if (frames >= 360) {
			frames = 0;
		} else {
			frames++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		
		g.setFont(dogicaPixelBold);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 56F));

		String title = "Wizards &";
		
		g.setColor(tan);
		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2 + 5,
				(int) (12 * Math.sin(Math.toRadians(frames)) + y) + 5);
	
		g.setColor(brown);
		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2,
				(int) (12 * Math.sin(Math.toRadians(frames)) + y));
		
		title = "Warlocks";
		
		g.setColor(tan);
		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2 + 5,
				(int) (12 * Math.sin(Math.toRadians(frames)) + y) + 75 + 5);
		
		g.setColor(brown);
		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2,
				(int) (12 * Math.sin(Math.toRadians(frames)) + y) + 75);
				
		exitButton.draw(g);
		shopButton.draw(g);
		playButton.draw(g);
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
		exitButton.mouseClicked(e);
		shopButton.mouseClicked(e);
		playButton.mouseClicked(e);
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
