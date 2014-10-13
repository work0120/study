package org.nebula.test.webmvc.controller;

import org.nebula.test.webmvc.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



/**
 * Created by Administrator on 2014-10-8.
 */
@Order(1)
@ControllerAdvice(basePackages = "org.nebula.test.webmvc.controller")
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println("----ResponseBodyAdvice--supports");
        return methodParameter.getMethod().getReturnType().isAssignableFrom(User.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        User user= (User) o;
        user.setName("---"+user.getName()+"---");
        System.out.println("----ResponseBodyAdvice--beforeBodyWrite");
        return user;
    }
}
