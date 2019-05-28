
package finalproject40s;

import gameTools.Constants;
import gameTools.Coordinates;
import gameTools.Image;
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

    public GruntShip(Image image, int delay, Engine engine) {
        super(image, Constants.BASE_SHIP_MOVEMENT, engine);
        canFire= true;
        health = Constants.BASE_ENEMY_HEALTH;
        damageOutput = Constants.BASE_SHIP_DAMAGE;
        firingTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    @Override
    public Coordinates changeDirection(Coordinates coordinates) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hit(int damage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          

}
