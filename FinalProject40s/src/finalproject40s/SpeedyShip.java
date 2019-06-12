package finalproject40s;


import gameTools.Constants;
import gameTools.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SpeedyShip extends EnemyShip {

    public SpeedyShip(Image image, Engine engine, int difficulty) {
        super(image, (int) (Constants.BASE_SHIP_MOVEMENT), engine);
        image.setDebug("", Color.CYAN);
        canFire= true;
        health = Constants.BASE_ENEMY_HEALTH / 2;
        if(difficulty <= 1) damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        else damageOutput = Constants.BASE_SHIP_DAMAGE;
        speed = (int)(Constants.BASE_SHIP_MOVEMENT * 1.5);
        coordinates.amount = speed;
        firingTimer = new Timer((2 + difficulty) * 305, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fire();
            }
        });
        firingTimer.start();
    }

}
