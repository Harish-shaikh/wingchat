package wingchat.wingchat.ChatControllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wingchat.wingchat.Entity.User;
import wingchat.wingchat.Repositorys.UserRepository;

@Controller
@RequestMapping("/chatboard")
public class UserControllers {

    @Autowired
    private UserRepository ur;

    @GetMapping("/run")
    public String home(Model m, Principal p, @RequestParam(name = "receiver", required = false) String receiver) {
        String name = p.getName();
        User userdata = this.ur.userdetails(name);
        m.addAttribute("userdata", userdata);

        List<User> users = ur.findAll();
        users.removeIf(user -> userdata.getName().equals(name));
        m.addAttribute("Friends", users);

        return "home";
    }

    @GetMapping("/worldchat")
    public String worldChat(Model m, Principal p) {
        String name = p.getName();
        User userdata = this.ur.userdetails(name);
        m.addAttribute("userdata", userdata);

        return "world";
    }
}
