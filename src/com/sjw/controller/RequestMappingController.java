package com.sjw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjw.model.TestObj;

/**
 * @description 匹配请求
 * @author sjw
 * @date 2018年7月17日
 */
@Controller
@RequestMapping("/request-mapping") //所有方法共享该匹配路劲
public class RequestMappingController {
    @RequestMapping("/{name}")
    public void getString(@PathVariable String name){
        System.out.println(name);
    }
    
    @RequestMapping("/{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}") //匹配路径如/spring-web-3.0.5 .jar
    public void handle(@PathVariable String version, @PathVariable String ext) {
        // ...
    }
    
    /**
     * 给请求限定条件
     */
    //限制请求数据类型及输出数据类型
    @RequestMapping(value = "/test-json",consumes="application/json",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public TestObj findPet(@RequestBody TestObj obj) {
        return obj;
    }
    //限制参数值
    @RequestMapping(value = "/pets/{petId}", params = "myParam=myValue")
    public void findPet(@PathVariable String petId) {
        // ...
    }
    @RequestMapping(value = "/pets", headers = "myHeader=myValue")//请求头中必须包含myHeader=myValue
    public void findPet2(@PathVariable String petId) {
        // ...
    }
    
}
