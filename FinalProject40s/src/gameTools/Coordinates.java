
package gameTools;

import finalproject40s.Ship;

/**
 * Coordinates.java - coordinates of something in a game
 * @Class Computer Science grade 11
 * @author riley.w
 * @since 29-Oct-2018
 */
public class Coordinates {
        
    public int x;
    public int y;
    public int left;
    public int right;
    public int top;
    public int bottom;
    public int height;
    public int width;
    public int direction;
    public int amount;
    private Image hitbox;
    
    
    /**
     * default constructor for this class
     * @param image the image of the object
     */
    public Coordinates(Image image) {
        hitbox             = image;         
        direction          = Constants.STOP_DIRECTION ; 
        amount             = Constants.STOP_DIRECTION ;
        update(); 
    }

    /**
     * updates all coordinate data based on the location of the label
     */
    public void update() {
        x      = hitbox.getX();
        y      = hitbox.getY();
        width  = hitbox.getWidth(); 
        height = hitbox.getHeight();       
        recalculate(); 
    }
    /**
     * recalculates needed data
     */
    public void recalculate() {
        left = x;
        top = y;
        right = left + width;
        bottom = top + height;
    }

   
    /**
     * Positions the coordinate data correctly against (sticks to) the target
     * coordinate data
     * @param target the coordinate data to stick to
     */
    public void stickTo(Coordinates target) {        
        if      (direction == Constants.NORTH_DIRECTION  || direction == Constants.UP_DIRECTION ) 
            y = target.y + target.height + 1;
        else if (direction == Constants.SOUTH_DIRECTION  || direction == Constants.DOWN_DIRECTION ) 
            y = target.y - height - 1;
        else if (direction == Constants.EAST_DIRECTION  || direction == Constants.RIGHT_DIRECTION ) 
            x  = target.x - width - 1;
        else if (direction == Constants.WEST_DIRECTION  || direction == Constants.LEFT_DIRECTION ) 
            x  = target.x + target.width  + 1;
        recalculate();
    }
    
    /**
     * Changes current direction and bounces off the target coordinate data
     * @param target the coordinate data to bounce off
     */
    public void bounceOff(Coordinates target) {
        stickTo(target);
        if      (direction == Constants.UP_DIRECTION)    direction      = Constants.DOWN_DIRECTION;
        else if (direction == Constants.DOWN_DIRECTION)  direction      = Constants.UP_DIRECTION;
        else if (direction == Constants.LEFT_DIRECTION)  direction      = Constants.RIGHT_DIRECTION;
        else if (direction == Constants.RIGHT_DIRECTION) direction      = Constants.LEFT_DIRECTION;       
        else if (direction == Constants.NORTH_DIRECTION) direction      = Constants.SOUTH_DIRECTION;
        else if (direction == Constants.SOUTH_DIRECTION) direction      = Constants.NORTH_DIRECTION;
        else if (direction == Constants.WEST_DIRECTION) direction       = Constants.EAST_DIRECTION;
        else if (direction == Constants.EAST_DIRECTION) direction       = Constants.WEST_DIRECTION; 
        else if (direction == Constants.SOUTH_EAST_DIRECTION) direction = Constants.NORTH_WEST_DIRECTION;         
        else if (direction == Constants.SOUTH_WEST_DIRECTION) direction = Constants.NORTH_EAST_DIRECTION;
        else if (direction == Constants.NORTH_EAST_DIRECTION) direction = Constants.SOUTH_WEST_DIRECTION;         
        else if (direction == Constants.NORTH_WEST_DIRECTION) direction = Constants.SOUTH_EAST_DIRECTION;         
    }
    /**
     * Determines if one set of coordinate data is overlapping (colliding) 
     * with the target coordinate data horizontally
     * @param target the coordinate data to check against 
     * @return it is colliding (true) or not (false) horizontally
     */
    public boolean isOverlappingHorizontally(Coordinates target) {
        if      (left         >= target.left && 
                 left         <= target.right)      return true;
        else if (right        >= target.left && 
                 right        <= target.right)      return true;
        else if (target.left  >= left        && 
                 target.left  <= right)             return true;
        else if (target.right >= left        && 
                 target.right <= right)             return true;
        else                                        return false;
    }
    
