import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Snake extends Tile
{
    private boolean head;
    private boolean tail;
    private int index;
    private int from, to;
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
        gc.setFill(Color.rgb(index + 10, index, index));
        if(IsHead())
        {
            gc.fillRect(xArg * scale, yArg * scale, scale, scale);
            gc.setFill(Color.RED);
            switch(from)//draw eyes
            {
                case 0:
                    gc.fillRect(xArg * scale, yArg * scale + scale*4/5, scale/5,  scale/5);
                    gc.fillRect(xArg * scale + scale*4/5, yArg * scale + scale*4/5, scale/5 , scale/5);
                    break;
                case 1:
                    gc.fillRect(xArg * scale + scale*4/5, yArg * scale, scale/5,  scale/5);
                    gc.fillRect(xArg * scale + scale*4/5, yArg * scale + scale*4/5, scale/5 , scale/5);
                    break;
                case 2:
                    gc.fillRect(xArg * scale, yArg * scale, scale/5,  scale/5);
                    gc.fillRect(xArg * scale + scale*4/5, yArg * scale, scale/5 , scale/5);
                    break;
                case 3:
                    gc.fillRect(xArg * scale, yArg * scale, scale/5,  scale/5);
                    gc.fillRect(xArg * scale, yArg * scale + scale*4/5, scale/5 , scale/5);
            }
        }
        else if(IsTail())
        {
            double[] triangleX;
            double[] triangleY;
            switch(to)
            {
                case 0:
                    triangleX = new double[]{scale*xArg, scale*xArg + scale, scale*xArg + scale/2};
                    triangleY = new double[]{scale*yArg + scale, scale*yArg + scale, scale*yArg};
                    gc.fillPolygon(triangleX, triangleY, 3);
                    break;
                case 1:
                    triangleX = new double[]{scale*xArg + scale, scale*xArg + scale, scale*xArg};
                    triangleY = new double[]{scale*yArg, scale*yArg + scale, scale*yArg + scale/2};
                    gc.fillPolygon(triangleX, triangleY, 3);
                    break;
                case 2:
                    triangleX = new double[]{scale*xArg, scale*xArg + scale, scale*xArg + scale/2};
                    triangleY = new double[]{scale*yArg, scale*yArg, scale*yArg + scale};
                    gc.fillPolygon(triangleX, triangleY, 3);
                    break;
                case 3:
                    triangleX = new double[]{scale*xArg, scale*xArg, scale*xArg + scale};
                    triangleY = new double[]{scale*yArg, scale*yArg + scale, scale*yArg + scale/2};
                    gc.fillPolygon(triangleX, triangleY, 3);
            }
        }
        else
        {
            gc.fillRect(xArg * scale, yArg * scale, scale, scale);
            gc.setFill(Color.SADDLEBROWN);
            gc.fillRect(xArg * scale + scale*3/7, yArg * scale + scale*3/7, scale/7, scale/7);
            switch(from)//draw first scale
            {
                case 0:
                    gc.fillRect(xArg * scale + scale*3/7, yArg * scale + scale*5/7, scale/7, scale/7);
                    break;
                case 1:
                    gc.fillRect(xArg * scale + scale*5/7, yArg * scale + scale*3/7, scale/7, scale/7);
                    break;
                case 2:
                    gc.fillRect(xArg * scale + scale*3/7, yArg * scale + scale/7, scale/7, scale/7);
                    break;
                case 3:
                    gc.fillRect(xArg * scale + scale/7, yArg * scale + scale*3/7, scale/7, scale/7);
                    break;
            }
            switch(to)//draw second scale
            {
                case 0:
                    gc.fillRect(xArg * scale + scale*3/7, yArg * scale + scale*5/7, scale/7, scale/7);
                    break;
                case 1:
                    gc.fillRect(xArg * scale + scale*5/7, yArg * scale + scale*3/7, scale/7, scale/7);
                    break;
                case 2:
                    gc.fillRect(xArg * scale + scale*3/7, yArg * scale + scale/7, scale/7, scale/7);
                    break;
                case 3:
                    gc.fillRect(xArg * scale + scale/7, yArg * scale + scale*3/7, scale/7, scale/7);
                    break;
            }
        }
    }
    public void Shape(int direction)//update snake tile's shape: to and from determine the direction the snake goes on and gets off a tile
    {
        if(IsTail()){from = 5;}
        else if(IsHead())
        {
            from = (direction + 2) % 4;
            to = 5;
        }
        else if(to == 5)
        {
            to = direction;
        }
    }
    public void SetShape(int fromArg, int toArg)
    {
        from = fromArg;
        to = toArg;
    }
}