package SoftwareEngineering.ToDoProject;

//Interface für das Subject im Observer-Pattern, um die Beobachter zu registrieren, zu entfernen und zu benachrichtigen
public interface WheelSubject {
    void registerObserver(TaskObserver observer);

    void removeObserver(TaskObserver observer);

    void notifyObserver(int chosenTaskId);
}
