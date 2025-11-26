package SoftwareEngineering.ToDoProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    //zeigt home.html an
    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

}
