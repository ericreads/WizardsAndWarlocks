import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameplayScreen extends GameScreen {

	private Player player;
	private Stage stage;
	@Override
	public void initialize() {
		stage = new Stage(0.1f);
		player = new Player(500, 500, stage);
	}

	@Override
	public void update(int deltaTime) {
		player.move(deltaTime);
	}

	@Override
	public void draw(Graphics2D g) {
		stage.draw(g);
		player.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
