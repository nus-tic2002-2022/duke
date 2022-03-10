public class Event extends Task{

    protected String event;

    public Event (String description, String event){
        super(description);
        this.event = event;
    }

    public String getEvent(){
        return this.event;
    }

    public void setEvent(String event){
        this.event = event;
    }

    @Override
    public String toString() {
        return " [E]   " + super.toString() + " (at: " + event + ")";
    }
}
