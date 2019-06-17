
package finalproject40s;

import gameTools.Constants;
import gameTools.LinkedList;
import java.io.Serializable;
/** required imports */

/**
 * GameAccount.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 2-May-2019
 */
 public class GameAccount implements Serializable {
        
    public String name;
    private String passward;
    public int totalPoints;
    public int pointsInLastGame;
    public int totalLivesLost;
    public int gamesPlayed;
    static public LinkedList<GameAccount> allAccounts = new LinkedList<>();
    static final public int MAX_CHARACTER = 30;
    static final public int MIN_CHARACTER = 5;
     
    /**
     * default constructor for this class
     */
    public GameAccount() {
        name = Constants.input("What do you want to name your Account?");
        do {
            boolean notCorrectName = Constants.getYesNoResponse("Is the name (" + name + 
                ") What you Wanted?");
            while(!notCorrectName) {
                name = Constants.input("What do you want to name your Account?");
                notCorrectName = Constants.getYesNoResponse("Is the name (" + name + 
                    ") What you Wanted?");
            }
            if(nameTaken(name)) {
                Constants.output("That Name is Already Taken!", true);
            }
        } while(nameTaken(name));
        passward = setPassward();
        totalPoints = 0;
        pointsInLastGame = 0;
        totalLivesLost = 0;
        gamesPlayed = 0;
        allAccounts.add(this);
        PropertiesManager.accountsCreated = true;
        if(PropertiesManager.adminMode) {
            totalPoints = Constants.getInteger("What points should account " + name + " Have?");
        }
    }   

    /**
     * Creates a placeholder account when no one is signed in
     * @param name used to signal that this is a Blank Account 
     */
    public GameAccount(String name) {
        this.name = name;
        passward = "1234567890123456789012345678901"; // Deliberately 31 Characters
        // so its not possible for a user to have this password
        totalPoints = 0;
        pointsInLastGame = 0;
        totalLivesLost = 0;
        gamesPlayed = 0;
        allAccounts.add(this);
    }
    
    @Override
    public String toString() {
        return name + "," + passward + "," + totalPoints + ","
                + pointsInLastGame + "," + totalLivesLost + "," + gamesPlayed;
    }
    
    
    /**
     * signs them into their account if they have the correct password
     * @return whether they they signed into their account or not
     */
    public boolean signIn() {
            String passwardEntered = Constants.input("What is the Password for"
                    + " (" + name + ") ?");
            if(passwardEntered.equalsIgnoreCase(passward) || PropertiesManager.adminMode) {
                Constants.output("You have entered the correct Password for the "
                        + "account (" + name + ")" 
                        + "Your Games will be recorded and saved to your account", true);
                boolean deleteAccount = Constants.getYesNoResponse("Do you want"
                        + " to Delete This Account?");
                if(deleteAccount) {
                    boolean secondCheck = Constants.getYesNoResponse("Are you "
                            + "Sure you want to Delete This Account? "
                            + "\n There is no Undoing this");
                    if(secondCheck) {
                        allAccounts.remove(this);
                        Constants.output("Account (" + name + ") Deleted", true);
                        return false;
                    }
                }
                return true;
            } 
        return false;
    }
    
    /**
     * displayed the statistics of this account to the user
     */
    public void displayStatistics() {
        Constants.output("Account Name = (" + name + ")"
                + "\nTotal Points = " + totalPoints 
                + "\nTotal Lives Lost = " + totalLivesLost 
                + "\nGames played = " + gamesPlayed
                + "\nPoints Earned in the Last Game this account played "
                        + "= " + pointsInLastGame, true);
    }
    
    /**
     * sets the password between the max and min characters and ensure the user
     * know what their password is
     * @return the correctly inputed password
     */
    private String setPassward() {
        String firstPassward = Constants.input(" What do you want your Password to be?"
                + " \n Capitals are ignored");
        String secondPassward = Constants.input("Please confirm you Password ");
        while(!firstPassward.equalsIgnoreCase(secondPassward) 
                || firstPassward.length() > MAX_CHARACTER 
                || firstPassward.length() < MIN_CHARACTER ) {
            if(firstPassward.length() > MAX_CHARACTER 
                || firstPassward.length() < MIN_CHARACTER) {
                firstPassward = Constants.input(" Your password length must be"
                        + " between " + MAX_CHARACTER + " and " + MIN_CHARACTER 
                        + "\n Make sure you enter the Password the same way each time"
                        + "\n What do you want your Password to be?"
                        + "\n       Capitals are ignored");
            } else {
                firstPassward = Constants.input(" Your Passwords did not match, Make "
                    + "sure you enter the Password the same way each time"
                    + "\n What do you want your Password to be?"
                    + " \n          Capitals are ignored");
            }
            secondPassward = Constants.input("Please confirm your Password ");
        }
        return firstPassward;
    }
  
    /**
     * determines if two accounts are the same
     * @param otherAccount the other account to compare to
     * @return if they are equals or not
     */
    public boolean equals(GameAccount otherAccount) {
        if(otherAccount.passward.equalsIgnoreCase(passward)
                && otherAccount.name.equalsIgnoreCase(name)) return true;
        return false;
    }

    /**
     * checks if a user with that name is already Taken 
     * @param name the name the user wants to use
     * @return if the name is taken or not
     */
    private boolean nameTaken(String name) {
        for (int i = 0; i < allAccounts.getLength(); i++) {
            if(allAccounts.get(i).name.equalsIgnoreCase(name)) return true;
        }
        return false;
    }
    
    /**
     * turns the names of all accounts into a string
     * @return the array of names in the form of strings
     */
    public static String[] getNames() {
        String[] names = new String[allAccounts.getLength()];
        for (int i = 0; i < names.length; i++) {
            names[i] = allAccounts.get(i).name;
        }
        return names;
    }
    
}
