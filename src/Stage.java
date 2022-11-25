//Import required modules
import java.awt.*;
import java.util.*;

public class Stage {
	// list to store the obstacles
	private Obstacle[][] obstacles;
	// Int to store the width and height of the obstacles
	private static int DIMENSION = 50;

	public Stage(float initialPercent) {
		// Initialize the obstacles list
		obstacles = new Obstacle[(1280 - DIMENSION) / DIMENSION][(720 - DIMENSION) / DIMENSION];

		// Create new obstacles for each array element
		for (int i = 0; i < obstacles.length; i++) {
			for (int j = 0; j < obstacles[i].length; j++) {
				obstacles[i][j] = new Obstacle(i * DIMENSION, j * DIMENSION, DIMENSION);
			}
		}
		// Randomize the blocks with the starting obstacle percent
		randomize(initialPercent);
	}

	// Method to randomize the blocks
	public void randomize(float obstaclePercent) {
		// Randomize the obstacles based on the obstacle percent
		for (int i = 0; i < obstacles.length; i++) {
			for (int j = 0; j < obstacles[i].length; j++) {

				if (Math.random() < obstaclePercent && i != 0 && i != obstacles.length-1 && j != 0 && j != obstacles[i].length-1)
					obstacles[i][j].enable();
				else
					obstacles[i][j].disable();

			}
		}
	}

	public void draw(Graphics2D g) {
		// Draw every obstacle in the array
		for (int i = 0; i < obstacles.length; i++) {
			for (int j = 0; j < obstacles[i].length; j++) {
				obstacles[i][j].draw(g);
			}
		}
	}

	// Method which gets the obstacles
	public Obstacle[][] getObstacles() {
		return obstacles;
	}
}
