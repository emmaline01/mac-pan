import java.util.TreeMap;
import apcs.Window;


/**
 * Abstract ghost superclass
 * Determines and executes the movements of the ghost using target coordinates 
 * from the subclasses: Pinky, Blinky, and Inky
 *
 * @author Emmaline Mai, Shannon Liu, Anubha Kale
 * @version May 26, 2018
 * @author Period: 5
 * @author Assignment: MacPan
 *
 * @author Sources: n/a
 */
public abstract class Ghost
{
    protected int x, y;

    protected int[][] maze;

    protected Pan pan;

    protected String direction;

    protected TreeMap<String, String> images;

    protected Timer timer;

    protected Timer frightenedTimer;

    private int frightened = 0; // 0: not frightened, 1: blue, 2: blinking blue,
                                // 3: initial frightened

    protected int dir;

    protected String[] dirArr = { "right", "up", "down", "left" };

    protected int[] oppDirIndex = { 3, 2, 1, 0 };

    protected int[] shiftXArr = { 4, 0, 0, -4 };

    protected int[] shiftYArr = { 0, -4, 4, 0 };


    /**
     * Constructor of the Ghost class
     * 
     * @param m maze
     * @param p pan
     */
    public Ghost( int[][] m, Pan p )
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
     * 
     * @return target x-coordinate
     */
    public abstract int targetX();


    /**
     * Find y-coordinate the image should move to
     * 
     * @return target y-coordinate
     */
    public abstract int targetY();


    /**
     * Places ghost on the default coordinate (380, 200) Sets the direction of
     * the ghost to the right.
     */
    public void initMove()
    {
        x = 380;
        y = 200;
        changeDir( 0 );
    }


    /**
     * Change the coordinates according the direction it will head in and
     * displays the image.
     */
    public void moveForward()
    {
        x += shiftXArr[dir];
        y += shiftYArr[dir];
        direction = dirArr[dir];

        Window.out.image( images.get( direction ), x, y );
        timer.count();
    }


    /**
     * Reverses the direction the ghost heads in. If it was heading right, it
     * will head left and vice versa. If it was heading up, it will head down
     * and vice versa.
     * 
     * @param d The direction the ghost was heading in initially
     */
    public void dirReverse( int d )
    {
        dir = oppDirIndex[d];
        direction = dirArr[dir];
    }


    /**
     * Determines whether or not the ghost is currently in the jail
     * 
     * @return true if the ghost is in jail
     */
    public boolean isInJail()
    {
        return ( ( maze[y / 20][x / 20] == 4 ) || ( maze[y / 20][( x + 19 ) / 20] == 4 )
            || ( maze[( y + 19 ) / 20][x / 20] == 4 )
            || ( maze[( y + 19 ) / 20][( x + 19 ) / 20] == 4 ) );
    }


    /**
     * The ghost will move horizontally until it hits the wall, reverses its
     * direction, and repeat.
     */
    public void moveInJail()
    {
        if ( !canMove( direction, isInJail() ) )
        {
            dirReverse( dir );
            moveForward();
            timer.count();
        }
        else
        {
            moveForward();
            timer.count();
        }
    }


    /**
     * Change the direction the ghost is heading in to a given direction.
     * 
     * @param d The index of the direction the ghost will head in.
     */
    public void changeDir( int d )
    {
        dir = d;
        direction = dirArr[d];
    }


    /**
     * Move in the direction that is specified.
     * 
     * @param d Index of the direction.
     */
    public void moveDir( int d )
    {
        changeDir( d );
        moveForward();
    }


    /**
     * Move to the center and move out of the jail.
     */
    public void moveOutOfJail()
    {
        if ( x < 388 )
        {
            moveDir( 0 );
        }
        else if ( x > 392 )
        {
            moveDir( 3 );
        }
        else
        {
            moveDir( 1 );
        }
    }


    /**
     * The ghost is moved back to it's initial position in jail
     */
    public void backToJail()
    {
        initMove();
        Window.out.image( images.get( direction ), x, y );
    }


