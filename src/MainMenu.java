import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class MainMenu extends GameScreen {

	private Button exitButton;
	private Button playButton;
	private Button shopButton;
	private Font buttonFont;
	private Font titleFont;
	
	@Override
	public void initialize() {
		titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 100);
		buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
		exitButton = new ExitButton(1280/2-(int)buttonFont.getStringBounds("Exit", frc).getWidth()/2, 600, "Exit", buttonFont, Color.blue.brighter(), Color.red);
		shopButton = new ShopButton(1280/2-(int)buttonFont.getStringBounds("Shop", frc).getWidth()/2, 500, "Shop", buttonFont, Color.blue.brighter(), Color.green);
		playButton = new PlayButton(1280/2-(int)buttonFont.getStringBounds("Play Game", frc).getWidth()/2, 400, "Play Game", buttonFont, Color.blue.brighter(), Color.green);
		SaveManager.getInstance().refreshVals();
	}

	@Override
	public void update(int deltaTime) {
		exitButton.update();
		shopButton.update();
		playButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(titleFont);
		g.drawString("Wizards And Warlocks", 125, 200);
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
