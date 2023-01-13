import java.awt.Graphics;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public class EnemyManager {
    private ArrayList<Enemy> enemies;
    private Stage stage;
    private Player player;
    private int enemyFreq;
    private int enemyCount;
    private int enemiesSpawned;
    private long counter;
    private HUD hud;

    private BufferedImage[] idle;
	private BufferedImage[] front_walk;
	private BufferedImage[] back_walk;
	private BufferedImage[] left_walk;
	private BufferedImage[] right_walk;
    
    public EnemyManager(Player player, Stage stage, HUD hud, int enemyCount, int enemyFreq)
    {
    	// Load sprites
    			idle = new BufferedImage[4];
    			front_walk = new BufferedImage[8];
    			back_walk = new BufferedImage[8];
    			left_walk = new BufferedImage[8];
    			right_walk = new BufferedImage[8];
    			
    			try 
    			{
    				// Player idle
    				idle[0] = ImageIO.read(getClass().getResourceAsStream("/enemy_sprites/elf_front_idle.png"));
    				idle[1] = ImageIO.read(getClass().getResourceAsStream("/enemy_sprites/elf_back_idle.png"));
    				idle[2] = ImageIO.read(getClass().getResourceAsStream("/enemy_sprites/elf_side02_idle.png")); // left
    				idle[3] = ImageIO.read(getClass().getResourceAsStream("/enemy_sprites/elf_side01_idle.png")); // right

    				// Player running forward
    				for (int i = 0; i < 8; i++) 
    				{
    					front_walk[i] = ImageIO
    							.read(getClass().getResourceAsStream("/enemy_sprites/elf_front_walk" + (i + 1) + ".png"));
    				}

    				// Player running back
    				for (int i = 0; i < 8; i++) 
    				{
    					back_walk[i] = ImageIO
    							.read(getClass().getResourceAsStream("/enemy_sprites/elf_back_walk" + (i + 1) + ".png"));
    				}

    				// Player running left
    				for (int i = 0; i < 8; i++) 
    				{
    					left_walk[i] = ImageIO
    							.read(getClass().getResourceAsStream("/enemy_sprites/elf_side02_walk" + (i + 1) + ".png"));
    				}
    				
    				// Player running right
    				for (int i = 0; i < 8; i++) 
    				{
    					right_walk[i] = ImageIO
    							.read(getClass().getResourceAsStream("/enemy_sprites/elf_side01_walk" + (i + 1) + ".png"));
    				}
    			} 
    			catch (IOException e) {
    				System.out.println(e.toString());
    			} 
        this.player = player;
        this.stage = stage;
        this.counter = 0;
        this.enemyFreq = enemyFreq;
        this.enemyCount = enemyCount;
        this.enemiesSpawned = 0;
        enemies = new ArrayList<Enemy>();
        this.hud = hud;
        enemies.add(new Enemy(-100, 0, player, stage, hud, idle, front_walk, back_walk, left_walk, right_walk));
    }
    public void update(int deltaTime)
    {
    	
        counter += deltaTime;
        if(counter > enemyFreq && enemiesSpawned < enemyCount)
        {
        	enemiesSpawned++;
            counter = 0;
            double randValue = Math.random();
            //Randomly spawn enemies on each side of the screen
            if(randValue < 0.25)
            {
				enemies.add(new Enemy((int) (randValue * 1264), -70, player, stage, hud,  idle, front_walk, back_walk, left_walk, right_walk));
            } 
            else if(randValue < 0.5)
            {
				enemies.add(new Enemy((int) (randValue*1264), 790, player,stage, hud,  idle, front_walk, back_walk, left_walk, right_walk));
            } 
            else if(randValue < 0.75)
            {
				enemies.add(new Enemy(-70, (int) (randValue*648), player, stage, hud,  idle, front_walk, back_walk, left_walk, right_walk));
            } 
            else
            {
				enemies.add(new Enemy(1350, (int) (randValue*648), player, stage, hud,  idle, front_walk, back_walk, left_walk, right_walk));
            }
        }
        for(Enemy enemy : enemies)
        {
            enemy.update(deltaTime);
        }
        for(int i = 0; i < enemies.size(); i++)
        {
            if(enemies.get(i).getDead())
            {
                enemies.remove(enemies.get(i));
                i--;
            }
        }
    }
    public void newWave(int count, int freq)
    {
        enemyCount = count;
        enemyFreq = freq;
        enemiesSpawned = 0;
    }
    public boolean shouldAdvance()
    {
    	return enemiesSpawned >= enemyCount && enemies.size() <= 0;
    }
    public void draw(Graphics2D g)
    {
        for(Enemy enemy : enemies)
        {
            enemy.draw(g);
        }
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    } 
}
