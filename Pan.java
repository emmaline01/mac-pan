import java.util.TreeMap;

import apcs.Window;


/**
 * The Pan class represents the player on the board. It is added to the board in
 * the main method. It is controlled by the user, who navigates in through the
 * maze as it eats macaroni while avoiding ghosts.
 * 
 *
 * @author Emmaline Mai, Shannon Liu, Anubha Kale
 * @version May 18, 2018
 * @author Period: 5
 * @author Assignment: MacPan
 *
 * @author Sources: None
 */
public class Pan
{
    private int x = 400;

    private int y = 360;

    private int[][] maze;

    private String direction;

    private Timer t;

    private String next;


    /**
     * Pan Constructor
     * 
     * @param m
     *            2d int array that represents the maze
     */
    public Pan( int[][] m )
    {
        maze = m;
        direction = "";

        t = new Timer();
        next = "";
    }


    /**
     * The Pan's move method moves the pan based on the user key input. It
     * checks if the Pan can move in certain directions using the canMove method
     * and adjusts its x and y fields accordingly.
     */
    public void move()
    {
        String[] dir = { "up", "down", "left", "right" };
        TreeMap<String, Integer> movement = new TreeMap<String, Integer>();
        movement.put( "up", new Integer( -5 ) );
        movement.put( "down", new Integer( 5 ) );
        movement.put( "right", new Integer( 5 ) );
        movement.put( "left", new Integer( -5 ) );
        boolean moved = false;

        if ( t.isCounting() )
        {
            t.count();
        }

        for ( String d : dir )
        {
            if ( Window.key.pressed( d ) )
            {
                if ( canMove( d ) )
                {
                    direction = d;
                    if ( d.equals( "right" ) || d.equals( "left" ) )
                    {
                        x = x + movement.get( d );
                    }
                    else
                    {
                        y = y + movement.get( d );
                    }
                    moved = true;
                }
                else
                {
                    if ( t.isCounting() )
                    {
                        t.reset();
                    }
                    next = d;
                    t.start( 1 );
                }
            }
        }

        // check if it can turn any time soon
        if ( t.isCounting() && t.getMillisecond() < 500 )
        {
            if ( canMove( next ) )
            {
                direction = next;
                if ( next.equals( "right" ) || next.equals( "left" ) )
                {
                    x = x + movement.get( next );
                }
                else
                {
                    y = y + movement.get( next );
                }
                next = "";
                t.reset();
            }
        }

        else
        {
            t.reset();
        }

        // keep going in the same direction
        if ( moved == false )
        {
            for ( String d : dir )
            {
                if ( direction.equals( d ) && canMove( d ) )
                {
                    if ( d.equals( "right" ) || d.equals( "left" ) )
                    {
                        x = x + movement.get( d );
                    }
                    else
                    {
                        y = y + movement.get( d );
                    }
                }
            }
        }

        Window.out.image( "pan1.png", x, y );
    }


    /**
     * The canMove method indicates whether or not the Pan can move in the
     * direction passed based on the coordinates of the board and maze.
     * 
     * @param direction
     *            String containing the direction can be either "up", "right",
     *            "down", or "left".
     * @return true if the Pan can move in the direction specified, false
     *         otherwise
     */
    private boolean canMove( String direction )
    {
        if ( direction.equals( "right" ) && ( x + 20 ) / 20 + 1 <= maze[0].length
            && maze[( y - 10 ) / 20 + 1][( x + 20 ) / 20] != 1 && y % 20 == 0 )
        {
            return true;
        }
        else if ( direction.equals( "left" ) && ( x / 20 ) >= 0
            && maze[( y - 10 ) / 20 + 1][( x - 5 ) / 20] != 1 && y % 20 == 0 )
        {
            return true;
        }
        else if ( direction.equals( "up" ) && ( y - 10 ) / 20 + 1 >= 0
            && maze[( y - 5 ) / 20][( x - 10 ) / 20 + 1] != 1 && x % 20 == 0 )
        {
            return true;
        }
        else if ( direction.equals( "down" ) && ( y + 40 ) / 20 + 1 <= maze.length
            && maze[( y + 20 ) / 20][( x - 10 ) / 20 + 1] != 1
            && maze[( y + 20 ) / 20][( x - 10 ) / 20 + 1] != 4 && x % 20 == 0 )
        {
            return true;
        }
        return false;
    }


