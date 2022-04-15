import duke.constants.DukeConstants;
import duke.tasklist.TaskList;
import duke.tasks.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class GradleTest{
    private Pattern d = Pattern.compile("^deadline.*$");
    private ArrayList<Task> taskArr;

    @Test
    public void testTask(){
        taskArr = new ArrayList<Task>(100);
        TaskList tasks1 = new TaskList(taskArr);
        String input = "test";
        tasks1.add(new Task(input, false));
    }

    @Test
    public void testDeadline(){
        taskArr = new ArrayList<Task>(100);
        TaskList tasks2 = new TaskList(taskArr);
        String input = "deadline test /by 2019-12-01 17:00";
        input = input.replaceAll("^deadline\\s", "");
        String[] splited = input.split("/by", 2);
        tasks2.add(new Deadline(splited[0], splited[1].replaceAll("^\\s", ""), false));
    }
}