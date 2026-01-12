package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements TaskObserver {

    private final List<Task> tasks = new ArrayList<>();
    private int currentId = 0;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getOpenTasks() {
        return tasks.stream().filter(task -> !task.isDone()).toList();
    }

    public void addTask(String name) {
        Task task = new Task(currentId++, name);
        tasks.add(task);
    }

    public void editTask(long id, String newName) {
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> t.setName(newName));
    }

    public void deleteTask(long id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    public void deleteAll() {
        tasks.clear();
        currentId = 0;
    }

    public void markDone(long id) {
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> t.setDone(true));
    }

    @Override
    public void update(long chosenTaskId) {
        // Alle Tasks auf "chosen = false" setzen
        tasks.forEach(t -> t.setChosen(false));

        // Den Gewinner markieren
        tasks.stream()
                .filter(t -> t.getId() == chosenTaskId)
                .findFirst()
                .ifPresent(t -> t.setChosen(true));
    }
}

