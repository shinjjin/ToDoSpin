package SoftwareEngineering.ToDoProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void TaskgetsStartingIDTest() {
        Task task = new Task(1, "TestTask");
        assertEquals(1, task.getId());
    }

    @Test
    void TaskgetsStartingNameTest() {
        Task task = new Task(1, "TestTask");
        assertEquals("TestTask", task.getName());
    }

    @Test
    void TaskIsNotDoneByDefaultTest() {
        Task task = new Task(1, "TestTask");
        assertFalse(task.isDone());
    }

    @Test
    void TaskIsNotChosenByDefaultTest() {
        Task task = new Task(1, "TestTask");
        assertFalse(task.isChosen());
    }

    @Test
    void ChosenTaskHasRigthID() {
        Task task = new Task(2, "ChosenTask", true);
        assertEquals(2, task.getId());
    }

    @Test
    void ChosenTaskHasRigthName() {
        Task task = new Task(2, "ChosenTask", true);
        assertEquals("ChosenTask", task.getName());
    }

    @Test
    void ChosenTaskIsNotDoneByDefault() {
        Task task = new Task(2, "ChosenTask", true);
        assertFalse(task.isDone());
    }

    @Test
    void ChosenTaskIsChosen() {
        Task task = new Task(2, "ChosenTask", true);
        assertTrue(task.isChosen());
    }

    @Test
    void ChangingTaskNamesWorks() {
        Task task = new Task(3, "OldName");
        task.setName("NewName");
        assertEquals("NewName", task.getName());
    }

    @Test
    void TaskCanBeSetToTrue() {
        Task task = new Task(3, "OldName");
        task.setDone(true);
        assertTrue(task.isDone());
    }

    @Test
    void TaskCanBeChosen() {
        Task task = new Task(3, "OldName");
        task.setChosen(true);
        assertTrue(task.isChosen());
    }
}
