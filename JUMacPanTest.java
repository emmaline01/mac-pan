import static org.junit.Assert.*;

import org.junit.Test;

import apcs.Window;

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
     * Constructor
     * move
     * dirReverse
     * isInJail
     * changeDir
     * initialFrightenedMove
     * frightenedFlash
     * isEaten
     */
    
    @Test
    public void ghostConstructor()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        
        assertTrue( "<<ghostConstructor Invalid>>", g.getX() == 380 && g.getY() == 200 
                        && g.getDir() == 0 && g.getDirection() == "right" );
    }
    
    @Test
    public void ghostReverseDir()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        
        g.dirReverse( 0 );
        
        assertTrue( "<<ghostReverseDir Invalid>>", g.getDir() == 3 && g.getDirection() == "left" );
        
        g.setDir( 1 );
        g.dirReverse( 1 );
        
        assertTrue( "<<ghostReverseDir Invalid>>", g.getDir() == 2 && g.getDirection() == "down" );
    }
    
    @Test
    public void ghostChangeDir()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        
        g.changeDir( 2 );
        
        assertTrue( "<<ghostReverseDir Invalid>>", g.getDir() == 2 && g.getDirection() == "down" );
    }
    
    @Test
    public void ghostMoveInJail()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        g.moveInJail();
        assertTrue( "<<ghostMoveInJail Invalid>>", g.getX() == 384 && g.getY() == 200 
                        && g.getDir() == 0 && g.getDirection() == "right" );
    }
    
    @Test
    public void ghostIsInJail()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        g.setX( 380 );
        g.setY( 200 );
        assertTrue( "<<ghostIsInJail Invalid>>", g.isInJail());
        
        g.setX( 436 );
        g.setY( 200 );
        assertTrue( "<<ghostIsInJail Invalid>>", g.isInJail());
        
        g.setX( 380 );
        g.setY( 236 );
        assertTrue( "<<ghostIsInJail Invalid>>", g.isInJail());
        
        g.setX( 436 );
        g.setY( 235 );
        assertTrue( "<<ghostIsInJail Invalid>>", g.isInJail());
        
        g.setX( 200 );
        g.setY( 40 );
        assertTrue( "<<ghostIsInJail Invalid>>", !g.isInJail());
    }
    
    @Test
    public void ghostInitialFrightenedMove()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        
        g.setFrightened( 3 );
        g.move();
        
        assertTrue( "<<ghostInitialFrightenedMove Invalid>>", g.getDirection() == "left"
                        && g.getFrightenedTimer().isCounting() );
    }
    
    @Test
    public void ghostFrightenedFlash()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        
        g.setFrightened( 3 );
        
        while(g.getFrightenedTimer().getSecond() <= 12)
        {
            g.move();
        }
        
        assertTrue( "<<ghostFrightenedFlash Invalid>>", g.getFrightened() == 2);
    }
    
    @Test
    public void ghostMoveOutOfJail()
    {
        Pan p = new Pan(maze);
        Ghost g = new Blinky(maze, p);
        int x = 0;
        int y = 0;
        
        while(!g.getTimer().isTimeUp())
        {
            g.move();
            x = g.getX();
            y = g.getY();
        }
        
        g.move();
        
        assertTrue( "<<ghostMoveOutOfJail Invalid>>", g.getX() == x && g.getY() == y - 4);
    }
    
    @Test
    public void ghostIsEaten()
    {
        Pan p = new Pan(maze);
        Ghost g = new Pinky(maze, p);
        
        while(g.isInJail())
        {
            g.move();
        }
        
        p.setX( g.getX() );
        p.setY( g.getY() );
        
        g.setFrightened( 3 );
        
        assertTrue( "<<ghostIsEaten Invalid>>", g.isEaten());
    }

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
        
        assertTrue( "<<inkyTargetY Invalid>>", inky.targetY() == 0 );
    }

    /*
     * Macaroni Tests: 
     * Constructor
     * remove
     */
    @Test
    public void macaroniConstructor()
    {
        Macaroni mac = new Macaroni( maze, 20, 40 );
        assertNotNull(mac);
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
     *  touchingMacaroni
     * touchingBlueMacaroni 
     * getX
     *  getY 
     *  getDirection
     */
     @Test
    public void panConstructor()
    {
        Pan p = new Pan( maze );
        assertNotNull( p );
    }


    @Test
    public void panMove()
    {
        pan.setX( 400 );
        pan.setY( 360 );
        int oldX = pan.getX();
        pan.setDirection( "right" );
        Window.frame();
        pan.move();
        assertTrue( pan.getX() > oldX );
        
        oldX = pan.getX();
        pan.timerTest("right");
        pan.move();
        assertTrue( pan.getX() > oldX );
        
        pan.setX( 760 );
        pan.setY( 150 );
        int oldY = pan.getY();
        pan.timerTest("up");
        pan.move();
        assertTrue( pan.getY() < oldY );

    }


    @Test
    public void panCanMove()
    {
        pan.setX( 760 );
        pan.setY( 150 );
        assertTrue( pan.canMoveTest( "down" ) );
        assertTrue( pan.canMoveTest( "up" ) );
        pan.setX( 400 );
        pan.setY( 360 );
        assertTrue( pan.canMoveTest( "left" ) );
        assertTrue( pan.canMoveTest( "right" ) );
        assertTrue( !pan.canMoveTest( "up" ) );
    }
    
    @Test
    public void panTouchingGhost() {
        Blinky blinky = new Blinky( maze , pan );
        blinky.setX( 410 );
        blinky.setY( 360 );
        pan.setX( 400 );
        pan.setY( 360 );
        assertTrue(pan.touchingGhost(blinky));
        pan.setX( 500 );
        pan.setY( 360 );
        assertTrue(!pan.touchingGhost(blinky));
        
    }

    @Test
    public void panTouchingMacaroni() {
        int arrayMacX = 20;
        int arrayMacY = 18;
        maze[arrayMacY][arrayMacX] = 2;
        pan.setX( 400 );
        pan.setY( 360 );
        assertTrue(pan.touchingMacaroni(arrayMacY, arrayMacX));
        pan.setX( 500 );
        pan.setY( 360 );
        assertTrue(!pan.touchingMacaroni(arrayMacY, arrayMacX));
    }
    
    @Test
    public void panTouchingBlueMacaroni() {
        int arrayMacX = 20;
        int arrayMacY = 18;
        maze[arrayMacY][arrayMacX] = 5;
        pan.setX( 400 );
        pan.setY( 360 );
        assertTrue(pan.touchingBlueMacaroni(arrayMacY, arrayMacX));
        pan.setX( 500 );
        pan.setY( 360 );
        assertTrue(!pan.touchingBlueMacaroni(arrayMacY, arrayMacX));
    }
    
    @Test
    public void panGetX() {
        pan.setX( 400 );
        assertTrue( pan.getX() == 400 );
        
    }
    
    @Test
    public void panGetY() {
        pan.setY( 300 );
        assertTrue( pan.getY() == 300 );
        
    }
    
    @Test
    public void panGetDirection() {
        pan.setDirection( "right" );
        assertTrue( pan.getDirection().equals( "right" ));
        
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
     * constructor
     * count
     * reset
     * start
     * isTimeUp
     * getSecond
     * saveTime
     * getSavedTarget
     */
    
    @Test
    public void timerConstructor()
    {
        Timer timer = new Timer();
        assertNotNull( timer );
    }
    
    @Test
    public void timerCount()
    {
        Timer timer = new Timer();
        assertTrue( timer.getSecond() == 0 );
        timer.start( 10 );
        timer.count();    
        assertTrue( timer.getMillisecond() == 30 );
    }
    
    @Test
    public void timerReset()
    {
        Timer timer = new Timer();
        assertTrue( timer.getSecond() == 0 );
        assertTrue( timer.getSavedTarget() == 0 );
        assertTrue( timer.isCounting() == false );
    }


    @Test
    public void timerStart()
    {
        Timer timer = new Timer();
        timer.start( 10 );
        assertTrue( timer.isCounting() == true );
    }
    
    @Test
    public void timerIsTimeUp()
    {
        Timer timer = new Timer();
        timer.start( 1 );
        assertTrue( timer.isTimeUp() == false );
        for ( int i = 0 ; i < 100 ; i ++ )
        {
            timer.count();
        }
        assertTrue( timer.isTimeUp() == true );
    }
    
    @Test
    public void timerGetSecond()
    {
        Timer timer = new Timer();
        assertTrue( timer.getSecond() == 0 );
        timer.start( 10 );
        for ( int i = 0 ; i < 100 ; i++ )
        {
            timer.count();
        }
        
        assertTrue( timer.getSecond() == 2);
    }
    
    @Test
    public void timerSaveTime()
    {
        Timer timer = new Timer();
        timer.start( 10 );
        timer.saveTime();
        assertTrue( timer.getSavedTarget() == 10 && timer.isCounting() == false );
    }
    
    @Test
    public void timerGetSavedTarget()
    {
        Timer timer = new Timer();
        timer.start( 17 );
        timer.saveTime();
        assertTrue( timer.getSavedTarget() == 17 );
    }

}
