import java.awt.*;
import java.awt.event.*;

public class TestScreen extends GameScreen {

	GameplayManager gameManager;
	
	@Override
	public void initialize() {
		gameManager = new GameplayManager();
	}

	@Override
	public void update(int deltaTime){
		try {
			gameManager.update(deltaTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		gameManager.mouseClicked(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		gameManager.mousePressed(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		gameManager.mouseReleased(e);
	}

}
