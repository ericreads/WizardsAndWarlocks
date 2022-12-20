import java.io.*;

public class SaveManager {
	private File saveFile;
	private static SaveManager instance;
	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;
	private PrintWriter pw;
	private int money;
	private SaveManager()
	{
		try {
			saveFile = new File("data.csv");
			if(saveFile.createNewFile()) 
			{
				fw = new FileWriter(saveFile);
				pw = new PrintWriter(fw); 
				pw.println("0");
				money = 0;
				pw.close();
				fw.close();
			} else
			{
				fr = new FileReader(saveFile);
				br = new BufferedReader(fr);
				String[] vals = br.readLine().split(",");
				money = Integer.parseInt(vals[0]);
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
	public void saveVals()
	{
		try 
		{
			fw = new FileWriter(saveFile);
			pw = new PrintWriter(fw); 
			pw.print(money);
			pw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
	public void refreshVals()
	{
		try 
		{
			fr = new FileReader(saveFile);
			br = new BufferedReader(fr);
			if(saveFile.createNewFile()) 
			{
				pw.println("0");
				money = 0;
				pw.close();
			} else
			{
				String[] vals = br.readLine().split(",");
				money = Integer.parseInt(vals[0]);
			}
			br.close();
			fr.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
}
