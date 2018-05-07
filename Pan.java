import apcs.Window;

public class Pan
{
    int x;
    int y;
    int[][] maze;
    String direction;
    
    public Pan (int[][] m) {
        x = 6; //500
        y = 6; //400
        maze = m;
        direction = "up";
        
    }
    
    public void move() {
        if (Window.key.pressed( "right" )) {
            if (canMove("right")) {
                x = x + 10;
                direction = "right";
            }
        }
        else if (Window.key.pressed( "left" )) {
            if (canMove("left")) {
                x = x - 10;
                direction = "left";
            }
        }
        else if (Window.key.pressed( "up" )) {
            if (canMove("up")) {
                y = y - 10;
                direction = "up";
            }
        }
        else if (Window.key.pressed( "down" )) {
            if (canMove("down")) {
                y = y + 10;
                direction = "down";
            }
        }
        else {
            if (direction.equals("right")) {
                if (canMove("right")) {
                    x = x + 10;
                }
            }
            else if (direction.equals("left")) {
                if (canMove("left")) {
                    x = x - 10;
                }
            }
            else if (direction.equals("up")) {
                if (canMove("up")) {
                    y = y - 10;
                }
            }
            else if (direction.equals("down")) {
                if (canMove("down")) {
                    y = y + 10;
                }
            }
        }
        
        //Window.out.image( "panImage.png", x, y );
        Window.out.color( "gray" );
        Window.out.circle( x, y, 12 ); //radius = 12 arbitrarily
    }
    
    private boolean canMove(String direction) { 
        
        if (x + 10 < 1000 - 12 && direction.equals( "right" )) {
            //1000 - 5 for the size of the screen - radius of ball
            if (maze[((y - 12)/25)][((x - 12 + 10 + 12)/25)] == 0) { 
                //+12 to account for offset of array maze to graphic maze
                //+10 to check for the movement right
                //+12 to account for radius
                return true;
            }
        }
        else if (x - 10 > 12 && direction.equals( "left" )) {
            if (maze[((y - 12)/25) ][((x - 12 - 10 + 6)/25)] == 0) {
                //why tf it's 6 i have no clue.. radius = 12???
                return true;
            }
        }
        else if (y - 10 > 12 && direction.equals( "up" )) {
            if (maze[((y - 12 - 10)/25)][((x - 12  + 6)/25)] == 0) {
                return true;
            }
        }
        else if (y + 10 < 600 - 12 && direction.equals( "down" )) {
            //600 - 12 for the size of the screen - radius of ball
            if (maze[((y - 12 + 10 + 12)/25)][((x - 12 + 6)/25)] == 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean touchingGhost(Ghost g) {
        //24 bc assuming ghost radius is 12, know that pan radius is 12
        if (Math.abs( g.getX() - x) <= 24 || Math.abs( g.getY() - y) <= 24) {
            return true;
        }
        return false;
    }
    
    public boolean touchingMacaroni(Macaroni m) {
        //know that Macaroni radius = 5, pan radius = 12
        if (Math.abs( m.getX() - x) <= 17 || Math.abs( m.getY() - y) <= 17) {
            return true;
        }
        return false;
        
        //TODO: getting rid of Macaroni getX and getY- use coordinates of maze array
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String getDirection() {
        return direction;
    }
}
