import java.awt.*;


/**
 * This ship tries to pursue other ships and fire at them.
 *
 * @author Ariel Zilbershteyin
 */
public class AggressiveSpaceShip extends SpaceShip {


    @Override
    public void doAction(SpaceWars game) {

        // gets the angle to the closest ship
        SpaceShip closetSpaceShip = game.getClosestShipTo(this);
        double angle = getPhysics().angleTo(closetSpaceShip.getPhysics());

        // moves in the direction of the closest ship
        turnTowards(angle);

        // fire whenever at range
        if (angle < 0.21)
            this.fire(game);

        super.doAction(game);
    }


    @Override
    public Image getImage() {

        if (getShieldState()) {
            return createImageIcon("/spaceship_aggressive.gif");

        } else {
            return createImageIcon("/spaceship_aggressive_shield.gif");

        }
    }
}
