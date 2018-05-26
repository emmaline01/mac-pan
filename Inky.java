/**
 * The Inky class represents an Inky ghost in the game (the light blue ghost).
 * Its purpose is to chase the Pan around the board. It extends the Ghost class
 * and it is used in the main method of the MacPan class.
 *
 * @author Emmaline Mai, Shannon Liu, Anubha Kale
 * @version May 26, 2018
 * @author Period: 5
 * @author Assignment: MacPan
 *
 * @author Sources:
 *         http://www.todayifoundout.com/index.php/2015/10/ghosts-pac-man-work/
 */
public class Inky extends Ghost
{
    Blinky blinky;


    /**
     * Constructor
     * 
     * @param m
     *            the maze represented by a 2D int array
     * @param p
     *            the Pan (or player) in the game
     * @param b
     *            the Blinky ghost in the game
     */
    public Inky( int[][] m, Pan p, Blinky b )
    {
        super( m, p );
        setImages();
        blinky = b;
        timer.start( 10 );
    }


    /**
     * Inky's implementation of Ghost's abstract method setImages. It sets the
     * images of Inky in the images tree map based on direction.
     * 
     * @see Ghost#setImages()
     */
    public void setImages()
    {
        images.put( "up", "InkyUp.png" );
        images.put( "right", "InkyRight.png" );
        images.put( "left", "InkyLeft.png" );
        images.put( "down", "InkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }


    /**
     * Inky's implementation of Ghost's abstract method targetX. It finds a
     * target location based on the location of Blinky and the location of the
     * Pan.
     * 
     * @return Inky's target x location
     * @see Ghost#targetX()
     */
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


    /**
     * Inky's implementation of Ghost's abstract method targetY. It finds a
     * target location based on the location of Blinky and the location of the
     * Pan.
     * 
     * @return Inky's target y location
     * @see Ghost#targetX()
     */
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
            if ( panY + dist2 <= 400 - 20 )
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
