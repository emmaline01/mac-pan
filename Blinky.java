import java.util.TreeMap;

import apcs.Window;


public class Blinky extends Ghost
{

    public Blinky( int[][] m, Pan p )
    {
        super( m, p );
        setImages();
    }


    @Override
    public void setImages()
    {
        images.put( "up", "BlinkyUp.png" );
        images.put( "right", "BlinkyRight.png" );
        images.put( "left", "BlinkyLeft.png" );
        images.put( "down", "BlinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }

    public String getName()
    {
        return "Blinky";
    }

    @Override
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
