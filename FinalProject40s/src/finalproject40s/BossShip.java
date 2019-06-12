package finalproject40s;

import gameTools.Constants;
import gameTools.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BossShip extends EnemyShip {

    final int MAX_SOUTH_DIRECTION = 750;

    public BossShip(Image image, int amount, int delay, Engine engine) {
        super(image, amount, engine);
        health = Constants.BASE_BOSS_HEALTH + 21 - (engine.manager.difficulty * 7);
        image.setDebug("", Color.BLUE);
        canFire = true;
        speed = amount;
        if (engine.manager.difficulty <= 1) damageOutput = Constants.BASE_SHIP_DAMAGE * 3;
        else damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        firingTimer = new Timer((2 + engine.manager.difficulty) * 475,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sideFire(true);
                fire();
                sideFire(false);
            }

        });
        firingTimer.start();
        directionTimer.stop();
        directionTimer = new Timer(Constants.STARTING_DIRECTION_DELAY, 
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDirection();
                if (coordinates.direction == Constants.SOUTH_DIRECTION) {
                    directionTimer.setDelay(Constants.STARTING_DIRECTION_DELAY / 2
                            + randomDirectionTime());
                } else {
                    directionTimer.setDelay(Constants.STARTING_DIRECTION_DELAY 
                            + randomDirectionTime());
                }
            }
        });
        directionTimer.start();
    }

    @Override
    public void hit(int damage, boolean shipDamage) {
        if(shipDamage) {
            player.coordinates.changeCoordinates(player.startingCoordinates);
        }
        health -= damage;
        engine.bossHealth.setValue(health);
        if (health <= 0) {
            destroyed();

        }
    }

    /**
     * when this ship is destroyed, the player wins the game
     *
     * @return if the ship was removed or not
     */
    @Override
    public boolean destroyed() {
        if (!isAlive) {
            return false;
        }
        engine.exit(Constants.WON_GAME);
        return shipList.remove(this);
    }

    public void sideFire(boolean rightSide) {
        if (isAlive) {
            engine.spawnBullet(this, rightSide);
            firingTimer.start();
        }
    }

    /**
     * randomly set the direction of the ship to one of 3 possibilities, north,
     * south, west or east
     */
    @Override
    public void changeDirection() {
        int direction = Constants.randomDirection(4);
        while (direction == coordinates.direction) {
            direction = Constants.randomDirection(4);
        }
        coordinates.direction = direction;
    }

    @Override
    public void action() {
        if (engine.isRunning) {
            move();
        }
        redraw();
        checkWalls();
        if (coordinates.bottom >= MAX_SOUTH_DIRECTION
                && coordinates.direction == Constants.SOUTH_DIRECTION) {
            coordinates.direction = Constants.NORTH_DIRECTION;
            directionTimer.setInitialDelay(Constants.STARTING_DIRECTION_DELAY * 2);
            directionTimer.restart();
        }
    }

}
