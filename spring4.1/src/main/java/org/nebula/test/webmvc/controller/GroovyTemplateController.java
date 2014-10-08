package org.nebula.test.webmvc.controller;


import org.nebula.test.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroovyTemplateController {

    @RequestMapping("/groovy/hello")
    public String groovyTemplate(Model model){
        System.out.println("进入 groovytemplate");
        model.addAttribute("user",new User(1L,"zhangsan"));
        return "hello";
    }

    @RequestMapping("/testgroovy")
    public String testGroovy(){
        System.out.println("tg");
        return "";
    }
}
