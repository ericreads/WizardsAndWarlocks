import java.util.List;

public class SpellManager {

	private List<Spell> playerSpells;
	private List<Spell> enemySpells;
	private Player player;
	private Stage stage;
	
	public SpellManager(Stage stage, EnemyManager enemies, Player player)
	{
		this.stage = stage;
		this.enemySpells = enemies;
		this.player = player;
	}
	
	public void add(Spell spell, boolean playerFired)
	{
		
	}
	
}
