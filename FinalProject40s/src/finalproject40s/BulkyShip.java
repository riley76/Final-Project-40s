package finalproject40s;


import gameTools.Constants;
import gameTools.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BulkyShip extends EnemyShip {

    public BulkyShip(Image image, Engine engine, int difficulty) {
        super(image, Constants.BASE_SHIP_MOVEMENT / 2,engine);
        image.setDebug("", Color.green);
        canFire= true;
        speed = Constants.BASE_SHIP_MOVEMENT / 2;
        health = Constants.BASE_ENEMY_HEALTH + (5 - difficulty);
        if(difficulty <= 1)damageOutput = Constants.BASE_SHIP_DAMAGE * 4;
        else damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        coordinates.amount = speed;
        firingTimer = new Timer((2 + difficulty) * 675, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fire();
            }
        });
        firingTimer.start();
    }

}
