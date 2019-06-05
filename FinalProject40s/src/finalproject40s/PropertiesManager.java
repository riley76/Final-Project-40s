package finalproject40s;

import static finalproject40s.FinalProject40s.APP_TITLE;
import static finalproject40s.GameAccount.allAccounts;
import gameTools.Constants;
import static gameTools.Constants.missingWork;
import static gameTools.Constants.options;
import static gameTools.Constants.output;
import gameTools.FileHandler;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * PropertiesManager.java - created to avoid conflicts with static methods
 *
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 2-May-2019
 */
public class PropertiesManager {

    final String[] MENUS = {"Main Section", "Rules and Explanations", "Options",
        "Leaderboards/Accounts"};
    final GameAccount GUEST_ACCOUNT = new GameAccount("Guest Stats");
    final int MAX_LEADERBOARDS = 10;
    String currentMenu = MENUS[0];
    int difficulty;
    int lives;
    boolean neverEnding = false;
    boolean usingArrowKeys;
    static boolean adminMode;
    static UiMenu ui;
    static boolean accountsCreated = false;
    GameAccount accountLoggedIn = GUEST_ACCOUNT;

    /**
     * default constructor for this class
     */
    public PropertiesManager() {
        play();
        usingArrowKeys = true;
        difficulty = 3;
        adminMode = true;
    }

    /**
     * starts the menu for the game
     */
    public void play() {
        ui = new UiMenu(this);
        ui.setSize(940, 550);
        ui.setLocationRelativeTo(null);
        ui.setResizable(false);
        changeMenu();
        toggleLoggedAccount(accountLoggedIn);
        ui.setVisible(true);
    }

    /**
     * sees if the user wants to continue playing, and responses to the user if
     * they lost or won
     *
     * @param endType they way they are exiting, whether just quitting, losing
     * or winning
     */
    public void keepPlaying(int endType) {
        int result;
        if (endType == Constants.WON_GAME) {
            result = JOptionPane.showConfirmDialog(null,
                    "You Beat the Game! Congratulations! "
                    + "Do you want to Play Again?",
                    APP_TITLE,
                    JOptionPane.YES_NO_OPTION);
        } else if (endType == Constants.LOST_GAME) {
            result = JOptionPane.showConfirmDialog(null,
                    "You lost! Bad luck i'm sure, want to try again?",
                    APP_TITLE,
                    JOptionPane.YES_NO_OPTION);
        } else {
            result = JOptionPane.showConfirmDialog(null,
                    "Do you want to keep playing?",
                    APP_TITLE,
                    JOptionPane.YES_NO_OPTION);
        }
        if (result == JOptionPane.YES_OPTION) {
            ui.dispose();
            play();
        } else {
            end();
        }
    }

    /**
     * changes the title of the page and the text of the buttons to properly
     * display what will happen if they are pressed
     */
    public void changeMenu() {
        if (currentMenu.equalsIgnoreCase(MENUS[0])) {
            ui.button1.setText(MENUS[1]);
            ui.button2.setText(MENUS[2]);
            ui.button3.setText(MENUS[3]);
            ui.button4.setText("Game Tutorial");
            ui.button5.setText("Play Game");
            ui.button6.setText("Empty");
            ui.button6.setVisible(false);
            ui.labTitle.setText(MENUS[0]);
        } else if (currentMenu.equalsIgnoreCase(MENUS[1])) {
            ui.button1.setText(MENUS[0]);
            ui.button2.setText(MENUS[2]);
            ui.button3.setText(MENUS[3]);
            ui.button4.setText("Story");
            ui.button5.setText("Controls");
            ui.button6.setText("Mechanics");
            ui.button6.setVisible(true);
            ui.labTitle.setText(MENUS[1]);
        } else if (currentMenu.equalsIgnoreCase(MENUS[2])) {
            ui.button1.setText(MENUS[1]);
            ui.button2.setText(MENUS[0]);
            ui.button3.setText(MENUS[3]);
            if (neverEnding) {
                ui.button4.setText("Play Story Mode (With Boss and End");
            } else {
                ui.button4.setText("Play Never Ending Mode(No Boss)");
            }
            ui.button5.setText("Set Difficulty");
            if (usingArrowKeys) {
                ui.button6.setText("Use WASD to move");
            } else {
                ui.button6.setText("Use Arrows to move");
            }
            ui.button6.setVisible(true);
            ui.labTitle.setText(MENUS[2]);
        } else if (currentMenu.equalsIgnoreCase(MENUS[3])) {
            ui.button1.setText(MENUS[1]);
            ui.button2.setText(MENUS[2]);
            ui.button3.setText(MENUS[0]);
            ui.button4.setText("View Top Leaderboards");
            ui.button5.setText("Sign into Account");
            ui.button6.setText("View Specific Account Statistics");
            ui.button6.setVisible(true);
            ui.labTitle.setText(MENUS[3]);
        }
        if (neverEnding) {
            ui.labNeverEndingMode.setText("Playing in Never Ending mode");
        } else {
            ui.labNeverEndingMode.setText("Playing In Story Mode");
        }
        if (usingArrowKeys) {
            ui.labControlType.setText("Control Type: Arrow Keys");
        } else {
            ui.labControlType.setText("Control Type: WASD Keys");
        }
    }

