import java.util.TreeMap;
import apcs.Window;

public abstract class Ghost
{
    protected int x, y;
    protected int[][] maze;
    protected Pan pan;
    protected String direction;
    protected TreeMap<String, String> images;
    protected Timer timer;
    protected Timer gameTimer;
    //protected Timer eatenTimer;
    protected boolean frightened = false;
    
    protected int dir;
    protected String[] dirArr = {"right", "up", "down", "left"};
    protected int[] oppDirIndex = { 3, 2, 1, 0 };
    
    protected int[] shiftXArr = {4, 0, 0, -4};
    protected int[] shiftYArr = {0, -4, 4, 0};
    
    public Ghost(int[][] m, Pan p, Timer t)
    {
        x = 390;
        y = 210;
        maze = m;
        pan = p;
        direction = "right";
        dir = 0;
        images = new TreeMap<String, String>();
        gameTimer = t;
        timer = new Timer();
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
     */
    public void move()
    {
        int targetX = targetX();
        int targetY = targetY();

        // Find how far each current coordinate value is to the target coordinate values
        int diffX = targetX - x;
        int diffY = targetY - y;

        int[] dirOrder = new int[4];
        if (diffX >= Math.abs( diffY ))
        {
            dirOrder[0] = 0; // right
        }
        else if (diffX <= -Math.abs( diffY ))
        {
            dirOrder[0] = 3; // left
        }
        else if (diffY >= Math.abs( diffX ))
        {
            dirOrder[0] = 2; // down
        }
        else
        {
            dirOrder[0] = 1; // up
        }
            
        if (dirOrder[0] == 0 || dirOrder[0] == 3)
        {
            if (diffY >= 0)
            {
                dirOrder[1] = 2; // down
            }
            else
            {
                dirOrder[1] = 1; // up
            }
        }
        else
        {
            if (diffX >= 0)
            {
                dirOrder[1] = 0; // right
            }
            else
            {
                dirOrder[1] = 3; // left
            }
        }
            
        dirOrder[2] = oppDirIndex[ dirOrder[0] ];
        dirOrder[3] = oppDirIndex[ dirOrder[1] ];
            
        for (int i = 0; i < dirOrder.length; i++)
        {
            dir = dirOrder[i];
            direction = dirArr[dirOrder[i]];
            
            if ( canMove(direction) )
            {
                moveForward();
                break;
            }
        }
    }
    
    public void moveForward()
    {
        x += shiftXArr[dir];
        y += shiftYArr[dir];
        direction = dirArr[dir];
        
        Window.out.image( images.get( direction ), x , y );
    }
    
    public void dirReverse(int d)
    {
        dir = oppDirIndex[d];
        direction = dirArr[dir];
    }
    
    public boolean isInJail()
    {
        return (( maze[ ( y + 1 ) / 20][ ( x + 1 ) / 20 ] == 4 )
                        || ( maze[ ( y + 1 ) / 20 ][ (x + 19) / 20] == 4 )
                        || ( maze[(y + 19) / 20][(x + 1) / 20] == 4 )
                        || ( maze[(y + 19) / 20][(x + 19) / 20] == 4 ));
    }
    
    public void moveInJail()
    {
        if (!canMove(direction))
        {
            dirReverse(dir);
            moveForward();
        }
        else
        {
            moveForward();
        }
    }
    
    public void moveDir(int d)
    {
        dir = d;
        direction = dirArr[d];
        moveForward();
    }
    
    public void moveOutOfJail()
    {
        if (x < 390)
        {
            moveDir(0);
        }
        else if (x > 390)
        {
            moveDir(3);
        }
        else
        {
            moveDir(1);
        }
    }
    
    public void backToJail()
    {
        //TODO
        x = 390;
        y = 210;
        
        direction = "up";
        Window.out.image( images.get( direction ), x , y );
    }
    
    /**
     * Identifies whether or not the ghost can move forward in the direction it's facing
     * @param direction The direction the ghost is heading in
     * @return true if the ghost can continue moving
     */
    private boolean canMove( String direction )
    {
        if ( direction.equals( "right" ) && ( x + 20 ) / 20 + 1<= maze[0].length
                        && maze[( y - 10 ) / 20 + 1 ][( x + 20 ) / 20] != 1)
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
        else if ( direction.equals( "down" ) && ( y + 40) / 20 + 1 <= maze.length
            && maze[ ( y + 20 ) / 20][ ( x - 10 ) / 20 + 1 ] != 1 
            && maze[ ( y + 20 ) / 20][ ( x - 10 ) / 20 + 1 ] != 4)
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
    
    public Timer getTimer()
    {
        return timer;
    }
    
    /**
     * The ghosts' movements when the pan eats the blue macaroni
     */
    public void frightenedMove()
    {
        if (timer.isCounting() == false)
        {
            timer.start();
            
            dirReverse(dir);
            
            x += shiftXArr[dir];
            y += shiftYArr[dir];
            direction = dirArr[dir];
            
            Window.out.image( images.get( "edible" ), x , y );
        }
        else if (!pan.touchingGhost( this ) && timer.getSecond() <= 20)
        {
            int ghostS = timer.getSecond();
            int ghostMS = timer.getMillisecond();
            
            int index = 0;
            
            if (!canMove(direction))
            {
                index = (int) (Math.random() * 4);
                
                while (!canMove(dirArr[index]))
                {
                    index = (int) (Math.random() * 4);
                }
            }
            else
            {
                for (int i = 0; i < dirArr.length; i++)
                {
                    if (dirArr[i].equals(direction))
                    {
                        index = i;
                        break;
                    }
                }
            }
            
            x += shiftXArr[index];
            y += shiftYArr[index];
            direction = dirArr[index];
            
            if (ghostS <= 10 || (ghostS > 10 && ghostS % 2 == 1))
            {
                Window.out.image( images.get( "edible" ), x , y );
            }
            else if (ghostS > 10 && ghostS % 2 == 0)
            {
                Window.out.image( images.get( "revert" ), x , y );
            }
        }
        else
        {
            if (pan.touchingGhost(this) && timer.getSecond() <= 25)
            {
                timer.stop();
                timer.reset();
                timer.start();
                //eatenTimer.start();
                frightened = false;
                this.backToJail();
            }
            else
            {
                timer.stop();
                timer.reset();
                frightened = false;
            }
        }
        
        timer.count();
    }
    
    public boolean isFrightened()
    {
        return frightened;
    }
}
