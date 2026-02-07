package SoftwareEngineering.ToDoProject;

// Interface für das Observer-Pattern, um die Beobachter über die ausgewählte Aufgabe zu informieren
public interface TaskObserver {
    void update(int chosenTaskId);
}
