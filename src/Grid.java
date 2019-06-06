import java.util.Random;
import java.lang.Math;
class Grid
{
	private int XSIZE;
	private int YSIZE;
	private int scale;
	private int direction; // 0,1,2,3 - UP, RIGHT, DOWN, LEFT
	private int newDirection;
	private Coordinates head;
	private Coordinates nextHead;
	private Coordinates fruit;
	private Random generator = new Random();
	private boolean eaten;
	private int growth;
	private int length;

	private Tile[][] array;

	public Grid(int xArg, int yArg, int scaleArg)
	{
		XSIZE = xArg;
		YSIZE = yArg;
		array = new Tile[YSIZE][XSIZE];
		scale = scaleArg;
		for(int i = 0; i < YSIZE ; ++i) //initiate the board with empty tiles
		{
			for(int j = 0; j < XSIZE; ++j)
			{
				array[i][j] = new Empty(scale);
			}
		}

		head = new Coordinates(2, 2); //initiate the initial snake tiles
		nextHead = new Coordinates(2, 3);

		array[2][2] = new Snake(true,false,2);
		array[2][1] = new Snake(false,false,1);
		array[2][0] = new Snake(false,true,0);
		((Snake)array[2][2]).SetShape(3,5);
		((Snake)array[2][1]).SetShape(3,1);
		((Snake)array[2][0]).SetShape(5,1);
		direction = newDirection = 1;

		fruit = new Coordinates(2, 4); //place the fruit

		growth = 0;
		length = 3;
		eaten = false;
		PlaceFruit();
	}
	public boolean Update()
	{
	    direction = newDirection; //update the direction
		if(!CanStep()) return false; //check if snake can advance
		eaten = Eat(); //check if it eats
		TakeStep(); //move the snake
		if(eaten) PlaceFruit(); //place the fruit if needed
		return true;
	}
	private void TakeStep()
	{
		for(int i = 0; i < YSIZE; ++i)
		{
			for(int j = 0; j < XSIZE; ++j)//For each tile:
			{
				if(!(array[i][j] instanceof Snake)) continue; //if the tile is empty or fruit, continue
				Snake temp = (Snake)array[i][j];
				if(temp.IsTail())//the tail becomes an empty tile
				{
					if(growth == 0)	array[i][j] = new Empty(scale);
				}
				else if(temp.IsHead())//the head is no longer a head
				{
					if(growth == 0) temp.Decrement();
					temp.SetHead(false);
				}
				else//any other body segment updates its index
				{
					if(growth == 0) temp.Decrement();
					if(temp.GetIndex()==0) temp.SetTail(true);
				}
				temp.Shape(direction);//update the shape of the snake
			}
		}

		if(growth > 0) length++;//let the snake grow
		array[nextHead.yPos][nextHead.xPos] = new Snake(true,false,length-1); //create new head
		if(growth > 0) growth--;
		head.xPos = nextHead.xPos;
		head.yPos = nextHead.yPos;
		((Snake)array[head.yPos][head.xPos]).Shape(direction);//update the shape of the head
	}
	public void TryDir(int dirArg)
	{
		if ((dirArg - direction + 4) % 4 != 2) newDirection = dirArg;//change the direction, dont allow turning back
	}
	private void PlaceFruit()
	{
		do
		{
			fruit.xPos = Math.abs(generator.nextInt() % XSIZE);
			fruit.yPos = Math.abs(generator.nextInt() % YSIZE);
		} while(!(array[fruit.yPos][fruit.xPos] instanceof Empty)); //generate random positions until empty is found
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
		if(nextHead.xPos < 0 || nextHead.xPos >= XSIZE || nextHead.yPos < 0 || nextHead.yPos >= YSIZE) //check collision with boundaries
		{
			return false;
		}
		if(array[nextHead.yPos][nextHead.xPos] instanceof Snake) //check collisions with self
		{
			Snake temp = (Snake) array[nextHead.yPos][nextHead.xPos];
			if(temp.IsTail() && growth == 0) return true;
			return false;
		}
		return true;
	}
	private boolean Eat()
	{
		if( ! (array[nextHead.yPos][nextHead.xPos] instanceof Fruit)) return false;
		Fruit temp = (Fruit) array[nextHead.yPos][nextHead.xPos];
		growth += temp.GetNutr();
		array[nextHead.yPos][nextHead.xPos] = new Empty(scale);
		return true;
	}
	public Tile GetTile(int xArg, int yArg)
	{
		return array[yArg][xArg];
	}
	public int GetLen()
	{
		return length;
	}
}
