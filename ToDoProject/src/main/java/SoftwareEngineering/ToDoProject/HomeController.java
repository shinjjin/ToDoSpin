package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private List<Task> tasks = new ArrayList<>();           //vom Nutzer eingegebene Tasks
    private int currentId = 0;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("tasks", tasks);     //übergibt die Tasks, damit sie angezeigt werden

        if (!model.containsAttribute("randomtask")){ 
            model.addAttribute("randomtask", "Random Task: ...");
        }
        return "home";                                      //zeigt home.html an

    }

    @PostMapping("/add")
    public String addTask(@RequestParam String text){
        Task task = new Task(currentId++, text);     //erstellt Task und gibt dem Task ID
        task.setName(text);                                      //übergibt den eingegeben Text
        tasks.add(task);                                         //fügt Task in ArrayList ein
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editTask(@RequestParam long id, @RequestParam String text){
        tasks.stream()                                           //geht durch Tasks und sucht nach Task mit passender ID
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setName(text));          //übergibt neuen Text
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam long id) {
        tasks.removeIf(task -> task.getId() == id);                //löscht den Task mit der passenden ID
        return "redirect:/";
    }

    @PostMapping("/spin")
    public String spinWheel(RedirectAttributes redirectAttributes){
        Spin spin = new Spin();

        tasks.forEach(task -> task.setChosen(false));           //alle Tasks werden nicht ausgewählt
        spin.getRandomTask(tasks);                                //damit diese Task allein als ausgewählt angezeigt wird

        return "redirect:/";
    }

    @PostMapping("/taskDone")
    public String taskDone(@RequestParam long id){
        tasks.stream()                                           //geht durch Tasks und sucht nach Task mit passender ID
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setDone(true));         //setzt Task auf Done
        return "redirect:/";
    }
    // 1. Zeigt das Glücksrad an
    @GetMapping("/wheel")
    public String showWheel(Model model) {
        // Wir filtern hier schon: Nur Tasks, die NICHT erledigt sind, kommen aufs Rad
        List<Task> openTasks = tasks.stream()
                .filter(t -> !t.isDone())
                .toList(); // oder .collect(Collectors.toList()) je nach Java Version

        model.addAttribute("openTasks", openTasks);
        return "wheel"; // Wir brauchen eine neue Datei wheel.html
    }

    // 2. Verarbeitet den Gewinner vom Glücksrad
    @GetMapping("/chooseFromWheel/{id}")
    public String chooseFromWheel(@PathVariable long id) {
        // Erstmal alle "chosen" auf false setzen (wie in eurer Spin Klasse)
        tasks.forEach(task -> task.setChosen(false));

        // Den Gewinner suchen und markieren
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setChosen(true));

        return "redirect:/"; // Zurück zur Hauptseite
    }
    @PostMapping("deleteAll")
    public String deleteAllTasks() {
        tasks.clear();                                           //löschen aller  Tasks
        currentId = 0;                                           //setzt ID auf 0 zurück
        return "redirect:/";
    }

    @GetMapping("/api/tasks/open")
    @ResponseBody
    public List<Task> getOpenTasks() {
        return tasks.stream().filter(t -> !t.isDone()).toList();
    }
}
