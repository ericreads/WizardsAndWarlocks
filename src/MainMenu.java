import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainMenu extends GameScreen {

	private Button exitButton;
	private Button playButton;
	private Font buttonFont;
	private Font titleFont;
	
	@Override
	public void initialize() {
		titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 100);
		buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		exitButton = new ExitButton(1280/2-75+50, 500, "Exit", buttonFont, Color.blue.brighter(), Color.red);
		playButton = new PlayButton(1280/2-150+50, 400, "Play Game", buttonFont, Color.blue.brighter(), Color.green);
	}

	@Override
	public void update(int deltaTime) {
		exitButton.update();
		playButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(titleFont);
		g.drawString("Wizards And Warlocks", 125, 200);;
		exitButton.draw(g);
		playButton.draw(g);;
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
