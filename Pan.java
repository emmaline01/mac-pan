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
    int x;

    int y;

    int[][] maze;

    String direction;


    /**
     * Pan Constructor
     * 
     * @param m
     *            2d int array that represents the maze
     */
    public Pan( int[][] m )
    {
        x = 400;
        y = 360;
        maze = m;
        direction = "";
    }


    /**
     * The Pan's move method moves the pan based on the user key input. It
     * checks if the Pan can move in certain directions using the canMove method
     * and adjusts its x and y fields accordingly.
     */
    public void move()
    {
        if ( Window.key.pressed( "right" ) )
        {
            if ( canMove( "right" ) )
            {
                x = x + 5;
                direction = "right";
            }
        }
        else if ( Window.key.pressed( "left" ) )
        {
            if ( canMove( "left" ) )
            {
                x = x - 5;
                direction = "left";
            }
        }
        else if ( Window.key.pressed( "up" ) )
        {
            if ( canMove( "up" ) )
            {
                y = y - 5;
                direction = "up";
            }
        }
        else if ( Window.key.pressed( "down" ) )
        {
            if ( canMove( "down" ) )
            {
                y = y + 5;
                direction = "down";
            }
        }
        else
        {
            if ( direction.equals( "right" ) )
            {
                if ( canMove( "right" ) )
                {
                    x = x + 5;
                }
            }
            else if ( direction.equals( "left" ) )
            {
                if ( canMove( "left" ) )
                {
                    x = x - 5;
                }
            }
            else if ( direction.equals( "up" ) )
            {
                if ( canMove( "up" ) )
                {
                    y = y - 5;
                }
            }
            else if ( direction.equals( "down" ) )
            {
                if ( canMove( "down" ) )
                {
                    y = y + 5;
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
        if ( direction.equals( "right" ) && ( x + 50 - 10 ) / 20 + 1 <= maze[0].length
            && maze[( y - 10 ) / 20 + 1][( x + 25 ) / 20] != 1 )
        {
            return true;
        }
        else if ( direction.equals( "left" ) && ( x / 20 ) >= 0
            && maze[( y - 10 ) / 20 + 1][( x - 5 ) / 20] != 1 )
        {
            return true;
        }
        else if ( direction.equals( "up" ) && ( y - 10 ) / 20 + 1 >= 0
            && maze[( y - 5 ) / 20][( x - 10 ) / 20 + 1] != 1 )
        {
            return true;
        }
        else if ( direction.equals( "down" ) && ( y + 50 - 10 ) / 20 + 1 <= maze.length
            && maze[( y + 25 - 10 ) / 20][( x - 10 ) / 20 + 1] != 1
            && maze[( y + 25 - 10 ) / 20][( x - 10 ) / 20 + 1] != 4 )
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
}