    /**
     * 
     * Move to target coordinates that is calculated in the subclass.
     */
    public void move()
    {
        if ( isInJail() && getFrightened() == 0 && ( !timer.isCounting() || timer.isTimeUp() ) )
        {
            moveOutOfJail();
            timer.reset();
        }
        else if ( isInJail() && getFrightened() == 0 && timer.isCounting() )
        {
            moveInJail();
        }
        else if ( getFrightened() == 3 )
        {
            initialFrightenedMove();
        }
        else if ( getFrightened() == 1 )
        {
            frightenedMove();
        }
        else if ( getFrightened() == 2 )
        {
            frightenedFlash();
        }
        else
        {
            int targetX = targetX();
            int targetY = targetY();

            // Find how far each current coordinate value is to the target
            // coordinate values
            int diffX = targetX - x;
            int diffY = targetY - y;

            int[] dirOrder = new int[4];
            if ( diffX >= Math.abs( diffY ) )
            {
                dirOrder[0] = 0; // right
            }
            else if ( diffX <= -Math.abs( diffY ) )
            {
                dirOrder[0] = 3; // left
            }
            else if ( diffY >= Math.abs( diffX ) )
            {
                dirOrder[0] = 2; // down
            }
            else
            {
                dirOrder[0] = 1; // up
            }

            if ( dirOrder[0] == 0 || dirOrder[0] == 3 )
            {
                if ( diffY >= 0 )
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
                if ( diffX >= 0 )
                {
                    dirOrder[1] = 0; // right
                }
                else
                {
                    dirOrder[1] = 3; // left
                }
            }

            dirOrder[2] = oppDirIndex[dirOrder[0]];
            dirOrder[3] = oppDirIndex[dirOrder[1]];

            for ( int i = 0; i < dirOrder.length; i++ )
            {
                changeDir( dirOrder[i] );

                if ( canMove( direction, isInJail() ) )
                {
                    moveForward();
                    break;
                }
            }
        }
    }


    /**
     * Identifies whether or not the ghost can move forward in the direction
     * it's facing
     * 
     * @param direction The direction the ghost is heading in
     * @return true if the ghost can continue moving
     */
    private boolean canMove( String direction, boolean inJail )
    {
        int v1 = 0;
        int v2 = 0;

        if ( direction.equals( "right" ) )
        {
            v1 = maze[y / 20][( x + 23 ) / 20];
            v2 = maze[( y + 19 ) / 20][( x + 23 ) / 20];
        }
        else if ( direction.equals( "left" ) )
        {
            v1 = maze[y / 20][( x - 4 ) / 20];
            v2 = maze[( y + 19 ) / 20][( x - 4 ) / 20];
        }
        else if ( direction.equals( "up" ) )
        {
            v1 = maze[( y - 4 ) / 20][x / 20];
            v2 = maze[( y - 4 ) / 20][( x + 19 ) / 20];
        }
        else if ( direction.equals( "down" ) )
        {
            v1 = maze[( y + 23 ) / 20][x / 20];
            v2 = maze[( y + 23 ) / 20][( x + 19 ) / 20];
        }

        if ( ( v1 == 1 ) || ( v2 == 1 ) )
        {
            return false;
        }

        if ( inJail )
        {
            return true;
        }

        return ( v1 != 4 ) && ( v2 != 4 );
    }


    /**
     * The ghost's initial movement when it becomes frightened
     */
    public void initialFrightenedMove()
    {
        // In the beginnning when the pan first eats the BlueMacaroni
        // The ghost will move in the opposite direction
        frightenedTimer.start( 15 );

        if ( isInJail() )
        {
            timer.saveTime();
        }

        dirReverse( dir );

        setFrightened( 1 );

        x += shiftXArr[dir];
        y += shiftYArr[dir];
        direction = dirArr[dir];

        Window.out.image( images.get( "edible" ), x, y );
    }


    /**
     * The ghosts' movements 10 seconds after the pan eats the blue macaroni If
     * the ghost is eaten, it will return to the jail and stay for 5 seconds
     * before leaving
     */
    public void frightenedMove()
    {
        if ( frightenedTimer.getSecond() <= 10 )
        {
            // For 10 seconds after the pan eats the BlueMacaroni, the ghost
            // will head in
            // a random direction when
            if ( !pan.touchingGhost( this ) )
            {
                int index = 0;

                // If the ghost hits a wall, it will head in a random direction
                if ( !canMove( direction, isInJail() ) )
                {
                    index = (int)( Math.random() * 4 );

                    while ( !canMove( dirArr[index], isInJail() ) )
                    {
                        index = (int)( Math.random() * 4 );
                    }
                }
                else
                {
                    for ( int i = 0; i < dirArr.length; i++ )
                    {
                        if ( dirArr[i].equals( direction ) )
                        {
                            index = i;
                            break;
                        }
                    }
                }

                x += shiftXArr[index];
                y += shiftYArr[index];
                direction = dirArr[index];

                Window.out.image( images.get( "edible" ), x, y );

                frightenedTimer.count();
            }
            else if ( pan.touchingGhost( this ) && !frightenedTimer.isTimeUp() )
            {
                frightenedTimer.reset();
                setFrightened( 0 );
                this.backToJail();
                timer.start( 5 );
            }
        }
        else
        {
            setFrightened( 2 );
        }
    }


