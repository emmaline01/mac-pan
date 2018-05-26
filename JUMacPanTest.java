import static org.junit.Assert.*;

import org.junit.Test;

public class JUMacPanTest
{
    private int[][] maze = {
        //  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //1
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //2
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //3
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //4
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //5
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //6
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //7
        {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1}, //8
        {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 4, 4, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1}, //9
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //10
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 1, 1, 1, 1, 5, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //11
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //12
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //13
        {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, //14
        {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //15
        {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1}, //16
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, //17
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1}, //18
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //19 
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //20
    };
    private Pan pan = new Pan(maze);
    
     /*
     * Blinky Tests:
     * constructor
     * targetX
     * targetY
     */
    @Test
    public void blinkyConstructor()
    {
        Pan pan = new Pan(maze);
        Blinky blinky = new Blinky( maze , pan );
        assertNotNull( blinky );
    }
    

    @Test
    public void blinkyTargetX()
    {
        Pan pan = new Pan(maze);
        Blinky blinky = new Blinky( maze , pan );
        assertTrue( "<<blinkyTargetX Invalid>>", blinky.targetX() == pan.getX() );
    }

    @Test
    public void blinkyTargetY()
    {
        Pan pan = new Pan(maze);
        Blinky blinky = new Blinky( maze , pan );
        assertTrue( "<<blinkyTargetY Invalid>>", blinky.targetY() == pan.getY() );
    }

     /*
     * BlueMacaroni Tests:
     * constructor
     */

    @Test
    public void blueMacaroniConstructor()
    {
        Macaroni bm = new BlueMacaroni( maze, 40, 20 );
        assertNotNull( bm );
        assertTrue( "<<blueMacaroniConstructor Invalid>>", bm.getX() == 40 
                        && bm.getY() == 20 );
    }

    /*
     * Counter Tests:
     * Constructor
     * displayCounter
     * setNumEaten
     * getNumEaten
     */
    @Test
    public void counterConstructor()
    {
        Counter c = new Counter();
        assertTrue( "<<counterConstructor Invalid>>", c.getNumEaten() == -1 );
    }
    
    @Test
    public void counterSetNumEaten()
    {
        Counter c = new Counter();
        c.setNumEaten( 145 );
        assertTrue( "<<counterSetNumEaten Invalid>>", c.getNumEaten() == 145 );
    }
    
    @Test
    public void counterGetNumEaten()
    {
        Counter c = new Counter();
        c.setNumEaten( 432 );
        assertTrue( "<<counterSetNumEaten Invalid>>", c.getNumEaten() == 432 );
    }

    /*
     * Ghost Tests:
     * 
     * 
     * 
     */

    /*
     * Inky Tests:
     * 
     * 
     * 
     */

    /*
     * Macaroni Tests:
     *  Constructor
     *  place
     *  remove
     * 
     */
    @Test
    public void macaroniConstructor() {
        Macaroni mac = new Macaroni (maze, 20, 20);
        assertNotNull(mac);
    }
    

    @Test
    public void macaroniPlace()
    {
        Macaroni mac = new Macaroni( maze, 20, 40 );
        assertTrue( "<<macaroniPlace Invalid>>", mac.getX() == 20 && mac.getY() == 40 );
    }
    
    @Test
    public void macaroniRemove()
    {
        Macaroni mac = new Macaroni( maze, 20, 40 );
        mac.remove();
        assertTrue( "<<macaroniRemove Invalid>>", mac.isRemoved() == true );
    }

    

    /*
     * Pan Tests:
     * Constructor
     * move
     * canMove
     * touchingGhost
     * touchingMacaroni
     * touchingBlueMacaroni
     * getX
     * getY
     * getDirection
     */
    @Test
    public void panConstructor() {
        Pan p = new Pan(maze);
        assertNotNull(p);
    }
    
    @Test
    public void panMove() {
        
    }

    /*
     * Pinky Tests:
     * 
     * 
     * 
     */

    /*
     * Timer Tests:
     * 
     * 
     * 
     */

}
