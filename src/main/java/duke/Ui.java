/**
 * Ui handles user's commands and prints messages to user in Duke.
 */
package duke;

import java.util.Scanner;

public class Ui {

    private static Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public static String getInput() {
        return input.nextLine();
    }

    //Print general messages

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printPrompt() {
        System.out.print("What can I do for you? (type 'help' if you need help to remember the commands) ");
    }

    public static void printHelp() {
        String help = "\nHere are the commands you can type: \n"
                + "1. type 'list' to see your current list.\n"
                + "2. type 'todo <add task here>' (e.g. todo read a book).\n"
                + "3. type 'deadline <add task here> /by <add deadline  in yyyy-MM-dd'T'HH:mm>' (e.g. deadline submit duke project /by 2022-04-11T23:59).\n"
                + "4. type 'event <add task here> /at <add event timing in yyyy-MM-dd'T'HH:mm>' (e.g. event attend TIC2002 class /at 2022-03-11T19:00).\n"
                + "5. type 'mark <add task number here>' (e.g. mark 1).\n"
                + "6. type 'unmark <add task number here>' (e.g. unmark 2).\n"
                + "7. type 'find <add keywords here>' (e.g. find tic).\n"
                + "8. type 'delete <add task number here>' (e.g. delete 2).\n"
                + "9. type 'sort' to sort the tasks by dates in ascending order.\n"
                + "10. type 'bye' to escape.\n"
                + "Find more in docs/README.md!\n";

        System.out.println(help);
    }

    //Print helpful messages after command

    public static void printAddTaskMsg() {
        System.out.println("Nice! I've added this task: ");
    }

    public static void printMarkDoneMsg() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public static void printMarkNotDoneMsg() {
        System.out.println("OK, I've marked this task as not done yet: ");
    }

    public static void printDeleteMsg() {
        System.out.println("Noted. I've removed this task: ");
    }

    public static void printSortMsg() {
        System.out.println("Noted. I've sorted the list by date: ");
    }

    public static void printFindMsg() {
        System.out.println("Here are the matching task(s) in your list: ");
    }

    public static void printNothingInListMsg() {
        System.out.println("There is nothing on the list! :)");
    }

    //Print error messages

    public static void printOutOfRangeMsg() {
        System.out.println("Sorry, you have chosen the task number you choose is out of range. Please type 'list' to check the number of tasks you have.");
    }

    public static void printBlankDescMsg() {
        System.out.println("Sorry, description cannot be blank. Type 'help' to check the format for Todo/Deadlines/Events.");
    }

    public static void printMarkDoneBeforeMsg() {
        System.out.println("This task has been marked done before!");
    }

    public static void printUnknownCommandMsg() {
        System.out.print("Sorry, I don't understand. ");
    }

    public static void printFileErrorMsg(String fileName) {
        System.out.println("WARNING: The list file " + fileName + " cannot be created and saved. Please ensure that the data folder exists in the same path as the JAR file. Type 'bye' and add the data folder before starting Duke again.");
    }

    public static void printDateFormatErrorMsg() {
        System.out.println("The date format is not correct. Please type 'help' to check the date format for Events/Deadlines.");
    }

    public static void printShortDescErrorMsg() {
        System.out.println("The description is too short. Please type 'help' to check the date format for Events/Deadlines.");
    }

}
