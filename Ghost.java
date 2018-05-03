
public abstract class Ghost
{
    private int x, y;
    private int[][] maze;
    private Pan pan;
    private String color;
    
    public Ghost(int[][] m, Pan p)
    {
        x = 500;
        y = 300;
        maze = m;
        pan = p;
        color = "normal";
    }
    
    public abstract void move();
    
    public boolean canMove()
    {
        //TODO
        return x < maze.length && y < maze.length && maze[x][y] != 1;
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
        
    }
    
    public boolean eaten()
    {
        //TODO
        return true;
    }
}
