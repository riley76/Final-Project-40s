
package finalproject40s;

import gameTools.Coordinates;
import gameTools.GameObject;
import gameTools.Image;

/**
 * Background.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 22-May-2019
 */
 class Background extends GameObject{
        
    final int NUMBER_OF_IMAGES = 326; 
    final int DIFFERENCE = 5;
    int current;
    UIGame ui;
//    Image[] images = new Image[NUMBER_OF_IMAGES];  

    Background(UIGame ui) {
        super(new Image(10, 10, 1625, 898));
//        current = 0;
        this.ui = ui;
        image.picture.setIcon(new 
            javax.swing.ImageIcon(getClass().getResource("/media/space1.jpg")));
//        for (int i = 0; i < NUMBER_OF_IMAGES; i++) {
//            images[i] = image;
//            if(i != 0) {
//                int y = images[i - 1].getY() + DIFFERENCE; 
//                images[i].show();
//                images[i].picture.setBounds(images[i].getX(), images[i].getY(), images[i].getWidth(), images[i].getHeight());
//            } 
//            this.ui.add(images[i].picture);
//        }
//        images[0].show();
    }
//
//    public void swap() {
//        images[current].hide();
//        if(current  == NUMBER_OF_IMAGES - 1) current = 0;
//        else current++;
//        images[current].show();
//    }
    
    
    
}
