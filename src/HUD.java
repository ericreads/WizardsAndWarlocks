import java.awt.*;
public class HUD {
	private Player player;
	private int curLevel;
	private Font font;
	private Weapon weapon;
	public HUD(Player player, int curLevel, Weapon weapon)
	{
		this.player = player;
		this.curLevel = curLevel;
		this.font = new Font(Font.SANS_SERIF, Font.PLAIN, 35);
		this.weapon = weapon;
	}
	//Set the wave number
	public void setLevel(int level)
	{
		curLevel = level;
	}
	
	
	public void draw(Graphics2D g)
	{
		//Ensure the bar is always 200 pixels no matter the player's health
		int segWidth = 200/player.getMaxHealth();
		//Draw the health bar
		for(int i = 0; i < player.getMaxHealth(); i++)
		{
			if(i < player.getHealth())
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.fillRect(25+i*segWidth, 25, segWidth, 20);
		}
		//Display the wave information
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString("Wave : " + curLevel, 1100, 45);
		
		// Display Spell Count
		g.setColor(Color.black);
		Font Spell_font = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
		g.setFont(Spell_font);
		g.drawString("Spells : " + weapon.getReload() + " /50", 1100, 645);
		
	}
}
