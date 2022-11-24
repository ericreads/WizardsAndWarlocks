import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class SpellManager {

	private List<Spell> playerSpells;
	private List<Spell> enemySpells;
	private Player player;
	private Stage stage;
	
	public SpellManager(Stage stage, List enemies, Player player)
	{
		this.stage = stage;
		this.player = player;
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
		}
		
		for (int i = 0; i < enemySpells.size(); i++)
		{
		enemySpells.get(i).update(deltaTime);
		}
	}
}
