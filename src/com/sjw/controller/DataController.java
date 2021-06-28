/**
 * 
 */
package com.sjw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjw.model.ObjList;
import com.sjw.model.TestObj;

/**
 * 增删改查Controller
 * @author Administrator
 * 2018年7月31日
 */
@Controller
@RequestMapping("/data-controller")
public class DataController {
	
	@RequestMapping("/addObj")
	@ResponseBody
	public Map<String, Object> addObj(@ModelAttribute TestObj obj,BindingResult br) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("sex:" + obj.getSex());
		System.out.println("hobbit:" + obj.getHobbit());
		//数据添加成功返回信息
		obj.setId("1");
		map.put("msg", "success");
		map.put("data", obj);
		return map;
	}
	
	/**
	 * 上传List数据
	 * @Description
	 * @param objList
	 * @return
	 */
	@RequestMapping("/addList")
	@ResponseBody
	public String addList(@ModelAttribute ObjList objList) {
		System.out.println(objList.getList().get(0).getName());
		System.out.println(objList.getList().get(1).getName());
		return "success";
	}
	
	@RequestMapping(value="/addList2",method=RequestMethod.POST)
	@ResponseBody
	public String addList2(@RequestBody List<TestObj> list) {
		System.out.println(list.get(0).getName());
		System.out.println(list.get(1).getName());
		return "success";
	}
	
	@RequestMapping(value="/sendString",method=RequestMethod.POST)
	@ResponseBody
	public String sendString(@RequestParam String text) {
		System.out.println(text);
		return "success";
	}
}
