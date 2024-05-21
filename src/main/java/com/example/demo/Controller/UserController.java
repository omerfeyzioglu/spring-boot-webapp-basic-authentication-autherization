package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository urP;
    @GetMapping("/register")
    public String getRegister(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String saveRegister(@ModelAttribute("user") User user ){

        user.setRole("USER");
        urP.save(user);
        return "redirect:/login";

    }

    @GetMapping("/index")
    private String getArtists(Model model){
        Iterable<User> users = urP.findAll();
        model.addAttribute("user", users);
        return "index" ;
    }


}
