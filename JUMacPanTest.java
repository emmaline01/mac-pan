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
     * constructor
     * targetX
     * targetY
     */
    @Test
    public void inkyConstructor()
    {
        Pan pan = new Pan( maze );
        Blinky blinky = new Blinky( maze, pan );
        Inky inky = new Inky( maze, pan, blinky );
        assertNotNull( inky );
    }


    @Test
    public void inkyTargetX()
    {
        Pan pan = new Pan( maze );
        Blinky blinky = new Blinky( maze, pan );
        Inky inky = new Inky( maze, pan, blinky );
        pan.setDirection( "up" );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == pan.getX() );
        pan.setDirection( "down" );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == pan.getX() );
        int dist = 2 * Math.abs( pan.getX() - blinky.getX() );
        pan.setDirection( "right" );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == pan.getX() + dist );
        pan.setDirection( "left" );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == pan.getX() - dist );
        pan.setX( 20 );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == pan.getX() );
        pan.setDirection( "right" ); 
        pan.setX( 780 );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == pan.getX() );
        pan.setDirection( "invalid" );
        assertTrue( "<<inkyTargetX Invalid>>", inky.targetX() == 0 );
    }


    @Test
    public void inkyTargetY()
    {
        Pan pan = new Pan( maze );
        Blinky blinky = new Blinky( maze, pan );
        Inky inky = new Inky( maze, pan, blinky );
        pan.setX( 100 );
        pan.setY( 380 );
        blinky.setX( 100 );
        blinky.setY( 380 );
        pan.setDirection( "right" );
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == pan.getY() );
        pan.setDirection( "left" );
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == pan.getY() );
        int dist = 2 * Math.abs( pan.getY() - blinky.getY() );
        pan.setDirection( "down" );
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == pan.getY() + dist );
        pan.setDirection( "up" );
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == pan.getY() - dist );
        pan.setY( 20 );
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == pan.getY() );
        pan.setDirection( "down" ); 
        pan.setX( 100 );
        pan.setY( 380 );
        blinky.setX( 200 );
        blinky.setY( 360 );
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == pan.getY() );
        pan.setDirection( "invalid" );
        System.out.println(inky.targetY() + " " + pan.getY());
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == 0 );
    }

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
     * constructor
     * targetX
     * targetY
     */
    @Test
    public void pinkyConstructor()
    {
        Pan pan = new Pan( maze );
        Pinky pinky = new Pinky( maze, pan );
        assertNotNull( pinky );
    }


    @Test
    public void pinkyTargetX()
    {
        Pan pan = new Pan( maze );
        Pinky pinky = new Pinky( maze, pan );
        pan.setDirection( "left" );
        assertTrue( "<<pinkyTargetX Invalid>>", pinky.targetX() == pan.getX() - 20 );
        pan.setDirection( "right" );
        assertTrue( "<<pinkyTargetX Invalid>>", pinky.targetX() == pan.getX() + 20 );
        pan.setDirection( "up" );
        assertTrue( "<<pinkyTargetX Invalid>>", pinky.targetX() == pan.getX() );
        pan.setDirection( "down" );
        assertTrue( "<<pinkyTargetX Invalid>>", pinky.targetX() == pan.getX() );
    }


    @Test
    public void pinkyTargetY()
    {
        Pan pan = new Pan( maze );
        Pinky pinky = new Pinky( maze, pan );
        pan.setDirection( "up" );
        assertTrue( "<<pinkyTargetY Invalid>>", pinky.targetY() == pan.getY() - 20 );
        pan.setDirection( "down" );
        assertTrue( "<<pinkyTargetY Invalid>>", pinky.targetY() == pan.getY() + 20 );
        pan.setDirection( "right" );
        assertTrue( "<<pinkyTargetY Invalid>>", pinky.targetY() == pan.getY() );
        pan.setDirection( "left" );
        assertTrue( "<<pinkyTargetY Invalid>>", pinky.targetY() == pan.getY() );
    }

    /*
     * Timer Tests:
     * 
     * 
     * 
     */

}
