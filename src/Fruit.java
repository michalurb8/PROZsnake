import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

class Fruit extends Tile
{
    private int nutrition;//determines how much snake grows if eats the fruit

    public Fruit()
    {

        nutrition = 1+Math.abs(new Random().nextInt())%4;
        switch(nutrition)
        {
            case 1:
                color = Color.WHITE;
                break;
            case 2:
                color = Color.LIGHTPINK;
                break;
            case 3:
                color = Color.PINK;
                break;
            case 4:
                color = Color.HOTPINK;
        }
    }
    public int GetNutr()
    {
        return nutrition;
    }

    public void Draw(int xArg, int yArg, int scale, GraphicsContext gc)
    {
        super.DrawBG(xArg, yArg, scale, gc);
        gc.setFill(color);
        gc.fillOval(xArg * scale, yArg * scale, scale, scale);
    }

}