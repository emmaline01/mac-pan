import apcs.Window;

public class Pan
{
    int x;
    int y;
    int[][] maze;
    String direction;
    
    public Pan (int[][] m) {
        x = 0; //500
        y = 0; //400
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
        Window.out.circle( x, y, 20 );
    }
    
    public boolean canMove(String direction) { //doesn't work YET
        if (x + 10 < maze[0].length && direction.equals( "right" )) {
            if (maze[1+12+x*25][12+y*25] == 0) {
                return true;
            }
        }
        else if (direction.equals( "left" )) {
            if (maze[12-1+x*25][12+y*25] == 0) {
                return true;
            }
        }
        else if (direction.equals( "up" )) {
            if (maze[12+x*25][12+1+y*25] == 0) {
                return true;
            }
        }
        else if (direction.equals( "down" )) {
            if (maze[12+x*25][12-1+y*25] == 0) {
                return true;
            }
        }
        return false;
    }
}
