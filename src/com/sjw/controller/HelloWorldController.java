package com.sjw.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjw.model.TestObj;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }
    
    @RequestMapping(method = RequestMethod.GET)//直接访问/hello-world将访问这个方法
    public Map<String, Object> get() {//跳转到hello-world.jsp
   	 System.out.println("get");
   	 Map<String, Object> map = new HashMap<String, Object>();
   	 map.put("name", "11");
        return map;
    }

    
    //处理json输入输出
    @RequestMapping(value = "/test-json")
    @ResponseBody
    public Map<String, Object> testJson(@RequestBody TestObj testObj) {
        System.out.println(testObj.getName());
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("obj", testObj);
        return result;
    }
}
