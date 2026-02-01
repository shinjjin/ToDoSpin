package SoftwareEngineering.ToDoProject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
@Component
@Entity
public class Task {
    @Id
    private Long id; //ID
    private String name;
    private boolean done;
    private boolean chosen;

    public Task(long id, String name) {
        this.id = id;
        this.name = name;
        this.done = false;  //abgehakt oder nicht
        this.chosen = false;
    }

    public Task(long id, String name, boolean chosen) {
        this.id = id;
        this.name = name;
        this.done = false;
        this.chosen = chosen;
    }

    public Task() {

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