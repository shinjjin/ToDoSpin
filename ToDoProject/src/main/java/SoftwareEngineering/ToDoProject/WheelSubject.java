package SoftwareEngineering.ToDoProject;

public interface WheelSubject {
    void registerObserver(TaskObserver observer);

    void removeObserver(TaskObserver observer);

    void notifyObserver(int chosenTaskId);
}
