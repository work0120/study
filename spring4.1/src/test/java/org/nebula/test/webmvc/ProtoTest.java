package org.nebula.test.webmvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nebula.test.webmvc.controller.MyExtensionRegistryInitializer;
import org.nebula.test.webmvc.pb.UserProtos;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ExtensionRegistryInitializer;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Administrator on 2014-10-9.
 */
public class ProtoTest {


    static RestTemplate restTemplate;
    private static Server server;
    String baseUri = "http://localhost:8080";

    @BeforeClass
    public static void beforeClass() throws Exception {
        //    创建一个server
        server = new Server(8080);
        WebAppContext context = new WebAppContext();
        String webapp = "spring4.1/src/main/webapp";
        context.setDescriptor(webapp + "/WEB-INF/web.xml");//指定web.xml配置文件
        context.setResourceBase(webapp);//指定web目录
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();

        restTemplate=new RestTemplate();
        ExtensionRegistryInitializer extensionRegistryInitializer=new MyExtensionRegistryInitializer();

        ProtobufHttpMessageConverter protobufHttpMessageConverter=new ProtobufHttpMessageConverter(extensionRegistryInitializer);

        restTemplate.getMessageConverters().add(0,protobufHttpMessageConverter);

    }

    @AfterClass
    public static void afterClass() throws Exception{
        server.stop();//测试结束停止服务器
    }

    @Test
    public void testRead(){
        HttpHeaders httpHeaders=new HttpHeaders();

        RequestEntity<UserProtos.User> requestEntity=
                new RequestEntity<UserProtos.User>(httpHeaders, HttpMethod.POST, URI.create(baseUri+"/proto/read"));
        ResponseEntity<UserProtos.User> responseEntity=
                restTemplate.exchange(requestEntity,UserProtos.User.class);
        System.out.println(responseEntity.getBody() );
    }
    @Test
    public void testWrite(){
        UserProtos.User user=UserProtos.User.newBuilder().setId(1).setName("lisi").build();
        HttpHeaders httpHeaders=new HttpHeaders();
        RequestEntity<UserProtos.User> requestEntity=new RequestEntity<UserProtos.User>(user,httpHeaders,HttpMethod.POST,URI.create(baseUri+"/proto/write"));

        ResponseEntity<UserProtos.User> responseEntity=
                restTemplate.exchange(requestEntity,UserProtos.User.class);
        System.out.println(responseEntity.getBody());

    }

}
