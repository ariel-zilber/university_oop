import java.awt.Image;

import oop.ex2.*;

import javax.swing.*;

import static java.lang.Math.PI;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *
 * @author oop
 */
public abstract class SpaceShip {

    /**
     * Represents the physical state of the space ship.
     */
    private SpaceShipPhysics physicalState;

    /**
     * Represents the health state of the Space ship.
     */
    private int health;

    /**
     * Represents the current energy level the spaceShip possess.
     */
    private int currentEnergyLevel;

    /**
     * Represents maximal energy this ship can possess
     */
    private int maxEnergyLevel;

    /**
     * Represents the state of the ship's shield
     */
    private boolean shieldState;

    /**
     * The first name of the patron.
     */
    private int weaponCoolDown;

    /**
     * The default constructor.
     */

    public SpaceShip() {
        this.physicalState = new SpaceShipPhysics();
        this.maxEnergyLevel = 210;
        this.currentEnergyLevel = 190;
        this.health = 22;
        this.shieldState = false;
        this.weaponCoolDown = 0;
    }

    /**
     * Gets the current state of the shield.
     *
     * @return the current state of the shield
     **/
    public boolean getShieldState() {
        return this.shieldState;
    }

    /**
     * used for responding to the spaceship state.
     * Whenever the current energy level is below 3 the shield is turned off.
     */
    public void updateShieldState() {
        if (getShieldState()) {
            if (this.currentEnergyLevel <= 3) {
                shieldOff();
            } else {
                decreaseEnergyLevel(3);
            }
        }
    }

    /**
     * Sets the current energy level of the ship
     *
     * @param energyLevel the current energy level of the ship
     */
    public void setCurrentEnergyLevel(int energyLevel) {
        this.currentEnergyLevel = energyLevel;
    }


    /**
     * Updates the weapon coolDown time
     **/
    void updateWeaponCoolDown() {
        if (this.weaponCoolDown > 0) {
            this.weaponCoolDown -= 1;
        }
    }

    /**
     * Increases the current energy level of the ship by a given amount
     */
    private void increaseEnergyLevel(int amount) {
        if (this.currentEnergyLevel + amount <= maxEnergyLevel) {

            setCurrentEnergyLevel(this.currentEnergyLevel + amount);
        }
    }

    /**
     * Increases the current energy level of the ship by a given amount
     * <p>
     **/
    private void decreaseEnergyLevel(int amount) {
        if ((this.currentEnergyLevel - amount < 0) && (this.currentEnergyLevel > 0)) {
            setCurrentEnergyLevel(0);
        } else {
            setCurrentEnergyLevel(this.currentEnergyLevel - amount);

        }
    }


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        updateShieldState();
        updateWeaponCoolDown();
        increaseEnergyLevel(1);
    }


    /**
     * This method is called every time a collision with this ship occurs.
     * Whenever the Ships shields are up it will "bash" another ship.
     */
    public void collidedWithAnotherShip() {
        if (shieldState) {
            this.maxEnergyLevel += 18;
            this.currentEnergyLevel += 18;
        } else {
            this.health -= 1;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        this.physicalState = new SpaceShipPhysics();
        this.maxEnergyLevel = 210;
        this.currentEnergyLevel = 190;
        this.health = 22;
        this.shieldState = false;
        this.weaponCoolDown = 0;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.physicalState;
    }


    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldState) {

            this.maxEnergyLevel -= 10;

            //checks whenever the current energy level exceeds the maximum
            if (this.maxEnergyLevel <= this.currentEnergyLevel) {
                this.currentEnergyLevel = this.maxEnergyLevel;
            }

            this.health -= 1;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public abstract Image getImage();

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if ((this.currentEnergyLevel > 19) && (this.weaponCoolDown == 0)) {
            this.weaponCoolDown = 7;
            game.addShot(physicalState);
            this.currentEnergyLevel -= 19;
        }
    }

    /**
     * used for turning away from the closest SpaceShip using it's angle.
     *
     * @param angle the angle of the closes ship relative to the current one
     */
    protected void turnAway(double angle) {
        if ((angle < PI) && (angle > 0)) {
            this.getPhysics().move(true, -1);
        }

        if ((angle > -PI) && (angle < 0)) {
            this.getPhysics().move(true, 1);
        } else {
            this.getPhysics().move(true, 0);
        }
    }

    /**
     * used for turning towards the closest SpaceShip using it's angle.
     *
     * @param angle the angle of the closes ship relative to the current one
     */
    protected void turnTowards(double angle) {
        if ((PI > angle && angle > 0)) {
            this.getPhysics().move(true, 1);

        } else if (angle > -PI && (angle < 0)) {
            this.getPhysics().move(true, -1);

        } else {
            this.getPhysics().move(true, 0);
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.currentEnergyLevel > 3) {
            this.shieldState = true;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOff() {
        this.shieldState = false;
    }

    /**
     * Sets the Physical state of the ship
     *
     * @param physics sets the current physical state of the device
     **/
    public void setPhysicalState(SpaceShipPhysics physics) {
        this.physicalState = physics;
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.currentEnergyLevel > 140) {
            this.currentEnergyLevel -= 140;
            setPhysicalState(new SpaceShipPhysics());
        }
    }

    /**
     * Creates an images used the Spaceship
     *
     * @param path the type of spaceship
     */
    protected Image createImageIcon(String path) {
        java.net.URL imgURL = JPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }
}
