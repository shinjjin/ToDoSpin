package SoftwareEngineering.ToDoProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskObserver {

    @Autowired
    TaskRepository repository;

    private int currentId = 0;
    private static final int MAX_TASKS = 15;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getOpenTasks() {
        return repository.findAll().stream()
                .filter(task -> !task.isDone()).toList();
    }

    public List<Task> getChosenTask(){
        return repository.findAll().stream()
                .filter(Task::isChosen)
                .toList();
    }

    public boolean addTask(String name) {
        if (getOpenTasks().size() >= MAX_TASKS) {
            return false;
        }

        Task task = new Task(currentId++, name);
        repository.save(task);
        return true;
    }

    public void editTask(int id, String newName) {
        repository.findAll().stream()
                .filter(t -> Integer.valueOf(id).equals(t.getId()))
                .findFirst()
                .ifPresent(t -> {
                    t.setName(newName);
                    repository.save(t);
                });
    }

    public void deleteTask(int id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
        currentId = 0;
    }

    public void markDone(int id) {
        repository.findAll().stream()
                .filter(t -> Integer.valueOf(id).equals(t.getId()))
                .findFirst()
                .ifPresent(task -> {
                    task.setDone(true);
                    task.setChosen(false);
                    repository.save(task);
                });
    }

    @Override
    public void update(int chosenTaskId) {
        repository.findById(chosenTaskId).ifPresent(task -> {
            task.setChosen(true);
            repository.save(task);
        });
    }
}

