import java.util.Scanner; 
class Game
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		char choice = sc.next().charAt(0);
		Grid a = new Grid();
		while(a.Update(choice))
		{
			choice = sc.next().charAt(0);
		}
	}
}
