import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static int taskCount = 0;
    private static Task[] tasks = new Task[100];

    public static void hello() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }


    public static void echo(String commands) {
        System.out.println("\tadded: " + commands);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void oneMore(Task t) {
        tasks[taskCount] = t;
        taskCount++;
    }

    public static void addTask(String type, String desc) {
        if (type.equals("todo")) {
            tasks[taskCount] = new Todo(desc);
            System.out.println("\t" + tasks[taskCount].toString());
        }
        if (type.equals("deadline")) {
            String[] arrOfDesc = desc.split("/by");
            tasks[taskCount] = new Deadline(arrOfDesc[0], arrOfDesc[1]);
            System.out.println("\t" + tasks[taskCount].toString());
        }
        if (type.equals("event")) {
            String[] arrOfDesc = desc.split("/at");
            tasks[taskCount] = new Event(arrOfDesc[0], arrOfDesc[1]);
            System.out.println("\t" + tasks[taskCount].toString());
        }
        System.out.println("\tNow you have " + (taskCount + 1) + " tasks in the list");
        taskCount++;
    }

    public static void showTask(Task[] tasks) {
        String taskInfo;

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            taskInfo = tasks[i].toString();
            System.out.println("\t" + (i+1) + "." + taskInfo);
        }
    }

    public static int getNum(String commands) {
        return Integer.parseInt(commands.substring(commands.indexOf(" ")+1, commands.length()));
    }

    public static void asDone(Task[] tasks, int taskNumber) {
        tasks[taskNumber-1].markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t [X] " + tasks[taskNumber-1].description);
    }

    public static void notDone(Task[] tasks, int taskNumber) {
        tasks[taskNumber-1].unmark();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t [ ] " + tasks[taskNumber-1].description);
    }

    public static void main(String[] args) {
        Duke.hello();
        String line;
        Scanner inPut = new Scanner(System.in);


        do {
            line = inPut.nextLine();

            if (line.toLowerCase().contains("bye")) {
                Duke.exit();
                break;
            }
            if (line.toLowerCase().startsWith("list")) {
                Duke.showTask(Arrays.copyOf(tasks,taskCount));
                continue;
            }
            if (line.toLowerCase().startsWith("mark")) {
                Duke.asDone(Arrays.copyOf(tasks,taskCount),Duke.getNum(line));
                continue;
            }
            if (line.toLowerCase().startsWith("unmark")) {
                Duke.notDone(Arrays.copyOf(tasks,taskCount),Duke.getNum(line));
                continue;
            }
            if (line.toLowerCase().startsWith("todo") || line.toLowerCase().startsWith("deadline") || line.toLowerCase().startsWith("event")) {
                String[] arrOfLine =  line.split(" ", 2);
                System.out.println("\tGot it. I've added this task:");
                Duke.addTask(arrOfLine[0], arrOfLine[1]);
                continue;
            }
            Duke.oneMore(new Task(line));
            Duke.echo(line);
        } while (true);

    }
}