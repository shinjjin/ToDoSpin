package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private int currentId = 0;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(String text) {
        Task task = new Task(currentId++, text);
        tasks.add(task);
    }

    public void editTask(long id, String newText) {
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> t.setName(newText));
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

    public void chooseRandomTask() {
        tasks.forEach(t -> t.setChosen(false));

        Spin spin = new Spin();
        spin.getRandomTask(tasks);
    }
}
