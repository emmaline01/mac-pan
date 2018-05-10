import java.util.ArrayList;
import java.util.TreeMap;
import apcs.Window;

public abstract class Ghost
{
    private int x, y, length, width;
    private int[][] maze;
    private Pan pan;
    private String direction;
    private TreeMap<String, String> images;
    
    public Ghost(int[][] m, Pan p)
    {
        x = 500;
        y = 300;
        length = 20;
        width = 20;
        maze = m;
        pan = p;
        direction = "up";
        images = new TreeMap<String, String>();
    }
    
    public abstract void setImages();
    
    public abstract int targetX();
    
    public abstract int targetY();
    
    public abstract void moveTo(int targetX, int targetY);
    
    public boolean canMove(String direction)
    {
        //TODO
        if (x + 10 < 1000 - 10 && direction.equals( "right" )) 
        {
            if (maze[((y - 10) / 20)][((x + 10) / 20)] != 1) 
            { 
                return true;
            }
        }
        else if (x - 10 > 10 && direction.equals( "left" )) 
        {
            if (maze[((y - 10) / 20) ][((x - 10) / 20)] != 1) 
            {
                return true;
            }
        }
        else if (y - 10 > 10 && direction.equals( "up" )) 
        {
            if (maze[((y - 10) / 20)][((x - 10) / 20)] != 1) 
            {
                return true;
            }
        }
        else if (y + 10 < 600 - 10 && direction.equals( "down" )) 
        {
            if (maze[((y + 10) / 20)][((x - 10) / 20)] != 1) 
            {
                return true;
            }
        }
        return false;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean eaten()
    {
        return pan.touchingGhost(this);
    }
    
    public void frightenedMove()
    {
        //TODO
        
        Window.frame();
        if (direction.equals( "up" ))
        {
            Window.out.rectangle( x, y - 10, 10, 15 );
            y -= 10;
        }
        else if (direction.equals("down"))
        {
            Window.out.rectangle( x, y + 10, 10, 15 );
            y += 10;
        }
        else if (direction.equals( "right" ))
        {
            Window.out.rectangle( x - 10, y, 10, 15 );
            x -= 10;
        }
        else
        {
            Window.out.rectangle( x + 10, y, 10, 15 );
            x +=10;
        }
    }
}
