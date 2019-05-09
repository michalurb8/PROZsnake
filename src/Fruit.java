import java.util.Random;

class Fruit extends Tile
{
    private int nutrition;
    private Random generator = new Random();

    public Fruit()
    {

        nutrition = 1+Math.abs(generator.nextInt())%3;
    }
    public int GetNutr()
    {
        return nutrition;
    }
    public void SetNutr(int nutritionArg)
    {
        nutrition = nutritionArg;
    }
}