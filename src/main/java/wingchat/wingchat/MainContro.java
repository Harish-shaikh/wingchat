package wingchat.wingchat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import wingchat.wingchat.Entity.User;
import wingchat.wingchat.Repositorys.UserRepository;

@Controller
public class MainContro {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/wingchat")
    public String front() {
        return "front";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/submitdata")
    public String submitData(@ModelAttribute("userdata") User user, Model m,
            @RequestParam("profileImage") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String imageName = file.getOriginalFilename();
                user.setUserImage(imageName);

                // set folder path and save
                File savefile = new ClassPathResource("static/image").getFile();

                // save and copy
                Files.copy(file.getInputStream(),
                        Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);

                // user.setUserImage(imageName);

                // File tempDir = Files.createTempDirectory("temp-files").toFile();
                // Path p = tempDir.toPath().resolve(imageName);
                // Files.copy(file.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // save data in database
        this.userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("title", "homepage");
        return "front";
    }

}
