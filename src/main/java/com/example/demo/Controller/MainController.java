package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/logout")
    public String getLogout(){
        return "login";
    }


    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }



}