    /**
     * The ghost's flashing movement 5 seconds before they return to normal If
     * the ghost is eaten, it will return to the jail and stay for 5 seconds
     * before leaving
     */
    public void frightenedFlash()
    {
        int ghostS = frightenedTimer.getSecond();

        if ( !pan.touchingGhost( this ) && !frightenedTimer.isTimeUp() )
        {
            int index = 0;

            // If the ghost hits a wall, it will head in a random direction
            if ( !canMove( direction, isInJail() ) )
            {
                index = (int)( Math.random() * 4 );

                while ( !canMove( dirArr[index], isInJail() ) )
                {
                    index = (int)( Math.random() * 4 );
                }
            }
            else
            {
                for ( int i = 0; i < dirArr.length; i++ )
                {
                    if ( dirArr[i].equals( direction ) )
                    {
                        index = i;
                        break;
                    }
                }
            }

            x += shiftXArr[index];
            y += shiftYArr[index];
            direction = dirArr[index];

            // If the frightenedTimer of the ghost is odd, it will be blue,
            // else, it will be white
            if ( ghostS % 2 == 1 )
            {
                Window.out.image( images.get( "edible" ), x, y );
                frightenedTimer.count();
            }
            else if ( ghostS % 2 == 0 )
            {
                Window.out.image( images.get( "revert" ), x, y );
                frightenedTimer.count();
            }
        }
        else
        {
            if ( pan.touchingGhost( this ) && !frightenedTimer.isTimeUp() )
            {
                frightenedTimer.reset();
                setFrightened( 0 );
                this.backToJail();
                timer.start( 5 );
            }
            else if ( frightenedTimer.isTimeUp() && isInJail() )
            {
                frightenedTimer.reset();
                setFrightened( 0 );
                timer.start( timer.getSavedTarget() );
            }
            else
            {
                frightenedTimer.reset();
                setFrightened( 0 );
            }
        }
    }


    /**
     * Returns the frightened mode number that the ghost is in
     * 
     * @return frightened mode number
     */
    public int getFrightened()
    {
        return frightened;
    }


    /**
     * Sets the frightened mode number to a given value
     * 
     * @param value
     *            The given frightened mode number
     */
    public void setFrightened( int value )
    {
        frightened = value;
    }


    /**
     * Returns true if the frightened number of the ghost is 1, 2, or 3.
     * 
     * @return true if the ghost is in frightened mode
     */
    public boolean isFrightened()
    {
        return frightened == 1 || frightened == 2 || frightened == 3;
    }


    /**
     * Returns true if the ghost touches the pan in frightened mode
     * 
     * @return true if the ghost is eaten by pan
     */
    public boolean isEaten()
    {
        return pan.touchingGhost( this ) && isFrightened();
    }


    /*
     * FOR TESTING PURPOSES ONLY
     */

    /**
     * Sets the x value to the given number
     */
    public void setX( int n )
    {
        x = n;
    }


    /**
     * Sets the y value to the given number
     */
    public void setY( int n )
    {
        y = n;
    }


    /**
     * Returns the x-coordinate of the ghost
     * 
     * @return current x-coordinate of the ghost
     */
    public int getX()
    {
        return x;
    }


    public void setDir( int n )
    {
        dir = n;
        direction = dirArr[n];
    }


    /**
     * Returns the y-coordinate of the ghost
     * 
     * @return current y-coordinate of the ghost
     */
    public int getY()
    {
        return y;
    }


    /**
     * Returns the index of the direction
     * 
     * @return dir Index of the direction
     */
    public int getDir()
    {
        return dir;
    }


    /**
     * Returns the string form of the direction
     * 
     * @return direction String of direction
     */
    public String getDirection()
    {
        return direction;
    }


    /**
     * Returns the timer of the ghost when it's in jail.
     * 
     * @return timer
     */
    public Timer getTimer()
    {
        return timer;
    }


    /**
     * Returns the timer of the ghost when it's in frightened mode.
     * 
     * @return frightenedTimer()
     */
    public Timer getFrightenedTimer()
    {
        return frightenedTimer;
    }
}
