package zhixuan.duke.parser;

import zhixuan.duke.commands.*;
import zhixuan.duke.ui.DukeUI;
import java.util.Scanner;

/**
 * Parser for user input
 *
 **/
public class Parser {

    private Scanner in ;
    DukeUI ui;

    private Parser () {
        ui = new DukeUI();
    }

    public Parser(Scanner in) {
        this();
        this.in = in;
    }

    /**
     * Processes user input
     * Calls ExitCommand if 'bye' is in user input
     * Else, calls determineAction for input
     *
     * @return Command class based on input, InvalidCommand if no matches
     **/
    public Command processInput() {
        String input = in.nextLine();
        if (ExitCommand.BYE_COMMAND.equals(input)) {
            return new ExitCommand();
        } else {
            try {
                return determineAction(input);
            } catch (UnsupportedOperationException e) {
                return new InvalidCommand("empty");
            } catch (IndexOutOfBoundsException e) {
                return new InvalidCommand("invalid_index");
            }
        }
    }

    /**
     * Calls command based on input
     *
     * @param command string user input
     * @return called Command class
     **/
    private Command determineAction(String command) throws UnsupportedOperationException, IndexOutOfBoundsException {

        if (ListCommand.LIST_COMMAND.equals(command)) {
            return new ListCommand();
        }

        if (LoadCommand.LOAD_COMMAND.equals(command)) {
            return new LoadCommand();
        }

        String[] commandList = command.split(" ");
        if (commandList.length < 2) {
            throw new UnsupportedOperationException();
        }

        switch (commandList[0]) {
            case MarkCommand.MARK_COMMAND:
            case MarkCommand.UNMARK_COMMAND:
                if (commandList.length > 2) {
                    throw new UnsupportedOperationException();
                }
                try {
                    int index = Integer.parseInt(commandList[1]);
                    return new MarkCommand(commandList[0], index);
                } catch (NumberFormatException e) {
                    return new InvalidCommand("empty");
                }
            case AddCommand.TODO_COMMAND:
            case AddCommand.EVENT_COMMAND:
            case AddCommand.DEADLINE_COMMAND:
                return new AddCommand(commandList[0], command.replaceFirst(commandList[0] + " ", ""));
            case DeleteCommand.DELETE_COMMAND:
                if (commandList.length > 2) {
                    return new InvalidCommand("empty");
                }
                try {
                    int index = Integer.parseInt(commandList[1]);
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    return new InvalidCommand("empty");
                }
            case FindCommand.FIND_COMMAND:
                if (commandList.length > 2) {
                    return new InvalidCommand("empty");
                }
                return new FindCommand(commandList[1]);
            case SnoozeCommand.SNOOZE_COMMAND:
                if (commandList.length > 4) {
                    return new InvalidCommand("empty");
                }
                try {
                    int index = Integer.parseInt(commandList[1]);
                    int amount = Integer.parseInt(commandList[3]);
                    return new SnoozeCommand(index, commandList[2], amount);
                } catch (NumberFormatException e) {
                    return new InvalidCommand("empty");
                }
            default:
                return new InvalidCommand("empty");
        }
    }

}
