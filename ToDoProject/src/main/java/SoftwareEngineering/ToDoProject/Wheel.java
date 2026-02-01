package SoftwareEngineering.ToDoProject;

import java.util.ArrayList;
import java.util.List;

public class Wheel implements WheelSubject{
    private final List<TaskObserver> observers = new ArrayList<>();
    @Override
    public void registerObserver(TaskObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(int chosenTaskId) {
        for (TaskObserver observer : observers) {
            observer.update(chosenTaskId);
        }
    }
}
