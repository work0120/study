package org.nebula.test.webmvc.controller;


import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Created by Administrator on 2014-10-8.
 */
@Order(2)
@ControllerAdvice(basePackages = "org.nebula.test.webmvc.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdvice(){
        super("callback","jsonp");//指定jsonpParameterNames
        System.out.println("----JsonAdvice");
    }

}
