public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getIsDone() {
        return (isDone ? "X" : " ");
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }

}
