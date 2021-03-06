
package gameTools;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Constants.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 9-Apr-2019
 */
 public class Constants {
        
     public final static String ADMIN_CODE     = "8719";
     
    public final static int WON_GAME           = 1;
    public final static int LOST_GAME          = 2;
    public final static int EXITED_GAME        = 3;
    
    public final static int BASE_SHIP_MOVEMENT = 2;
    public final static int BASE_LIVES         = 5;
    public final static int BASE_PLAYER_HEALTH = 6;
    public final static int BASE_BOSS_HEALTH   = 40;
    public final static int BASE_SHIP_DAMAGE   = 1;
    public final static int BASE_SHIP_SIZE     = 30;
    public final static int BASE_BULLET_SIZE   = 5;
    public final static int BASE_UPGRADE_SIZE  = 20;
    public final static int BASE_ENEMY_HEALTH  = 2;
    public final static int PLAYER_SHIP_NUMBER = -1;
    public final static int ENEMY_SPAWN_Y      = 65;
    
    public final static int ENEMY_TYPE_GRUNT   = 1;
    public final static int ENEMY_TYPE_FAST    = 2;
    public final static int ENEMY_TYPE_BULLKY  = 3;
    public final static int POINTS_TO_BOSS     = 50; 
    
    public final static int STARTING_DIRECTION_DELAY = 500;
    public final static int NUMBER_OF_UPGRADES       = 5;
    public final static int NUMBER_OF_UPGRADE_SLOTS  = 3;
    public final static int ONE_SECOND               = 1000;
    public final static int UPGRADE_COUNT            = 12;
    public final static int PLAYER_FIRING_DELAY      = 500;
    
    
    public final static Font BASE_FONT = new Font("Tahoma", 1, 14);
    
    
    public static final int STOP_DIRECTION       = 0;
    public static final int UP_DIRECTION         = 1;
    public static final int DOWN_DIRECTION       = 2;
    public static final int LEFT_DIRECTION       = 3;
    public static final int RIGHT_DIRECTION      = 4;
    public static final int NORTH_DIRECTION      = 5;
    public static final int SOUTH_DIRECTION      = 6;
    public static final int WEST_DIRECTION       = 7;
    public static final int EAST_DIRECTION       = 8;
    public static final int NORTH_WEST_DIRECTION = 9;
    public static final int NORTH_EAST_DIRECTION = 10;
    public static final int SOUTH_EAST_DIRECTION = 11;
    public static final int SOUTH_WEST_DIRECTION = 12;
    
     
    /**
     * gets a integer from the user in a dialog box
     * @param text the text to display in the dialog
     * @return a valid integer the user inputted
     */
    public static int getInteger(String text) {
        String userInput = JOptionPane.showInputDialog(null, text, "Menu", JOptionPane.QUESTION_MESSAGE);
        if (userInput == null) {
            return 0;
        } else if (userInput.equals("")) {
            return 0;
        } else {
            int number = Integer.parseInt(userInput);
            return number;
        }
    }
    
    
    /**
     * returns a random direction
     * @param numberOFDirections the number of directions to pick from
     * @return the randomly generated direction
     */
    public static int randomDirection(int numberOFDirections) {
        if      (numberOFDirections == 2) return random(LEFT_DIRECTION ,RIGHT_DIRECTION );
        else if (numberOFDirections == 3) return random(SOUTH_DIRECTION ,EAST_DIRECTION );
        else if (numberOFDirections == 4) return random(NORTH_DIRECTION ,EAST_DIRECTION );
        else if (numberOFDirections == 8) return random(NORTH_DIRECTION ,SOUTH_WEST_DIRECTION );
        else                              return random(NORTH_DIRECTION ,SOUTH_DIRECTION );
    } 
    
    /**
     * generates a "random" number from the lowest and highest possible values
     * @param low the lowest possible value for the "random" number
     * @param high the highest possible value for the "random" number
     * @return the "random" number
     */
    public static int random(int low, int high) {
        double seed = Math.random();
        double L = (double)low;
        double H = (double)high;
        double number = (H - L + 1d) * seed + L;
        return (int)number;
    }
    
    /**
     * generates a "random" number from 1 to 100
     * @return the "random" number
     */
    public static int getPercentage() {
        double seed = Math.random();
        double L = 1;
        double H = 100;
        double number = (H - L + 1d) * seed + L;
        return (int)number;
    }
    
    
    
   /**
     * Displays a message to the user through a message box or through System.out
     * @param text the text to output to the user
     * @param displayBox whether to display through JOptionPane
     */
    public static void output(String text, boolean displayBox) {
        if(displayBox) JOptionPane.showMessageDialog(null, text);
        else System.out.println(text);
    }
    
    /**
     * takes an input from the user, making sure they enter something
     * @param text the text displayed to the user explain what to enter
     * @return what the user entered 
     */
    public static String input(String text){
        String output = JOptionPane.showInputDialog(text);
        while(output.equalsIgnoreCase(" ") || output.equalsIgnoreCase("")) {
        output = JOptionPane.showInputDialog("You Must enter Something! \n " + text);
        }
        return output;
    } 
    
    /**
     * asks the user a yes or no question and returns their response
     * @param question the question to ask the user
     * @return the answer from the user
     */
    public static boolean getYesNoResponse(String question) {
        int result = JOptionPane.showConfirmDialog(null, question, "Please answer Yes or No", 
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * displays the options to the user and returns what the user choices
     * @param text the current text on the screen that asks the question that
     * this method gets the answer to
     * @param OPTIONS the choice the user made form the options
     * @return how the user responds
     */
    public static String options(String text, final String[] OPTIONS) {
        JTextArea area = getTextArea(text);
        String result = JOptionPane.showInputDialog(null, area, "Chose One of the Options Below",
                JOptionPane.PLAIN_MESSAGE, null, OPTIONS, OPTIONS[0]).toString();
        System.out.println("result = " + result);
        if (result == null) return OPTIONS[0];
        return result;
    }
    
    private static JTextArea getTextArea(String text) {
        JTextArea area = new JTextArea();
        Font font = new Font("Consolas", Font.PLAIN, 14);
        area.setFont(font);
        Color background = new Color(238, 238, 238);
        Color foreground = new Color(0, 0, 0);
        area.setBackground(background);
        area.setForeground(foreground);
        area.setText(text);
        return area;
    }
    
    /**
     * used to check if there is an error
     * @param worked if it worked (true) or nod (false)
     * @param whatTriedToOccur what the method was trying to do, to display if it did not work 
     */
    public static void errorCheck(boolean worked, String whatTriedToOccur) {
        if(worked) return;
        output("There was an error while (" + whatTriedToOccur + ") has "
                + "failed for some reason", false);
    }
    
    /**
     * tells if an integer is even or odd
     * @param input the number that the method decides is even or odd
     * @return if input is even
     */
    public static boolean isEven(int input) {
        if ((input %2) == 0 ) return true; 
        else return false;
    }
    
    
}
