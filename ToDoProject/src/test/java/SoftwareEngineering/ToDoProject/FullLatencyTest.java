package SoftwareEngineering.ToDoProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class FullLatencyTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setup() {
        taskRepository.deleteAll();
    }

    // Hilfsmethode zur Zeitmessung in Millisekunden
    private double measure(Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    @Test
    void testCreateLatency() {
        double ms = measure(() -> taskService.addTask("Neuer Task"));
        System.out.println("Latenz - Create Task: " + ms + " ms");
        assertTrue(ms < 200);
    }

    @Test
    void testReadLatency() {
        // Setup: 10 Tasks anlegen
        for(int i=0; i<10; i++) taskService.addTask("Task " + i);

        double ms = measure(() -> taskService.getAllTasks());
        System.out.println("Latenz - Read All Tasks: " + ms + " ms");
        assertTrue(ms < 200);
    }

    @Test
    void testMarkDoneLatency() {
        taskService.addTask("Done Test");
        int id = taskRepository.findAll().get(0).getId();

        double ms = measure(() -> taskService.markDone(id));
        System.out.println("Latenz - Mark Done: " + ms + " ms");
        assertTrue(ms < 200);
    }

    @Test
    void testEditLatency() {
        taskService.addTask("Alt");
        int id = taskRepository.findAll().get(0).getId();

        double ms = measure(() -> taskService.editTask(id, "Neu"));
        System.out.println("Latenz - Edit Task: " + ms + " ms");
        assertTrue(ms < 200);
    }

    @Test
    void testDeleteLatency() {
        taskService.addTask("Zu löschen");
        int id = taskRepository.findAll().get(0).getId();

        double ms = measure(() -> taskService.deleteTask(id));
        System.out.println("Latenz - Delete Task: " + ms + " ms");
        assertTrue(ms < 200);
    }

    @Test
    void testWheelSelectionLatency() {
        // Simuliert den Observer-Call (Wichtigster Test für dein Design)
        taskService.addTask("Vom Rad gewählt");
        int id = taskRepository.findAll().get(0).getId();

        double ms = measure(() -> taskService.update(id));
        System.out.println("Latenz - Wheel Observer Update: " + ms + " ms");
        assertTrue(ms < 200);
    }

    @Test
    void testDeleteAllLatency() {
        for(int i=0; i<10; i++) taskService.addTask("Task " + i);

        double ms = measure(() -> taskService.deleteAll());
        System.out.println("Latenz - Delete All Tasks: " + ms + " ms");
        assertTrue(ms < 200);
    }
}