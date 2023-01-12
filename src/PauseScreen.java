import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class PauseScreen extends GameScreen 
{
	private Button exitButton;
	private TextBox textBox;
	
	private Font dogicaPixelBold;
	private Font buttonFont; 
	
	private BufferedImage play1, play2, play3;
	private BufferedImage home1, home2, home3;
	private BufferedImage pause1;
	
	private boolean playPressed = false;
	private boolean homePressed = false; 
	private int frames = 0; 	
	
	private Color brown, black, red;
	
	@Override
	public void initialize() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			play1 = ImageIO.read(getClass().getResourceAsStream("/ui/play1.png"));
			play2 = ImageIO.read(getClass().getResourceAsStream("/ui/play2.png"));
			play3 = ImageIO.read(getClass().getResourceAsStream("/ui/play3.png"));
			
			home1 = ImageIO.read(getClass().getResourceAsStream("/ui/home1.png"));
			home2 = ImageIO.read(getClass().getResourceAsStream("/ui/home2.png"));
			home3 = ImageIO.read(getClass().getResourceAsStream("/ui/home3.png"));
			
			pause1 = ImageIO.read(getClass().getResourceAsStream("/ui/pause1.png"));
		} 
		catch (FontFormatException | IOException e) {
			System.out.println(e.toString());
		}
		
		String[] text = {" "};
		textBox = new TextBox(text);
		
		buttonFont = dogicaPixelBold.deriveFont(Font.PLAIN, 24F);

		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		brown = new Color(63, 38, 49);
		black = new Color(20, 20, 20);
		red = new Color(232, 69, 55);
		
		exitButton = new ExitButton(1265 / 2 - (int) buttonFont.getStringBounds("EXIT TO DESKTOP", frc).getWidth() / 2, 375,
				"EXIT TO DESKTOP", buttonFont, black, brown);
	}

	@Override
	public void update(int deltaTime) {
		if (playPressed || homePressed)
		{
			frames++;
		}
		if (frames > 15)
		{
			frames = 0;
			
			if (homePressed)
			{
				GameScreenManager.getInstance().clearScreens();
				GameScreenManager.getInstance().addScreen(new MainMenu());
			}
			else if (playPressed)
			{
				playPressed = false;
				GameScreenManager.getInstance().clearScreen();
			}
		}
		
		exitButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		
		g.setColor(new Color(35, 35, 35, 100));
		g.fillRect(0, 0, 1280, 680);
		
		textBox.draw(g);
		
		g.setFont(dogicaPixelBold);
		g.setColor(red);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 42F));

		String title = "PAUSED";

		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2,
				320);
		
		
		// Draw play button
		if (!playPressed)
		{
			g.drawImage(play1, 1135, 10, 45, 45, null);
		}
		else
		{
			if (frames < 5)
			{
				g.drawImage(play2, 1135, 10, 45, 45, null);
			}
			else if (frames < 10)
			{
				g.drawImage(play3, 1135, 10, 45, 45, null);
			}
			else
			{
				g.drawImage(pause1, 1135, 10, 45, 45, null);
			}
		}
		
		// Draw home button
		if (!homePressed)
		{
			g.drawImage(home1, 1193, 10, 45, 45, null);
		}
		else 
		{
			if (frames < 5)
			{
				g.drawImage(home2, 1193, 10, 45, 45, null);
			}
			else if (frames < 10)
			{
				g.drawImage(home3, 1193, 10, 45, 45, null);
			}
			else
			{
				g.drawImage(home1, 1193, 10, 45, 45, null);
			}
		}
		
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
		exitButton.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (GameJPanel.getMouseX() > 1135 && GameJPanel.getMouseX() < 1180 
				&& GameJPanel.getMouseY() > 10 && GameJPanel.getMouseY() < 55)
		{
			playPressed = true;
		}
		else if (GameJPanel.getMouseX() > 1193 && GameJPanel.getMouseX() < 1238 
				&& GameJPanel.getMouseY() > 10 && GameJPanel.getMouseY() < 55)
		{
			homePressed = true; 
		}
		
	}

}
