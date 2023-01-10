import java.awt.event.*;
import java.awt.*;

public class TutorialGameplayScreen extends GameScreen {

	private Player player;
	private Stage stage;
	private HUD hud;
	
	private EnemyManager enemyManager;
	private SpellManager spellManager;
	
	@Override
	public void initialize() 
	{
		stage = new Stage(0.15f);
		player = new Player(600, 370, stage);
		hud = new HUD(player, 0);
		
		enemyManager = new EnemyManager(player, stage, hud, 1, 3000);
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
		
	}

	@Override
	public void draw(Graphics2D g) 
	{
		stage.draw(g);
        enemyManager.draw(g);
        player.draw(g);
        spellManager.draw(g);
        hud.draw(g);
        
        // Check if player should advance to regular gameplay
        if (enemyManager.shouldAdvance())
        {
        	GameScreenManager.getInstance().clearScreens();
        	GameScreenManager.getInstance().addScreen(new InstructionScreen(4));
        }
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
