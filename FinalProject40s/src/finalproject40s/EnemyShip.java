package finalproject40s;


import gameTools.Constants;
import gameTools.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public abstract class EnemyShip extends Ship {

    public static int totalNumber;
    public static PlayerShip player;
    public Timer directionTimer;
    
    public void changeDirection() {
        int direction = Constants.randomDirection(3);
        while(direction == coordinates.direction) {
            direction = Constants.randomDirection(3);
        }
        coordinates.direction = direction;
    } 
    
    public EnemyShip(Image image, int amount, Engine engine) {
        super(image, amount,engine);
        totalNumber++;
        shipNumber = totalNumber;
        firingDirection = Constants.SOUTH_DIRECTION;
        coordinates.direction = firingDirection;
        directionTimer = new Timer(Constants.STARTING_DIRECTION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDirection();
                if(coordinates.direction == Constants.SOUTH_DIRECTION) {
                    directionTimer.setDelay(Constants.STARTING_DIRECTION_DELAY);
                } else directionTimer.setDelay(650);
            }
        });
        directionTimer.start();
    }
    
    /**
     * when the ship is destroyed,. it is removed from the list and is hidden
     * from the user
     * @return if the ship was removed or not
     */
    public boolean destroyed() {
        if(!isAlive) return false;
        engine.addPoint(this);
        shutDown();
        return shipList.remove(this);
    }
    
    @Override
    public void hit(int damage) {
        if(!isAlive) return; // to avoid false errors
        health-= damage;
        if(health <= 0) {
            Constants.errorCheck(destroyed(), "Ship destroyed in walls");
        }
    }
    
    /**
     * checks to see if this ship is colliding with any walls
     */
    @Override
    public void checkWalls() {
        for (int i = 0; i < engine.walls.length; i++) {
            if(isColliding(engine.walls[i]) && isAlive) {
                if(engine.walls[i].isEndWall) {
                  engine.player.hit(4 - engine.manager.difficulty);
                  Constants.errorCheck(destroyed(), "Ship destroyed in walls");
                } else coordinates.bounceOff(engine.walls[i].coordinates);
            }
        }
    }
    
    
    /**
     * fires this ships weapon and starts firing delay timer
     */
    @Override
    public void fire() {
       if(isAlive) {
           engine.spawnBullet(this);
           firingTimer.start();
       }
    }
    
    
    
    
    @Override
    public void action() {
        if(engine.isRunning) move();
        redraw();
        checkWalls();
    }
    
}
