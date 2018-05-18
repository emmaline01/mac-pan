import java.util.ArrayList;
import java.util.TreeMap;
import apcs.Window;

public abstract class Ghost
{
    private ArrayList<String> bDirSwitch;
    private ArrayList<String> iDirSwitch;
    
    protected int x, y;
    protected int[][] maze;
    protected Pan pan;
    protected String direction;
    protected TreeMap<String, String> images;
    protected Timer timer;
    protected String name;
    
    protected String[] dirArr = {"right", "up", "down", "left"};
    protected int[] dirIndex = {0, 1, 2, 3};
    protected int[] shiftXArr = {4, 0, 0, -4};
    protected int[] shiftYArr = {0, -4, 4, 0};
    
    public Ghost(int[][] m, Pan p)
    {
        x = 390;
        y = 210;
        maze = m;
        pan = p;
        direction = "up";
        images = new TreeMap<String, String>();
        timer = new Timer();
        name = getName();
        
        bDirSwitch = new ArrayList<String>();
        bDirSwitch.add( "right" );
        bDirSwitch.add( "left" );
        
        iDirSwitch = new ArrayList<String>();
        iDirSwitch.add( "left" );
        iDirSwitch.add( "right" );
    }
    
    /**
     * Put the images of individual ghosts when heading in different directions
     */
    public abstract void setImages();
    
    public abstract String getName();
    
    /**
     * Find x-coordinate the image should move to
     * @return target x-coordinate
     */
    public abstract int targetX();
    
    /**
     * Find y-coordinate the image should move to
     * @return target y-coordinate
     */
    public abstract int targetY();
    
    /**
     * Move to specified coordinates.
     * @param targetX target x-coordinate
     * @param targetY target y-coordinate
     */

    public void move()
    {
        if (maze[((y - 10) / 20)][((x - 10) / 20)] == 4)
        {
            if (name.equals( "Pinky" ))
            {
                direction = "up";
                y -= 5;
            }
            /*
            else if (name.equals( "Blinky" ))
            {
                if (timer.getSecond() <= 10)
                {
                    if (canMove(bDirSwitch.get( 0 )))
                    {
                        direction = bDirSwitch.get(0);
                        if (direction.equals( "right" ))
                        {
                            x += 5;
                        }
                        else
                        {
                            x -= 5;
                        }
                    }
                    else
                    {
                        bDirSwitch.add( bDirSwitch.remove( 0 ) );
                        direction = bDirSwitch.get(0);
                        if (direction.equals( "right" ))
                        {
                            x += 5;
                        }
                        else
                        {
                            x -= 5;
                        }
                    }
                }
                else
                {
                    if (x < 390)
                    {
                        direction = "right";
                        x += 5;
                    }
                    else if (x > 390)
                    {
                        direction = "left";
                        x -= 5;
                    }
                    else
                    {
                        direction = "up";
                        y -= 5;
                    }
                }
            }
            else if (name.equals( "Inky" ))
            {
                if (timer.getSecond() <= 20)
                {
                    if (canMove(iDirSwitch.get( 0 )))
                    {
                        direction = iDirSwitch.get(0);
                        if (direction.equals( "right" ))
                        {
                            x += 5;
                        }
                        else
                        {
                            x -= 5;
                        }
                    }
                    else
                    {
                        iDirSwitch.add( iDirSwitch.remove( 0 ) );
                        direction = iDirSwitch.get(0);
                        if (direction.equals( "right" ))
                        {
                            x += 5;
                        }
                        else
                        {
                            x -= 5;
                        }
                    }
                }
                else
                {
                    if (x < 390)
                    {
                        direction = "right";
                        x += 5;
                    }
                    else if (x > 390)
                    {
                        direction = "left";
                        x -= 5;
                    }
                    else
                    {
                        direction = "up";
                        y -= 5;
                    }
                }
            }
            
            Window.out.image( images.get( direction ), x , y );
            timer.count();
            return;*/
        }
        else
        {
            int targetX = targetX();
            int targetY = targetY();
            //System.out.println("targetx: " + targetX + " targety: " + targetY);

            // Find how far each current coordinate value is to the target coordinate values
            int diffX = targetX - x;
            int diffY = targetY - y;

            int[] dirOrder = new int[4];
            
            if (diffX >= Math.abs( diffY ))
            {
                dirOrder[0] = 0;
            }
            else if (diffX <= -Math.abs( diffY ))
            {
                dirOrder[0] = 3;
            }
            else if (diffY >= Math.abs( diffX ))
            {
                dirOrder[0] = 2;
            }
            else
            {
                dirOrder[0] = 1;
            }
            
            if (dirOrder[0] == 0 || dirOrder[0] == 3)
            {
                if (diffY >= 0)
                {
                    dirOrder[1] = 2;
                }
                else
                {
                    dirOrder[1] = 1;
                }
            }
            else
            {
                if (diffX >= 0)
                {
                    dirOrder[1] = 0;
                }
                else
                {
                    dirOrder[1] = 3;
                }
            }
            
            dirOrder[2] = 3 - dirOrder[0];
            dirOrder[3] = 3 - dirOrder[1];
            
            for (int i = 0; i < dirOrder.length; i++)
            {
                int index = dirOrder[i];

                direction = dirArr[dirOrder[i]];
                
                if (canMove(direction))
                {
                    x += shiftXArr[index];
                    y += shiftYArr[index];
                    break;
                }
            }
            Window.out.image( images.get( direction ), x , y );
            timer.count();
        }
    }
    
