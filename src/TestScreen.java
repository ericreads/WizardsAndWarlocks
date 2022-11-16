//Import required modules
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

//This class is just a test screen to demonstrate the functionality of GameScreen and GameScreenManager
public class TestScreen extends GameScreen {

	private int x;
	private int y;
	private float vx;
	private float vy;
	
	@Override
	public void initialize() {
		//Initialize values needed for ball movement
		x = 100;
		y = 100;
		vx = 0.3f;
		vy = 0.3f;
	}

	@Override
	public void update(int deltaTime) {
		//Ball animation logic
		x += (int)(vx*(float)deltaTime);
		y += (int)(vy*(float)deltaTime);
		if(x > 1280-50 || x < 0)
		{
			vx = -vx;
		}
		if(y > 720-50 || y < 0)
		{
			vy = -vy;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		//Draw a white background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1280, 720);
		//Draw a ball
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Nothing for now

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Nothing for now

	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Nothing for now

	}

}
