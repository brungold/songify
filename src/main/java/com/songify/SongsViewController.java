package com.songify;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongsViewController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/view/songs")
    public String songs(){
        return "songs";
    }
}
