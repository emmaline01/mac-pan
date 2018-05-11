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
    
    /**
     * Put the images of individual ghosts when heading in different directions
     */
    public abstract void setImages();
    
    /**
     * Find x-coordinate the image should move to
     * @return target x-coordinate
     */
    public abstract int targetX();
    
    /**
     * Find y-coordinate the image should move to
     * @return target y-coordinate
     */
    public abstract int targetY();
    
    /**
     * Move to specified coordinates.
     * @param targetX target x-coordinate
     * @param targetY target y-coordinate
     */
    public abstract void moveTo(int targetX, int targetY);
    
    /**
     * Identifies whether or not the ghost can move forward in the direction it's facing
     * @param direction The direction the ghost is heading in
     * @return true if the ghost can continue moving
     */
    public boolean canMove(String direction)
    {
        if ( direction.equals( "right" ) && ( x + 50 - 10 ) / 20 + 1 <= maze[0].length
            && maze[( y - 10 ) / 20 + 1][( x + 25 - 10 ) / 20] != 1 ) 
            
        // ( x - 10 ) / 20 + 1 + 1
        // -10 for array conversion
        // + 20 for center of pan
        {
            return true;
        }
        else if ( direction.equals( "left" ) && ( x - 10 ) / 20 + 1 >= 0
            && maze[( y - 10 ) / 20 + 1][( x - 10 ) / 20] != 1 )
        {
            return true;
        }
        else if ( direction.equals( "up" ) && ( y - 10 ) / 20 + 1 >= 0
            && maze[( y - 10 ) / 20][( x - 10 ) / 20 + 1] != 1 )
        {
            return true;
        }
        else if ( direction.equals( "down" ) && ( y + 50 - 10 ) / 20 + 1 <= maze.length
            && maze[( y + 25 - 10 ) / 20][( x - 10 ) / 20 + 1] != 1 )
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the x-coordinate of the ghost
     * @return current x-coordinate of the ghost
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Returns the y-coordinate of the ghost
     * @return current y-coordinate of the ghost
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Identifies whether or not the ghost is eaten by the pan.
     * @return true if the ghost is touching the pan.
     */
    public boolean eaten()
    {
        return pan.touchingGhost(this);
    }
    
    /**
     * The ghosts' movements when the pan eats the blue macaroni
     */
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
    
    public void returnToJail()
    {
        //TODO
    }
}
