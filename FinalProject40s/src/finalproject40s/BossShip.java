package finalproject40s;

import gameTools.Constants;
import gameTools.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BossShip extends EnemyShip {

    public BossShip(Image image, int amount, int delay, Engine engine) {
        super(image, amount, engine);
        health = Constants.BASE_BOSS_HEALTH + 21 - (engine.manager.difficulty * 7);
        image.setDebug("", Color.BLUE);
        canFire= true;
        speed = amount;
        if(engine.manager.difficulty <= 1)damageOutput = Constants.BASE_SHIP_DAMAGE * 3;
        else damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        firingTimer = new Timer((2 + engine.manager.difficulty) * 475, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fire(true);
                fire(false);
            }

        });
        firingTimer.start();
        
        
    }

    @Override
    public void hit(int damage) {
        health-= damage;
        engine.bossHealth.setValue(health);
        if(health <= 0) {
            destroyed();
            
        }
    }
    
    /**
     * when this ship is destroyed, the player wins the game
     * @return if the ship was removed or not
     */
    @Override
    public boolean destroyed() {
        if(!isAlive) return false;
        engine.exit(Constants.WON_GAME);
        return shipList.remove(this);
    }
    
    
    public void fire(boolean rightSide) {
       if(isAlive) {
           engine.spawnBullet(this, rightSide);
           firingTimer.start();
       }
    }
    
    
    
}
