import apcs.Window;

public class Pan
{
    int x;
    int y;
    int[][] maze;
    String direction;
    
    public Pan (int[][] m) {
        x = 500; //500
        y = 400; //400
        maze = m;
        direction = "up";
        
    }
    
    public void move() {
        if (Window.key.pressed( "right" )) {
            if (canMove("right")) {
                x = x + 7;
                direction = "right";
            }
        }
        else if (Window.key.pressed( "left" )) {
            if (canMove("left")) {
                x = x - 7;
                direction = "left";
            }
        }
        else if (Window.key.pressed( "up" )) {
            if (canMove("up")) {
                y = y - 7;
                direction = "up";
            }
        }
        else if (Window.key.pressed( "down" )) {
            if (canMove("down")) {
                y = y + 7;
                direction = "down";
            }
        }
        else {
            if (direction.equals("right")) {
                if (canMove("right")) {
                    x = x + 7;
                }
            }
            else if (direction.equals("left")) {
                if (canMove("left")) {
                    x = x - 7;
                }
            }
            else if (direction.equals("up")) {
                if (canMove("up")) {
                    y = y - 7;
                }
            }
            else if (direction.equals("down")) {
                if (canMove("down")) {
                    y = y + 7;
                }
            }
        }
        
        //image is 35x35
        // x-17 and 7-17 bc otherwise coordinates are top left corner of pic, so this centers it
        Window.out.image( "panImage.png", x-17, y-17 );
//        Window.out.color( "gray" );
//        Window.out.circle( x, y, 12 ); //radius = 12 arbitrarily
    }
    
    private boolean canMove(String direction) { 
        if (x + 7 < 1000 - 12 && direction.equals( "right" )) {
            //1000 - 7 for the size of the screen - radius of ball
            if (maze[((y - 12)/25)][((x - 12 + 7 + 12)/25)] != 1) { 
                //+12 to account for offset of array maze to graphic maze
                //+7 to check for the movement right
                //+12 to account for radius
                return true;
            }
        }
        else if (x - 7 > 12 && direction.equals( "left" )) {
            if (maze[((y - 12)/25) ][((x - 12 - 7 + 6)/25)] != 1) {
                //why tf it's 6 i have no clue.. radius = 12???
                return true;
            }
        }
        else if (y - 7 > 12 && direction.equals( "up" )) {
            if (maze[((y - 12 - 7)/25)][((x - 12  + 6)/25)] != 1) {
                return true;
            }
        }
        else if (y + 7 < 600 - 12 && direction.equals( "down" )) {
            //600 - 12 for the size of the screen - radius of ball
            if (maze[((y - 12 + 7 + 12)/25)][((x - 12 + 6)/25)] != 1) {
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
    
    public boolean touchingMacaroni(int arrayY, int arrayX) {
        //know that Macaroni radius = 7, pan radius = 12
//        if (Math.abs( m.getX() - x) <= 17 || Math.abs( m.getY() - y) <= 17) {
//            return true;
//        }
//        return false;
        
        if (maze[arrayY][arrayX] == 2 &&
                        (Math.abs( (12+arrayX*25) - x) <= 15 && Math.abs( (12+arrayY*25) - y) <= 15)) {
            maze[arrayY][arrayX] = 0;
            return true;
        }
        return false;
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
