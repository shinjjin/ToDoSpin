package SoftwareEngineering.ToDoProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private HomeController homeController;
    @Mock
    private Model model;
    private TaskService taskService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        homeController = new HomeController(taskService);
    }

    @Test
    void homeTest() {
        String expected = "home";

        String actual = homeController.home(model);

        assertEquals(expected, actual);
    }

    @Test
    void addTaskTest() {
        String expected = "redirect:/";

        String actual = homeController.addTask("aTask");

        assertEquals(expected, actual);
    }

    @Test
    void addEmptyTaskTest(){
        String expected = "redirect:/";

        String actual = homeController.addTask("");

        assertEquals(expected, actual);
    }

    @Test
    void editTaskWithCorrectIdTest() {
        String expected = "redirect:/";

        homeController.addTask("aTask");
        String actual = homeController.editTask(1, "editedTask");

        assertEquals(expected, actual);
    }

    @Test
    void editTaskWithWrongIdTest(){
        String expected = "redirect:/";

        homeController.addTask("aTask");
        String actual = homeController.editTask(4242,"editedTask");

        assertEquals(expected, actual);
    }

    @Test
    void editTaskWhenTasklistIsEmpty(){
        String expected = "redirect:/";

        String actual = homeController.editTask(1, "editedTask");

        assertEquals(expected, actual);
    }

    @Test
    void deleteTaskWithCorrectIdTest() {
        String expected = "redirect:/";

        homeController.addTask("aTask");
        String actual = homeController.deleteTask(1);

        assertEquals(expected, actual);
    }

    @Test
    void deleteTaskWithWorngIdTest(){
        String expected = "redirect:/";

        homeController.addTask("TestTask");
        String actual = homeController.deleteTask(4242);

       assertEquals(expected, actual);
    }

    @Test
    void deleteTaskWhenTasklistIsEmpty(){
        String expected = "redirect:/";

        String actual = homeController.deleteTask(1);

        assertEquals(expected, actual);
    }

    @Test
    void TaskDoneWithCorrectIdTest(){
        String expected = "redirect:/";

        homeController.addTask("TestTask");
        String actual = homeController.taskDone(1);

        assertEquals(expected, actual);
    }

    @Test
    void TaskDoneWithWrongIdTest(){
        String expected = "redirect:/";

        homeController.addTask("TestTask");
        String actual = homeController.taskDone(4242);

        assertEquals(expected, actual);
    }

    @Test
    void TaskDoneWhenListIsEmpty(){
        String expected = "redirect:/";

        String actual = homeController.taskDone(1);

        assertEquals(expected, actual);
    }

}