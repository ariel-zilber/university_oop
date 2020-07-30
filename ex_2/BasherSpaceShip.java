import java.awt.*;

/**
 * This class represents the Basher ship Type.
 * This ship tries to avoid all other ships.
 *
 * @author Ariel Zilbershteyin
 */
public class BasherSpaceShip extends SpaceShip {


    @Override
    public void doAction(SpaceWars game) {

        // gets the angle to the closes ship
        SpaceShip closetSpaceShip = game.getClosestShipTo(this);
        double distance = closetSpaceShip.getPhysics().distanceFrom(this.getPhysics());
        double angle = getPhysics().angleTo(closetSpaceShip.getPhysics());

        // moves towards the closest ship
        turnTowards(angle);

        // attempt to start activate the shield
        if (distance < 0.19)
            this.shieldOn();
        else
            this.shieldOff();

        super.doAction(game);
    }


    @Override
    public Image getImage() {

        if (getShieldState()) {
            return createImageIcon("/spaceship_basher_shield.gif");

        } else {
            return createImageIcon("/spaceship_basher.gif");
        }

    }
}
