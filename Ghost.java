import apcs.Window;

public abstract class Ghost
{
    private int x, y;
    private int[][] maze;
    private Pan pan;
    private String color;
    private String direction;
    
    public Ghost(int[][] m, Pan p)
    {
        x = 500;
        y = 300;
        maze = m;
        pan = p;
        color = "normal";
        direction = "up";
    }
    
    public abstract void move();
    
    public boolean canMove()
    {
        //TODO
        if (x >= 0 && y >= 0 && x < maze.length && y < maze.length)
        {
            int mazeX = x - 12 / 25;
            int mazeY = y - 12 / 25;
            if (maze[x][y] != 1)
            {
                return true;
            }
        }
        return false;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setColor (String c)
    {
        color = c;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public void frightenedMove()
    {
        //TODO
        
        Window.frame();
        if (direction.equals( "up" ))
        {
            Window.out.rectangle( x, y - 10, 10, 15 );
            y -= 10;
        }
        else if (direction.equals("down"))
        {
            Window.out.rectangle( x, y + 10, 10, 15 );
            y += 10;
        }
        else if (direction.equals( "right" ))
        {
            Window.out.rectangle( x - 10, y, 10, 15 );
            x -= 10;
        }
        else
        {
            Window.out.rectangle( x + 10, y, 10, 15 );
            x +=10;
        }
    }
    
    public boolean eaten()
    {
        //TODO
        return pan.touchingGhost();
    }
}
