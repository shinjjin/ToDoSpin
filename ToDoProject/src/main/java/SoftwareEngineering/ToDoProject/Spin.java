package SoftwareEngineering.ToDoProject;
import java.util.List;
import java.util.Random;

public class Spin {
    public void getRandomTask(List<Task> tasks){
        //neue Liste, damit für die Berechnungen nicht alle Tasks, sondern nur die undone Tasks beachtet werden
        List<Task> undoneTasks = tasks.stream().filter(task -> !task.isDone()).toList();

        if (undoneTasks.isEmpty()) return;

        //int extraTurns = randomNumber(3, 5) * 360;                     //Generiert zusätzliche Drehungen für Visuellen Effekt
        int randomStop = randomNumber(0, 359);                         //Letzte Drehung zum bestimmen der Task

        int pointerOffset = 270;
        int adjusted = (pointerOffset - (randomStop % 360) + 360) % 360;

        double segment = 360.0 / undoneTasks.size();
        int taskIndex = (int) (adjusted / segment);

        if (taskIndex < 0) taskIndex = 0;
        if (taskIndex >= undoneTasks.size()) taskIndex = undoneTasks.size() -1;

        long chosenId = undoneTasks.get(taskIndex).getId();

        //ausgewählte Task wird in der ursprünglichen Task-Liste auf chosen gesetzt
        for (Task task : tasks) {
                task.setChosen(task.getId() == chosenId);
        }

    }

    private int randomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;                            //Liefert zufällige Zahl zwischen min und max
    }

}
