package com.spf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-07-06 16:06
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(){
        return "Hello World! Spring Boot 微信点餐系统";
    }

}
