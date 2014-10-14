package org.nebula.test.webmvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * Created by Administrator on 2014-10-14.
 */
@RestController
public class MvcUriComponentsBuilderController {
    @RequestMapping("/uri")
    public String mvcUriComponentsBuilder1(){
        System.out.println("mvcUriComponentsBuilder1-----------");
        return MvcUriComponentsBuilder.fromMappingName("MUCBC#mvcUriComponentsBuilder1").build();
    }
    @RequestMapping("/uri/{id}")
     public String mvcUriComponentsBuilder2(@PathVariable Long id){
        System.out.println("mvcUriComponentsBuilder2-------"+id);
        return MvcUriComponentsBuilder.fromMappingName("MUCBC#mvcUriComponentsBuilder2").arg(0,"张三").build();
    }

    @RequestMapping("/uri/{id}/{id1}")
    public String mvcUriComponentsBuilder3(@PathVariable Long id,@PathVariable Long id1){
        System.out.println("mvcUriComponentsBuilder2-------"+id);
        return MvcUriComponentsBuilder.fromMappingName("MUCBC#mvcUriComponentsBuilder3").arg(0,"123").arg(1,"456").build();
    }
}
