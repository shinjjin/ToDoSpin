package SoftwareEngineering.ToDoProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void constructorDefaults() {
        Task task = new Task(1, "TestTask");
        
        assertEquals(1, task.getId());
        assertEquals("TestTask", task.getName());
        assertFalse(task.isDone());
        assertFalse(task.isChosen());  
    }

    @Test
    void constructorChosen() {
        Task task = new Task(2, "ChosenTask", true);
        
        assertEquals(2, task.getId());
        assertEquals("ChosenTask", task.getName());
        assertFalse(task.isDone());
        assertTrue(task.isChosen());  
    }

    @Test
    void settersAndGetters() {
        Task task = new Task(3, "OldName");

        task.setName("NewName");
        assertEquals("NewName", task.getName());
        
        task.setDone(true);
        assertTrue(task.isDone());

        task.setChosen(true);
        assertTrue(task.isChosen());  
    }


}
