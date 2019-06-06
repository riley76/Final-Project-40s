
package finalproject40s;

import gameTools.GameCharacter;
import gameTools.Image;
import java.awt.Color;

/**
 * Upgrade.java - an upgrade for the player that gives some random positive effect
 * @Class Computer Science grade 11
 * @author riley.w
 * @since 19-Dec-2018
 */
public class Upgrade extends GameCharacter{
    
    Engine engine;
    public int upgradeType;
    public static final int LIFE     = 5;
    public static final int FIRING   = 1;
    public static final int STRENGTH = 3;
    public static final int DAMAGE   = 4;
    public static final int SPEED    = 2;
    public static final Color[] SHIP_COLOURS = {Color.magenta, Color.cyan, 
        Color.green, Color.orange, Color.PINK};
    public static final String[] NAMES = {"Firing", "Speed", "Invincible", "Damage", "Life"};
    
    
    
    /**
     * default constructor for this class
     * @param image the image of the upgrade
     * @param engine the engine that runs general logic for the game
     * @param upgradeType the type of upgrade this is
     */
    public Upgrade(Image image, Engine engine, int upgradeType) {
        super(image, 10, 1);
        this.engine = engine;
        this.upgradeType = upgradeType;
        if(upgradeType == STRENGTH) {
            image.setDebug("Strong", SHIP_COLOURS[upgradeType - 1]);
        } else {
            image.setDebug(NAMES[upgradeType - 1], SHIP_COLOURS[upgradeType - 1]);
        }
    }

    @Override
    public void action() {
        checkPlayer();
    }

    /**
     * checks to see if colliding with the player
     */
    private void checkPlayer() {
        if(isColliding(engine.player)) {
            if(upgradeType == LIFE) engine.changeLives(1);
            else engine.player.pickUpUpgrade(upgradeType);
            shutDown();
        }
    }
    
    
}
