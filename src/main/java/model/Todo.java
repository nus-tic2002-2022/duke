package model;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Print the task in certain format
     *
     */
    @Override
    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[T][" + status + "] " + description);
    }


    /**
     * Format the task to store into file
     *
     * @return Formatted string
     */
    @Override
    public String toFileString(){
        String status = done ? "1" : "0";
        return "T|" + status + "|" + description;
    }
}