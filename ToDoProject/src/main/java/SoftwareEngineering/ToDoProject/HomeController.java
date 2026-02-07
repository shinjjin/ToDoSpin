package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {

    private final TaskService taskService;
    private final Wheel wheel;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
        this.wheel = new Wheel();
        this.wheel.registerObserver(taskService);       // TaskService hört auf Wheel
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());         //übergibt die Tasks, damit sie angezeigt werden
        model.addAttribute("openTasks", taskService.getOpenTasks());
        return "home";                                                              //zeigt home.html an
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String text){
        taskService.addTask(text);                                                  //fügt Task in ArrayList ein
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editTask(@RequestParam int id, @RequestParam String text){
        taskService.editTask(id, text);                                              //übergibt neuen Text
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);                                                  //löscht den Task mit der passenden ID
        return "redirect:/";
    }

    @PostMapping("/taskDone")
    public String taskDone(@RequestParam int id){
        taskService.markDone(id);                                                   //setzt Task auf Done
        return "redirect:/";
    }

    // 1. Zeigt das Glücksrad mit allen offenen Tasks an
    @GetMapping("/wheel")
    public String showWheel(Model model) {
        model.addAttribute("openTasks", taskService.getOpenTasks());
        return "wheel";
    }

    // 2. Verarbeitet den Gewinner vom Glücksrad
    @GetMapping("/chooseFromWheel/{id}")
    public String chooseFromWheel(@PathVariable int id) {
        wheel.notifyObserver(id);
        return "redirect:/";                                                        // Zurück zur Hauptseite
    }

    @PostMapping("deleteAll")
    public String deleteAllTasks() {
        taskService.deleteAll();                                                     //löscht alle Tasks, die nicht auf Done stehen
        return "redirect:/";
    }

    @GetMapping("/api/tasks/open")
    @ResponseBody                                                                     // Gibt eine Liste aller offenen Tasks zurück
    public List<Task> getOpenTasks() {
        return taskService.getOpenTasks();
    }

    @GetMapping("/api/tasks/chosen")
    @ResponseBody                                                                      // Gibt eine Liste aller gewählten Tasks zurück
    public List<Task> getChosenTask(){
        return taskService.getChosenTask();
    }
}
