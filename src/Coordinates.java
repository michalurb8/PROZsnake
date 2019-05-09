class Coordinates implements Cloneable
{
	public int xPos, yPos;
	public Coordinates()
	{
		xPos = 5;
		yPos = 0;	
	}
	public Coordinates(int xArg, int yArg)
	{
		xPos = xArg;
		yPos = yArg;
	}
	public Coordinates clone()
	{
		return new Coordinates(xPos, yPos);
	}
}