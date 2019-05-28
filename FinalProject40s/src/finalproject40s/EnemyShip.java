package finalproject40s;


import gameTools.Constants;
import gameTools.Coordinates;
import gameTools.Image;

public abstract class EnemyShip extends Ship {

    public static int totalNumber;
    
    
    public abstract Coordinates changeDirection(Coordinates coordinates);
    
    public EnemyShip(Image image, int amount, Engine engine) {
        super(image, amount,engine);
        totalNumber++;
        firingDirection = Constants.SOUTH_DIRECTION;
        speed = Constants.BASE_SHIP_MOVEMENT;
    }
    
    public boolean destroyed() {
        return shipList.remove(this);
    }
    
    
}
