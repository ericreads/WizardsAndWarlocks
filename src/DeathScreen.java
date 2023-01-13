import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.*;

import javax.imageio.ImageIO;

public class DeathScreen extends GameScreen {
	private String[] dialogue;
	private String[] message;
	
	private TextBox textBox;
	private Color brown, black, tan, red;

	private Font dogicaPixelBold;
	private Font dogicaPixel;

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
		
		// Initialize dialogue array
		dialogue = new String[5];
		
		dialogue[0] = "\"I will avenge my grandfather!/nThis does not end here.\"";
		dialogue[1] = "\"We mages have suffered this/nopression for too long.\"";
		dialogue[2] = "\"The fight is not over./nI will be back.\"";
		dialogue[3] = "\"I cannot let them win!/nI will not back down.\"";
		dialogue[4] = "\"You'll see, I can use magic/njust as well as you.\"";
		
		// Set text as a random dialogue piece
		message = dialogue[(int)(Math.floor(Math.random() * 5))].split("/n");
		
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
		String[] text = {" ", " " };
		textBox.setText(text);
		textBox.draw(g);

		// Draw text
		g.setFont(dogicaPixelBold.deriveFont(Font.PLAIN, 22F));
		g.setColor(brown);

		g.drawString("You Died!", (int) (632 - g.getFont().getStringBounds("You Died!", frc).getWidth() / 2), 285);

		g.setFont(dogicaPixel.deriveFont(Font.ITALIC, 14F));

		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], (int) (632 - g.getFont().getStringBounds(message[i], frc).getWidth() / 2),
					325 + (25 * i));
		}
		
		g.setFont(dogicaPixelBold.deriveFont(Font.PLAIN, 13F));
		g.drawString("Press space to return to main menu.",
				(int) (632 - g.getFont().getStringBounds("Press space to return to main menu.", frc).getWidth() / 2),
				390);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			GameScreenManager.getInstance().clearScreens();
			GameScreenManager.getInstance().addScreen(new MainMenu());
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
