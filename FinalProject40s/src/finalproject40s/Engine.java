package finalproject40s;

import collections.LinkedList;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import gameTools.Constants;
import gameTools.Image;
import gameTools.Wall;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Engine.java -
 *
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 9-Apr-2019
 */
public class Engine {

//    public boolean usingArrowKeys;
//    public boolean adminMode;
//    public int difficulty;
    public int points;
    public int lives;
    private int timeMinutes;
    private int timeSeconds;
    private Timer playingTime;
    private Timer spawnTimer;
    public Wall[] walls;
    public UIGame ui;
    public PropertiesManager manager;
    public PlayerShip player;
    private Background background;
    public boolean isRunning;
    public LinkedList<Bullet> bulletList = new LinkedList<>();

    /**
     * default constructor for this class setting everything up
     *
     * @param ui The UI displayed to the user
     * @param manager the manager that started the program (Used to store
     * Customizable properties)
     * @param tutorial
     */
    public Engine( PropertiesManager manager, boolean tutorial) {
        this.manager = manager;
        ui = new UIGame(this); 
        isRunning = true;
        ui.getContentPane().setBackground(Color.BLACK);
        ui.setSize(1650, 1025);
        ui.setLocationRelativeTo(null);
        ui.setResizable(false);
        buildPresetImages();
        ui.setVisible(true);
        playingTime = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
//                    background.swap();
                    timeSeconds++;
                    if (timeSeconds >= 60) {
                        timeSeconds -= 60;
                        timeMinutes++;
                    }
                }

            }
        });
        spawnTimer = new Timer( 3000 +(2000 * this.manager.difficulty), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    spawnShip();
                }
            }
        });
        lives = Constants.BASE_LIVES - 3 + manager.difficulty;
        points = 0;
        spawnTimer.start();
        playingTime.start();
    }

    /**
     * adds a point to the players total
     * @param ship the ship that the player destroyed to get the point
     */
    public void addPoint(Ship ship) {
        points++;
        manager.addPoint();
        if(points == Constants.POINTS_TO_BOSS && !manager.neverEnding) spawnBoss();
        Constants.output("Add the change in life display code here", true);
    }

    /**
     * exits the game, and display7s a message appropriate to the reason for the
     * exit, whether that is that they left, lost or won and displays the time
     * they played for
     *
     * @param exitType the reason why the exit is happening
     */
    public void exit(int exitType) {
        ui.dispose();
        isRunning = false;
        for (int i = 0; i < Ship.shipList.getLength(); i++) {
            Ship.shipList.get(i).shutDown();
        }
        manager.GUEST_ACCOUNT.pointsInLastGame = points;
        if (!manager.GUEST_ACCOUNT.equals(manager.accountLoggedIn)) {
            manager.accountLoggedIn.pointsInLastGame = points;
        }
        System.exit(0);
        if (exitType == Constants.EXITED_GAME) {
            Constants.output("You Exited the Game " + "\n You played for " + timeMinutes
                    + " minutes and " + timeSeconds + " seconds \n You gained "
                    + points + " before you exited!", true);
        } else if (exitType == Constants.LOST_GAME) {
            Constants.output("You Lost!!! " + "\n You lasted " + timeMinutes
                    + " minutes and " + timeSeconds + " seconds until your death"
                    + "\n You Destroyed " + points + " ships before "
                    + "you died!", true);
        } else if (exitType == Constants.WON_GAME) {
            Constants.output(" You have beaten the Space Monsters! Way to go!"
                    + "\n It took you " + timeMinutes + " minutes and " + timeSeconds + " seconds to Win!", true);
        }
        manager.play();
    }
    
    /**
     * spawns one of the regular enemy types
     */
    public void spawnShip() {
        int shipType = getShipType();
        if(shipType == Constants.ENEMY_TYPE_GRUNT) {
            GruntShip ship = new GruntShip(new Image(getXCoordinates(),
                    Constants.ENEMY_SPAWN_Y, 25, 25), this, manager.difficulty);
            add(ship.image.picture);
        } else if(shipType == Constants.ENEMY_TYPE_BULLKY) {
            BulkyShip ship = new BulkyShip(new Image(getXCoordinates(),
                    Constants.ENEMY_SPAWN_Y, 35, 35),this, manager.difficulty);
            add(ship.image.picture);
        } else if(shipType == Constants.ENEMY_TYPE_FAST) {
            SpeedyShip ship = new SpeedyShip(new Image(getXCoordinates(),
                    Constants.ENEMY_SPAWN_Y, 25, 25), this, manager.difficulty);
            add(ship.image.picture);
        } else {
            System.out.println("Error in ship typing ");
        }
    }

    /**
     * spawns a bullet above the ship that fired it
     * @param ship the ship that fired it
     * @return if a bullet was spawned or not
     */
    public boolean spawnBullet(Ship ship) {
        if (ship == null || ship.shipNumber < Constants.PLAYER_SHIP_NUMBER) {
            return false;
        }
        Bullet bullet = new Bullet(new Image(ship.coordinates.x, ship.coordinates.y,
                ship.coordinates.width, ship.coordinates.height),
                ship.speed * 3, 10, ship, this);
        add(bullet.image.picture);
        return true;
    }

    /**
     * creates the in game menu and the player images that are displayed to the
     * user
     */
    private void buildPresetImages() {

        Image[] wallImages = {new Image(0, 0, 10, 905), new Image(0, 0, 1700, 10),
            new Image(1635, 0, 10, 905)};
        walls = new Wall[wallImages.length + 1];
        for (int i = 0; i < wallImages.length; i++) {
            walls[i] = new Wall(wallImages[i], false);
            ui.add(walls[i].image.picture);
        }
        walls[wallImages.length] = new Wall(new Image(0, 905, 1700, 15), true);
        ui.add(walls[wallImages.length].image.picture);
        player = new PlayerShip(new Image(100, 100, 25, 25), Constants.BASE_SHIP_MOVEMENT, this);
        player.image.setDebug("", Color.BLUE);
        ui.add(player.image.picture);
        javax.swing.JButton closeButton = new javax.swing.JButton();
        closeButton.setBounds(1, 920, 100, 75);
        closeButton.setText("Exit Game");
        closeButton.setBackground(Color.RED);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(Constants.EXITED_GAME);
            }
        });
        closeButton.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                player.keyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                player.keyReleased(evt);
            }
        });
        ui.add(closeButton);
        background = new Background(ui);
        ui.add(background.image.picture);
    }

    /**
     * gets the type of ship being spawned by chance
     * @return the type of ship thats being created
     */
    private int getShipType() {
        return Constants.ENEMY_TYPE_GRUNT;
        
//        int random = Constants.random(1, 100);
//        int gruntChance = 35 + (12 * manager.difficulty);
//        System.out.println("chnage " + gruntChance);
//        int bullkyChance = gruntChance + (5 * 12 * manager.difficulty);
//         System.out.println("bulk chance " + bullkyChance);
//        if(random <= gruntChance) return Constants.ENEMY_TYPE_GRUNT;
//        else if (random <= bullkyChance) return Constants.ENEMY_TYPE_BULLKY;
//        else return Constants.ENEMY_TYPE_FAST;
    }

    
    /**
     * returns one "randomly" possible x coordinate for a ship to spawn at
     * @return the x coordinate "randomly" picked to spawn at
     */
    private int getXCoordinates() {
        final int[] OPTIONS = {150, 300, 450, 600, 750, 900, 1050};
        int random = Constants.random(1, OPTIONS.length);
        return OPTIONS[random - 1];
    }

    /**
     * adds the Jlabel to the user interface and adds the background after so
     * that the background is always in the background
     * @param object the Jlabel to add
     * @return if the Label was added or not
     */
    public boolean add(JLabel object) {
        if(object == null) return false;
        ui.add(object);
        ui.add(background.image.picture);
        return true;
    }
    
    
    
    /**
     * spawns the boss ship and creates the bosses health bar at the top of the screen
     */
    private void spawnBoss() {
        
    }

}
