/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncer;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;

/**
 *
 * @author lordstorm
 */
public class Game
{
    Random r;
    int pspeed = 1;
    String direction = "NO";
    int pheight = 20;
    int width;
    int height;
    int raadius;
    int pwidth;
    int time_between_tick_in_millisecons = 1;
    double speed = 0.30;
    double speed_change = 0.02;
    int start_delay_in_milliseconds = 3000;
    Color ball_color;
    Color plank_color;
    Timer timer;
    
    double ball_x = 0;
    double ball_y = 0;
    double plank_x = 0;
    double plank_y = 0;
    
    double ball_speed = 1;
    double ball_speed_x = -0.6;
    double ball_speed_y = 0.8;
    
    boolean game_continue = true;
    public Game(int width1, int height1, int raadius1, int pwidth1, int time_between_tick_in_millisecons1, int start_delay_in_milliseconds1, Color ball_color1, Color plank_color1)
    {
        r = new Random();
        width = width1;
        height = height1;
        raadius = raadius1;
        pwidth = pwidth1;
        time_between_tick_in_millisecons = time_between_tick_in_millisecons1;
        start_delay_in_milliseconds = start_delay_in_milliseconds1;
        ball_color = ball_color1;
        plank_color = plank_color1;
        ball_x = (width/2);
        ball_y = (width/2);
        plank_x = 0;
        plank_y = height-pheight;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() 
        {
        @Override
        public void run() 
        {
            if(game_continue)
            {
            update();
            }
        }},start_delay_in_milliseconds, time_between_tick_in_millisecons);
    }
    public void update()
    {
        if(direction == "LEFT")
        {
            plank_x = plank_x - speed*pspeed;
        }
        if(direction == "RIGHT")
        {
            plank_x = plank_x + speed*pspeed;
        }
        ball_x = ball_x + speed*ball_speed_x;
        ball_y = ball_y + speed*ball_speed_y;
        if((plank_y-(ball_y+2*raadius))<= 1)
        {
            if(((ball_x+raadius)>=plank_x)&&((plank_x+pwidth)>=(ball_x+raadius)))
            {
                 ball_speed_y = -ball_speed_y;
                 //update_direction();
                 speed = speed+speed_change;
            }
            else
            {
                game_continue = !game_continue;
            }
        }
        if((ball_x+2*raadius)>this.width-1)
        {
            ball_speed_x = -ball_speed_x;
            //update_direction();
        }
        if(ball_x<0)
        {
            ball_speed_x = -ball_speed_x;
            //update_direction();
        }
        if(ball_y<0)
        {
            ball_speed_y = -ball_speed_y;
            //update_direction();
        }
        
    }
    public void update_direction()
    {
        
        double temp1 = (r.nextDouble()/5) + 0.9;
        
        double temp2;
        while(ball_speed_x*temp1 >= 1)
        {
            temp1 = (r.nextDouble()/5) + 0.9;
           
        }
        ball_speed_x = ball_speed_x*temp1;
        if(ball_speed_y >= 0)
        {
            temp2 = Math.sqrt(1-ball_speed_x*ball_speed_x);
            ball_speed_y = temp2;
        }
        else
        {
            temp2 = Math.sqrt(1-ball_speed_x*ball_speed_x);
            ball_speed_y = -temp2;
        }
    }

}
