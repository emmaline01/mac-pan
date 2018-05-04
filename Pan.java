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
            }
        }
        else if (Window.key.pressed( "left" )) {
            if (canMove("left")) {
                x = x - 10;
            }
        }
        else if (Window.key.pressed( "up" )) {
            if (canMove("up")) {
                y = y - 10;
            }
        }
        else if (Window.key.pressed( "down" )) {
            if (canMove("down")) {
                y = y + 10;
            }
        }
        
        Window.out.color( "gray" );
        Window.out.circle( x, y, 12 ); //radius = 12 arbitrarily
    }
    
    public boolean canMove(String direction) { //doesn't work YET
        /* x
         * 0.....37 = 0
         * 37....74 = 1
         */
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
}
