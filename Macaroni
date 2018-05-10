
import apcs.Window;


public class Macaroni
{
    private int[][] maze;

    private boolean removed = false;

    private int x, y;


    public Macaroni( int[][] maze, int a, int b )
    {
        this.maze = maze;
        x = a;
        y = b;
        place( a, b );
    }


    public void place()
    {
        if ( removed != true )
        {
            Window.out.image( "macaroniPic.png", x * 20, y * 20 );
            maze[y][x] = 2;
        }
    }


    public void place( int a, int b )
    {
        if ( removed != true )
        {
            Window.out.image( "macaroniPic.png", a * 20, b * 20 );
            maze[b][a] = 2;
        }
    }


    public void remove()
    {
        removed = true;
    }


    public boolean isRemoved()
    {
        return removed;
    }

}
