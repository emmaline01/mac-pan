import java.util.ArrayList;
import java.util.TreeMap;
import apcs.Window;

public class Inky extends Ghost
{
    private int x, y, length, width;
    private TreeMap<String, String> images;
    private int[][] maze;
    private Pan pan;
    private String direction;
    private Ghost blinky;
    
    public Inky( int[][] m, Pan p, Ghost g )
    {
        super( m, p );
        blinky = g;
    }

    public void setImages()
    {
        images.put( "up", "InkyUp.png" );
        images.put( "right", "InkyRight.png" );
        images.put( "left", "InkyLeft.png" );
        images.put( "down", "InkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }

    public int targetX()
    {
        // TODO
        String pDir = pan.getDirection();
        
        int pX = pan.getX();
        int bX = blinky.getX();
        int targetX = 0;
        
        if (pDir.equals( "right" ))
        {
            targetX = pX + 2 + (2 * Math.abs( pX - bX )); //(2 * Math.abs( (pX + 2) - bX ))
        }
        else if (pDir.equals( "left" ))
        {
            targetX = pX - 2 - (2 * Math.abs( pX - bX ));
        }
        else
        {
            targetX = x;
        }
        
        return targetX;
    }

    public int targetY()
    {
        // TODO
        String pDir = pan.getDirection();
        
        int pY = pan.getY();
        int bY = blinky.getY();
        int targetY = 0;
        
        if (pDir.equals( "up" ))
        {
            targetY = pY - 2 - (2 * Math.abs( pY - bY ));
        }
        else if (pDir.equals( "down" ))
        {
            targetY = pY + 2 + (2 * Math.abs( pY - bY ));
        }
        else
        {
            targetY = y;
        }
        
        return targetY;
    }
    
    public void moveTo(int targetX, int targetY)
    {
        // TODO
        String xDir = "";
        String yDir = "";
        
        // Find if Inky should go left or right to get to the target
        if (targetX > x)
        {
            xDir = "right";
        }
        else if (x > targetX)
        {
            xDir = "left";
        }
        
        //Find if Inky should go up or down to get the target
        if (targetY > y)
        {
            yDir = "down";
        }
        else if (y > targetY)
        {
            yDir = "up";
        }
        
        int diffX = Math.abs( targetX - x );
        int diffY = Math.abs( targetY - y );
        if (diffX > diffY)
        {
            if (!xDir.isEmpty() && canMove(xDir))
            {
                direction = xDir;
                if (xDir.equals("right"))
                {
                    x = x + 5;
                }
                else
                {
                    x = x - 5;
                }
            }
            else if (!yDir.isEmpty() && canMove(yDir))
            {
                direction = yDir;
                if (yDir.equals( "up" ))
                {
                    y = y - 5;
                }
                else
                {
                    y = y + 5;
                }
            }
        }
        
        Window.out.image( images.get( direction ), x - 10, y - 10 );
    }

}
