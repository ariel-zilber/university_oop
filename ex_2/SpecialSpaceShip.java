import oop.ex2.SpaceShipPhysics;

import java.awt.*;


/**
 * This class represents the Special ship Type.
 * This ship is Supposed to annoy the player in a manner similar to
 * the Boo enemy type from Nintendo's Super Mario Bros video game.
 *
 * @author Ariel Zilbershteyin
 */
public class SpecialSpaceShip extends AggressiveSpaceShip{

    /**
     * The first name of the patron.
     */
    private int specialBehaviorCoolDown;

    /**
     * The time time the protect mode will go on
     */
    private static int PROTECT_BEHAVIOR_START = 100;

    /**
     * The time the special behaviour time will go on
     */
    private static int SPECIAL_BEHAVIOUR_START = 200;

    /**
     * Creates a new Special  SpaceShip
     **/
    public SpecialSpaceShip() {
        this.specialBehaviorCoolDown = SPECIAL_BEHAVIOUR_START;
    }

    @Override
    public void collidedWithAnotherShip() {
        if (specialBehaviorCoolDown > PROTECT_BEHAVIOR_START) {
            super.collidedWithAnotherShip();
        }
    }

    @Override
    public void gotHit() {
        if (specialBehaviorCoolDown > PROTECT_BEHAVIOR_START) {
            super.gotHit();
        }
    }

    @Override
    public void doAction(SpaceWars game) {
        if (specialBehaviorCoolDown == SPECIAL_BEHAVIOUR_START) {
            teleport();
        }

        if (specialBehaviorCoolDown == 0) {
            this.specialBehaviorCoolDown = SPECIAL_BEHAVIOUR_START;
            return;
        }

        if (specialBehaviorCoolDown > PROTECT_BEHAVIOR_START) {

            // purses and shoots at the closest ship
            super.doAction(game);

        }

        // decrease the behavior stop cool down
        this.specialBehaviorCoolDown -= 1;
    }

    @Override
    public void teleport() {
        this.setPhysicalState(new SpaceShipPhysics());
    }

    @Override
    public Image getImage() {
        if (specialBehaviorCoolDown > PROTECT_BEHAVIOR_START) {
            return createImageIcon("/spaceship_special.gif");

        } else {
            return createImageIcon("/spaceship_special_stone.gif");
        }
    }
}
