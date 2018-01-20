package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPage {
    

    @GetMapping("/")
    public String testPage(){
        return "testPage";
    }
}
