import java.awt.event.*;
import java.awt.*;

public class TutorialGameplayScreen extends GameScreen {

	private Player player;
	private Stage stage;
	private HUD hud;
	
	private EnemyManager enemyManager;
	private SpellManager spellManager;
	
	private int frames = 0;
	
	@Override
	public void initialize() 
	{
		stage = new Stage(0.15f);
		player = new Player(600, 370, stage);
		hud = new HUD(player, 0);
		
		enemyManager = new EnemyManager(player, stage, hud, 4, 3000);
		spellManager = new SpellManager(stage, enemyManager, player);
		
		player.setSpellManager(spellManager);
	}

	@Override
	public void update(int deltaTime) 
	{
		player.update(deltaTime);
        enemyManager.update(deltaTime);
        spellManager.update(deltaTime);
		hud.update(deltaTime);	
		
		if (player.getHealth() <= 0)
		{
			GameScreenManager.getInstance().addScreen(new InstructionScreen(4));
		}
		
		// Check if player should advance to regular game-play
        if (enemyManager.shouldAdvance())
        {
        	if ((frames++) > 60)
        	{
        		frames = 0;
        		GameScreenManager.getInstance().addScreen(new InstructionScreen(5));
        	}
        }
	}

	@Override
	public void draw(Graphics2D g) 
	{
		stage.draw(g);
        enemyManager.draw(g);
        player.draw(g);
        spellManager.draw(g);
        hud.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		player.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		player.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		player.mouseReleased(e);
    	hud.mouseReleased(e);
	}
}
