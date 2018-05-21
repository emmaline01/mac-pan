import java.util.TreeMap;

import apcs.Window;


public class Blinky extends Ghost
{

    public Blinky( int[][] m, Pan p, Timer t )
    {
        super( m, p, t );
        dir = 3;
        direction = "left";
        setImages();
    }

    public void setImages()
    {
        images.put( "up", "BlinkyUp.png" );
        images.put( "right", "BlinkyRight.png" );
        images.put( "left", "BlinkyLeft.png" );
        images.put( "down", "BlinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }
    
    public void move()
    {
        if (isInJail() && gameTimer.getSecond() <= 10)
        {
            moveInJail();
        }
        else if (isInJail() && gameTimer.getSecond() > 10 && timer.getSecond() <= 5)
        {
            moveInJail();
            timer.count();
        }
        else if (isInJail() && gameTimer.getSecond() > 10 && timer.getSecond() > 5)
        {
            timer.reset();
            timer.stop();
            moveOutOfJail();
        }
        else if (isInJail() && gameTimer.getSecond() > 10)
        {
            moveOutOfJail();
        }
        else
        {
            super.move();
        }
    }

    public int targetX()
    {
        return pan.getX();
    }


    @Override
    public int targetY()
    {
        return pan.getY();
    }


}
