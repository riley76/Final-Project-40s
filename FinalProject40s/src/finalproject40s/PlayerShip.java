package finalproject40s;

import gameTools.Constants;
import gameTools.Coordinates;
import gameTools.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class PlayerShip extends Ship {

    public int[] upgrades;
    private Timer[] upgradeTimers;
    private int[] upgradeCount;
    public boolean invincible;
    private final Coordinates startingCoordinates;

    /**
     * constructor for the player ship
     *
     * @param image the image to display to the user to visualize this ship
     * @param engine the engine that runs the general logic of the game
     */
    public PlayerShip(Image image, Engine engine) {
        super(image, Constants.BASE_SHIP_MOVEMENT * 2, engine);
        firingDirection = Constants.NORTH_DIRECTION;
        damageOutput = Constants.BASE_SHIP_DAMAGE;
        health = Constants.BASE_PLAYER_HEALTH;
        speed = Constants.BASE_SHIP_MOVEMENT * 2;
        shipNumber = Constants.PLAYER_SHIP_NUMBER;
        canFire = true;
        this.image.picture.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/media/startingPlayerShip.png")));
        invincible = false;
        upgrades = new int[3];
        firingTimer = new Timer(Constants.PLAYER_FIRING_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canFire = true;
                firingTimer.stop();
            }
        });
        upgradeTimers = new Timer[Constants.NUMBER_OF_UPGRADES - 1];
        upgradeCount = new int[Constants.NUMBER_OF_UPGRADES - 1];
        upgradeCount[Upgrade.FIRING - 1] = Constants.UPGRADE_COUNT;
        upgradeTimers[Upgrade.FIRING - 1] = new Timer(Constants.ONE_SECOND,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradeCount[Upgrade.FIRING - 1]--;
                engine.changeUpgradeCount(Upgrade.FIRING, upgradeCount[Upgrade.FIRING - 1]);
                if (upgradeCount[Upgrade.FIRING - 1] <= 0) {
                    upgradeTimers[Upgrade.FIRING - 1].stop();
                    firingTimer.setInitialDelay(Constants.PLAYER_FIRING_DELAY);
                    upgradeCount[Upgrade.FIRING - 1] = Constants.UPGRADE_COUNT;
                }
            }
        });
        upgradeCount[Upgrade.SPEED - 1] = Constants.UPGRADE_COUNT;
        upgradeTimers[Upgrade.SPEED - 1] = new Timer(Constants.ONE_SECOND,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradeCount[Upgrade.SPEED - 1]--;
                engine.changeUpgradeCount(Upgrade.SPEED, upgradeCount[Upgrade.SPEED - 1]);
                if (upgradeCount[Upgrade.SPEED - 1] <= 0) {
                    upgradeTimers[Upgrade.SPEED - 1].stop();
                    coordinates.amount = Constants.BASE_SHIP_MOVEMENT * 2;
                    speed = coordinates.amount;
                    upgradeCount[Upgrade.SPEED - 1] = Constants.UPGRADE_COUNT;
                }
            }
        });
        upgradeCount[Upgrade.STRENGTH - 1] = Constants.UPGRADE_COUNT;
        upgradeTimers[Upgrade.STRENGTH - 1] = new Timer(Constants.ONE_SECOND,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradeCount[Upgrade.STRENGTH - 1]--;
                engine.changeUpgradeCount(Upgrade.STRENGTH,
                        upgradeCount[Upgrade.STRENGTH - 1]);
                if (upgradeCount[Upgrade.STRENGTH - 1] <= 0) {
                    invincible = false;
                    upgradeTimers[Upgrade.STRENGTH - 1].stop();
                    upgradeCount[Upgrade.STRENGTH - 1] = Constants.UPGRADE_COUNT;
                    image.picture.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/media/startingPlayerShip.png")));
                }
            }
        });
        upgradeCount[Upgrade.DAMAGE - 1] = Constants.UPGRADE_COUNT;
        upgradeTimers[Upgrade.DAMAGE - 1] = new Timer(Constants.ONE_SECOND, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradeCount[Upgrade.DAMAGE - 1]--;
                engine.changeUpgradeCount(Upgrade.DAMAGE, upgradeCount[Upgrade.DAMAGE - 1]);
                if (upgradeCount[Upgrade.DAMAGE - 1] <= 0) {
                    damageOutput = Constants.BASE_SHIP_DAMAGE;
                    upgradeTimers[Upgrade.DAMAGE - 1].stop();
                    upgradeCount[Upgrade.DAMAGE - 1] = Constants.UPGRADE_COUNT;
                }
            }
        });
        startingCoordinates = new Coordinates(image);
        startingCoordinates.changeCoordinates(coordinates);
        EnemyShip.player = this;
    }

    /**
     * updates the list of upgrades the player has and adds the newest picked up
     * upgrade
     *
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
        engine.updateUpgradeImages();
    }

    /**
     * uses the first upgrade if there is one
     */
    public void useUpgrade() {
        if (upgrades[0] == 0) {
            return;
        }
        if (upgrades[0] == Upgrade.FIRING) {
            firingTimer.setInitialDelay(75);
            upgradeTimers[Upgrade.FIRING - 1].start();
        } else if (upgrades[0] == Upgrade.STRENGTH) {
            invincible = true;
            image.picture.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/media/greenShip.png")));
            upgradeCount[Upgrade.STRENGTH - 1] = Constants.UPGRADE_COUNT;
            upgradeTimers[Upgrade.STRENGTH - 1].start();
        } else if (upgrades[0] == Upgrade.DAMAGE) {
            upgradeTimers[Upgrade.DAMAGE - 1].start();
            damageOutput = Constants.BASE_SHIP_DAMAGE * 2;
        } else if (upgrades[0] == Upgrade.SPEED) {
            coordinates.amount = Constants.BASE_SHIP_MOVEMENT * 3;
            speed = coordinates.amount;
            upgradeTimers[Upgrade.SPEED - 1].start();
        }
        engine.changeUpgradeCount(upgrades[0], Constants.UPGRADE_COUNT);
        upgrades[0] = upgrades[1];
        upgrades[1] = upgrades[2];
        upgrades[2] = 0;
        engine.updateUpgradeImages();
    }

    /**
     * stops the ship from moving when they release the key they were holding
     *
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
     * reduces health, kills player if out of health and ends game if out of
     * lives
     *
     * @param damage the amount of damage the player has been dealt
     */
    @Override
    public void hit(int damage) {
        if (!invincible) {
            health -= damage;
            engine.playerHealthBar.setValue(health);
            if (health <= 0) {
                hide();
                engine.changeLives(-1);
                Constants.output("You Died!! \nYou have " + engine.lives + " Lives left", true);
                if (engine.lives <= 0) {
                    engine.exit(Constants.LOST_GAME);
                }
                coordinates.changeCoordinates(startingCoordinates);
                damageOutput = Constants.BASE_SHIP_DAMAGE;
                health = Constants.BASE_PLAYER_HEALTH;
                speed = Constants.BASE_SHIP_MOVEMENT * 2;
                firingTimer.setInitialDelay(1100);
                redraw();
                show();
            }
        }

        engine.playerHealthBar.setValue(health);
    }

    @Override
    public void action() {
        move();
        redraw();
        checkWalls();
        checkShips();
    }

    /**
     * controls to make bug testing easier and quicker
     *
     * @param evt key pressed
     */
    private void adminControls(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_1) {
            hit(1);
        } else if (evt.getKeyCode() == KeyEvent.VK_2) {
            engine.spawnShip();
            shipList.get(shipList.getLength() - 1).redraw();
        } else if (evt.getKeyCode() == KeyEvent.VK_3) {
            Constants.output("X = " + coordinates.x + ", y = " + coordinates.y + ","
                    + " direction = " + coordinates.direction + ", health = "
                    + health + ", damage = " + damageOutput + ", invincible = "
                    + invincible, false);
        } else if (evt.getKeyCode() == KeyEvent.VK_4) {
            pickUpUpgrade(Constants.random(1, Constants.NUMBER_OF_UPGRADES - 1));
        } else if (evt.getKeyCode() == KeyEvent.VK_5) {
            engine.addPoint(this);
        } else if (evt.getKeyCode() == KeyEvent.VK_6) {
            engine.spawnBoss();
        }
    }

}
