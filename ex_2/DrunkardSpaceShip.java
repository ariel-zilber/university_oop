import java.awt.*;
import java.util.Random;

/**
 * This class represents the Drunkard ship type.
 * This ship is controlled by a drunk pilot .
 * Every 200 rounds it changes it behavior .
 * It either chases the player,runs away from the player or spins in circles
 *
 * @author Ariel Zilbershteyin
 */
public class DrunkardSpaceShip extends SpaceShip {

    /**
     * Cooldown for raffling a behavior
     */
    private int raffleCoolDown;

    /**
     * The current behavior  type
     */
    private int currentAction;

    /**
     * The default constructor this ship type uses.
     **/
    public DrunkardSpaceShip() {
        this.currentAction = 1;
        this.raffleCoolDown = 0;
    }

    /**
     * return a random action the ship will take from the predefined actions
     */
    private int getRandomAction() {
        Random rand = new Random();

        return rand.nextInt(3);

    }


    @Override
    public void doAction(SpaceWars game) {

        // gets the angle towards the closest ship
        SpaceShip closetSpaceShip = game.getClosestShipTo(this);
        double angle = getPhysics().angleTo(closetSpaceShip.getPhysics());

        // raffles a new behaviour when needed
        if (raffleCoolDown <= 0) {
            currentAction = getRandomAction();
            raffleCoolDown = 200;
        }

        // acts according to the currently raffled behaviour
        switch (currentAction) {
            case 1:
                turnTowards(angle);
                break;
            case 2:
                turnAway(angle);
                break;
            default:
                this.getPhysics().move(true, 1);
                break;
        }

        raffleCoolDown -= 1;

        super.doAction(game);
    }

    @Override
    public Image getImage() {

        //returns an image suited to the current raffled behavior
        switch (currentAction) {
            case 1:
                if (getShieldState())
                    return createImageIcon("/spaceship_drunk_angry_shield.gif");
                else return createImageIcon("/spaceship_drunk_angry.gif");
            case 2:
                return createImageIcon("/spaceship_drunk_scared.gif");
            default:
                return createImageIcon("/spaceship_drunk_overdose.gif");

        }


    }
}
