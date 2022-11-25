import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
public class EnemyManager {
    private ArrayList<Enemy> enemies;
    private Stage stage;
    private Player player;
    private int enemyFreq;
    private int enemyCount;
    private int enemiesSpawned;
    private long counter;

    public EnemyManager(Player player, Stage stage, int enemyCount)
    {
        this.player = player;
        this.stage = stage;
        this.counter = 0;
        this.enemyFreq = 2000;
        this.enemyCount = enemyCount;
        this.enemiesSpawned = 0;
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(-100, 0, player, stage));
    }
    public void update(int deltaTime)
    {
        counter += deltaTime;
        if(counter > enemyFreq && enemiesSpawned < enemyCount)
        {
        	enemiesSpawned++;
            counter = 0;
            double randValue = Math.random();
            if(randValue < 0.25)
            {
                enemies.add(new Enemy((int)(randValue * 1280), -70, player, stage));
            } else if(randValue < 0.5)
            {
                enemies.add(new Enemy((int)(randValue*1280), 790, player,stage));
            } else if(randValue < 0.75)
            {
                enemies.add(new Enemy(-70, (int)(randValue*720), player, stage));
            } else
            {
                enemies.add(new Enemy(1350, (int)(randValue*720), player, stage));
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
    public void newWave(int count)
    {
        enemyCount = count;
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
