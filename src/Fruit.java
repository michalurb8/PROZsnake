class Fruit extends Tile
{
    private int nutrition;

    public Fruit()
    {
        nutrition = 1;
    }

    public int getNutr()
    {
        return nutrition;
    }
    public void setNutr(int nutritionArg)
    {
        nutrition = nutritionArg;
    }
    public boolean isEmpty()
    {
        return false;
    }
    public boolean isSnake()
    {
        return false;
    }
    public boolean isFruit()
    {
        return true;
    }
}