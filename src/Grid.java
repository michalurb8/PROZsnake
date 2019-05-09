import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.lang.Math;
class Grid
{
	private static final int XSIZE = 20;
	private static final int YSIZE = 20;
	private static int direction; // 0,1,2,3 - UP, RIGHT, DOWN, LEFT
	private Coordinates head;
	private Coordinates nextHead;
	private Coordinates fruit;
	private Random generator = new Random();
	private boolean eaten;
	private int growth;
	private int length;

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

		head = new Coordinates(2, 2);
		nextHead = new Coordinates(2, 3);

		array[2][2] = new Snake(true,false,2);
		array[2][1] = new Snake(false,false,1);
		array[2][0] = new Snake(false,true,0);
		
		direction = 1;

		fruit = new Coordinates(2, 4);

		growth = 0;
		length = 3;
		eaten = false;
		PlaceFruit();
	}
	public boolean Update(char choice)
	{
		if(direction != 0 && choice == 's') direction = 2;
		if(direction != 1 && choice == 'a') direction = 3;
		if(direction != 2 && choice == 'w') direction = 0;
		if(direction != 3 && choice == 'd') direction = 1;
		try
		{
			TimeUnit.MILLISECONDS.sleep(100);
		}
		catch(Exception xd){}
		if(CanStep() == false)
		{
			Dead();
			return false;
		}
		eaten = Eat();
		TakeStep();
		if(eaten) PlaceFruit();
		//direction = ((3+direction + (Math.abs(generator.nextInt()%3))))%4;
		PrintGame();
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
	private void Dead()
	{
		//System.out.println("ur ded");
	}
	public void PrintHead()
	{
		System.out.println("sznek: " + head.xPos + " " + head.yPos + ", Len: " + length + ", Frute: " + fruit.xPos + " " + fruit.yPos + ", Dir: " + direction);
	}
	public void PrintGame()
	{
		for(int j = 0; j < XSIZE + 2; ++j) System.out.print("X");
		System.out.println("");
		for(int i = YSIZE - 1; i > 0; --i)
		{
			System.out.print("X");
			for(int j = 0; j < XSIZE; ++j)
			{
				if(array[i][j] instanceof Empty) System.out.print(" ");
				if(array[i][j] instanceof Fruit)
				{
					Fruit temp = (Fruit)array[i][j];
					System.out.print(temp.GetNutr());
				}
				if(array[i][j] instanceof Snake)
				{

					Snake temp = (Snake)array[i][j];
					if(temp.IsHead()) System.out.print("Y");
					else if(temp.IsTail()) System.out.print("I");
					else System.out.print("B");
				}
			}
			System.out.println("X");
		}
		for(int j = 0; j < XSIZE + 2; ++j) System.out.print("X");
		System.out.println("");
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
			System.out.println("ur ded bc wall");
			return false;
		}
		if(array[nextHead.yPos][nextHead.xPos] instanceof Snake)
		{
			System.out.println("ur ded bc urself");
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
}
