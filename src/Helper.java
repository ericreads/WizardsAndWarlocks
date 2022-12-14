//A class for static helper functions
public class Helper {
	//Private constructor (can't be instanciated)
	private Helper()
	{
		
	}
	//Returns the angle in radians between two points
	public static double angleTowards(int x1, int y1, int x2, int y2)
	{
		int deltaX = x2-x1;
		int deltaY = y2-y1;
		
		return Math.atan2(deltaY, deltaX);
	}
	//Returns a unit vector between two points in an double array array[0] being x and array[1] being y
	public static double[] unitVector(int x1, int y1, int x2, int y2)
	{
		int deltaX = x2-x1;
		int deltaY = y2-y1;
		return new double[] {deltaX/Math.sqrt((double)deltaX*deltaX+deltaY*deltaY), deltaY/Math.sqrt((double)deltaX*deltaX + deltaY*deltaY)};
	}
	//Returns true if an integer is 1
	public static boolean intToBool(int a)
	{
		return a == 1;
	}
	//Return a 0 if false, 1 if true
	public static int boolToInt(boolean a)
	{
		if(a)
			return 1;
		else 
			return 0;
	}
}
