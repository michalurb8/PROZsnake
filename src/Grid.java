import java.util.Random;
import java.lang.Math;
class Grid
{
	private static int XSIZE = 20;
	private static int YSIZE = 20;
	private static int direction; // 0,1,2,3 - UP, RIGHT, DOWN, LEFT
	private static int newDirection;
	private Coordinates head;
	private Coordinates nextHead;
	private Coordinates fruit;
	private Random generator = new Random();
	private boolean eaten;
	private int growth;
	private int length;

	private Tile[][] array;

	public Grid(int xArg, int yArg)
	{
		XSIZE = xArg;
		YSIZE = yArg;
		array = new Tile[YSIZE][XSIZE];
		for(int i = 0; i < YSIZE ; ++i)
		{
			for(int j = 0; j < XSIZE; ++j)
			{
				array[i][j] = new Empty();
			}
		}

		head = new Coordinates(2, 2);
		nextHead = new Coordinates(2, 3);

		array[2][2] = new Snake(true,false,2);
		array[2][1] = new Snake(false,false,1);
		array[2][0] = new Snake(false,true,0);
		
		direction = newDirection = 1;

		fruit = new Coordinates(2, 4);

		growth = 0;
		length = 3;
		eaten = false;
		PlaceFruit();
	}
	public boolean Update()
	{
	    direction = newDirection;
		if(!CanStep()) return false;
		eaten = Eat();
		TakeStep();
		if(eaten) PlaceFruit();
		return true;
	}
	private void TakeStep()
	{
		for(int i = 0; i < YSIZE; ++i)
		{
			for(int j = 0; j < XSIZE; ++j)
			{
				if(!(array[i][j] instanceof Snake)) continue;
				Snake temp = (Snake)array[i][j];
				if(temp.IsTail())
				{
					if(growth == 0)	array[i][j] = new Empty();
				}
				else if(temp.IsHead())
				{
					if(growth == 0) temp.Decrement();
					temp.SetHead(false);
				}
				else
				{
					if(growth == 0) temp.Decrement();
					if(temp.GetIndex()==0) temp.SetTail(true);
				}
			}
		}

		if(growth > 0) length++;
		array[nextHead.yPos][nextHead.xPos] = new Snake(true,false,length-1);
		if(growth > 0) growth--;
		head.xPos = nextHead.xPos;
		head.yPos = nextHead.yPos;
	}
	public void TryDir(int dirArg)
	{
		if ((dirArg - direction + 6) % 4 != 0) newDirection = dirArg;
	}
	private void PlaceFruit()
	{
		do
		{
			fruit.xPos = Math.abs(generator.nextInt() % XSIZE);
			fruit.yPos = Math.abs(generator.nextInt() % YSIZE);
		} while(!(array[fruit.yPos][fruit.xPos] instanceof Empty));
		array[fruit.yPos][fruit.xPos] = new Fruit();
	}
	private boolean CanStep()
	{
		nextHead = head.clone();
		switch(direction)
		{
			case 0:
				nextHead.xPos = head.xPos;
				nextHead.yPos = head.yPos+1;
				break;
			case 1:
				nextHead.xPos = head.xPos+1;
				nextHead.yPos = head.yPos;
				break;
			case 2:
				nextHead.xPos = head.xPos;
				nextHead.yPos = head.yPos-1;
				break;
			default:
				nextHead.xPos = head.xPos-1;
				nextHead.yPos = head.yPos;
		}
		if(nextHead.xPos < 0 || nextHead.xPos >= XSIZE || nextHead.yPos < 0 || nextHead.yPos >= YSIZE)
		{
			return false;
		}
		if(array[nextHead.yPos][nextHead.xPos] instanceof Snake)
		{
			Snake temp = (Snake) array[nextHead.yPos][nextHead.xPos];
			if(temp.IsTail()) return true;
			return false;
		}
		
		return true;
	}
	private boolean Eat()
	{
		if( ! (array[nextHead.yPos][nextHead.xPos] instanceof Fruit)) return false;
		Fruit temp = (Fruit) array[nextHead.yPos][nextHead.xPos];
		growth += temp.GetNutr();
		array[nextHead.yPos][nextHead.xPos] = new Empty();
		return true;
	}
	public Tile GetTile(int xArg, int yArg)
	{
		return array[yArg][xArg];
	}
}
