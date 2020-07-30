import java.awt.*;

/**
 * This class represents the Human controlled ship.
 * This ship is controlled by the user.
 *
 * @author Ariel Zilbershteyin
 */
public class HumanSpaceShip extends SpaceShip {

    @Override
    public Image getImage() {
        if (getShieldState()) {
            return createImageIcon("/spaceship_human_shield.gif");

        } else {
            return createImageIcon("/spaceship_human.gif");

        }
    }

    @Override
    public void doAction(SpaceWars game) {
        boolean accelerate = false;

        // checks for teleport action activation
        if (game.getGUI().isTeleportPressed()) {
            this.teleport();
        }

        // checks for movements type action activation
        if (game.getGUI().isUpPressed()) {
            accelerate = true;
        }

        if (game.getGUI().isLeftPressed()) {
            this.getPhysics().move(accelerate, 1);

        } else if (game.getGUI().isRightPressed()) {
            this.getPhysics().move(accelerate, -1);

        } else {
            this.getPhysics().move(accelerate, 0);
        }

        // checks for shield activation action type
        if (game.getGUI().isShieldsPressed()) {
            this.shieldOn();
        } else {
            this.shieldOff();
        }

        // checks for shot firing action activation
        if (game.getGUI().isShotPressed()) {
            this.fire(game);
        }

        super.doAction(game);
    }
}
