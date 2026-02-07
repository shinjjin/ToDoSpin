package SoftwareEngineering.ToDoProject;

import java.util.ArrayList;
import java.util.List;

//Klasse Wheel implementiert das Interface WheelSubject und verwaltet eine Liste von TaskObservern
public class Wheel implements WheelSubject{
    private final List<TaskObserver> observers = new ArrayList<>();
    @Override
    public void registerObserver(TaskObserver observer) {
        observers.add(observer);
    }

    //Die Methode entfernt einen TaskObserver aus der Liste der Beobachter
    @Override
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    //Die Methode informiert alle Beobachter über die ausgewählte Aufgabe, indem sie die update-Methode jedes Beobachters aufruft
    @Override
    public void notifyObserver(int chosenTaskId) {
        for (TaskObserver observer : observers) {
            observer.update(chosenTaskId);
        }
    }
}
