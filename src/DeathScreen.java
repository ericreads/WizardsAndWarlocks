import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.*;

import javax.imageio.ImageIO;

public class DeathScreen extends GameScreen
{
	private String[] dialogue;
	private String[] text; 
	
	private Font dogicaPixelBold;
	private Font dogicaPixel;
	
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
		
		// Initialize dialogue array
		dialogue = new String[5];
		
		dialogue[0] = "I will avenge my grandfather!/nThis does not end here.";
		dialogue[1] = "We mages have suffered this/nopression for too long.";
		dialogue[2] = "The fight is not over./nI will be back.";
		dialogue[3] = "I cannot let them win!/nI will not back down.";
		dialogue[4] = "You'll see, I can use magic/njust as well as you.";
		
		// Set text as a random dialogue piece
		text = dialogue[(int)(Math.random() * 4)].split("/n");
		
		AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
		
		brown = new Color(63, 38, 49);
		black = new Color(35, 35, 35);
		tan = new Color(207, 130, 84);
		red = new Color(232, 69, 55);
	}

	@Override
	public void update(int deltaTime) {
		if (frames >= 360) {
			frames = 0;
		} else {
			frames++;
		}
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
		String title = "You Died!";

		g.setColor(brown);
		g.drawString(title, 1265 / 2 - (int) g.getFont().getStringBounds(title, frc).getWidth() / 2, 310);

		// Display dialogue
		g.setFont(dogicaPixel);
		g.setFont(g.getFont().deriveFont(Font.ITALIC, 20F));
		
		for (int i = 0; i < 2; i++)
		{
			g.drawString(text[i], 1265 / 2 - (int) g.getFont().getStringBounds(text[i], frc).getWidth() / 2, 385 + (i * 28));
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
		GameScreenManager.getInstance().clearScreens();
		GameScreenManager.getInstance().addScreen(new MainMenu());
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
