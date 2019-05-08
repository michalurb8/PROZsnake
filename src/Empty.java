class Empty extends Tile
{
    public boolean isEmpty()
    {
        return true;
    }
    public boolean isSnake()
    {
        return false;
    }
    public boolean isFruit()
    {
        return false;
    }
}