import apcs.Window;


public class Macaroni
{
    protected int[][] maze;
    protected boolean removed = false;
    protected int x, y;
    protected String image;


    public Macaroni( int[][] maze, int a, int b )
    {
        this.maze = maze;
        x = a;
        y = b;
        image = "macaroniPic.png";
        place( a, b );
    }


    public void place()
    {
        if ( removed != true )
        {
            Window.out.image( image, x * 20, y * 20 );
            maze[y][x] = 2;
        }
    }


    public void place( int a, int b )
    {
        if ( removed != true )
        {
            Window.out.image( image, a * 20, b * 20 );
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
