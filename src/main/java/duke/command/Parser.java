package duke.command;
import duke.task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {
    protected static String by;
    protected static String input;
    protected static LocalDateTime localDateTime;
    protected static LocalDate localDate;
    protected static List<DateTimeFormatter> knownPatterns = new ArrayList<>();

    public Parser(String input) {
        Parser.input = input;
    }

    public static LocalDateTime parse(String input) throws DukeException {
        if (input.trim().equalsIgnoreCase("bye"))         { TaskList.Bye();}
        else if (input.trim().equalsIgnoreCase("list"))   { TaskList.List();}
        else if (input.toLowerCase().contains("done"))               { TaskList.Done(input);}
        else if (input.toLowerCase().contains("delete"))             { TaskList.Delete(input);}
        else if (input.toLowerCase().contains("todo"))               { TaskList.Todo(input);}

        else if ((input.toLowerCase().contains("deadline")) || (input.toLowerCase().contains("event")) ||
                (input.toLowerCase().contains("occurrence")) || (input.contains("[D]")) || (input.contains("[E]"))) {

            int n = 0;
            if (input.contains("/")) {n = input.indexOf('/'); }
            else if (input.contains("(")) {n = input.indexOf('('); }

            by = input.substring(n + 4).trim();

            knownPatterns = DateTimeList.knownPatterns;

            for (DateTimeFormatter pattern : knownPatterns) {
                try {
                    localDate = LocalDate.parse(by,pattern);
                    localDateTime = LocalDateTime.parse(by, pattern);
                } catch (DateTimeParseException ignored) {}
            }

            if (localDateTime == null) {
                throw new DukeException("No known Date format found: " + by);
            }

            if (input.toLowerCase().contains("deadline")){
                TaskList.Deadline(input, localDateTime);
            }
            else if (input.toLowerCase().contains("event")) {
                TaskList.Event(input, localDateTime);
            }
            else if ((input.toLowerCase().contains("occurrence"))){
                TaskList.Occurrence(localDate);
            }
            else {
                return localDateTime;
            }
        }
        else throw new DukeException("OOPS!!! Please enter a valid task description\n");
        return null;
    }
}