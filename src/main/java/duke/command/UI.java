package duke.command;
import duke.task.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to represent the user-interface of the program. It displays an introductory message and provides a list of possible commands that the user can input.
 * <br> Once the input is registered, it refers the input to the Parser Class to parse and understand the command that the user wants.
 */

public class UI {
    public static void command() throws IOException, DukeException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Parser.parse(line);
        Storage.writeToFile();
        System.out.println("\n");
    }

    /**
     * This method starts the interaction with the user and asks to provide for a user-input so that it can scan the input and provide it to another class for execution purposes.
     * @throws DukeException Error that is thrown if user inputs an incompatible command.
     */

    public static void main() throws DukeException {
        System.out.println("Hello! I'm Duke\n" + "Let me load the existing data for you (if any)\n");
        if (TaskList.UpdatedList().size() == 0) {
            System.out.println("No existing data is found");
        }
        else {
            Parser.parse("list"); //Load initial list onto screen
        }
        System.out.println("\nWhat would you like to do ?");
        System.out.println("List of valid entries include the following:\n\n" +
                "Bye\n" +
                "List\n" +
                "Done         'X'\n" +
                "Delete       'X'\n" +
                "Todo         'Y'\n" +
                "Event        'Y' /at 'Z'\n" +
                "Deadline     'Y' /by 'Z'\n" +
                "Occurrence       /on 'Z'" +  "    //Specify which events / deadlines occur on a particular date\n\n" +
                "Where 'X' refers to the task number, 'Y' refers to the task description and 'Z' refers to the date.\n");
        while (true) {
            try {
                command();
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
