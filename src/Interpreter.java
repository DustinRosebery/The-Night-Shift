
import java.util.ArrayList;

/**
 * Interprets inputs to perform necessary actions
 * @author Connor Nelson
 */

public class Interpreter {

    private ArrayList<Command> commands;

    /**
     * @param cmds List of commands to be offered
     */
    public Interpreter(Command... cmds) {
        this.commands = new ArrayList<Command>();
        offer(cmds);
    }

    /**
     * Iterates through all commands and executes those in which the condition is met
     * @param input Input for the command to match against
     */
    public void interpret(Object input) {
        for (Command cmd : commands)
            cmd.execute(input);
    }

    /**
     * @param cmds Added to the interpreters list of commands to be checked
     */
    public void offer(Command... cmds) {
        for (Command cmd : cmds)
            commands.add(cmd);
    }

}
