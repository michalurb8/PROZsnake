abstract class Tile
{
    private Coordinates position;
    public Tile()
    {
        position = new Coordinates();
    }
    public Coordinates GetPosition()
    {
        return position;
    }
    public void SetPosition(Coordinates positionArg)
    {
        position = positionArg;
    }
}