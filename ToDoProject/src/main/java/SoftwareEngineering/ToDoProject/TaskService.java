package SoftwareEngineering.ToDoProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TaskService implements TaskObserver {                                     //Implementiert TaskObserver, damit die Klasse auf Benachrichtigungen vom Wheel reagieren kann

    @Autowired
    TaskRepository repository;                                                        /*Autowired sorgt dafür, dass Spring automatisch eine Instanz von TaskRepository bereitstellt und in die TaskService-Klasse injiziert.
                                                                                       Dadurch kann TaskService auf die Methoden von TaskRepository zugreifen, um Datenbankoperationen durchzuführen*/
    private int currentId = 0;
    private static final int MAX_TASKS = 15;
                                                                                      //Methode ruft alle Aufgaben aus der Datenbank ab, indem sie die findAll-Methode des TaskRepository verwendet.
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
                                                                                      //Methode ruft alle noch nicht erledigten Aufgaben ab
    public List<Task> getOpenTasks() {
        return repository.findAll().stream()
                .filter(task -> !task.isDone()).toList();
    }
                                                                                      //Methode ruft ausgewählte Aufgabe ab
    public List<Task> getChosenTask(){
        return repository.findAll().stream()
                .filter(Task::isChosen)
                .toList();
    }
                                                                                      //Methode fügt eine neue Aufgabe hinzu. Wenn die maximale Anzahl erreicht ist, gibt sie false zurück.
    public boolean addTask(String name) {
        if (getOpenTasks().size() >= MAX_TASKS) {
            return false;
        }

        int lastId = getAllTasks().stream().map(Task::getId).max(Comparator.comparingDouble(Integer::intValue)).orElse(0);

        Task task = new Task(currentId = lastId + 1, name);
        repository.save(task);
        return true;
    }
                                                                                      //Methode bearbeitet den Namen einer Aufgabe basierend auf der ID. Sie sucht die Aufgabe in der Datenbank, aktualisiert den Namen und speichert die Änderungen.
    public void editTask(int id, String newName) {
        repository.findAll().stream()
                .filter(t -> Integer.valueOf(id).equals(t.getId()))
                .findFirst()
                .ifPresent(t -> {
                    t.setName(newName);
                    repository.save(t);
                });
    }
                                                                                     //Methode löscht eine Aufgabe basierend auf der ID
    public void deleteTask(int id) {
        repository.deleteById(id);
    }
                                                                                    //Methode löscht alle Aufgaben, die noch nicht erledigt sind
    public void deleteAll() {
        repository.findAll().stream()
                .filter(task -> !task.isDone())
                .forEach(repository::delete);
    }
                                                                                  //Methode markiert eine Aufgabe als erledigt, indem sie die Aufgabe in der Datenbank sucht, den Status auf "done" setzt und die Änderungen speichert
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
                                                                                 //Methode wird aufgerufen, wenn das Glücksrad eine Benachrichtigung sendet
    @Override
    public void update(int chosenTaskId) {
        repository.findById(chosenTaskId).ifPresent(task -> {
            task.setChosen(true);
            repository.save(task);
        });
    }
}

