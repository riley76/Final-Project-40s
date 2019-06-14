
package finalproject40s;

import gameTools.Image;

/**
 * Background.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 22-May-2019
 */
 class Background {
        
    final int MAX_BOTTOM = 925; 
    final int DIFFERENCE = 2;
    final int IMAGES = 2;
    final int HEIGHT = 898;
    final int WIDTH = 1625;
    final int X_VALUE = 10;
    final int TOP = 0;
    UIGame ui;
    Image top;
    Image middle;
    Image bottom;
    Image[] images = new Image[IMAGES];
    

    Background(UIGame ui) {
        this.ui = ui;
        final int[] STARTING_POSITION = new int[IMAGES];
        for (int i = 0; i < IMAGES; i++) {
            STARTING_POSITION[i] = 10 - (i * 890);
            images[i] = new Image(X_VALUE, STARTING_POSITION[i] , WIDTH, HEIGHT);
            images[i].picture.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/media/space1.jpg")));
        }
        
    }
    
    /**
     * moves all the images down, and to the top if off screen
     */
    public void swap() {
        int[] yValues = new int[IMAGES];
        for (int i = 0; i < IMAGES; i++) {
            yValues[i] = images[i].getY() + DIFFERENCE;
            if(yValues[i] >= MAX_BOTTOM) yValues[i] = TOP;
            images[i].picture.setBounds(X_VALUE, yValues[i], WIDTH, HEIGHT);
            images[i].picture.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/media/space1.jpg")));
        }
        addToUI();
    }

    /**
     * adds all images to the ui
     */
    public void addToUI() {
        for (int i = 0; i < IMAGES; i++) {
            ui.add(images[i].picture);
        }
    }
    
    
    
}
