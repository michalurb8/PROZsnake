import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

class Fruit extends Tile
{
    private int nutrition;
    private Random generator = new Random();

    public Fruit()
    {

        nutrition = 1+Math.abs(generator.nextInt())%3;
        switch(nutrition)
        {
            case 1:
                color = Color.LIGHTPINK;
                break;
            case 2:
                color = Color.PINK;
                break;
            case 3:
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