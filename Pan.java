import apcs.Window;


public class Pan
{
    int x;

    int y;

    int[][] maze;

    String direction;

    /**
     * Constructs a pan by setting the starting position and initializing the provate variables.
     * @param m is the maze.
     */
    public Pan( int[][] m )
    {
        x = 400; 
        y = 360;
        maze = m;
        direction = "";

    }

    /**
     * 
     * The move method allows the Pan to move when certain keys are pressed. 
     * If checks if it can move in that direction, then moves by changing its coordinates, 
     * redrawing itself, and changing the private variable that holds its direction.
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

        Window.out.image( "pan1.png", x , y );
    }


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


    public boolean touchingGhost( Ghost g )
    {
        if ( Math.abs( g.getX() - x ) <= 10 && Math.abs( g.getY() - y ) <= 10 )
        {
            return true;
        }
        return false;
    }


    public boolean touchingMacaroni( int arrayY, int arrayX )
    {
        // know that Macaroni radius = 7, pan radius = 12
        // if (Math.abs( m.getX() - x) <= 17 || Math.abs( m.getY() - y) <= 17) {
        // return true;
        // }
        // return false;

        if ( maze[arrayY][arrayX] == 2 && 
                        ( Math.abs( ( 10 + arrayX * 20 ) - (x + 10) ) <= 10 )
                        && (Math.abs( (10 + arrayY * 20 ) - (y + 10) ) <= 10 ) )
        {
            maze[arrayY][arrayX] = 0;
            return true;
        }
        return false;
    }
    
    public boolean touchingBlueMacaroni(int arrayY, int arrayX)
    {
        //TODO
        if ( maze[arrayY][arrayX] == 5 && 
                        ( Math.abs( ( 10 + arrayX * 20 ) - (x + 10) ) <= 10 )
                        && (Math.abs( (10 + arrayY * 20 ) - (y + 10) ) <= 10 ) )
        {
            maze[arrayY][arrayX] = 0;
            return true;
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


    public String getDirection()
    {
        return direction;
    }
}
