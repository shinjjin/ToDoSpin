package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