    /**
     * toggles Admin mode used for testing purposes, if anyone sees this then i
     * forgot to delete it
     */
    public void toggleAdmin() {
        if (adminMode) {
            adminMode = false;
            ui.buttonAdmin.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "Admin Mode Disabled!");
        } else {
            adminMode = true;
            ui.buttonAdmin.setBackground(Color.green);
            JOptionPane.showMessageDialog(null, "Admin Mode Enabled!");
        }
    }

    /**
     * does the action that the button currently represents
     */
    public void clickedButton2() {
        if (currentMenu.equalsIgnoreCase(MENUS[2])) {
            currentMenu = MENUS[0];
        } else {
            currentMenu = MENUS[2];
        }
        changeMenu();
    }

    /**
     * does the action that the button currently represents
     */
    public void clickedButton1() {
        if (currentMenu.equalsIgnoreCase(MENUS[1])) {
            currentMenu = MENUS[0];
        } else {
            currentMenu = MENUS[1];
        }
        changeMenu();
    }

    /**
     * does the action that the button currently represents
     */
    public void clickedButton3() {
        if (currentMenu.equalsIgnoreCase(MENUS[3])) {
            currentMenu = MENUS[0];
        } else {
            currentMenu = MENUS[3];
        }
        changeMenu();
    }

    /**
     * does the action that the button currently represents
     */
    public void clickedButton5() {
        if (currentMenu.equalsIgnoreCase(MENUS[0])) {
            loadGame(false);
        } else if (currentMenu.equalsIgnoreCase(MENUS[1])) {
            missingWork("Controls Explain");
        } else if (currentMenu.equalsIgnoreCase(MENUS[2])) {
            final String DIFFICULTY_OPTIONS[] = {"Easy", "Medium", "Hard"};
            String curentDifficulty = "";
            if (difficulty == 3) {
                curentDifficulty = DIFFICULTY_OPTIONS[0];
            } else if (difficulty == 2) {
                curentDifficulty = DIFFICULTY_OPTIONS[1];
            } else {
                curentDifficulty = DIFFICULTY_OPTIONS[2];
            }
            String input = options("What Difficulty Do you want to Play? "
                    + "You are Currently Playing at " + curentDifficulty +
                    " difficulty", DIFFICULTY_OPTIONS);
            if (input.equalsIgnoreCase(DIFFICULTY_OPTIONS[2])) {
                difficulty = 1;
            } else if (input.equalsIgnoreCase(DIFFICULTY_OPTIONS[1])) {
                difficulty = 2;
            } else {
                difficulty = 3;
            }
        } else if (currentMenu.equalsIgnoreCase(MENUS[3])) {
            if (accountLoggedIn.name.equalsIgnoreCase("Guest Stats")) {
                String input = options("What account do you want", 
                        GameAccount.accountNamesList.toArray(new String[GameAccount.accountNamesList.getLength()]));
                if (input.equalsIgnoreCase(GameAccount.accountNamesList.get(0))) {
                    toggleLoggedAccount(new GameAccount());
                    ui.button5.setText("Sign out of Account");
                } else {
                    for (int i = 1; i < GameAccount.accountNamesList.getLength(); i++) {
                        if (GameAccount.accountNamesList.get(i).equalsIgnoreCase(input)) {
                            if (GameAccount.allAccounts.get(i - 1).signIn()) {
                                toggleLoggedAccount(GameAccount.allAccounts.get(i - 1));
                                ui.button5.setText("Sign out of Account");
                            } else {
                                output("Incorrect password", true);
                            }
                        }
                    }
                }
            } else {
                output("(" + accountLoggedIn.name + ") is now logged out", true);
                toggleLoggedAccount(GUEST_ACCOUNT);
                ui.button5.setText("Sign into Account");
            }
        }
    }

    /**
     * does the action that the button currently represents
     */
    public void clickedButton6() {
        if (currentMenu.equalsIgnoreCase(MENUS[0])) {
            output("This Button has no Action, How Did you Press it?", true);
        } else if (currentMenu.equalsIgnoreCase(MENUS[1])) {
            missingWork("Mechanics Explained");
        } else if (currentMenu.equalsIgnoreCase(MENUS[2])) {
            if (usingArrowKeys) {
                usingArrowKeys = false;
                ui.button6.setText("Use Arrows to move");
                ui.labControlType.setText("Control Type: WASD Keys");
                output("Now using WASD Keys for Movement", true);
            } else {
                usingArrowKeys = true;
                ui.labControlType.setText("Control Type: Arrow Keys");
                ui.button6.setText("Use WASD to move");
                output("Now using Arrow Keys for Movement", true);
            }
        } else if (currentMenu.equalsIgnoreCase(MENUS[3])) {
            if (!accountsCreated) {
                output("No Accounts to view!", true);
                return;
            }
            FileHandler fileHandler = new FileHandler("Accounts");
            String input = options("What Account do You Want To View?",
                    GameAccount.accountNamesList.toArray(new String[GameAccount.accountNamesList.getLength()]));
            for (int i = 1; i < GameAccount.allAccounts.getLength() + 1; i++) {
                if (input.equalsIgnoreCase(GameAccount.accountNamesList.get(i))) {
                    GameAccount.allAccounts.get(i).displayStatistics();
                }
            }
        }
    }

    /**
     * does the action that the button currently represents
     */
    public void clickedButton4() {
        if (currentMenu.equalsIgnoreCase(MENUS[0])) {
            loadGame(true);
        } else if (currentMenu.equalsIgnoreCase(MENUS[1])) {
            missingWork("Story Explained");
        } else if (currentMenu.equalsIgnoreCase(MENUS[2])) {
            if (neverEnding) {
                neverEnding = false;
                ui.button4.setText("Play Never Ending Mode(No Boss)");
                ui.labNeverEndingMode.setText("Playing In Story Mode");
                output("Now Game set to Story Mode"
                        + "\nYou can beat the game by defeating the boss"
                        + "\nIn which case you will end playing the game", true);
            } else {
                neverEnding = true;
                ui.button4.setText("Play Story Mode (With Boss and End");
                ui.labNeverEndingMode.setText("Playing in Never Ending mode");
                output("Now Game set to Never Ending mod"
                        + "\n Now the game will only end when you die or exit, and"
                        + "\n no boss will spawn", true);
            }
        } else if (currentMenu.equalsIgnoreCase(MENUS[3])) {
            displayLeaderboards();
        }
    }
    
    /**
     * displays the top players and their score to the user
     */
    private void displayLeaderboards() {
        if (GameAccount.allAccounts.getLength() == 0) {
            output("No Accounts Have Been Created!", true);
            return;
        }
        GameAccount leaderBoards[];
        if (GameAccount.allAccounts.getLength() >= MAX_LEADERBOARDS) {
            leaderBoards = new GameAccount[MAX_LEADERBOARDS];
        } else {
            leaderBoards = new GameAccount[GameAccount.allAccounts.getLength()];
        }
        for (int i = 0; i < GameAccount.allAccounts.getLength(); i++) {
            int spot = spotWorks(leaderBoards.length - 
                    getSpot(allAccounts.toArray(new 
                            GameAccount[allAccounts.getLength()]), 0, 
                            GameAccount.allAccounts.get(i).totalPoints,
                            leaderBoards.length), leaderBoards);
            if(spot < MAX_LEADERBOARDS) leaderBoards[spot] = GameAccount.allAccounts.get(i);
        }
        String text = "Best Players of All Time in Space Monster Shoot Em Up!!! \n";
        for (int i = 0; i < leaderBoards.length; i++) {
            text += (i + 1) + " Name: (" + leaderBoards[i].name
                    + ") Points = " + leaderBoards[i].totalPoints + "\n";
        }
        output(text, true);
    }

    /**
     * Sets UI to display to the user the person currently logged in
     *
     * @param account the account being logged in
     */
    private void toggleLoggedAccount(GameAccount account) {
        accountLoggedIn = account;
        ui.labLoggedInName.setText("(" + account.name + ")");
        ui.labLoggedInPoints.setText("(" + account.totalPoints + ")");
        ui.labLoggedInGames.setText("(" + account.gamesPlayed + ")");
        ui.labLoggedInLives.setText("(" + account.totalLivesLost + ")");
    }

    /**
     * ends the program with an exit message
     */
    public void end() {
        JOptionPane.showMessageDialog(null, " Thank You for Playing " + APP_TITLE + ", "
                + "\n We hope you Play again soon", APP_TITLE, 1);
        System.exit(0);
        accountLoggedIn.signIn();
    }
    
    private int getSpot(GameAccount[] accountsToCheck, int index, int compare, int length) {
        if(index >= length) return 0;
        if(compare < accountsToCheck[index].totalPoints) {
            return getSpot(accountsToCheck, index + 1, compare, length);
        }
        return 1 + getSpot(accountsToCheck, index + 1, compare, length);         
                
    }
    
    
    private int spotWorks(int spot, GameAccount[] leaderBoards) {
            if(spot >= leaderBoards.length) return MAX_LEADERBOARDS;
            if(leaderBoards[spot] == null) return spot;
            return spotWorks(spot + 1, leaderBoards);
            
    }
    
    /**
     * adds a point to the appropriate account(s)
     */
    public void addPoint() {
        if(!accountLoggedIn.equals(GUEST_ACCOUNT)) accountLoggedIn.totalPoints++;
        GUEST_ACCOUNT.totalPoints++;
        
    }
    
    /**
     * loads the game itself, either the tutorial or the full game
     * @param tutorial if using the tutorial mode or not
     */
    public void loadGame(boolean tutorial) {
        GUEST_ACCOUNT.gamesPlayed++;
        System.out.println("difficulty = " + difficulty);
        if(!accountLoggedIn.equals(GUEST_ACCOUNT)) accountLoggedIn.gamesPlayed++;
        ui.dispose();
        new Engine(this, tutorial);
    } 
    
}
