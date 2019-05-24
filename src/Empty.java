import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

class Empty extends Tile
{
    Empty()
    {
        switch(new Random().nextInt(40))
        {
            case 0:
                color = Color.DARKBLUE;
                break;
            case 1:
                color = Color.YELLOW;
                break;
            case 2:
                color = Color.ORANGERED;
                break;
            default:
                color = Color.DARKGREEN;
        }
    }
    public void Draw(int xArg, int yArg, int scale, GraphicsContext gc)
    {
        super.DrawBG(xArg, yArg, scale, gc);
        gc.setFill(color);
        gc.fillOval(15 + xArg * scale,  10 + yArg * scale, 5, 5);
    }

}