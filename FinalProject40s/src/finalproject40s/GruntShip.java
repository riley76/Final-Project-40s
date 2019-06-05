
package finalproject40s;

import gameTools.Constants;
import gameTools.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * GruntShip.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 1-May-2019
 */
 public class GruntShip extends EnemyShip{

    public GruntShip(Image image, Engine engine, int difficulty) {
        super(image, Constants.BASE_SHIP_MOVEMENT, engine);
        canFire= true;
        image.setDebug("", Color.ORANGE);
        health = Constants.BASE_ENEMY_HEALTH;
        speed = Constants.BASE_SHIP_MOVEMENT;
        coordinates.amount = speed;
        if(difficulty <= 1)damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        else damageOutput = Constants.BASE_SHIP_DAMAGE;
        firingTimer = new Timer((2 + difficulty) * 600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fire();
            }
        });
        firingTimer.start();
    }
    
}
