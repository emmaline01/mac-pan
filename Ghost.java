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
    protected Timer frightenedTimer;
    private int frightened = 0;  // 0: not frightened, 1: blue, 2: blinking blue
    protected int dir;
    protected String[] dirArr = {"right", "up", "down", "left"};
    protected int[] oppDirIndex = { 3, 2, 1, 0 };
    
    protected int[] shiftXArr = {4, 0, 0, -4};
    protected int[] shiftYArr = {0, -4, 4, 0};
    
    public Ghost(int[][] m, Pan p)
    {
        maze = m;
        pan = p;
        images = new TreeMap<String, String>();
        timer = new Timer();
        frightenedTimer = new Timer();
        initMove();
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
            changeDir(dirOrder[i]);
            
            if ( canMove(direction, isInJail()) )
            {
                moveForward();
                break;
            }
        }
    }
    
    public void initMove()
    {
        x = 380;
        y = 200;
        changeDir(0);
    }
    
    public void moveForward()
    {
        x += shiftXArr[dir];
        y += shiftYArr[dir];
        direction = dirArr[dir];
        
        Window.out.image( images.get( direction ), x , y );
        timer.count();
    }
    
    public void dirReverse(int d)
    {
        dir = oppDirIndex[d];
        direction = dirArr[dir];
    }
    
    public boolean isInJail()
    {
        return ( ( maze[y / 20][x / 20] == 4 ) ||
                        ( maze[y / 20][(x + 19) / 20] == 4 ) ||
                        ( maze[(y + 19) / 20][x / 20] == 4 ) ||
                        ( maze[(y + 19) / 20][(x + 19) / 20] == 4 ) );
    }
    
    public void moveInJail()
    {
        if (!canMove(direction, isInJail()))
        {
            dirReverse(dir);
            moveForward();
            timer.count();
        }
        else
        {
            moveForward();
            timer.count();
        }
    }
    
    public void changeDir(int d)
    {
        dir = d;
        direction = dirArr[d];
    }
    
    public void moveDir(int d)
    {
        changeDir(d);
        moveForward();
    }
    
    public void moveOutOfJail()
    {
        if (x < 388)
        {
            moveDir(0);
        }
        // else if (x > 390)
        else if (x > 392)
        {
            moveDir(3);
        }
        else // x == 388 || x == 392
        {
            moveDir(1);
        }
    }
    
    public void backToJail()
    {
        initMove();
        Window.out.image( images.get( direction ), x , y );
    }
    
    /**
     * Identifies whether or not the ghost can move forward in the direction it's facing
     * @param direction The direction the ghost is heading in
     * @return true if the ghost can continue moving
     */
    private boolean canMove( String direction, boolean inJail )
    {
        int v1 = 0;
        int v2 = 0;
        
        if ( direction.equals( "right" ) )
        {
            v1 = maze[y / 20][(x + 23) / 20];
            v2 = maze[(y + 19) / 20][(x + 23) / 20];
        }
        else if ( direction.equals( "left" ) )
        {
            v1 = maze[y / 20][(x - 4) / 20];
            v2 = maze[(y + 19) / 20][(x - 4) / 20];
        }
        else if ( direction.equals( "up" ) )
        {
            v1 = maze[(y - 4) / 20][x / 20];
            v2 = maze[(y - 4) / 20][(x + 19) / 20];
        }
        else if ( direction.equals( "down" ) )
        {
            v1 = maze[(y + 23) / 20][x / 20];
            v2 = maze[(y + 23) / 20][(x + 19) / 20];
        }
        
        if ((v1 == 1) || (v2 == 1))
        {
            return false;
        }
        
        if (inJail)
        {
            return true;
        }
        
        return (v1 != 4) && (v2 != 4);
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
    
    public void initialFrightenedMove()
    {
        // In the beginnning when the pan first eats the BlueMacaroni
        //The ghost will move in the opposite direction
        frightenedTimer.start(15);
        
        if (isInJail())
        {
            timer.saveTime();
        }
        
        dirReverse(dir);
        
        setFrightened(1);
        
        x += shiftXArr[dir];
        y += shiftYArr[dir];
        direction = dirArr[dir];
        
        Window.out.image( images.get( "edible" ), x , y );
    }
    
    /**
     * The ghosts' movements when the pan eats the blue macaroni
     */
    public void frightenedMove()
    {
        if (frightenedTimer.getSecond() <= 10)
        {
            // For 10 seconds after the pan eats the BlueMacaroni, the ghost will head in
            // a random direction when 
            if (!pan.touchingGhost( this ))
            {
                int index = 0;
                
                // If the ghost hits a wall, it will head in a random direction
                if (!canMove(direction, isInJail()))
                {
                    index = (int) (Math.random() * 4);
                    
                    while (!canMove(dirArr[index], isInJail()))
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
                
                Window.out.image( images.get( "edible" ), x , y );
                
                frightenedTimer.count();
            }
            else if (pan.touchingGhost(this) && !frightenedTimer.isTimeUp())
            {
                frightenedTimer.reset();
                setFrightened(0);
                this.backToJail();
                timer.start( 5 );
            }
        }
        else
        {
            setFrightened(2);
        }
    }
    
    public void frightenedFlash()
    {
        int ghostS = frightenedTimer.getSecond();
        
        if (!pan.touchingGhost( this ) && !frightenedTimer.isTimeUp())
        {
            int index = 0;
            
            // If the ghost hits a wall, it will head in a random direction
            if (!canMove(direction, isInJail()))
            {
                index = (int) (Math.random() * 4);
                
                while (!canMove(dirArr[index], isInJail()))
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
            
            if (ghostS % 2 == 1)
            {
                Window.out.image( images.get( "edible" ), x , y );
                frightenedTimer.count();
            }
            else if (ghostS % 2 == 0)
            {
                Window.out.image( images.get( "revert" ), x , y );
                frightenedTimer.count();
            }
        }
        else
        {
            if (pan.touchingGhost(this) && !frightenedTimer.isTimeUp())
            {
                frightenedTimer.reset();
                setFrightened(0);
                this.backToJail();
                timer.start( 5 );
            }
            else if (frightenedTimer.isTimeUp() && isInJail())
            {
                frightenedTimer.reset();
                setFrightened(0);
                timer.start( timer.getSavedTarget() );
            }
            else
            {
                frightenedTimer.reset();
                setFrightened(0);
            }
        }
    }
    
    public int getFrightened()
    {
        return frightened;
    }
    
    public void setFrightened(int value)
    {
        frightened = value;
    }
    
    public boolean isFrightened()
    {
        return frightened == 1 || frightened == 2 || frightened == 3;
    }
    
    public boolean isEaten()
    {
        return pan.touchingGhost( this ) && isFrightened();
    }
}
