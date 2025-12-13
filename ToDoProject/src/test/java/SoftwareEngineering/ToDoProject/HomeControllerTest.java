package SoftwareEngineering.ToDoProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private HomeController homeController;
    @Mock
    private Model model;

    @BeforeEach
    void setUp(){
        homeController = new HomeController();
        MockitoAnnotations.openMocks(this);
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

    }

    @Test
    void editTaskWhenTasklistIsEmpty(){

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

    }

    @Test
    void deleteTaskWhenTasklistIsEmpty(){
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

    }

    @Test
    void TaskDoneWhenListIsEmpty(){

    }
}