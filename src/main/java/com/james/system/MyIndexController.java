package com.james.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ACT
 */
@Controller
public class MyIndexController {

    @RequestMapping("/")
    public String index() {
        return "forward:login.html";
    }

}