    /**
     * The ghosts' movements when the pan eats the blue macaroni
     * protected String[] dirArr = {"right", "up", "down", "left"};
     * protected int[] dirIndex = {0, 1, 2, 3};
     * protected int[] shiftXArr = {4, 0, 0, -4};
     * protected int[] shiftYArr = {0, -4, 4, 0};
     */
    public void frightenedMove()
    {
        //TODO
        if (timer.getMillisecond() == 0)
        {
            int index = 0;
            for (; index < dirArr.length ; index++)
            {
                if (dirArr[index].equals(direction))
                {
                    break;
                }
            }
            
            index = 3 - index; 
            
            direction = dirArr[index];
            x += shiftXArr[index];
            y += shiftYArr[index];
            
            Window.out.image( images.get( "edible" ), x , y );
        }
        else
        {
            if (timer.getSecond() <= 15 || (timer.getSecond() > 15 && timer.getSecond() % 2 == 0))
            {
                int index = 0;
                
                if (!canMove(direction))
                {
                    index = (int) (Math.random() * 4);
                    
                    while (!canMove(dirArr[index]))
                    {
                        index = (int) (Math.random() * 4);
                    }
                    
                    direction = dirArr[index];
                    x += shiftXArr[index];
                    y += shiftYArr[index];
                }
                else
                {
                    for (int i = 0; i < dirArr.length; i++)
                    {
                        if (dirArr[i].equals(direction))
                        {
                            index = i;
                            break;
                        }
                    }
                    
                    x += shiftXArr[index];
                    y += shiftYArr[index];
                }
                
                Window.out.image( images.get( "edible" ), x , y );
            }
            else if (timer.getSecond() > 15 && timer.getSecond() % 2 == 1)
            {
                int index = 0;
                
                if (!canMove(direction))
                {
                    index = (int) (Math.random() * 4);
                    
                    while (!canMove(dirArr[index]))
                    {
                        index = (int) (Math.random() * 4);
                    }
                    
                    direction = dirArr[index];
                    x += shiftXArr[index];
                    y += shiftYArr[index];
                }
                else
                {
                    for (int i = 0; i < dirArr.length; i++)
                    {
                        if (dirArr[i].equals(direction))
                        {
                            index = i;
                            break;
                        }
                    }
                    
                    x += shiftXArr[index];
                    y += shiftYArr[index];
                }
                
                Window.out.image( images.get( "revert" ), x , y );
            }
        }
        
        timer.count();
        //System.out.println("millisecond: " + timer.getMillisecond() + "   second: " + timer.getSecond());
    }

    /**
     * Identifies whether or not the ghost can move forward in the direction it's facing
     * @param direction The direction the ghost is heading in
     * @return true if the ghost can continue moving
     */
    private boolean canMove( String direction )
    {
        if ( direction.equals( "right" ) && (x / 20) <= maze[0].length
                        && maze[( y - 10 ) / 20 + 1 ][( x + 20 ) / 20] != 1)
        {
            return true;
        }
        else if ( direction.equals( "left" ) && (x / 20) >= 0
            && maze[(y - 10 ) / 20 + 1][(x - 5) / 20] != 1)
        {
            return true;
        }
        else if ( direction.equals( "up" ) && ( y - 10 ) / 20 + 1 >= 0
            && maze[( y - 5 ) / 20 ][( x - 10 ) / 20 + 1 ] != 1)
        {
            return true;
        }
        else if ( direction.equals( "down" ) && ( y + 20) / 20 <= maze.length
            && maze[ ( y + 20 ) / 20][ ( x - 10 ) / 20 + 1 ] != 1 
            && maze[ ( y + 20 ) / 20][ ( x - 10 ) / 20 + 1 ] != 4 )
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns the x-coordinate of the ghost
     * @return current x-coordinate of the ghost
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Returns the y-coordinate of the ghost
     * @return current y-coordinate of the ghost
     */
    public int getY()
    {
        return y;
    }
    
    public Timer getTimer()
    {
        return timer;
    }
    
    /**
     * Identifies whether or not the ghost is eaten by the pan.
     * @return true if the ghost is touching the pan.
     */
    public boolean eaten()
    {
        return pan.touchingGhost(this);
    }
    
    public void backToJail()
    {
        x = 390;
        y = 210;
        direction = "up";
        Window.out.image( images.get( direction ), x , y );
    }
}
