
package gameTools;

import finalproject40s.Engine;
import java.awt.Color;

/**
 * Wall.java - represents a wall that can not be hit
 * @Class Computer Science grade 11
 * @author riley.w
 * @since 12-Nov-2018
 */
public class Wall extends GameObject {
        
    public boolean isEndWall;
    public boolean isSideWall;
    private Engine engine;
    
    public Wall(Image hitbox, boolean isEndWall, boolean isSideWall, Engine engine) {
        super(hitbox);
        this.isEndWall = isEndWall;
        this.isSideWall = isSideWall;
        this.engine = engine;
        if(isEndWall) {
            hitbox.setDebug(" ", Color.red); 
        } else {
            hitbox.setDebug(" ", Color.blue); 
        }
    }
    
    public Wall(Image hitbox, boolean isEndWall) {
        super(hitbox);
        hitbox.setDebug(" ", Color.gray);
        this.isEndWall = isEndWall;
        if(isEndWall) {
            hitbox.setDebug(" ", Color.DARK_GRAY); 
        }
    }

    
    
}
