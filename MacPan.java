import java.util.TreeMap;
import java.util.ArrayList;

import apcs.Window;


public class MacPan
{

    public static void main( String[] args )
    {
        Window.size( 800, 400 );
        
        while(true)
        {
            Window.frame();
            Window.out.background("black");
            Window.out.color( "white" );
            Window.out.fontSize( 40 );
            Window.out.print( "W  E  L  C  O  M  E  !" , 240, 170 );
            Window.out.fontSize( 20 );
            Window.out.print( " Please press the spacebar to start!  ", 268, 240 );
            
            if (Window.key.pressed( "space" ))
            {
                break;
            }
        }
        
        //4 = the jail for ghosts (no macaronis placed there)
        //1 = walls
        //0 = maze & macaroni
        //40 x 20
        int[][] maze = {
            //  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //1
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //2
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //3
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 5, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //4
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //5
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 5, 1}, //6
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //7
            {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1}, //8
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 4, 4, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1}, //9
            {1, 5, 1, 1, 1, 1, 1, 0, 1, 5, 0, 0, 0, 0, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //10
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 1, 1, 1, 1, 5, 1, 5, 1, 1, 1, 1, 1, 0, 1}, //11
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //12
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //13
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 5, 1}, //14
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //15
            {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1}, //16
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, //17
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1}, //18
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //19 
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //20
        };
        
        Sound sound = new Sound();
        sound.music();
        
        Counter c = new Counter();
        Pan p = new Pan( maze );
        
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
        
        Pinky pinky = new Pinky (maze, p ); //pink
        Blinky blinky = new Blinky (maze, p ); //red
        Inky inky = new Inky (maze, p , blinky ); //blue
        
        ghosts.add( pinky );
        ghosts.add( blinky );
        ghosts.add( inky );

        TreeMap<Integer, Macaroni> map = new TreeMap<Integer, Macaroni>();
        for ( int y = 0; y < maze.length; y++ )
        {
            for ( int x = 0; x < maze[0].length; x++ )
            {
                if ( maze[y][x] == 0 )
                {
                    map.put( y * 100 + x, new Macaroni( maze, x, y ) );
                }
                else if (maze[y][x] == 5)
                {
                    map.put( y * 100 + x, new BlueMacaroni(maze, x, y) );
                }
            }
        }

        Boolean touchedBlueMac = false;
        
        while ( true )
        {
            Window.frame();
            Window.out.background( "blue" );
            
            for ( int y = 0; y < maze.length; y++ )
            {
                for ( int x = 0; x < maze[0].length; x++ )
                {
                    if ( maze[y][x] != 1 )
                    {
                        Window.out.color( "black" ); // blue is walls-> can go
                                                     // in 0
                        Window.out.rectangle( 10 + x * 20, 10 + y * 20, 20, 20 );
                    }
                    if ( p.touchingMacaroni( y, x ) )
                    {
                        c.setNumEaten( c.getNumEaten() + 1);
                        map.get( y * 100 + x ).remove();
                        map.remove( y * 100 + x  );

                    }
                    if (p.touchingBlueMacaroni( y, x ))
                    {
                        c.setNumEaten( c.getNumEaten() + 1);
                        map.get( y * 100 + x ).remove();
                        map.remove( y * 100 + x  );
                        touchedBlueMac = true;
                    }
                }
            }
            
            if ( map.isEmpty() )
            {
                break; // end game
            }
            
            for ( Integer i : map.keySet() )
            {
                map.get( i ).place();
            }
            
            p.move();
            
            if (touchedBlueMac == true)
            {
                pinky.setFrightened( 3 );
                blinky.setFrightened( 3 );
                inky.setFrightened( 3 );
                touchedBlueMac = false;
            }
            
            pinky.move();
            blinky.move();
            inky.move();
            
            if (pinky.isEaten() || blinky.isEaten() || inky.isEaten())
            {
                for (Ghost g : ghosts)
                {
                    if (g.isEaten())
                    {
                        c.setNumEaten( c.getNumEaten() + 5 );
                    }
                }
            }
            
            c.displayCounter();
            
            if ( (p.touchingGhost( pinky ) && !pinky.isFrightened()) 
                            || (p.touchingGhost( blinky ) && !blinky.isFrightened())
                            || (p.touchingGhost( inky ) && !inky.isFrightened())) 
            {
                break; //end game
            }
        }
        
        while ( true )
        {
            if (map.isEmpty())
            {
                Window.frame();
                Window.out.color( "black" );
                Window.out.rectangle( 1, 1, 1600, 800 );
                Window.out.color( "green" );
                Window.out.print( "   Y  O  U    W  O  N  !" , 320, 190 );
            }
            else
            {
                Window.frame();
                Window.out.color( "black" );
                Window.out.rectangle( 1, 1, 1600, 800 );
                Window.out.color( "red" );
                Window.out.print( "G  A  M  E    O  V  E  R" , 320, 190 );
            }
            
        }

    }

}
