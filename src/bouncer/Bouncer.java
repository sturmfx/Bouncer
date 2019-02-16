/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lordstorm
 */
public class Bouncer extends Application 
{
    String debug_info = "";
    Game g;
    @Override
    public void start(Stage primaryStage) {
        int width = 1000;
        int height = 1000;
        int raadius = 20;
        int pwidth = 200;
        int time_between_tick_in_millisecons = 1;
        int start_delay_in_milliseconds = 3000;
        Color ball_color = Color.BLACK;
        Color plank_color = Color.RED;
        g = new Game(width, height, raadius, pwidth, time_between_tick_in_millisecons, start_delay_in_milliseconds, ball_color, plank_color);
        Canvas can = new Canvas(g.width,g.height);
         new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                debug_info = "ball_x_speed = " + g.ball_speed_x + "; ball_speed_y = " + g.ball_speed_y + "; speed = " + g.speed; 
                primaryStage.setTitle(debug_info);
                GraphicsContext gc = can.getGraphicsContext2D();
                gc.clearRect(0, 0, g.width, g.height);
                gc.setFill(g.ball_color);
                gc.fillOval(g.ball_x, g.ball_y, 2*g.raadius, 2*g.raadius);
                gc.setFill(g.plank_color);
                gc.fillRect(g.plank_x, g.plank_y, g.pwidth, g.pheight);
            }
        }.start();
        
        StackPane root = new StackPane();
        root.getChildren().add(can);
        
        Scene scene = new Scene(root, g.width, g.height);
        scene.setOnKeyPressed
        (
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent e)
                {
                   if(KeyCode.A == e.getCode())
                   {
                       g.direction = "LEFT";
                       e.consume();
                   }
                   if(KeyCode.D == e.getCode())
                   {
                       g.direction = "RIGHT";
                       e.consume();
                   }
                   e.consume();
                }
            }
        );
        scene.setOnKeyReleased
        (
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent e)
                {
                   if(KeyCode.A == e.getCode())
                   {
                       g.direction = "NO";
                       e.consume();
                   }
                   if(KeyCode.D == e.getCode())
                   {
                       g.direction = "NO";
                       e.consume();
                   }
                   e.consume();
                }
            }
        );
                   
        
        primaryStage.setTitle("Hello Bouncer!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
