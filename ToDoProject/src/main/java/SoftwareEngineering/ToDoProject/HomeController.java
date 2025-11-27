package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("tasks", tasks);     //übergibt die Tasks, damit sie angezeigt werden
        return "home";                                      //zeigt home.html an
    }

}
