package SoftwareEngineering.ToDoProject;

public class Task {
    private long id; //ID
    private String name;
    private boolean state;

    public Task(int i, String text, boolean state) {
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(name);
    }

<<<<<<< Updated upstream
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
=======

>>>>>>> Stashed changes
}