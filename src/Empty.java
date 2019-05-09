class Empty extends Tile
{
    private boolean changes;

    Empty()
    {
        changes = false;
    }

    public boolean GetChanges()
    {
        return changes;
    }

    public void SetChanges(boolean changesArg)
    {
        changes = changesArg;
    }

    public boolean Deadly()
    {
        return false;
    }
    public void Eat()
    {
    }
}