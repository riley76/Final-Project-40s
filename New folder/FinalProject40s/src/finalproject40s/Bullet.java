package finalproject40s;


import static finalproject40s.Ship.shipList;
import gameTools.Constants;
import gameTools.GameCharacter;
import gameTools.Image;
import java.awt.Color;

public class Bullet extends GameCharacter {

    private int damage;
    private Engine engine;
    private Ship ship;
    
    /**
     * the default constructor for this class
     * @param image the image of this class
     * @param amount the amount to move by 
     * @param delay the delay between movement
     * @param shipFiredFrom the ship this bullet was fired from
     * @param engine the engine of this game running general logic
     */
    public Bullet(Image image, int amount, int delay, Ship shipFiredFrom, Engine engine) {
        super(image, amount, delay);
        this.engine = engine;
        ship = shipFiredFrom;
        this.damage = ship.damageOutput;
        coordinates.direction = ship.firingDirection;
        if(ship.firingDirection == Constants.UP_DIRECTION || ship.firingDirection == Constants.NORTH_DIRECTION) {
            coordinates.y = coordinates.y - (int)(ship.coordinates.height *.5);
        } else if(ship.firingDirection == Constants.DOWN_DIRECTION || ship.firingDirection == Constants.SOUTH_DIRECTION) {
            coordinates.y = coordinates.y + (int)(ship.coordinates.height *1.25);
        }
        coordinates.recalculate();
        image.setDebug(" ", Color.RED);
    }

    @Override
    public void action() {
        move();
        redraw();
        checkShips();
        checkWalls();
    }

    /**
     * checks to see if colliding with any ships
     */
    private void checkShips() {
        for (int i = 0; i < shipList.getLength(); i++) {
            if(isColliding(shipList.get(i)) && ship.shipNumber != shipList.get(i).shipNumber) {
                if(ship.shipNumber == Constants.PLAYER_SHIP_NUMBER) shipList.get(i).hit(damage, false);
                else shipList.get(i).selfHit(damage);
                shutDown();
                return;
            }
        }
    }
    
    /**
     * checks to see if the bullet is colliding with any wall and destroys it if true
     */
     private void checkWalls() {
        for (int i = 0; i < engine.walls.length; i++) {
            if(isColliding(engine.walls[i])) {
                shutDown();
                return;
            }
        }       
    }
    
    
}
