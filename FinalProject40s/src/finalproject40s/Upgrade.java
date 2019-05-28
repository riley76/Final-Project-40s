
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
    
    PlayerShip player;
    public int upgradeType;
    public static final int LIFE     = 5;
    public static final int FIRING   = 1;
    public static final int STRENGTH = 3;
    public static final int MISSILE  = 4;
    public static final int SPEED    = 2;
    
    
    
    /**
     * default constructor for this class
     * @param image the image of the upgrade
     * @param player the user playing the game
     * @param upgradeType the type of upgrade this is
     */
    public Upgrade(Image image, PlayerShip player, int upgradeType) {
        super(image, 10, 1);
        this.player = player;
        this.upgradeType = upgradeType;
        if(upgradeType == LIFE) {
            image.setDebug("Life", Color.yellow);
        } else if(upgradeType == FIRING) {
            image.setDebug("Firing", Color.magenta);
        } else if(upgradeType == STRENGTH) {
            image.setDebug("Strong", Color.green);
        } else if(upgradeType == MISSILE) {
            image.setDebug("Missile", Color.orange);
        } else {
            image.setDebug("Speed", Color.cyan);
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
        if(isColliding(player)) {
            if(upgradeType == LIFE) player.getLife();
            else player.pickUpUpgrade(upgradeType);
            shutDown();
        }
    }
    
    
}
