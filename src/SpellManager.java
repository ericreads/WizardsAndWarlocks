import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SpellManager {
	private EnemyManager enemies;
	private ArrayList<Spell> playerSpells;
	private ArrayList<Spell> enemySpells;
	private Player player;
	private Stage stage;
	
	public SpellManager(Stage stage, EnemyManager enemies, Player player)
	{
		this.stage = stage;
		this.player = player;
		this.enemies = enemies;
		playerSpells = new ArrayList<Spell>();
		enemySpells = new ArrayList<Spell>();
	}
	
	public void add(Spell spell, boolean playerFired)
	{
		
		if (playerFired)
		{
			playerSpells.add(spell);
		}
		
		else if (!playerFired)
		{
			enemySpells.add(spell);
		}
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < playerSpells.size(); i++)
		{
		playerSpells.get(i).draw(g2d);
		}
		
		for (int i = 0; i < enemySpells.size(); i++)
		{
		enemySpells.get(i).draw(g2d);
		}
		
	}
	
	public void update(int deltaTime)
	{
		
		for (int i = 0; i < playerSpells.size(); i++)
		{
			playerSpells.get(i).update(deltaTime);
			for(Enemy enemy : enemies.getEnemies())
			{
				if(playerSpells.get(i).getBounds().intersects(enemy.getPosition()))
				{
					enemy.takeDamage(playerSpells.get(i).getDamage());
					playerSpells.get(i).kill();
				}
			}
			for(int j = 0; j < stage.getObstacles().length; j++)
			{
				for(int k = 0; k < stage.getObstacles()[j].length; k++)
				{
					if(stage.getObstacles()[j][k].getEnabled() && stage.getObstacles()[j][k].getBounds().intersects(playerSpells.get(i).getBounds()))
						playerSpells.get(i).kill();
				}
			}
		}
		for (int i = 0; i < playerSpells.size(); i++)
		{
			if(playerSpells.get(i).getIsDead())
			{
				playerSpells.remove(playerSpells.get(i));
				i--;
			}
		}
		for (int i = 0; i < enemySpells.size(); i++)
		{
			enemySpells.get(i).update(deltaTime);
			if(enemySpells.get(i).getBounds().intersects(player.getPosition()))
			{
				player.takeDamage(enemySpells.get(i).getDamage());
				enemySpells.get(i).kill();
			}
			for(int j = 0; j < stage.getObstacles().length; j++)
			{
				for(int k = 0; k < stage.getObstacles()[j].length; k++)
				{
					if(stage.getObstacles()[j][k].getEnabled() && stage.getObstacles()[j][k].getBounds().intersects(enemySpells.get(i).getBounds()))
						enemySpells.get(i).kill();
				}
			}
		}
		for (int i = 0; i < enemySpells.size(); i++)
		{
			if(enemySpells.get(i).getIsDead())
			{
				enemySpells.remove(enemySpells.get(i));
				i--;
			}
		}
	}
}
