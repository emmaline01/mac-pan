import java.util.TreeMap;

import apcs.Window;


public class Pinky extends Ghost
{

    public Pinky( int[][] m, Pan p, Timer t )
    {
        super( m, p, t );
        setImages();
    }

    public void setImages()
    {
        images.put( "up", "PinkyUp.png" );
        images.put( "right", "PinkyRight.png" );
        images.put( "left", "PinkyLeft.png" );
        images.put( "down", "PinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }
    
    public void move()
    {
        if (isInJail() && gameTimer.getSecond() < 5)
        {
            moveOutOfJail();
        }
        else if (isInJail() && timer.getSecond() <= 5)
        {
            moveInJail();
            timer.count();
        }
        else if (isInJail() && timer.getSecond() > 5)
        {
            timer.reset();
            timer.stop();
            moveOutOfJail();
        }
        else
        {
            super.move();
        }
    }
    
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
}
