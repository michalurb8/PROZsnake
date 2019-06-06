import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application
{
    private final static int scale = 40, width = 20, height = 20;
    private static boolean over  = false, pause = false;
    private static Grid board;
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage)
    {
        try
        {
            board = new Grid(width, height, scale); //Adding new board to play on
            VBox root = new VBox();
            Canvas canv = new Canvas(width * scale, height * scale);
            GraphicsContext gc = canv.getGraphicsContext2D();

            root.getChildren().add(canv);

            Scene scene = new Scene(root, width * scale, height * scale);

            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
               if(key.getCode() == KeyCode.W)
               {
                   board.TryDir(2);
               }
               else if(key.getCode() == KeyCode.A)
               {
                   board.TryDir(3);
               }
               else if(key.getCode() == KeyCode.S)
               {
                   board.TryDir(0);
               }
               else if(key.getCode() == KeyCode.D)
               {
                   board.TryDir(1);
               }
               else if(key.getCode() == KeyCode.P)//P toggles pause if game is not over
               {
                   if(!over)
                   {
                       if(pause)
                       {
                           pause = false;
                       }
                       else
                       {
                           gc.setFill(Color.BLUEVIOLET);
                           gc.setFont(new Font("", 50));
                           gc.fillText("Game paused\n\n\n[p]", 100, 250);
                           pause = true;
                       }
                   }
               }
               else if(key.getCode() == KeyCode.R)//R resets the game anytime
               {
                   pause = false;
                   board = new Grid(width, height, scale);
                   over = false;
               }

            });

            stage.setScene(scene);
            stage.setTitle("SZNAKE");
            stage.show();

            new AnimationTimer()
            {
                long lastTick = 0;
                @Override
                public void handle(long now)
                {
                    if (lastTick == 0)
                    {
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if (now - lastTick > 160000000 - 800000 * board.GetLen())//framerate depends on snake length
                    {
                        lastTick = now;
                        tick(gc);
                    }

                }
            }.start();

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private static void tick(GraphicsContext gc)
    {
        if(pause || over)//return if the game is paused or lost
        {
            return;
        }
        over = !board.Update();//update one frame
        if(over)//and check if the game is lost
        {
            gc.setFill(Color.BLACK);
            gc.setFont(new Font("", 50));
            gc.fillText("Game over.\n\nScore: " + board.GetLen() +  "\n\n\n[r]", 100, 250);
            return;
        }
        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                board.GetTile(x, y).Draw(x, y, scale, gc); //if not, draw everything on the screen
            }
        }
    }
}
