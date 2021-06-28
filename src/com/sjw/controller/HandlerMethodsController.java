package com.sjw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sjw.model.Pet;
import com.sjw.model.TestObj;
import com.sjw.service.MyService;

@Controller
@RequestMapping("/handler-methods")
@SessionAttributes("pet")
public class HandlerMethodsController {
    @RequestMapping(value = "/request-param", method = RequestMethod.GET)
    public String getNewForm(@RequestParam("name") String name) {
        System.out.println(name);
        return "success";
    }
    
    @RequestMapping(value = "/request-header")
    public void testReqHeader(@RequestHeader("Accept-Encoding") String encoding,@RequestHeader("Keep-Alive") long keepAlive) {
        System.out.println(encoding);
    }
    
    @RequestMapping(value = "/cookie-value")
    public void testCookieValue(@CookieValue("JSESSIONID") String cookie) {
        System.out.println(cookie);
    }
    
    /*
     * 个人理解：
     * ModelAttribute的作用：获取及存储对象
     * ModelAttribute的特点：比较灵活，可以自动从多数据源获取数据
     * */
    
    /**
     * testObj解析顺序
     * 1如果已经添加到Model，则从Model获取
     * 2从HTTP Session获取
     * 3从路径参数获取：如/accounts/{account}中的account
     * 4从默认构造器获取
     * 5根据主构造器匹配请求参数
     * 访问：http://localhost:8080/Spring-MVC-Demo/handler-methods/model-attribute?name=test，输出test
     * @param testObj
     * @param result
     * @param index
     * @return
     */
    @RequestMapping(value = "/model-attribute")
    public String add(@ModelAttribute TestObj testObj, BindingResult result) {
        if (result.hasErrors()) {
            return "appointments/new";
        }
        System.out.println(testObj.getName());
        return "success";
    }

    //ModelAttribute标注的方法将在RequestMapping标注方法前调用
    /*@ModelAttribute
    public TestObj findAccount(@PathVariable String nameParam) {
        TestObj obj = new TestObj();
        obj.setName(nameParam);
        return obj;
    }*/
    
    /**
     * 访问http://localhost:8080/Spring-MVC-Demo/handler-methods/sessionAttributes，系统默认将自定义对象类型的Pet参数当做ModelAttribute处理
     * pet会从Session读取
     * @Description
     * @param pet2
     * @param br
     * @return
     */
    @RequestMapping(value = "/model-attribute2")
    public String modelAttr2(Pet pet,BindingResult br) {
		System.out.println(pet.getName());//输出pet1
		return "success";
	}
    
    /**
     * 访问http://localhost:8080/Spring-MVC-Demo/handler-methods/sessionAttributes，系统默认将自定义对象类型的Pet参数当做ModelAttribute处理
     * pet将会添加到model，同时存储到SessionAttributes中
     * @Description
     * @param pet
     * @param br
     * @return
     */
    @RequestMapping(value = "/sessionAttributes")
    public String sessionAttr(Pet pet,BindingResult br) {
		pet.setName("pet1");
		return "success";
	}


}
