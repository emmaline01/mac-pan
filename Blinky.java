/**
 * The Blinky class represents a Blinky ghost in the game (the red ghost). Its
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

public class Blinky extends Ghost
{

    /**
     * Constructor
     * 
     * @param m
     *            the maze represented by a 2D int array
     * @param p
     *            the Pan (or player) in the game
     */
    public Blinky( int[][] m, Pan p )
    {
        super( m, p );
        changeDir( 3 );
        setImages();
        timer.start( 5 );
    }


    /**
     * Blinky's implementation of Ghost's abstract method setImages. It sets the
     * images of Blinky in the images tree map based on direction.
     * 
     * @see Ghost#setImages()
     */
    public void setImages()
    {
        images.put( "up", "BlinkyUp.png" );
        images.put( "right", "BlinkyRight.png" );
        images.put( "left", "BlinkyLeft.png" );
        images.put( "down", "BlinkyDown.png" );
        images.put( "edible", "BlueGhost.png" );
        images.put( "revert", "RevertingBackGhost.png" );
    }


    /**
     * Blinky's implementation of Ghost's abstract method targetX. It finds a
     * target location based on the location the Pan.
     * 
     * @return Blinky's target x location
     * @see Ghost#targetX()
     */
    public int targetX()
    {
        return pan.getX();
    }


    /**
     * Blinky's implementation of Ghost's abstract method targetY. It finds a
     * target location based on the location the Pan.
     * 
     * @return Blinky's target y location
     * @see Ghost#targetY()
     */
    public int targetY()
    {
        return pan.getY();
    }

}
