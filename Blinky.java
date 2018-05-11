import java.util.ArrayList;
import java.util.TreeMap;
import apcs.Window;

public class Blinky extends Ghost
{
    private int x, y, length, width;
    private TreeMap<String, String> images;
    private int[][] maze;
    private Pan pan;
    private String direction;
    
    public Blinky( int[][] m, Pan p )
    {
        super( m, p );
        setImages();
    }

    public void setImages()
    {
        images.put( "up", "BlinkyUp.png" );
        images.put( "right", "BlinkyRight.png" );
        images.put( "left", "BlinkyLeft.png" );
        images.put( "down", "BlinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }

    public int targetX()
    {
        return pan.getX();
    }

    public int targetY()
    {
        return pan.getY();
    }

    public void moveTo( int targetX, int targetY )
    {
        // TODO
        int diffX = Math.abs( targetX - x );
        int diffY = Math.abs( targetY - y );
        String xDir = "";
        String yDir = "";
        
        //Determines whether blinky should head right or left based on x-coordinate.
        if (targetX > x)
        {
            xDir = "right";
        }
        else if (x > targetX)
        {
            xDir = "left";
        }
        
        //Determines whether blinky should head up or down based on y-coordinate.
        if (targetY > y)
        {
            yDir = "down";
        }
        else
        {
            yDir = "up";
        }
        
        if (targetX > targetY && canMove(xDir))
        {
            direction = xDir;
            
            if (xDir.equals( "right" ))
            {
                x = x + 5;
            }
            else
            {
                x = x - 5;
            }
        }
        else if (targetY > targetX && canMove(yDir))
        {
            direction = yDir;
            
            if (xDir.equals( "up" ))
            {
                y = y - 5;
            }
            else
            {
                y = y + 5;
            }
        }
        
        Window.out.image( images.get( direction ), x - 10, y - 10 );
    }   
}
