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
        shipNumber = totalNumber;
        firingDirection = Constants.SOUTH_DIRECTION;
        speed = Constants.BASE_SHIP_MOVEMENT;
    }
    
    /**
     * when the ship is destroyed,. it is removed from the list and is hidden
     * from the user
     * @return if the ship was removed or not
     */
    public boolean destroyed() {
        shutDown();
        return shipList.remove(this);
    }
    
    @Override
    public void hit(int damage) {
        health-= damage;
        if(health <= 0) {
            if(!destroyed()) System.out.println("Error in destroying ship");
        }
    }
    
    @Override
    public void action() {
        move();
        redraw();
        checkWalls();
        checkPlayer();
    }
    
}
