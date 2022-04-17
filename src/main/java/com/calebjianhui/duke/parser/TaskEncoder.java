package com.calebjianhui.duke.parser;

import java.util.ArrayList;

import com.calebjianhui.duke.taskmanager.DateModule;
import com.calebjianhui.duke.taskmanager.FixedDurationModule;
import com.calebjianhui.duke.taskmanager.Task;
import com.calebjianhui.duke.taskmanager.ToDos;

/**
 * Encode a given task for storage purposes
 **/
public class TaskEncoder {
    private static final String ENCODING_SEPARATOR = " /| ";

    /**
     * Encode a given task list
     *
     * @param taskList Arraylist containing the tasks to be encoded
     * @return Arraylist containing the encoded task in string format
     **/
    public static ArrayList<String> encodeTaskList(ArrayList<Task> taskList) {
        ArrayList<String> taskStringList = new ArrayList<>();
        for (Task task: taskList) {
            taskStringList.add(encodeTask(task));
        }
        return taskStringList;
    }

    /**
     * Returns a string containing the encoded task
     *
     * @param task Task to be encoded
     * @return Encoded task in string format
     * @throws AssertionError Should there be an invalid task type received
     **/
    private static String encodeTask(Task task) {
        String type = task.getType();
        String description;
        if (ToDos.TYPE_INDICATOR.equals(type)) {
            description = task.getDescription();
        } else if (task instanceof DateModule) {
            description = ((DateModule) task).getRawDescription();
        } else if (task instanceof FixedDurationModule) {
            description = ((FixedDurationModule) task).getRawDescription();
        } else {
            // Type of task should only consist of the above, therefore throw AssertionError
            String errorMessage = "Invalid type of task received.";
            assert false : errorMessage;
            throw new AssertionError(errorMessage);
        }
        return type.concat(ENCODING_SEPARATOR).concat(task.getDoneStatus() ? "M" : "U")
                .concat(ENCODING_SEPARATOR).concat(description);
    }

}
