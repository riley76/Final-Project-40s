package finalproject40s;


import gameTools.Constants;
import gameTools.Coordinates;
import gameTools.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class PlayerShip extends Ship {

    private int[] upgrades;
    private int invincibleCount;
    private Timer[] upgradeTimers;
    public boolean invincible;
    private final Coordinates startingCoordinates;

    /**
     * constructor for the player ship
     * @param image the image to display to the user to visualize this ship
     * @param amount the amount to move by
     * @param delay the delay before moving again
     * @param engine the engine that runs the general logic of the game
     */
    public PlayerShip(Image image, int amount, Engine engine) {
        super(image, amount, engine);
        firingDirection = Constants.NORTH_DIRECTION;
        damageOutput = Constants.BASE_SHIP_DAMAGE;
        health = Constants.BASE_PLAYER_HEALTH;
        speed = Constants.BASE_SHIP_MOVEMENT;
        shipNumber = Constants.PLAYER_SHIP_NUMBER;
        
        canFire = true;
        
        invincible = false;
        upgrades = new int[3];
        firingTimer = new Timer(1100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canFire = true;
                firingTimer.stop();
            }
        });
        upgradeTimers = new Timer[Constants.NUMBER_OF_UPGRADES - 2];
        upgradeTimers[Upgrade.FIRING - 1] = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradeTimers[0].stop();
                firingTimer.setInitialDelay(1100);
            }
        });
        upgradeTimers[Upgrade.SPEED - 1] = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradeTimers[1].stop();
                coordinates.amount = Constants.BASE_SHIP_MOVEMENT;
            }
        });
        invincibleCount = 17;
        upgradeTimers[Upgrade.STRENGTH - 1] = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invincibleCount--;
                Constants.output("Change progress bar for invincible code here", true);
                if (invincibleCount <= 0) {
                    invincible = false;
                    upgradeTimers[1].stop();
                    Constants.output("Change progress bar for invincible display code here", true);
               }
            }
        });
        startingCoordinates = coordinates;
        show();
    }


    /**
     * updates the list of upgrades the player has and adds the newest picked up upgrade
     * @param newUpgradeType the type of the newest upgrade that was picked up
     */
    public void pickUpUpgrade(int newUpgradeType) {
        if (upgrades[0] == 0) {
            upgrades[0] = newUpgradeType;
        } else if (upgrades[1] == 0) {
            upgrades[1] = newUpgradeType;
        } else if (upgrades[2] == 0) {
            upgrades[2] = newUpgradeType;
        } else {
            upgrades[0] = upgrades[1];
            upgrades[1] = upgrades[2];
            upgrades[2] = newUpgradeType;
        }
        updateUpgradeImages();
    }

    /**
     * uses the first upgrade if there is one
     */
    public void useUpgrade() {
        if(upgrades[0] == 0) return;
        if(upgrades[0] == Upgrade.FIRING) {
            firingTimer.setInitialDelay(50);
            upgradeTimers[Upgrade.FIRING - 1].start();
        } else if(upgrades[0] == Upgrade.STRENGTH) {
            invincible = true;
            Constants.output("Put change ship colour code here", true);
            invincibleCount = 17;
            upgradeTimers[Upgrade.STRENGTH - 1].start();
        } else if(upgrades[0] == Upgrade.MISSILE) {
            fireMissile();
        } else if(upgrades[0] == Upgrade.SPEED) {
            coordinates.amount = Constants.BASE_SHIP_MOVEMENT * 3;
            upgradeTimers[Upgrade.SPEED - 1].start();
        }
        upgrades[0] = upgrades[1];
        upgrades[1] = upgrades[2];
        upgrades[2] = 0;
        updateUpgradeImages();
    }


    /**
     * stops the ship from moving when they release the key they were holding
     * @param evt the key the user pressed
     */
    public void keyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP && coordinates.direction
                == Constants.NORTH_DIRECTION && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_W && coordinates.direction
                == Constants.NORTH_DIRECTION && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_S && coordinates.direction
                == Constants.SOUTH_DIRECTION && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN && coordinates.direction
                == Constants.SOUTH_DIRECTION && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_A && coordinates.direction
                == Constants.WEST_DIRECTION && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT && coordinates.direction
                == Constants.WEST_DIRECTION && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_D && coordinates.direction
                == Constants.EAST_DIRECTION && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT && coordinates.direction
                == Constants.EAST_DIRECTION && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.STOP_DIRECTION;
        }
    }
    
    /**
     * a user keyboard event
     *
     * @param evt the keyboard event registered
     */
    public void keyPressed(KeyEvent evt) {
        if (engine.manager.adminMode) {
            adminControls(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.NORTH_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_W && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.NORTH_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.SOUTH_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_S && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.SOUTH_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.WEST_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_A && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.WEST_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_D && !engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.EAST_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT && engine.manager.usingArrowKeys) {
            coordinates.direction = Constants.EAST_DIRECTION;
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                fire();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            useUpgrade();
        }
    }

    /**
     * reduces health, kills player if out of health and ends game if out of lives
     * @param damage the amount of damage the player has been dealt
     */
    @Override
    public void hit(int damage) {
        health-= damage;
        if(health <0) {
            hide();
            Constants.output("You Died!! \nYou have " + engine.lives + " Lives left", true);
            engine.lives--;
            if(engine.lives <= 0) engine.exit(Constants.LOST_GAME);
            coordinates.changeCoordinates(startingCoordinates);
            damageOutput = Constants.BASE_SHIP_DAMAGE;
            health = Constants.BASE_PLAYER_HEALTH;
            speed = Constants.BASE_SHIP_MOVEMENT;
            firingTimer.setInitialDelay(1100);
            redraw();
            show();
        }
    }
    
    @Override
    public void action() {
        move();
        checkWalls();
        checkShips();
    }
    
    /**
     * controls to make bug testing easier and quicker
     * @param evt key pressed
     */
    private void adminControls(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_1) {
            
        } else if (evt.getKeyCode() == KeyEvent.VK_2) {
            
        }else if (evt.getKeyCode() == KeyEvent.VK_3) {
            
        } else if (evt.getKeyCode() == KeyEvent.VK_4) {
            
        } else if (evt.getKeyCode() == KeyEvent.VK_5) {
            
        }
    }

    
    /**
     * increases the amount of lives the player has by one
     */
    public void getLife() {
            engine.lives++;
    }

    /**
     * updates the upgrade display so it is in line with the upgrades the user has
     */
    private void updateUpgradeImages() {
        Constants.output("Put update upgrade slot images code here", true);
    }

    private void fireMissile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
