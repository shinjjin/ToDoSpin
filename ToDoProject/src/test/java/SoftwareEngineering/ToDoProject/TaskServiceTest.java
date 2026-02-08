package SoftwareEngineering.ToDoProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testGetAllTasks() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Task(1, "A"), new Task(2, "B")));
        assertEquals(2, taskService.getAllTasks().size());
    }

    @Test
    void testGetOpenTasks() {
        Task t1 = new Task(1, "Open");
        Task t2 = new Task(2, "Done");
        t2.setDone(true);
        when(repository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Task> openTasks = taskService.getOpenTasks();
        assertEquals(1, openTasks.size());
        assertFalse(openTasks.get(0).isDone());
    }

    @Test
    void testGetChosenTask() {
        Task t1 = new Task(1, "Chosen");
        t1.setChosen(true);
        when(repository.findAll()).thenReturn(Collections.singletonList(t1));

        assertEquals(1, taskService.getChosenTask().size());
        assertTrue(taskService.getChosenTask().get(0).isChosen());
    }

    @Test
    void testAddTask_Success() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        boolean result = taskService.addTask("New Task");

        assertTrue(result);
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    void testAddTask_LimitReached() {
        // Simuliere 15 offene Tasks
        Task[] tasks = new Task[15];
        for(int i=0; i<15; i++) tasks[i] = new Task(i, "Task " + i);
        when(repository.findAll()).thenReturn(Arrays.asList(tasks));

        boolean result = taskService.addTask("Too many");

        assertFalse(result);
        verify(repository, never()).save(any(Task.class));
    }

    @Test
    void testEditTask_Found() {
        Task t = new Task(1, "Old Name");
        when(repository.findAll()).thenReturn(Collections.singletonList(t));

        taskService.editTask(1, "New Name");

        assertEquals("New Name", t.getName());
        verify(repository).save(t);
    }

    @Test
    void testEditTask_NotFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        taskService.editTask(1, "New Name");
        verify(repository, never()).save(any());
    }

    @Test
    void testDeleteTask() {
        taskService.deleteTask(1);
        verify(repository).deleteById(1);
    }

    @Test
    void testDeleteAll() {
        Task t1 = new Task(1, "Open");
        Task t2 = new Task(2, "Done");
        t2.setDone(true);
        when(repository.findAll()).thenReturn(Arrays.asList(t1, t2));

        taskService.deleteAll();

        verify(repository, times(1)).delete(t1);
        verify(repository, never()).delete(t2);
    }

    @Test
    void testMarkDone_Found() {
        Task t = new Task(1, "Task");
        t.setChosen(true);
        when(repository.findAll()).thenReturn(Collections.singletonList(t));

        taskService.markDone(1);

        assertTrue(t.isDone());
        assertFalse(t.isChosen());
        verify(repository).save(t);
    }

    @Test
    void testUpdate_Observer_Found() {
        Task t = new Task(1, "Task");
        when(repository.findById(1)).thenReturn(Optional.of(t));

        taskService.update(1);

        assertTrue(t.isChosen());
        verify(repository).save(t);
    }

    @Test
    void testUpdate_Observer_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        taskService.update(1);
        verify(repository, never()).save(any());
    }
}