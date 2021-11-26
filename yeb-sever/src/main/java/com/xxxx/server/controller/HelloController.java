package com.xxxx.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yeb
 * @description: 测试
 * @author: 作者
 * @create: 2021-09-13 16:44
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public  String hello(){
        return "hello";
    }
    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return  "/employee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return  "/employee/advanced/hello";
    }
}
