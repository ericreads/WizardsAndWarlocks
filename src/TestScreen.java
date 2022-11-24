import java.awt.*;
import java.awt.event.KeyEvent;

public class TestScreen extends GameScreen {

	GameplayManager gameManager;
	
	@Override
	public void initialize() {
		gameManager = new GameplayManager();
	}

	@Override
	public void update(int deltaTime) {
		gameManager.update(deltaTime);
	}

	@Override
	public void draw(Graphics2D g) {
		gameManager.draw(g);
	}
		

	@Override
	public void keyPressed(KeyEvent e) {
		gameManager.keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameManager.keyReleased(e);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
