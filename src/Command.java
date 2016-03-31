
/**
 * Execute action when condition is met based on given input
 * @author Connor Nelson
 */

interface Command {

    boolean condition(String input);
    void execute();

}
