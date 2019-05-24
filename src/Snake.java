import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Snake extends Tile
{
    private boolean head;
    private boolean tail;
    private int index;
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
    public void Decrement()
    {
        index--;
    }

    public void Draw(int xArg, int yArg, int scale, GraphicsContext gc)
    {
        super.DrawBG(xArg, yArg, scale, gc);
        gc.setFill(Color.rgb(index, index, index));
        gc.fillRect(xArg * scale, yArg * scale, scale, scale);
    }
}