
package finalproject40s;

import gameTools.GameObject;
import gameTools.Image;
import java.awt.Color;

/**
 * Background.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 22-May-2019
 */
 class Background {
        
    final int MAX_BOTTOM = 500; 
    final int STARTING_TOP = -880;
    final int STARTING_BOTTOM = 10;
    final int DIFFERENCE = 5;
    UIGame ui;
    Image top;
    Image bottom;
    

    Background(UIGame ui) {
        super();
        this.ui = ui;
        top = new Image(10, -STARTING_TOP, 1620, 898);
        top.picture.setIcon(new 
            javax.swing.ImageIcon(getClass().getResource("/media/space1.jpg")));
        bottom = new Image(10, STARTING_BOTTOM, 1620, 898);
        bottom.picture.setIcon(new 
        javax.swing.ImageIcon(getClass().getResource("/media/space1.jpg")));
        
        
//        for (int i = 0; i < NUMBER_OF_IMAGES; i++) {
//            images[i] = new Image(image.getX(), image.getY(), image.getWidth(), image.getHeight());
//            if(i != 0) {
//                int y = images[i - 1].getY() + DIFFERENCE; 
//                images[i].picture.setBounds(image.getX(), y, image.getWidth(), image.getHeight());
//            } 
//        }
    }
//
    public void swap() {
        int yBottom = bottom.getY() + DIFFERENCE;
        int yTop = top.getY() + DIFFERENCE;
        if(yBottom >= MAX_BOTTOM) {
            yBottom = STARTING_BOTTOM;
        } else if(yTop >= MAX_BOTTOM) {
            yTop = STARTING_TOP;
        }
        top.picture.setBounds(top.getX(), yTop, top.getWidth(), top.getHeight());
        bottom.picture.setBounds(bottom.getX(), yBottom, bottom.getWidth(), bottom.getHeight());
        ui.add(top.picture);
        ui.add(bottom.picture);
    }
    
    
    
}
