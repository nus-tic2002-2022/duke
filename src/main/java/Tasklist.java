import java.util.ArrayList;
import duke.*;

public class Tasklist {
    public ArrayList<Task> taskArr;
    public Tasklist(){
    }
    public Tasklist(ArrayList<Task> taskArr){
        this.taskArr = taskArr;
    }
    public void list(){
        for(int i=0;i<taskArr.size();i++){
            System.out.println((i+1) + ". " + taskArr.get(i).getDescription());
        }
    }
    public void mark(int i){
        if(i <= taskArr.size() && i != 0){
            taskArr.get(i-1).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n" + taskArr.get(i-1).getDescription());
        }else{
            System.out.println("Out of range!");
        }
    }

    public void unmark(int i){
        if(i <= taskArr.size() && i != 0){
            taskArr.get(i-1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" + taskArr.get(i-1).getDescription());
        }else{
            System.out.println("Out of range!");
        }
    }
    public void delete(int i){
        if(i <= taskArr.size() && i != 0){
            System.out.println("Noted. I've removed this task: \n" + taskArr.get(i-1).getDescription() + "\nNow you have " + (taskArr.size() - 1) + " tasks in the list.");
            taskArr.remove(i-1);
        }else{
            System.out.println("Out of range!");
        }
    }
    public void add(Task task){
        taskArr.add(task);
    }

    public Task get(int i){
        return taskArr.get(i);
    }
    public int size(){
        return taskArr.size();
    }
}
