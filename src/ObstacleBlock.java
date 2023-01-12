
public class ObstacleBlock {

	private Obstacle[][] obstacleBlock; // two-dimensional array contains the arrangement of nodes to from an obstacle
	
	private int x;
	private int y;
	private int dimension; 
	
	public ObstacleBlock(int x, int y, int dimension) {
		obstacleBlock = new Obstacle[4][4];
		
		for (int i = 0; i < obstacleBlock.length; i++) {
			for (int j = 0; j < obstacleBlock[i].length; j++) {
				obstacleBlock[i][j] = new Obstacle(x + (i * dimension), y + (j * dimension), dimension);
			}
		}
	}
	
	public void setObstacles() {
		// obstacle 1
		
		
	}
	
	
}
