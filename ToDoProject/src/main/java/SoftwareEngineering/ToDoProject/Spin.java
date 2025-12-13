package SoftwareEngineering.ToDoProject;
import java.util.List;
import java.util.Random;

public class Spin {
    public void getRandomTask(List<Task> tasks){
        //neue Liste, damit für die Berechnungen nicht alle Tasks, sondern nur die undone Tasks beachtet werden
        List<Task> undoneTasks = tasks.stream().filter(task -> !task.isDone()).toList();
        int extraTurns = randomNumber(3, 5) * 360;                     //Generiert zusätzliche Drehungen für Visuellen Effekt
        int randomStop = randomNumber(0, 359);                         //Letzte Drehung zum bestimmen der Task

        int taskAmount = undoneTasks.size();                                          //Berechnung für Anzahl der Segmente
        int segmentSize = 360 / taskAmount;

        int taskIndex = randomStop / segmentSize;                              //Findet Index der Task, die dem Segment entspricht

        String taskName = undoneTasks.get(taskIndex).getName();         //Name der ausgewählten Task

        //ausgewählte Task wird in der ursprünglichen Task-Liste auf chosen gesetzt
        for (Task task : tasks){
            if (task.getName().equals(taskName)){
                task.setChosen(true);
            }
        }

    }

    private int randomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;                            //Liefert zufällige Zahl zwischen min und max
    }

}
