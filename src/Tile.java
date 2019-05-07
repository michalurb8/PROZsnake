class Tile
{
	private int xPos, yPos;
	public Tile()
	{
		xPos = 0;
		yPos = 0;	
	}
	public void SetCoordinates(int xArg, int yArg)
	{
		xPos = xArg;
		yPos = yArg;
	}
	public int GetX()
	{
		return xPos;
	}
	public int GetY()
	{
		return yPos;
	}
}
