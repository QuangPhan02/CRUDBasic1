package com.example.crudbasic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CRUDController {
    @GetMapping("")
    public String showHomepage() {
        return "index";
    }
}
