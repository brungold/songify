package com.songifyDatabase.song.infructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SongsViewController {
    private Map<Integer, String> database = new HashMap<>();
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/view/songs")
    public String songs(Model model){
        database.put(1, "shawnmendes song1");
        database.put(2, "metallica one");
        database.put(3, "sting two");
        database.put(4, "barnaba three");

        model.addAttribute("songMap", database);
        return "songs";
    }
}
