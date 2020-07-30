import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * measures the run-times
 *
 * @author Ariel Zilbershteyin
 */

public class SimpleSetPerformanceAnalyzer {

    // init the relevant data sets
    private static String[] data1 = Ex4Utils.file2array("src/data1.txt");
    private static String[] data2 = Ex4Utils.file2array("src/data2.txt");
    private static String HI_WORD = "hi";
    private static String NEGATIVE_VALUE = "-13170890158";
    private static String INT_VALUE = "23";
    private static int NUM_OF_CYCLES = 70000;
    private static int NUM_OF_CYCLES_LINKED_LIST = 7000;
    private static int MIILI_IN_NANO = 1000000;
    private static int NUM_OF_SETS = 5;
    private static String FIRST_TEST = "addData1";
    private static String SECOND_TEST = "addData2";
    private static String THIRD_TEST = "containsHi1";
    private static String FOURTH_TEST = "containsNegativeValue";
    private static String FIFTH_TEST = "contains23";
    private static String SIXTH_TEST = "containsHi2";
    private static SimpleSet[] simpleSets;
    private static long[] measureResults = new long[5];

    public static void main(String[] args) {

        /* test 1 - add data 1 */
        addPerformanceTest(data1, FIRST_TEST);

        /* test 2 - add data 2 */
        addPerformanceTest(data2, SECOND_TEST);

        /* test 3 - contains "hi" with data 1 */
        containsPerformanceTest(data1, THIRD_TEST, HI_WORD);

        /* test 4 - contains "-13170890158" with data 2 */
        containsPerformanceTest(data1, FOURTH_TEST, NEGATIVE_VALUE);

        /* test 5 - contains 23 with data 2 */
        containsPerformanceTest(data2, FIFTH_TEST, INT_VALUE);

        /* test 6 - contains "hi" with data 2 */
        containsPerformanceTest(data2, SIXTH_TEST, HI_WORD);
    }

    private static void printResults(String testName) {
        System.out.print("****************************************************\n");
        System.out.println("\tThe results for " + testName + " duration are:");
        System.out.println("\t(1)Open Hash Set:" + measureResults[0]);
        System.out.println("\t(2)Closed Hash Set:" + measureResults[1]);
        System.out.println("\t(3)tree set is:" + measureResults[2]);
        System.out.println("\t(4)linked list:" + measureResults[3]);
        System.out.println("\t(5)hash Set:" + measureResults[4]);
        System.out.print("****************************************************\n");
    }

    /**
     * The main method used for performance test for the add method
     *
     * @param dataSet  the dataset from which words are added
     * @param testName the name of test performed
     */
    private static void addPerformanceTest(String[] dataSet, String testName) {
        System.out.println("Performing " + testName + " method performance test:");

        /* init the data structures */
        initSets();

        /* perform the measurements*/
        addMeasureSets(dataSet);

        /* print  the results */
        printResults(testName);

    }

    /**
     * The main method used for performance test for the contains method
     *
     * @param dataSet  the dataset from which words are used for the datasets
     * @param testName the name of test performed contains on
     * @param word the word to perform con
     */
    public static void containsPerformanceTest(String[] dataSet, String testName, String word) {
        System.out.println("Performing " + testName + " contains method performance test:");

        /* init the data structures */
        initSets();
        addAllData(dataSet);

        /* warms up all the needed data structures*/
        containsWarmUp(word);

        // messures the contain method
        containsMeasureSets(word);

        /* Write the results to the RESULT file */
        printResults(testName);

    }


    /**
     * Inits the data  sets set that would be used in the test
     */
    private static void initSets() {
        System.out.println("\tReseting the data sets");

        simpleSets = new SimpleSet[5];
        simpleSets[0] = new OpenHashSet();
        simpleSets[1] = new ClosedHashSet();
        simpleSets[2] = new CollectionFacadeSet(new TreeSet<String>());
        simpleSets[3] = new CollectionFacadeSet(new LinkedList<String>());
        simpleSets[4] = new CollectionFacadeSet(new HashSet<String>());
        System.out.println("\tDone.");

    }


