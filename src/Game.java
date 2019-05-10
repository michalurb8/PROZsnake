import java.util.Scanner;
class Game
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
        Grid Board = new Grid();
        Board.PrintGame();
        Help();
		char choice = sc.next().charAt(0);
		while(Board.Update(choice))
		{
            do
            {
                choice = sc.next().charAt(0);
                if( ! (choice =='q' || choice == 'w' || choice == 'a' || choice == 's' || choice == 'd')) Help();

            }while( ! (choice =='q' || choice == 'w' || choice == 'a' || choice == 's' || choice == 'd'));
		}
    }
    static private void Help()
    {
        System.out.println("Move with w s a d");
        System.out.println("Quit with q");
        System.out.println("Confirm with enter");
    }
}

