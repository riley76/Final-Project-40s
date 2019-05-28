
package gameTools;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameCharacter.java - a character in a game that can move
 * @Class Computer Science grade 11
 * @author riley.w
 * @since 29-Oct-2018
 */
public abstract class GameCharacter extends GameObject{
        
    public Timer timer;    
    
    /**
     * constructor for the class sets class properties
     * @param hitbox the label the game image is inside
     * @param amount the amount to move this object
     * @param delay the movement delay for the timer
     */
    public GameCharacter(Image hitbox, int amount, int delay) {
        super(hitbox);
        coordinates.amount = amount;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        timer.start();        
    }
    
    public abstract void action();
    
    /**
     * redraws the object on the screen in its new coordinates
     */
    public void redraw() {  
        image.redraw(coordinates);
    }
    
    /**
     * checks and moves based on the stored direction 
     */
    public void move() {
        if      (coordinates.direction == Constants.LEFT_DIRECTION )  moveLeft();
        else if (coordinates.direction == Constants.RIGHT_DIRECTION ) moveRight();
        else if (coordinates.direction == Constants.UP_DIRECTION )    moveUp();
        else if (coordinates.direction == Constants.DOWN_DIRECTION )  moveDown();        
        else if (coordinates.direction == Constants.NORTH_DIRECTION ) moveNorth();
        else if (coordinates.direction == Constants.SOUTH_DIRECTION ) moveSouth();
        else if (coordinates.direction == Constants.WEST_DIRECTION )  moveWest();
        else if (coordinates.direction == Constants.EAST_DIRECTION )  moveEast();
        else if (coordinates.direction == Constants.NORTH_EAST_DIRECTION ) moveNorthEast();
        else if (coordinates.direction == Constants.NORTH_WEST_DIRECTION ) moveNorthWest();
        else if (coordinates.direction == Constants.SOUTH_EAST_DIRECTION ) moveSouthEast();
        else if (coordinates.direction == Constants.SOUTH_WEST_DIRECTION ) moveSouthWest();
    }
     
    /**
     * stops all movement
     */
    public void stop() {
        coordinates.recalculate();
        coordinates.direction = Constants.STOP_DIRECTION ;
    }
    
    /**
     * moves all the coordinates North
     */
    public void moveUp() {
        coordinates.y = coordinates.y - coordinates.amount;
        coordinates.recalculate();        
        coordinates.direction = Constants.UP_DIRECTION ;
    }
    
    /**
     * moves all the coordinates North
     */
    public void moveDown() {
        coordinates.y = coordinates.y + coordinates.amount;
        coordinates.recalculate();
        coordinates.direction = Constants.DOWN_DIRECTION ;
    }
    
    /**
     * moves all the coordinates North
     */
    public void moveLeft() {
        coordinates.x = coordinates.x - coordinates.amount;
        coordinates.recalculate();
        coordinates.direction = Constants.LEFT_DIRECTION ;
    }
    
    /**
     * moves all the coordinates North
     */
    public void moveRight() {
        coordinates.x = coordinates.x + coordinates.amount;
        coordinates.recalculate();
        coordinates.direction = Constants.RIGHT_DIRECTION ;
    }
    
    /**
     * moves all the coordinates North
     */
    public void moveNorth() {
        moveUp();
        coordinates.direction = Constants.NORTH_DIRECTION ;
    }
    
    /**
     * moves all the coordinates South
     */
    public void moveSouth() {
        moveDown();
        coordinates.direction = Constants.SOUTH_DIRECTION ; 
    }  
    
    /**
     * moves all the coordinates West
     */
    public void moveWest() {     
        moveLeft();
        coordinates.direction = Constants.WEST_DIRECTION ;
    } 
    
    /**
     * moves all the coordinates East
     */
    public void moveEast() {
        moveRight();
        coordinates.direction = Constants.EAST_DIRECTION ;
    }     
    
    /**
     * Shuts this character down stops the timer, hides the character, and sets
     * it to not alive
     */
    public void shutDown() {
        image.hide();
        timer.stop();
        isAlive = false;
    }
    
    /**
     * moves all the coordinates NorthEastht
     */
    private void moveNorthEast() {
        moveNorth();
        moveEast();
        coordinates.direction = Constants.NORTH_EAST_DIRECTION ;
    }
    
    /**
     * moves all the coordinates NorthWest
     */  
    private void moveNorthWest() {
        moveNorth();
        moveWest();
        coordinates.direction = Constants.NORTH_WEST_DIRECTION ;
    }
    
    /**
     * moves all the coordinates south east
     */
    private void moveSouthEast() {
        moveSouth();
        moveEast();       
        coordinates.direction = Constants.SOUTH_EAST_DIRECTION ;
    }
    
    /**
     * moves all the coordinates SouthWest
     */   
    private void moveSouthWest() {
        moveSouth();
        moveWest();
        coordinates.direction = Constants.SOUTH_WEST_DIRECTION ;
    }   
}
