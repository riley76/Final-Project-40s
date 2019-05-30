package finalproject40s;


import collections.LinkedList;
import gameTools.Constants;
import gameTools.GameCharacter;
import gameTools.Image;
import javax.swing.Timer;

public abstract class Ship extends GameCharacter{

    public Timer firingTimer;
    public int shipNumber;
    public int health;
    public int damageOutput;
    public int speed;
    protected Engine engine;
    public boolean canFire;
    public int firingDirection;
    public static LinkedList<Ship> shipList = new LinkedList<>();

    public Ship(Image hitbox, int amount, Engine engine) {
        super(hitbox, amount, 10);
        shipList.add(this);
        this.engine = engine;
        canFire = true;
    }

    /**
     * fires this ships weapon and starts firing delay timer
     */
    public void fire() {
       if(canFire && isAlive) {
           engine.spawnBullet(this);
           firingTimer.start();
           canFire = false;
       }
    }
    
    /**
     * damages the ship by the damage specified
     * @param damage the amount to remove from health
     */
    public abstract void hit(int damage);

    /**
     * checks to see if this ship is colliding with any walls
     */
    public void checkWalls() {
        for (int i = 0; i < engine.walls.length; i++) {
            if(isColliding(engine.walls[i]) && isAlive) {
                if(engine.walls[i].isEndWall && shipNumber != Constants.PLAYER_SHIP_NUMBER) {
                  engine.player.hit(3 - engine.manager.difficulty);
                  shutDown();
                } else coordinates.stickTo(engine.walls[i].coordinates);
            }
        }
    }
    
    /**
     * checks to see if this ship is colliding with any walls
     */
    public void checkShips() {
        for (int i = 0; i < shipList.getLength(); i++) {
            if(isColliding(shipList.get(i)) && shipList.get(i).shipNumber != 
                    shipNumber && isAlive) {
                hit(shipList.get(i).damageOutput * 2);
                shipList.get(i).hit(damageOutput * 2);
                return;
            }
        }
    }
    
    
   /**
     * Determines if two objects are "equal" in this context
     * @param object the object to compare to
     * @return the objects are "equal" (true) or not (false)
     */
    @Override
    public boolean equals(Object object) {
        if(object == null) return false;
        Ship ship = (Ship) object;
        if(ship.shipNumber == shipNumber && ship.isAlive
                && ship.coordinates.equals(coordinates)) {
            return true;
        }
        return false;
    }
    
    
}
