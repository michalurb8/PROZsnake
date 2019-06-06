import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

class Empty extends Tile
{
    private int xPos, yPos, size;
    Empty(int scale)
    {
        size = new Random().nextInt(2) + 5; //flower variables
        xPos = new Random().nextInt(scale - size);
        yPos = new Random().nextInt(scale - size);
        switch(new Random().nextInt(40))
        {
            case 0:
                color = Color.DARKBLUE;
                break;
            case 1:
                color = Color.YELLOW;
                break;
            case 2:
                color = Color.MEDIUMVIOLETRED;
                break;
            case 3:
                color = Color.PINK;
                break;
            default:
                color = Color.DARKGREEN;
        }
    }
    public void Draw(int xArg, int yArg, int scale, GraphicsContext gc)
    {
        super.DrawBG(xArg, yArg, scale, gc);
        gc.setFill(color);
        gc.fillOval(xPos + xArg * scale,  yPos + yArg * scale, size, size); //draw flowers
    }

}