
package gameTools;

/**
 * GameObject.java - 
 * @Class Computer Science grade 11
 * @author riley.w
 * @since 29-Oct-2018
 */
public class GameObject {
        
    public Coordinates coordinates;
    public Image image;
    public boolean isAlive;
    
    /**
     * constructor method for this class sets class properties
     * @param image the image of the object
     */
    public GameObject(Image image) {
        this.image = image;
        coordinates = new Coordinates(image);
        coordinates.update();   
        show();
    }
   
     /**
     * Determines if this game character is colliding with a game object 
     * @param target the game object to check for collision with
     * @return it is colliding (true) or not (false)
     */
    public boolean isColliding(GameObject target) {
        if (!target.isAlive) return false; 
        else return coordinates.isOverlapping(target.coordinates);
    }
    
    /** 
     * Shows the game object in the user interface
     */
    public void show() {
        isAlive = true;
        image.show();
    }
    
    /**
     * hides the game object in the user interface
     */
    public void hide() {
        isAlive = false;
        image.hide();
    }
    
    
}
