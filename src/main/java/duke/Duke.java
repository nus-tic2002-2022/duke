package duke;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.lang.StringIndexOutOfBoundsException;

public class Duke {

    public static String fileSeparator = File.separator; // to change accordingly to the OS
    public final static String fileName = "data" + fileSeparator + "duke.txt";
    private final static int LEN_FILENAME = 12; //starting index for clean format (format in the file)

    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke(String fileName) {
        ui = new Ui();
        assert fileName.length() > LEN_FILENAME; //length will be more than 12 if there is a fileSeparator
        storage = new Storage(fileName);
        try {
            storage.readFile();
            TaskList.printNoOfItemsInList();
        } catch (IOException e) {
            Ui.printFileErrorMsg(fileName);
        }
    }

    public void run() {

        int dukeStatus = 0;

        Ui.printHello();

        do {

            Ui.printPrompt();

            assert dukeStatus == 0 : "dukeStatus should be FALSE (Duke will not be terminated yet).";

            try {

                String line = Ui.getInput();

                int index;
                if (line.equalsIgnoreCase("bye")) {
                    Ui.printBye();
                    dukeStatus = 1; // can escape from loop
                } else if (line.equalsIgnoreCase("help")) {
                    Ui.printHelp();
                } else if (line.equalsIgnoreCase("hello")) {
                    Ui.printHello();
                } else if (line.equalsIgnoreCase("list")) { //Print list
                    TaskList.printList();
                    TaskList.printNoOfItemsInList();
                } else if (line.startsWith("todo ")) {
                    if (line.substring(4, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    TaskList.addTodo(line.substring(5, line.length()));
                    Ui.printAddTaskMsg();
                    TaskList.printOneTask(TaskList.getListLength() - 1);
                    TaskList.printNoOfItemsInList();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("deadline ")) {
                    if (line.substring(9, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    TaskList.addDeadline(line.substring(9, line.length()));
                    Ui.printAddTaskMsg();
                    TaskList.printOneTask(TaskList.getListLength() - 1);
                    TaskList.printNoOfItemsInList();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("event ")) {
                    if (line.substring(6, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    TaskList.addEvent(line.substring(6, line.length()));
                    Ui.printAddTaskMsg();
                    TaskList.printOneTask(TaskList.getListLength() - 1);
                    TaskList.printNoOfItemsInList();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("mark ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        Ui.printOutOfRangeMsg();
                        continue;
                    }
                    if (TaskList.checkDone(index)) {
                        Ui.printMarkDoneBeforeMsg();
                        TaskList.printOneTask(index);
                    } else {
                        TaskList.markDone(index);
                        Ui.printMarkDoneMsg();
                        TaskList.printOneTask(index);
                    }
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("unmark ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        Ui.printOutOfRangeMsg();
                        continue;
                    }
                    TaskList.markNotDone(index);
                    Ui.printMarkNotDoneMsg();
                    TaskList.printOneTask(index);
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("delete ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("delete ") + 7, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        Ui.printOutOfRangeMsg();
                        continue;
                    }
                    Ui.printDeleteMsg();
                    TaskList.printOneTask(index);
                    TaskList.deleteTask(index);
                    TaskList.printNoOfItemsInList();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.equalsIgnoreCase("sort")) {
                    Ui.printSortMsg();
                    TaskList.sortList();
                    TaskList.printList();
                    TaskList.printNoOfItemsInList();
                    storage.writeFile(list); // save file
                } else if (line.startsWith("find ")) {
                    if (line.substring(4, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    Ui.printFindMsg();
                    TaskList.findTask(line.substring(5, line.length()));
                    continue;
                } else {
                    Ui.printUnknownCommandMsg();
                    Ui.printHelp();
                }
            } catch (IOException e) {
                Ui.printFileErrorMsg(fileName);
            } catch (DateTimeParseException e) {
                Ui.printDateFormatErrorMsg();
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printShortDescErrorMsg();
            } catch (NumberFormatException e) {
                Ui.printOutOfRangeMsg();
            }

        } while (dukeStatus == 0);

    }

    public static void main(String[] args) {
        new Duke(fileName).run();
    }

}
