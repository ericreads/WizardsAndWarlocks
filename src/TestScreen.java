import java.awt.*;
import java.awt.event.KeyEvent;
//import javax.swing.*;

public class TestScreen extends GameScreen {

	Player player;
	Stage stage;
	Enemy[] enemies;
	
	@Override
	public void initialize() {
		
		stage = new Stage(0.15f);
		player = new Player(600, 370, stage);
		
		
		enemies = new Enemy[20];
		
		
		
		for (int i = 0; i < enemies.length; i++)
		{
			enemies[i] = new Enemy((i * 300), -100, player, stage);
		}
	}

	@Override
	public void update(int deltaTime) {
		player.update(deltaTime);
		
		for (Enemy enemy : enemies)
		{
			enemy.update(deltaTime);
		}

	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);
		stage.draw(g);
		
		for (Enemy enemy : enemies)
		{
			enemy.draw(g);
		}

		
		
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
		// TODO Auto-generated method stub

	}

}
