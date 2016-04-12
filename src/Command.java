
/**
 * Execute command based on a given input
 * @author Connor Nelson
 */

public interface Command {

    /**
     * @param input Execute command based on given input
     * @return Whether or not the command handled the input
     */
    boolean execute(Object input);

}
