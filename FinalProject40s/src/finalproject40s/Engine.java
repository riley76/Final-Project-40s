package finalproject40s;

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
    public javax.swing.JProgressBar playerHealthBar;
    private javax.swing.JButton closeButton;
    private Image livesCounter;
    private Image time;
    public Image pointsCounter;
    private Image[] upgradeSlots;
    private Image[] upgradeTimers;
    private Image bossHealthLabel;
    public javax.swing.JProgressBar bossHealth;
    
    

    /**
     * default constructor for this class setting everything up
     *
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
        lives = Constants.BASE_LIVES - 3 + manager.difficulty;
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
                    String text = "Time ";
                    if(timeMinutes >= 10) text = text + timeMinutes;
                    else text = text + "0" + timeMinutes;
                    text = text + ":";
                    if(timeSeconds >= 10) text = text + timeSeconds;
                    else text = text + "0" + timeSeconds;
                    time.picture.setText(text);
                }
            }
        });
        spawnTimer = new Timer( 3000 +(2000 * this.manager.difficulty),
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    spawnShip();
                }
            }
        });
        points = 0;
        spawnTimer.start();
        playingTime.start();
        closeButton.setFocusable(false);
        ui.requestFocus();
    }

    /**
     * adds a point to the players total
     * @param ship the ship that the player destroyed to get the point
     */
    public void addPoint(Ship ship) {
        points++;
        pointsCounter.setDebug("Points: " + points, Color.BLACK);
        pointsCounter.picture.setFont(Constants.BASE_FONT);
        manager.addPoint();
        int upgradeSpawn = Constants.getPercentage();
        if(upgradeSpawn <= (10 + (18 * manager.difficulty))) spawnUpgrade(ship);
        if(points >= Constants.POINTS_TO_BOSS && !manager.neverEnding) spawnBoss();
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
                    + "\n It took you " + timeMinutes + " minutes and " + 
                    timeSeconds + " seconds to Win!", true);
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
                    Constants.ENEMY_SPAWN_Y, Constants.BASE_SHIP_SIZE,
                    Constants.BASE_SHIP_SIZE), this, manager.difficulty);
            add(ship.image.picture);
        } else if(shipType == Constants.ENEMY_TYPE_BULLKY) {
            BulkyShip ship = new BulkyShip(new Image(getXCoordinates(),
                    Constants.ENEMY_SPAWN_Y, (int)(Constants.BASE_SHIP_SIZE * 1.5),
                    (int)(Constants.BASE_SHIP_SIZE * 1.5)),this, manager.difficulty);
            add(ship.image.picture);
        } else if(shipType == Constants.ENEMY_TYPE_FAST) {
            SpeedyShip ship = new SpeedyShip(new Image(getXCoordinates(),
                    Constants.ENEMY_SPAWN_Y, Constants.BASE_SHIP_SIZE, 
                    Constants.BASE_SHIP_SIZE), this, manager.difficulty);
            add(ship.image.picture);
        } else {
            System.out.println("Error in ship typing, type atempt that failed "
                    + "was " + shipType);
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
        Bullet bullet = new Bullet(new Image(ship.coordinates.x +
                (int)(ship.coordinates.width / 2), ship.coordinates.y,
                Constants.BASE_BULLET_SIZE, Constants.BASE_BULLET_SIZE),
                ship.speed * 3, 10, ship, this);
        add(bullet.image.picture);
        return true;
    }

    /**
     * spawns a bullet above the ship that fired it to one direction or the other
     * @param ship the ship that fired it
     * @return if a bullet was spawned or not
     */
    public boolean spawnBullet(Ship ship, boolean rightSide) {
        if (ship == null || ship.shipNumber < Constants.PLAYER_SHIP_NUMBER) {
            return false;
        }
        Bullet bullet;
        if(rightSide) {
            bullet = new Bullet(new Image(ship.coordinates.x + 
                ship.coordinates.width - 5, ship.coordinates.y,
                Constants.BASE_BULLET_SIZE, Constants.BASE_BULLET_SIZE),
                ship.speed * 3, 10, ship, this);
        } else {
            bullet = new Bullet(new Image(ship.coordinates.x + 5, ship.coordinates.y,
                Constants.BASE_BULLET_SIZE, Constants.BASE_BULLET_SIZE),
                ship.speed * 3, 10, ship, this);
        }
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
        player = new PlayerShip(new Image(800, 750, 25, 45), this);
        ui.add(player.image.picture);
        closeButton = new javax.swing.JButton();
        closeButton.setBounds(1, 920, 90, 75);
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
        Image healthLabel = new Image(95, 928, 55, 60);
        healthLabel.setDebug("Health:", Color.BLACK);
        healthLabel.picture.setForeground(Color.WHITE);
        healthLabel.picture.setFont(Constants.BASE_FONT);
        ui.add(healthLabel.picture);
        playerHealthBar = new javax.swing.JProgressBar();
        playerHealthBar.setBounds(155, 928, 315, 60);
        playerHealthBar.setMaximum(Constants.BASE_PLAYER_HEALTH - 3 +
                manager.difficulty);
        playerHealthBar.setForeground(Color.green);
        playerHealthBar.setBackground(Color.RED);
        playerHealthBar.setValue(player.health);
        ui.add(playerHealthBar);
        livesCounter = new Image(475, 925, 85, 30);
        changeLives(0);
        livesCounter.picture.setForeground(Color.WHITE);
        livesCounter.picture.setFont(Constants.BASE_FONT);
        ui.add(livesCounter.picture);
        time = new Image(475, 960, 95, 30);
        time.setDebug("Time: 00:00", Color.BLACK);
        time.picture.setForeground(Color.WHITE);
        time.picture.setFont(Constants.BASE_FONT);
        ui.add(time.picture);
        pointsCounter = new Image(575, 960, 80, 30);
        pointsCounter.setDebug("Points: 0", Color.BLACK);
        pointsCounter.picture.setForeground(Color.WHITE);
        pointsCounter.picture.setFont(Constants.BASE_FONT);
        ui.add(pointsCounter.picture);
        upgradeSlots = new Image[Constants.NUMBER_OF_UPGRADE_SLOTS];
        for (int i = 0; i < upgradeSlots.length; i++) {
            upgradeSlots[i] = new Image(665 +(95 * i), 925, 65, 65);
            upgradeSlots[i].setDebug("Slot " + (i + 1) + ": Empty", Color.BLUE);
            ui.add(upgradeSlots[i].picture);
            time.picture.setForeground(Color.WHITE);
        }
        upgradeTimers = new Image[Constants.NUMBER_OF_UPGRADES - 1];
        for (int i = 0; i < upgradeTimers.length; i++) {
            if(Constants.isEven(i)) upgradeTimers[i] = new Image(940 + (i * 70),
                    930, 135, 30);
            else upgradeTimers[i] = new Image(940 + ((i - 1) * 70), 965, 135, 30);
            upgradeTimers[i].setDebug(Upgrade.NAMES[i] + " Time: " + 
                    Constants.UPGRADE_COUNT, Color.BLACK);
            upgradeTimers[i].picture.setForeground(Color.WHITE);
            upgradeTimers[i].picture.setFont(Constants.BASE_FONT);
            upgradeTimers[i].hide();
            ui.add(upgradeTimers[i].picture);
        }
        bossHealthLabel = new Image(1385, 925, 100, 68);
        bossHealthLabel.picture.setText("Boss Health");
        bossHealthLabel.picture.setForeground(Color.WHITE);
        bossHealthLabel.picture.setFont(Constants.BASE_FONT);
        ui.add(bossHealthLabel.picture);
        bossHealthLabel.hide();
        bossHealth = new javax.swing.JProgressBar();
        bossHealth.setBounds(1225, 925, 415, 68);
        bossHealth.setMaximum(Constants.BASE_BOSS_HEALTH + 21 - (manager.difficulty * 7));
        bossHealth.setForeground(Color.GREEN);
        bossHealth.setBackground(Color.RED);
        bossHealth.setValue(bossHealth.getMaximum());
        bossHealth.setVisible(false);
        ui.add(bossHealth);
        background = new Background(ui);
        ui.add(background.image.picture);
    }

    /**
     * changes lives by given amount and updates the user interface accordingly
     * @param change the amount to change the number of lives by
     */
    public void changeLives(int change) {
        lives+= change;
        livesCounter.setDebug("Lives = " + lives, Color.BLACK);
        livesCounter.picture.setFont(Constants.BASE_FONT);
    }
    
    
    /**
     * gets the type of ship being spawned by chance
     * @return the type of ship thats being created
     */
    private int getShipType() {
        int random = Constants.getPercentage();
        int gruntChance = 30 + (7 * manager.difficulty);
        int fastChance = gruntChance + 20 +(5 * manager.difficulty);
        if(random > fastChance) return Constants.ENEMY_TYPE_BULLKY;
        else if (random >= gruntChance) return Constants.ENEMY_TYPE_FAST;
        else return Constants.ENEMY_TYPE_GRUNT;
    }

    
    /**
     * returns one "randomly" possible x coordinate for a ship to spawn at
     * @return the x coordinate "randomly" picked to spawn at
     */
    private int getXCoordinates() {
        final int[] OPTIONS = {150, 300, 450, 600, 750, 900, 1050, 1300, 1450};
        return OPTIONS[Constants.random(1, OPTIONS.length) - 1];
    }

    /**
     * adds the Jlabel to the user interface and adds the "background" after so
     * that the "background" is always in the background
     * @param object the Jlabel to add
     */
    public void add(JLabel object) {
        if(object == null) System.out.println("Error adding Object");
        ui.add(object);
        ui.add(background.image.picture);
    }
    
    
    /**
     * spawns the boss ship and creates the bosses health bar at the
     * bottom of the screen
     */
    public void spawnBoss() {
        bossHealthLabel.show();
        bossHealth.setVisible(true);
        BossShip boss = new BossShip(new Image(ui.getWidth() / 2, Constants.ENEMY_SPAWN_Y,
                (int)(Constants.BASE_SHIP_SIZE * 3.5), 
                (int)(Constants.BASE_SHIP_SIZE * 3.5)), 
                (int) (Constants.BASE_SHIP_MOVEMENT), 10, this);
        add(boss.image.picture);
    }

    /**
     * creates an upgrade where the ship is
     * @param ship the ship the upgrade is coming from
     */
    private void spawnUpgrade(Ship ship) {
        int upgradeType = Constants.random(1, Constants.NUMBER_OF_UPGRADES);
        Upgrade upgrade = new Upgrade(new Image(ship.coordinates.x, 
                ship.coordinates.y, Constants.BASE_UPGRADE_SIZE, 
                Constants.BASE_UPGRADE_SIZE), this, upgradeType);
        add(upgrade.image.picture);
    }

    /**
     * changes the upgrade images to reflect what they are set to
     */
    public void updateUpgradeImages() {
        for (int i = 0; i < upgradeSlots.length; i++) {
            if(player.upgrades[i] <= 0) upgradeSlots[i].setDebug("Slot " + (i + 1)
                    + ": Empty", Color.BLUE);
            else upgradeSlots[i].setDebug(Upgrade.NAMES[player.upgrades[i] - 1], 
                    Upgrade.SHIP_COLOURS[player.upgrades[i] - 1]);
        }
    }
    
    /**
     * changes the time display of how long the upgrade will last for 
     * @param upgrade the upgrade time display being changed
     * @param timeLeft the time left before the timer reaches the end
     */
    public void changeUpgradeCount(int upgrade, int timeLeft) {
        if(upgrade > upgradeTimers.length || timeLeft < 0)  {
            System.out.println("error in upgrade time Left code with " + timeLeft
                    + " Time Left on upgrade " + upgrade);
            return;
        }
        if(timeLeft == 0) upgradeTimers[upgrade - 1].hide();
        else upgradeTimers[upgrade - 1].show();
        upgradeTimers[upgrade - 1].setDebug(Upgrade.NAMES[upgrade - 1] + " Time: " +
                timeLeft, Color.BLACK);
        upgradeTimers[upgrade - 1].picture.setFont(Constants.BASE_FONT);
    }
    
}
