public class Inky extends Ghost
{
    Blinky blinky;
    public Inky( int[][] m, Pan p , Blinky b, Timer t )
    {
        super( m, p, t );
        setImages();
        blinky = b;
    }

    public void setImages()
    {
        images.put( "up", "InkyUp.png" );
        images.put( "right", "InkyRight.png" );
        images.put( "left", "InkyLeft.png" );
        images.put( "down", "InkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }
    
    public void move()
    {
        if (isInJail() && gameTimer.getSecond() <= 20)
        {
            moveInJail();
        }
        else if (isInJail() && gameTimer.getSecond() > 20 && timer.getSecond() <= 5)
        {
            moveInJail();
            timer.count();
        }
        else if (isInJail() && gameTimer.getSecond() > 20 && timer.getSecond() > 5)
        {
            timer.reset();
            timer.stop();
            moveOutOfJail();
        }
        else if (isInJail() && gameTimer.getSecond() > 20)
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
        int panX = pan.getX();
        int dist = Math.abs( panX - blinky.getX() );
        int dist2 = 2 * dist;
        if ( pan.getDirection().equals( "up" ) || pan.getDirection().equals( "down" ) )
        {
            return panX;
        }
        else if ( pan.getDirection().equals( "left" ) )
        {
            if ( panX - dist2 >= 20 )
            {
                return panX - dist2;
            }
            else
            {
                return panX;
            }
        }
        else if ( pan.getDirection().equals( "right" ) )
        {
            if ( panX + dist2 <= 800 - 20 )
            {
                return panX + dist2;
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
        int dist = Math.abs( panY - blinky.getY() );
        int dist2 = 2 * dist;
        if ( pan.getDirection().equals( "left" ) || pan.getDirection().equals( "right" ) )
        {
            return panY;
        }
        else if ( pan.getDirection().equals( "up" ) )
        {
            if ( panY - dist2 >= 20 )
            {
                return panY - dist2;
            }
            else
            {
                return panY;
            }
        }
        else if ( pan.getDirection().equals( "down" ) )
        {
            if ( panY + dist2 <= 800 - 20 )
            {
                return panY + dist2;
            }
            else
            {
                return panY;
            }
        }
        return 0;
    }

}
