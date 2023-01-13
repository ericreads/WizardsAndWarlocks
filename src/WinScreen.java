import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.*;

import javax.imageio.ImageIO;

public class WinScreen extends GameScreen {
	private TextBox textBox;
	private Color brown, black, tan, red;

	private Font dogicaPixelBold;
	private Font dogicaPixel;

	private int position = 0; 
	private int frames = 0;

	@Override
	public void initialize() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);

			is = getClass().getResourceAsStream("/fonts/dogicapixel.ttf");
			dogicaPixel = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (IOException | FontFormatException e) {
			System.out.println(e.toString());
		}

		brown = new Color(63, 38, 49);
		black = new Color(35, 35, 35);
		tan = new Color(207, 130, 84);
		red = new Color(232, 69, 55);

		textBox = new TextBox(null);
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
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);

		// Draw background
		g.setColor(new Color(35, 35, 35, 100));
		g.fillRect(0, 0, 1280, 680);

		// Draw text box
		String[] text = { " ", " " };
		textBox.setText(text);
		textBox.draw(g);

		// Draw text
		g.setFont(dogicaPixelBold.deriveFont(Font.PLAIN, 22F));
		g.setColor(brown);

		if (position == 0)
		{
			g.drawString("You Win!", (int) (632 - g.getFont().getStringBounds("You Win!", frc).getWidth() / 2), 280);

			g.setFont(dogicaPixel.deriveFont(Font.PLAIN, 14F));
			text[0] = "Congratulations! You defeated the King";
			text[1] = "and the citizens of Exalos are free.";

			for (int i = 0; i < text.length; i++) {
				g.drawString(text[i], (int) (632 - g.getFont().getStringBounds(text[i], frc).getWidth() / 2),
						325 + (25 * i));
			}
	    
			
		}
		else
		{
			g.drawString("Credits", (int) (632 - g.getFont().getStringBounds("Credits", frc).getWidth() / 2), 280);
			
			String[] names = {"Eric", "Dev", "Thaneesha"};
				
			g.setFont(dogicaPixel.deriveFont(Font.PLAIN, 14F));
			
			for (int i = 0; i < 3; i++)
			{
				g.drawString("Developer: " + names[i], 430, 310 + (i * 30) );
			}
		}
		
		g.setFont(dogicaPixelBold.deriveFont(Font.PLAIN, 15F));
		g.drawString("Press space to continue.",
				(int) (632 - g.getFont().getStringBounds("Press space to continue.", frc).getWidth() / 2),
				400);	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (position == 0)
			{
				position++;
			}
			else
			{
				GameScreenManager.getInstance().clearScreens();
				GameScreenManager.getInstance().addScreen(new MainMenu());
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
