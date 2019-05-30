package finalproject40s;


import gameTools.Constants;
import gameTools.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BulkyShip extends EnemyShip {

    public BulkyShip(Image image, Engine engine, int difficulty) {
        super(image, Constants.BASE_SHIP_MOVEMENT /2,engine);
        canFire= true;
        health = Constants.BASE_ENEMY_HEALTH + (5 - difficulty);
        if(difficulty <= 1)damageOutput = Constants.BASE_SHIP_DAMAGE * 4;
        else damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        firingTimer = new Timer((2 + difficulty) * 775, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fire();
            }
        });
    }

}
