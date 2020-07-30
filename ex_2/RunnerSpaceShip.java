import java.awt.*;

/**
 * This class represents the Basher ship Type.
 * This ship tries to avoid all other ships.
 *
 * @author Ariel Zilbershteyin
 */
public class RunnerSpaceShip extends SpaceShip {

    @Override
    public void doAction(SpaceWars game) {

        // gets the angle and the  distance to the closest ship
        SpaceShip closetSpaceShip = game.getClosestShipTo(this);
        double distance = closetSpaceShip.getPhysics().distanceFrom(this.getPhysics());
        double angle = getPhysics().angleTo(closetSpaceShip.getPhysics());

        //attempts to teleport whenever threatened.otherwise just runs away from the closes ship
        if (distance < 0.25 && angle < 0.23)
            this.teleport();

        //moves away from the closest spaceship
        turnAway(angle);

        super.doAction(game);
    }

    @Override
    public Image getImage() {
        return createImageIcon("/spaceship_runner.gif");
    }
}
