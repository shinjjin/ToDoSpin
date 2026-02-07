package SoftwareEngineering.ToDoProject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Task {                                  // Task Klasse mit den Attributen id, name, done und chosen. Die Klasse ist als Entity annotiert, um sie in der Datenbank zu speichern
    @Id
    private Integer id;
    private String name;
    private boolean done;
    private boolean chosen;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.done = false;
        this.chosen = false;
    }

    public Task(int id, String name, boolean chosen) {
        this.id = id;
        this.name = name;
        this.done = false;
        this.chosen = chosen;
    }

    public Task() {
    }

    public Integer getId(){
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