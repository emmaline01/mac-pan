import java.util.TreeMap;

import apcs.Window;


public class MacPan
{

    public static void main( String[] args )
    {
        int numEaten = -1;
        
        //4 = the jail for ghosts (no macaronis placed there)
        //5 = blue macaroni
        //1 = walls
        //0 = maze & macaroni
        //40 x 20
        int[][] maze = {
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
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 4, 4, 4, 4, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, //11
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
        Window.size( 800, 400 );
        Pan p = new Pan( maze );
        Pinky pinky = new Pinky (maze, p ); //pink
        Blinky blinky = new Blinky (maze, p ); //red
        Inky inky = new Inky (maze, p , blinky ); //blue

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
                        map.get( y * 100 + x ).remove();
                        numEaten++;
                    }
                    if (p.touchingBlueMacaroni( y, x ))
                    {
                        numEaten++;
                        map.get( y * 100 + x ).remove();
                    }
                }
            }
            for ( Integer i : map.keySet() )
            {
                map.get( i ).place();
            }

            Window.out.color( "white" );
            Window.out.print( "Macaroni eaten: " + numEaten, 630, 397 );

            p.move();
            //pinky.move(); //pinky ghost
            //inky.move(); //blue ghost
            //blinky.move(); //red ghost
            
            if ( p.touchingGhost( pinky ) || p.touchingGhost( inky ) 
                 || p.touchingGhost( blinky )) 
            {
                break; //end game
                
            }
        }
        
        while ( true )
        {
            Window.frame();
            Window.out.color( "black" );
            Window.out.rectangle( 1, 1, 1600, 800 );
            Window.out.color( "red" );
            Window.out.print( "G  A  M  E    O  V  E  R" , 320, 190 );
        }

    }

}