    /**
     * Determines if one set of coordinate data is overlapping (colliding) 
     * with the target coordinate data vertically
     * @param target the coordinate data to check against 
     * @return it is colliding (true) or not (false) vertically
     */
    public boolean isOverlappingVertically(Coordinates target) {
        if      (top           >= target.top && 
                 top           <= target.bottom)    return true;
        else if (bottom        >= target.top && 
                 bottom        <= target.bottom)    return true;
        else if (target.top    >= top        && 
                 target.top    <= bottom)           return true;
        else if (target.bottom >= top        && 
                 target.bottom <= bottom)           return true;
        else                                        return false;
    }
    
    /**
     * Determines if one set of coordinate data is overlapping (colliding) 
     * with the target coordinate data both vertically and horizontally
     * @param target the coordinate data to check against 
     * @return it is colliding (true) or not (false) 
     */
    public boolean isOverlapping(Coordinates target) {
        if (isOverlappingVertically(target) &&
            isOverlappingHorizontally(target)) {
            return true;                            
        }
        else {
            return false;        
        }
    }
    
    /**
     * Checks to see if the target is above these coordinates
     * @param target the coordinates of the other object to see if these coordinates are above them
     * @return if these coordinates are above the target or not
     */
    public boolean isAbove(Coordinates target) {
        if(bottom <= target.top) return true;
        return false;
    }
    
     /**
     * Checks to see if the target is below these coordinates
     * @param target the coordinates of the other object to see if these coordinates are below them
     * @return if these coordinates are below the target or not
     */
    public boolean isBelow(Coordinates target) {
        if(top <= target.bottom) return true;
        return false;
    }
    
    /**
     * Checks to see if the target is right of these coordinates
     * @param target the coordinates of the other object to see if these coordinates are right of them
     * @return if these coordinates are right of the target or not
     */
    public boolean isToTheRight(Coordinates target) {
        if(left < target.right) return true;
        return false;
    }
    
    /**
     * Checks to see if the target is left of these coordinates
     * @param target the coordinates of the other object to see if these coordinates are left of them
     * @return if these coordinates are left of the target or not
     */
    public boolean isToTheLeft(Coordinates target) {
        if(right < target.left) return true;
        return false;
    }
    
    /**
     * checks to see the relative location of target coordinates compared to these coordinates
     * @param target the target coordinates to compare to
     * @return the direction the target is from these coordinates
     */
    public int checkRelativeLocationOfCoordinates(Coordinates target) {
        if(isAbove(target)) return Constants.NORTH_DIRECTION; 
        else if(isBelow(target)) return Constants.SOUTH_DIRECTION;
        else if(isToTheLeft(target)) return Constants.WEST_DIRECTION;
        else if(isToTheRight(target)) return Constants.EAST_DIRECTION;
        else return Constants.STOP_DIRECTION;
    }
    
    /**
     * sets all the values of these coordinates to that of new coordinates
     * @param newCoordinates to change the data to
     */
    public void changeCoordinates(Coordinates newCoordinates) {
        x = newCoordinates.x;
        y = newCoordinates.y;
        left = newCoordinates.left;
        right = newCoordinates.right;
        top = newCoordinates.top;
        bottom = newCoordinates.bottom;
        height = newCoordinates.height;
        width = newCoordinates.width;
        direction = newCoordinates.direction;
        amount = newCoordinates.amount;
        hitbox = newCoordinates.hitbox;  
    }
    
    /**
     * Determines if two objects are "equal" in this context
     * @param object the object to compare to
     * @return the objects are "equal" (true) or not (false)
     */
    @Override
    public boolean equals(Object object) {
        if(object == null) return false;
        Coordinates co = (Coordinates) object;
        if(co.x == x && co.y == y && co.width == width && co.height == height &&
                co.hitbox.equals(hitbox)) return true;
        return false;
    }
    
    
}