    /**
     * The touchingGhost method indicates whether the Pan is touching a Ghost.
     * 
     * @param g
     *            the Ghost to be checked
     * @return true if the Pan is touching the Ghost, false otherwise
     */
    public boolean touchingGhost( Ghost g )
    {
        if ( Math.abs( g.getX() - x ) <= 10 && Math.abs( g.getY() - y ) <= 10 )
        {
            return true;
        }
        return false;
    }


    /**
     * The touchingMacaroni method takes the row and column of a maze and checks
     * if the Pan is touching a Macaroni at that location.
     * 
     * @param arrayY
     *            the column of the board
     * @param arrayX
     *            the row of the board
     * @return true if Pan is touching a macaroni at that location, false
     *         otherwise
     */
    public boolean touchingMacaroni( int arrayY, int arrayX )
    {
        if ( maze[arrayY][arrayX] == 2 && ( Math.abs( ( 10 + arrayX * 20 ) - ( x + 10 ) ) <= 10 )
            && ( Math.abs( ( 10 + arrayY * 20 ) - ( y + 10 ) ) <= 10 ) )
        {
            maze[arrayY][arrayX] = 0;
            return true;
        }
        return false;
    }


    /**
     * The touchingMacaroni method takes the row and column of a maze and checks
     * if the Pan is touching a Blue Macaroni at that location.
     * 
     * @param arrayY
     *            the column of the board
     * @param arrayX
     *            the row of the board
     * @return true if Pan is touching a Blue Macaroni at that location, false
     *         otherwise
     */
    public boolean touchingBlueMacaroni( int arrayY, int arrayX )
    {
        if ( maze[arrayY][arrayX] == 5 && ( Math.abs( ( 10 + arrayX * 20 ) - ( x + 10 ) ) <= 10 )
            && ( Math.abs( ( 10 + arrayY * 20 ) - ( y + 10 ) ) <= 10 ) )
        {
            maze[arrayY][arrayX] = 0;
            return true;
        }
        return false;
    }


    /**
     * Accessor method for Pan's x coordinate
     * 
     * @return x coordinate of Pan's current location
     */
    public int getX()
    {
        return x;
    }


    /**
     * Accessor method for Pan's y coordinate
     * 
     * @return y coordinate of Pan's current location
     */
    public int getY()
    {
        return y;
    }


    /**
     * Accessor method for Pan's x direction
     * 
     * @return direction The direction that the Pan is facing and moving in.
     */
    public String getDirection()
    {
        return direction;
    }


    // methods for testing

    /**
     * 
     * Setter method for direction.
     */
    public void setDirection( String s )
    {
        direction = s;
    }


    /**
     * 
     * Setter method for the x coordinate.
     * 
     * @param i
     *            is the new x coordinate
     */
    public void setX( int i )
    {
        x = i;
    }


    /**
     * 
     * Setter method for the y coordinate.
     * 
     * @param i
     *            is the new y coordinate
     */
    public void setY( int i )
    {
        y = i;
    }

    /**
     * 
     * Accessor method for the canMove test
     * @param s is the direction it's checking
     * @return whether it can move in that direction
     */
    public boolean canMoveTest( String s )
    {
        return canMove( s );
    }
    
    /**
     * 
     * Starts a timer to test that section of the move method's code.
     * @param n is the direction the Pan wants to move next
     */
    public void timerTest( String n) {
        t.start( 1 );
        next = n;
    }
}
