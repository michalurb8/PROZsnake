import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract class Tile
{
    public abstract void Draw(int xArg, int yArg, int scale, GraphicsContext gc);
    public void DrawBG(int xArg, int yArg, int scale, GraphicsContext gc)
    {
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(xArg * scale, yArg * scale, scale, scale);
    }

    public Color color;
}