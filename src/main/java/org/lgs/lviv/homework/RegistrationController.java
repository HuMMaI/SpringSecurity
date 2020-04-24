package org.lgs.lviv.homework;

import org.lgs.lviv.homework.domain.User;
import org.lgs.lviv.homework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user){
        Optional<User> userFromDb = userService.findByUsername(user.getUsername());

        if (userFromDb.isPresent()){
            return "registration";
        }

        userService.save(user);

        return "redirect:/login";
    }
}
