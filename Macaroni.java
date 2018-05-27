import apcs.Window;

/**
 * A Macaroni object represents a token (shaped as a Macaroni) that is placed on
 * the board for the Pan to eat. It is added to the board in the main method. It
 * has methods to place and remove it. Its fields are protected so that
 * BlueMacaroni (extends Macaroni) can use them.
 *
 * @author Anubha Kale, Shannon Liu, Emmaline Mai
 * @version May 20, 2018
 * @author Period: 5
 * @author Assignment: MacPan
 *
 * @author Sources: None
 */
public class Macaroni
{
    protected int[][] maze;

    protected boolean removed = false;

    protected int x, y;

    protected String image;


    /**
     * The Macaroni constructor.
     * 
     * @param maze
     *            The MacPan board which is represented by a 2d int array.
     * @param a
     *            The x coordinate of the location where the Macaroni is
     *            intended to be placed.
     * @param b
     *            The y coordinate of the location where the Macaroni is
     *            intended to be placed.
     */
    public Macaroni( int[][] maze, int a, int b )
    {
        this.maze = maze;
        x = a;
        y = b;
        image = "macaroniPic.png";
    }


    /**
     * The place method places the Macaroni on the board based on its x and y
     * coordinates that were passed in its creation (through the constructor).
     */
    public void place()
    {
        if ( removed != true )
        {
            Window.out.image( image, x * 20, y * 20 );
            maze[y][x] = 2;
        }
    }

    /**
     * The method remove sets removed to true.
     */
    public void remove()
    {
        removed = true;
    }


    /*
     * FOR TESTING PURPOSES ONLY
     */

    /**
     * The method isRemoved returns whether or not the Macaroni is removed (has
     * been eaten by the pan).
     * 
     * @return removed Whether or not the Macaroni has been eaten by Pan.
     */
    public boolean isRemoved()
    {
        return removed;
    }


    /**
     * Accessor method for pan's x coordinate
     * 
     * @return x coordinate of the pan
     */
    public int getX()
    {
        return x;
    }


    /**
     * Accessor method for pan's y coordinate
     * 
     * @return y coordinate of the pan
     */
    public int getY()
    {
        return y;
    }

}
