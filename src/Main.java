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

import java.util.Random;

public class Main extends Application
{
    private final static int scale = 30, width = 30, height = 30;
    private static Grid board;
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        try {
            board = new Grid(width, height);
            VBox root = new VBox();
            Canvas canv = new Canvas(width * scale, height * scale);
            GraphicsContext gc = canv.getGraphicsContext2D();

            root.getChildren().add(canv);

            new AnimationTimer()
            {
                long lastTick = 0;
                @Override
                public void handle(long now)
                {
                    if (lastTick == 0)
                    {
                        lastTick = now;
                        if(!tick(gc)) super.stop();
                        return;
                    }
                    if (now - lastTick > 170000000 - 1200000 * board.GetLen())
                    {
                        lastTick = now;
                        if(!tick(gc)) super.stop();
                    }

                }
            }.start();
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
               else if(key.getCode() == KeyCode.R)
               {
                   board = new Grid(width, height);
               }
            });

            stage.setScene(scene);
            stage.setTitle("SZNEK");
            stage.show();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private static boolean tick(GraphicsContext gc) {
        if (!board.Update()) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER\n\nScore: " + board.GetLen(), 100, 250);
            return false;
        }
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                board.GetTile(x, y).Draw(x, y, scale, gc);
            }
        }
        return true;
    }
}
