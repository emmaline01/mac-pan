public class Blinky extends Ghost
{

    public Blinky( int[][] m, Pan p )
    {
        super( m, p );
        changeDir(3);
        setImages();
        timer.start( 5 );
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
        if (isInJail() && getFrightened() == 0 && (!timer.isCounting() || timer.isTimeUp()))
        {
            moveOutOfJail();
            timer.reset();
        }
        else if (isInJail() && getFrightened() == 0 && !timer.isTimeUp())
        {
            moveInJail();
        }
        else if (getFrightened() == 3)
        {
            initialFrightenedMove();
        }
        else if (getFrightened() == 1)
        {
            frightenedMove();
        }
        else if (getFrightened() == 2)
        {
            frightenedFlash();
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
