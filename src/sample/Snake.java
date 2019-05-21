class Snake extends Tile
{
    private boolean head;
    private boolean tail;
    private int index;

    public Snake()
    {
        head = true;
        tail = false;
        index = 0;
    }
    public Snake(boolean headArg, boolean tailArg, int indexArg)
    {
        head = headArg;
        tail = tailArg;
        index = indexArg;
    }
    public boolean IsHead()
    {
        return head;
    }
    public boolean IsTail()
    {
        return tail;
    }
    public void SetHead(boolean headArg)
    {
        head = headArg;
    }
    public void SetTail(boolean tailArg)
    {
        tail = tailArg;
    }
    public int GetIndex()
    {
        return index;
    }
    public void SetIndex(int indexArg)
    {
        index = indexArg;
    }
    public void Decrement()
    {
        index--;
    }
}