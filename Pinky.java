import java.util.TreeMap;

import apcs.Window;


public class Pinky extends Ghost
{

    public Pinky( int[][] m, Pan p )
    {
        super( m, p );
        setImages();
    }


    @Override
    public void setImages()
    {
        images.put( "up", "PinkyUp.png" );
        images.put( "right", "PinkyRight.png" );
        images.put( "left", "PinkyLeft.png" );
        images.put( "down", "PinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }


    @Override
    public int targetX()
    {
        int panX = pan.getX();
        if ( pan.getDirection().equals( "up" ) || pan.getDirection().equals( "down" ) )
        {
            return panX;
        }
        else if ( pan.getDirection().equals( "left" ) )
        {
            if ( panX - 20 >= 20 )
            {
                return panX - 20;
            }
            else
            {
                return panX;
            }
        }
        else if ( pan.getDirection().equals( "right" ) )
        {
            if ( panX + 20 <= 800 - 20 )
            {
                return panX + 20;
            }
            else
            {
                return panX;
            }
        }
        return 0;
    }


    @Override
    public int targetY()
    {
        int panY = pan.getY();
        if ( pan.getDirection().equals( "left" ) || pan.getDirection().equals( "right" ) )
        {
            return panY;
        }
        else if ( pan.getDirection().equals( "up" ) )
        {
            if ( panY - 20 >= 20 )
            {
                return panY - 20;
            }
            else
            {
                return panY;
            }
        }
        else if ( pan.getDirection().equals( "down" ) )
        {
            if ( panY + 20 <= 800 - 20 )
            {
                return panY + 20;
            }
            else
            {
                return panY;
            }
        }
        return 0;
    }


    @Override
    public void move()
    {
        int targetX = targetX();
        int targetY = targetY();

        // TODO
        int diffX = Math.abs( targetX - x );
        int diffY = Math.abs( targetY - y );
        String xDir = "";
        String yDir = "";

        // Determines whether blinky should head right or left based on
        // x-coordinate.
        if ( targetX > x )
        {
            xDir = "right";
        }
        else if ( x > targetX )
        {
            xDir = "left";
        }

        // Determines whether blinky should head up or down based on
        // y-coordinate.
        if ( targetY > y )
        {
            yDir = "down";
        }
        else
        {
            yDir = "up";
        }

        if ( targetX > targetY && canMove( xDir ) )
        {
            direction = xDir;

            if ( xDir.equals( "right" ) )
            {
                x = x + 5;
            }
            else
            {
                x = x - 5;
            }
        }
        else if ( targetY > targetX && canMove( yDir ) )
        {
            direction = yDir;

            if ( xDir.equals( "up" ) )
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
