
package gameTools;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

/**
 * Image.java - the image of the label
 * @Class Computer Science grade 11
 * @author riley.w
 * @since 8-Nov-2018
 */
public class Image {
   
public JLabel picture;

    /**
     * default constructor for this class
     * @param x the x value of the image
     * @param y the y value of the image
     * @param width the width of the image
     * @param height the height of the image
     */
    public  Image(int x, int y, int width, int height) {
        picture = new JLabel(" ");
        setBorder(false);
        picture.setBounds(x, y, width, height); 
        picture.setOpaque(false);
    }



    /**
     * Class constructor sets class properties based on jlabel
     * @param label the JLabel to display the image in
     */
    public Image(JLabel label) {
        picture = label;
        setBorder(false); 
        picture.setText(" ");
        picture.setOpaque(false);
    }
    
    /**
     * Sets the image to debug mode, meaning it removes any graphics and
     * changes to a colored rectangle with text
     * 
     * @param text the text to display in the image
     * @param background the background color of the image
     */
    public void setDebug(String text, Color background) {
        setBorder(true);
        if (text != null)  {
            picture.setText(text);
            picture.setHorizontalAlignment(CENTER);
        }
        if (background != null) {
            picture.setOpaque(true);
            picture.setBackground(background);
        }
        if (picture.getIcon() != null) {
            picture.setIcon(null);
        }
        Font font = new Font("Arial Narrow", Font.PLAIN, 10);
        picture.setFont(font);
    } 
    
    /**
     * Re-positions the image in it's container based on game character's data
     * 
     * @param coordinate the coordinate data for this image to use
     */
    public void redraw(Coordinates coordinate) {
        picture.setBounds(coordinate.x, coordinate.y,
                          coordinate.width, coordinate.height);
    }    
    
    /**
     * Shows the image in the user interface
     */
    public void show() {
        picture.setVisible(true);
    }
    
    /**
     * Hides the image in the user interface
     */
    public void hide() {
        picture.setVisible(false);
    }

    /**
     * Accesses the current x coordinate of the image in it's container
     * @return the current x coordinate
     */
    public int getX() {
        return picture.getX();
    }
    
    /**
     * Accesses the current y coordinate of the image in it's container
     * 
     * @return the current y coordinate
     */
    public int getY() {
        return picture.getY();
    }
    
    /**
     * Accesses the current width of the image in it's container
     * 
     * @return the current width
     */
    public int getWidth() {
        return picture.getWidth();
    }
    
    /**
     * Accesses the current height of the image in it's container
     * 
     * @return the current height
     */
    public int getHeight() {
        return picture.getHeight();
    }
    
    /**
     * Accesses the background color of the image
     * 
     * @return the images background color
     */
    public Color getBackground() {
        return picture.getBackground();
    }
    
    /**
     * Sets a border around the image (or not)
     * 
     * @param haveBorder should have a border (true) or not (false)
     */
    public void setBorder(boolean haveBorder) {
        if (haveBorder) picture.setBorder(BorderFactory.createEtchedBorder()); 
        else            picture.setBorder(null); 
    }  
    
    
}
