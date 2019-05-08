import java.util.concurrent.TimeUnit;
class Grid
{
	private static final int XSIZE = 20;
	private static final int YSIZE = 20;
	private static int direction = 0; // 0,1,2,3 - UP, RIGHT, DOWN, LEFT
	private Coordinates head;

	Tile[][] array;

	public Grid()
	{
		array = new Tile[YSIZE][XSIZE];
		for(int i = 0; i < YSIZE ; ++i)
		{
			for(int j = 0; j < XSIZE; ++j)
			{
				array[i][j] = new Empty();
			}
		}
		array[0][0] = new Fruit();
		head = new Coordinates(2, 2);
	}
	public boolean Update()
	{
		try
		{
			TimeUnit.MICROSECONDS.sleep(100);
		}
		catch(Exception xd){}

		if(IsNextEmpty() == false)
		{
			Dead();
			return false;
		}
		Advance();
		direction = (direction+3) % 5 % 4; //up left right up left right ...
		PrintHead();
		return true;
	}
	private void Advance()
	{
		if(direction == 0) head.yPos++;
		else if(direction == 1) head.xPos++;
		else if(direction == 2) head.yPos--;
		else head.xPos--;
	}

	public Tile GetElement(int xArg, int yArg)
	{
		return array[yArg][xArg];
	}
	private void Dead()
	{
		System.out.println("ur ded");
	}

	public void PrintHead()
	{
		System.out.println(head.xPos + " " + head.yPos);
	}

	private boolean IsNextEmpty()
	{
		if(direction == 0)
		{
			if(head.yPos + 1 < 0 || head.yPos + 1 >= YSIZE) return false;
			if(array[head.yPos + 1][head.xPos].isEmpty()) return true;
			return false;
		}
		if(direction == 1)
		{
			if(head.xPos + 1 < 0 || head.xPos + 1 >= XSIZE) return false;
			if(array[head.yPos][head.xPos + 1].isEmpty()) return true;
			return false;
		}
		if(direction == 2)
		{
			if(head.yPos - 1 < 0 || head.yPos - 1 >= YSIZE) return false;
			if(array[head.yPos - 1][head.xPos].isEmpty()) return true;
			return false;
		}
		else
		{
			if(head.xPos - 1 < 0 || head.xPos - 1 >= XSIZE) return false;
			if(array[head.yPos][head.xPos - 1].isEmpty()) return true;
			return false;
		}
	}
}
