import java.util.TreeMap;


public class Pinky extends Ghost
{

    private int x, y, length, width;

    private TreeMap<String, String> images;

    private int[][] maze;

    private Pan pan;

    private String direction;


    public Pinky( int[][] m, Pan p )
    {
        super( m, p );
        direction = "";
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
        if ( pan.getDirection().equals( "up" ) || pan.getDirection().equals( "down" ))
        {
            return panX;
        }
        else if ( pan.getDirection().equals( "left" ))
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
        else if ( pan.getDirection().equals( "right" ))
        {
            if ( panX + 20 < 1000 )
        }
        return 0;
    }


    @Override
    public int targetY()
    {
        int panY = pan.getY();

        return 0;
    }


    @Override
    public void moveTo( int targetX, int targetY )
    {
        // TODO Auto-generated method stub

    }

}
