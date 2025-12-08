package SoftwareEngineering.ToDoProject;

public class Task {
    private final long id; //ID
    private String name;
    private boolean done;
    private boolean chosen;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.done = false;  //abgehakt oder nicht
        this.chosen = false;
    }

    public Task(int id, String name, boolean chosen) {
        this.id = id;
        this.name = name;
        this.done = false;
        this.chosen = chosen;
    }
    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}