    /**
     * Warms up the provided data set per as instructed in  Ex4
     *
     * @param word the word to use for warm up
     */
    private static void containsWarmUp(String word) {
        System.out.println("\tWarming up the datasets:");

        System.out.println("\t\t-Warming up the openSet!");
        for (int i = 0; i < NUM_OF_CYCLES; i++) {
            simpleSets[0].contains(word);
        }

        System.out.println("\t\t-Warming up the closedSet!");
        for (int i = 0; i < NUM_OF_CYCLES; i++) {
            simpleSets[1].contains(word);
        }

        System.out.println("\t\t-Warming up the treeSet!");
        for (int i = 0; i < NUM_OF_CYCLES; i++) {
            simpleSets[2].contains(word);
        }
        System.out.println("\t\t-Warming up the HashList!");
        for (int i = 0; i < NUM_OF_CYCLES; i++) {
            simpleSets[4].contains(word);
        }
        System.out.println("\tWarming up done!");

    }

    /**
     * Adds all the values in a give data to all the sets
     *
     * @param data the data to add
     */
    private static void addAllData(String[] data) {
        System.out.println("\tAdding all data to the sets:");

        System.out.println("\t\t-Adding all data to the open set");
        for (String word : data) {
            simpleSets[0].add(word);
        }
        System.out.println("\t\t-Adding all data to the closed set");
        for (String word : data) {
            simpleSets[1].add(word);
        }

        System.out.println("\t\t-Adding all data to the Tree set");
        for (String word : data) {
            simpleSets[2].add(word);
        }

        System.out.println("\t\t-Adding all data to the LinkedList");
        for (String word : data) {
            simpleSets[3].add(word);
        }

        System.out.println("\t\t-Adding all data to the HashList");
        for (String word : data) {
            simpleSets[4].add(word);
        }

        System.out.println("\tDone.");
    }

    /**
     * Adds the words in a given data source to a given  set
     *
     * @param data the data source to add
     * @param set  the set to measure
     * @return the duration the action took
     **/
    private static long addMeasureSet(String[] data, SimpleSet set) {
        System.out.println("\t\t-Performing add method");

        long timeBefore = System.nanoTime();

        // add the words one by one
        for (String word : data) {
            set.add(word);
        }

        // calculate the duration
        long timeAfter = System.nanoTime();
        System.out.println("\t\t-Done.");

        // return the calculation results
        return (timeAfter - timeBefore) / MIILI_IN_NANO;
    }

    /**
     * Adds the words in a given data source to all the sets
     *
     * @param data the data source to add
     **/
    private static void addMeasureSets(String[] data) {
        System.out.println("\tPerforming add method :");

        for (int i = 0; i < simpleSets.length; i++) {
            measureResults[i] = addMeasureSet(data, simpleSets[i]);
        }
    }

    /**
     * messures the time it takes for a simple set to perform contains
     *
     * @param set   the sets to measures
     * @param value the value to perform contains on
     */
    private static long containsMeasureSet(SimpleSet set, String value, long numOfIteration) {
        System.out.println("\t\t-Performing contains " + value + " " + numOfIteration + " of times");

        long timeBefore = System.nanoTime();

        // check whenever the value is contained
        for (int i = 0; i < numOfIteration; i++) {
            set.contains(value);
        }

        // calculate the duration
        long timeAfter = System.nanoTime();
        System.out.println("\tDone.");
        return ((timeAfter - timeBefore) / numOfIteration);

    }

    /**
     * Messures the time it takes for a simple set to perform contains
     *
     * @param value the value to perform contains on
     */
    private static void containsMeasureSets(String value) {

        System.out.println("\tPerforming contains " + value + " :");

        measureResults[0] = containsMeasureSet(simpleSets[0], value, NUM_OF_CYCLES);
        measureResults[1] = containsMeasureSet(simpleSets[1], value, NUM_OF_CYCLES);
        measureResults[2] = containsMeasureSet(simpleSets[2], value, NUM_OF_CYCLES);
        measureResults[3] = containsMeasureSet(simpleSets[3], value, NUM_OF_CYCLES_LINKED_LIST);
        measureResults[4] = containsMeasureSet(simpleSets[4], value, NUM_OF_CYCLES);
    }

}