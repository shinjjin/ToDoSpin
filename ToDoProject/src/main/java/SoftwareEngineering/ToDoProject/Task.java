package SoftwareEngineering.ToDoProject;

public class Task {
    private final long id; //ID
    private String name;
    private boolean state;
    private boolean chosen;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.state = false;
        this.chosen = false;
    }

    public Task(int id, String name, boolean chosen) {
        this.id = id;
        this.name = name;
        this.state = false;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}