// Import required modules
import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class TextBox 
{
	private String[] text;
	private BufferedImage image;
	
	private Font dogicaPixel;
	private Font dogicaPixelBold; 
	
	private Color brown;
	
	public TextBox(String[] text)
	{
		try 
		{
			InputStream is = getClass().getResourceAsStream("/fonts/dogicapixelbold.ttf");
			dogicaPixelBold = Font.createFont(Font.TRUETYPE_FONT, is);
			
			is = getClass().getResourceAsStream("/fonts/dogicapixel.ttf");
			dogicaPixel = Font.createFont(Font.TRUETYPE_FONT, is);
			
			image = ImageIO.read(getClass().getResourceAsStream("ui/text_box.png"));
		} 
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		
		this.text = text;
		brown = new Color(63, 38, 49);
	}
	
	public void setText(String[] text)
	{
		this.text = text;
	}
	
	public void draw(Graphics2D g)
	{
		g.setFont(dogicaPixel.deriveFont(Font.PLAIN, 14F));
		g.setColor(brown);

		g.drawImage(image, 400, 224, null);
		
		for (int i = 0; i < text.length; i++)
		{
			if (i == text.length - 1)
			{
				g.setFont(dogicaPixelBold.deriveFont(Font.PLAIN, 15F));
			}
			g.drawString(text[i], 430,  265 + (25 * i));
		}
	}
}
