package org.nebula.test.webmvc.controller;

import org.nebula.test.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2014-10-8.
 */
@Controller
public class JsonpController {

    @RequestMapping("/jsonp1")
    public User jsonp1() {//没有@ResponseBody 直接走MappingJackson2JsonView
        return new User(1L, "zhangsan");
    }


    @RequestMapping("/jsonp2")
    @ResponseBody
    public User jsonp2() {
        return new User(1L, "zhangsan");
    }
}
