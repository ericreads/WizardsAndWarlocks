import java.awt.*;


import java.awt.event.*;
public class GameplayManager {
    private Player player;
    private Stage stage;
    private SpellManager spellManager;
    private EnemyManager enemyManager;
    private HUD hud;
   
    //Arrays to hold random stage values and the number of enemies spawned in each level and enemy spawn speeds
    private float[] stageRands = new float[] {0.25f, 0.15f, 0.10f, 0.05f, 0.25f};
    private int[] enemyLevels = new int[] {10, 20, 30, 40, 50, 75};
    private int[] enemyFreq = new int[] {2000, 1500, 1000, 500, 250};
    //Into to store the current level
    private int level = 0;
    public GameplayManager()
    {
        stage = new Stage(stageRands[0]);
		player = new Player(600, 370, stage);
		hud = new HUD(player, 1);
        enemyManager = new EnemyManager(player, stage, enemyLevels[0], enemyFreq[0]);
        spellManager = new SpellManager(stage, enemyManager, player);
        player.setSpellManager(spellManager);
        
       
    }
    public void update(int deltaTime)
    {
        player.update(deltaTime);
        enemyManager.update(deltaTime);
        spellManager.update(deltaTime);
        //Check if all enemies are dead and have been spawned, if yes advance the level
        if(enemyManager.shouldAdvance() && level + 1 < stageRands.length)
        {
            level++;
            hud.setLevel(level+1);
            stage.randomize(stageRands[level]);
            enemyManager.newWave(enemyLevels[level], enemyFreq[level]);
            player.reset();
        }
        
        
    }
    
    public void draw(Graphics2D g)
    {
    	//Draw all the actors
        stage.draw(g);
        player.draw(g);
        enemyManager.draw(g);
        spellManager.draw(g);
        hud.draw(g);
    }
    public void keyPressed(KeyEvent e)
    {
        player.keyPressed(e);
    }
    public void keyReleased(KeyEvent e)
    {
        player.keyReleased(e);
    }
    public void mouseClicked(MouseEvent e)
    {
    	
    }
    public void mousePressed(MouseEvent e)
    {
    	player.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e)
    {
    	player.mouseReleased(e);
    }
}
