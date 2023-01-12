import java.io.*;

public class SaveManager 
{
	private File saveFile;
	private static SaveManager instance;
	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;
	private PrintWriter pw;
	private int money;
	private int playerHealth;
	private int numHealthPotions;
	private boolean hasSpellSprayer;
	private boolean hasSpellSlinger;
	private SaveManager()
	{
		try {
			saveFile = new File("data.csv");
			if(saveFile.createNewFile()) 
			{
				fw = new FileWriter(saveFile);
				pw = new PrintWriter(fw); 
				pw.println("0,0,0,100,0");
				money = 0;
				hasSpellSlinger = false;
				hasSpellSprayer = false;
				playerHealth = 100;
				numHealthPotions = 0;
				pw.close();
				fw.close();
			} else
			{
				fr = new FileReader(saveFile);
				br = new BufferedReader(fr);
				String[] vals = br.readLine().split(",");
				money = Integer.parseInt(vals[0]);
				hasSpellSlinger = Helper.intToBool(Integer.parseInt(vals[1]));
				hasSpellSprayer = Helper.intToBool(Integer.parseInt(vals[2]));
				playerHealth = Integer.parseInt(vals[3]);
				numHealthPotions = Integer.parseInt(vals[4]);
				br.close();
				fr.close();
			}
		} catch (IOException e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	public static SaveManager getInstance()
	{
		if(instance == null)
		{
			instance = new SaveManager();
		}
		return instance;
	}
	public void setMoney(int money)
	{
		this.money = money;
	}
	public int getMoney()
	{
		return money;
	}

	public void buySpellSlinger() 
	{
		hasSpellSlinger = true;
	}

	public void buySpellSprayer() 
	{
		hasSpellSprayer = true;
	}

	public boolean hasSpellSlinger() 
	{
		return hasSpellSlinger;
	}

	public boolean hasSpellSprayer() 
	{
		return hasSpellSprayer;
	}
	//Save values stored in fields to save file
	public void saveVals()
	{
		try 
		{
			fw = new FileWriter(saveFile);
			pw = new PrintWriter(fw); 
			pw.print(money + "," + Helper.boolToInt(hasSpellSlinger) + "," + Helper.boolToInt(hasSpellSprayer) + "," + playerHealth + "," + numHealthPotions);
			pw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
	//Update values stored in fields to reflect save file
	public void refreshVals()
	{
		try 
		{
			fr = new FileReader(saveFile);
			br = new BufferedReader(fr);
			if(saveFile.createNewFile()) 
			{
				pw.println("0,0,0,100,0");
				money = 0;
				hasSpellSlinger = false;
				hasSpellSprayer = false;
				playerHealth = 100;
				numHealthPotions = 0;
				pw.close();
			} 
			else
			{
				String[] vals = br.readLine().split(",");
				money = Integer.parseInt(vals[0]);
				hasSpellSlinger = Helper.intToBool(Integer.parseInt(vals[1]));
				hasSpellSprayer = Helper.intToBool(Integer.parseInt(vals[2]));
				playerHealth = Integer.parseInt(vals[3]);
				numHealthPotions = Integer.parseInt(vals[4]);
			}
			br.close();
			fr.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
	public int getPlayerHealth() {
		return playerHealth;
	}
	public void setPlayerHealth(int health)
	{
		playerHealth = health;
	}
	public boolean isEmpty()
	{
		return ((money == 0) && !hasSpellSlinger && !hasSpellSprayer && playerHealth == 100);
	}
	public int getHealthPotionNum() { return numHealthPotions; }
	public void setHealthPotionNum(int num) { numHealthPotions = num; }
}
