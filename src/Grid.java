class Grid
{
	private static final int XSIZE = 20;
	private static final int YSIZE = 20;
	
	Tile[][] array;

	public Grid()
	{
		array = new Tile[YSIZE][XSIZE];
	}
	public Tile GetElement(int xArg, int yArg)
	{
		return array[yArg][xArg];
	}
}
