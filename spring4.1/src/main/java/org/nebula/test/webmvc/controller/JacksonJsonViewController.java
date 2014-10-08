package org.nebula.test.webmvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.nebula.test.webmvc.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2014-10-8.
 */
@RestController
public class JacksonJsonViewController {

    @RequestMapping("/jackson1")
    @JsonView(User.OnlyIdView.class)
    public User test1(){
        System.out.println("---------jackson1");
        return new User(1L,"zhangsan");
    }
    @RequestMapping("/jackson2")
    @JsonView(User.OnlyNameView.class)
    public User test2(){
        return new User(1L,"zhangsan");
    }

    @RequestMapping("/jackson3")
    @JsonView(User.AllView.class)
    public User test3(){
        return new User(1L,"zhangsan");
    }
    @RequestMapping("/jackson4")
    public String test4(){
        System.out.println("----jackson4");
        return "jackson4";
    }

}
