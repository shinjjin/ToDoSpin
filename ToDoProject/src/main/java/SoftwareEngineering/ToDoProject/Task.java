package SoftwareEngineering.ToDoProject;

public class Task {
    private long id; //ID
    private String name;
    private boolean state;

    public Task(int i, String text, boolean state) {
        this.id = id;
        this.name = name;
        this.state = state;
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
}