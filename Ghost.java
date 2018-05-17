import java.util.ArrayList;
import java.util.TreeMap;
import apcs.Window;

public abstract class Ghost
{
    protected int x, y;
    protected int[][] maze;
    protected Pan pan;
    protected String direction;
    protected TreeMap<String, String> images;
    
    protected String[] dirArr = {"right", "up", "down", "left"};
    protected int[] dirIndex = {0, 1, 2, 3};
    protected int[] shiftXArr = {4, 0, 0, -4};
    protected int[] shiftYArr = {0, -4, 4, 0};
    
    public Ghost(int[][] m, Pan p)
    {
        x = 390;
        y = 210;
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

    public void move()
    {
        if (maze[((y - 10) / 20)][((x - 10) / 20)] == 4)
        {
            direction = "up";
            y = y - 5;
        }
        else
        {
            int targetX = targetX();
            int targetY = targetY();
            System.out.println("targetx: " + targetX + " targety: " + targetY);

            // Find how far each current coordinate value is to the target coordinate values
            int diffX = targetX - x;
            int diffY = targetY - y;

            int[] dirNum = new int[4];
            
            if (diffX >= Math.abs( diffY ))
            {
                dirNum[0] = 0;
            }
            else if (diffX <= -Math.abs( diffY ))
            {
                dirNum[0] = 3;
            }
            else if (diffY >= Math.abs( diffX ))
            {
                dirNum[0] = 2;
            }
            else
            {
                dirNum[0] = 1;
            }
            
            if (dirNum[0] == 0 || dirNum[0] == 3)
            {
                if (diffY >= 0)
                {
                    dirNum[1] = 2;
                }
                else
                {
                    dirNum[1] = 1;
                }
            }
            else
            {
                if (diffX >= 0)
                {
                    dirNum[1] = 0;
                }
                else
                {
                    dirNum[1] = 3;
                }
            }
            
            dirNum[2] = 3 - dirNum[0];
            dirNum[3] = 3 - dirNum[1];
            
            for (int i = 0; i < dirNum.length; i++)
            {
                int index = dirNum[i];

                direction = dirArr[dirNum[i]];
                
                if (canMove(direction))
                {
                    x += shiftXArr[index];
                    y += shiftYArr[index];
                    break;
                }
                //System.out.println( direction );
            }
        }

        Window.out.image( images.get( direction ), x , y );
    }

    /**
     * Identifies whether or not the ghost can move forward in the direction it's facing
     * @param direction The direction the ghost is heading in
     * @return true if the ghost can continue moving
     */
    private boolean canMove( String direction )
    {
        if ( direction.equals( "right" ) && ( x + 50 - 10 ) / 20 + 1<= maze[0].length
                        && maze[( y - 10 ) / 20 + 1 ][( x + 25 ) / 20] != 1)
        {
            return true;
        }
        else if ( direction.equals( "left" ) && (x / 20) >= 0
            && maze[(y - 10 ) / 20 + 1][(x - 5) / 20] != 1)
        {
            return true;
        }
        else if ( direction.equals( "up" ) && ( y - 10 ) / 20 + 1 >= 0
            && maze[( y - 5 ) / 20 ][( x - 10 ) / 20 + 1 ] != 1)
        {
            return true;
        }
        else if ( direction.equals( "down" ) && ( y + 50 -10) / 20 + 1 <= maze.length
            && maze[ ( y + 25 - 10 ) / 20][ ( x - 10 ) / 20 + 1 ] != 1 
            && maze[ ( y + 25 - 10 ) / 20][ ( x - 10 ) / 20 + 1 ] != 4 )
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
}
