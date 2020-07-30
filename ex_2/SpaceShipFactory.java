/**
 * This class represents the factory used by the SpaceWars main loop to build different SpaceShips types.
 * <p>
 *
 * @author Ariel Zilbershteyin
 */
public class SpaceShipFactory {

    /**
     * creates different space ship types by the provided parameters
     *
     * @param args space ships  types to create
     **/
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShips = new SpaceShip[args.length];

        // initialize the most suited  spaceship type base
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "h":
                    spaceShips[i] = new HumanSpaceShip();
                    break;
                case "d":
                    spaceShips[i] = new DrunkardSpaceShip();
                    break;
                case "r":
                    spaceShips[i] = new RunnerSpaceShip();
                    break;
                case "a":
                    spaceShips[i] = new AggressiveSpaceShip();
                    break;
                case "b":
                    spaceShips[i] = new BasherSpaceShip();
                    break;
                case "s":
                    spaceShips[i] = new SpecialSpaceShip();
                    break;

            }
        }

        return spaceShips;
    }

}
