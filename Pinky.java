/**
 * The Pinky class represents an Pinky ghost in the game (the pink ghost). Its
 * purpose is to chase the Pan around the board. It extends the Ghost class and
 * it is used in the main method of the MacPan class.
 *
 * @author Emmaline Mai, Shannon Liu, Anubha Kale
 * @version May 26, 2018
 * @author Period: 5
 * @author Assignment: MacPan
 *
 * @author Sources:
 *         http://www.todayifoundout.com/index.php/2015/10/ghosts-pac-man-work/
 */
public class Pinky extends Ghost
{

    /**
     * Constructor
     * 
     * @param m
     *            the maze represented by a 2D int array
     * @param p
     *            the Pan (or player) in the game
     */
    public Pinky( int[][] m, Pan p )
    {
        super( m, p );
        setImages();
    }


    /**
     * Pinky's implementation of Ghost's abstract method setImages. It sets the
     * images of Pinky in the images tree map based on direction.
     * 
     * @see Ghost#setImages()
     */
    public void setImages()
    {
        images.put( "up", "PinkyUp.png" );
        images.put( "right", "PinkyRight.png" );
        images.put( "left", "PinkyLeft.png" );
        images.put( "down", "PinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }


    /**
     * Pinky's implementation of Ghost's abstract method targetX. It finds a
     * target location based on the location and direction of the Pan by trying
     * to calculate where the Pan will soon be.
     * 
     * @return Pinky's target x location
     * @see Ghost#targetX()
     */
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


    /**
     * Pinky's implementation of Ghost's abstract method targetY. It finds a
     * target location based on the location and direction of the Pan by trying
     * to calculate where the Pan will soon be.
     * 
     * @return Pinky's target y location
     * @see Ghost#targetY()
     */
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
            if ( panY + 20 <= 400 - 20 )
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
