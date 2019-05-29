package finalproject40s;


import gameTools.Constants;
import gameTools.Coordinates;
import gameTools.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SpeedyShip extends EnemyShip {

    public SpeedyShip(Image image, Engine engine, int difficulty) {
        super(image, Constants.BASE_SHIP_MOVEMENT * 3, engine);
        canFire= true;
        health = Constants.BASE_ENEMY_HEALTH;
        if(difficulty <= 1)damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        else damageOutput = Constants.BASE_SHIP_DAMAGE;
        firingTimer = new Timer((2 + difficulty) * 375, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fire();
            }
        });
        
    }

    @Override
    public Coordinates changeDirection(Coordinates coordinates) {
        return coordinates;
    }

}
