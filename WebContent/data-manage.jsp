<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据管理</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/JSON.js"></script>
</head>
<body>
<label>添加一条数据：</label>
<form id="dataForm">
    <!-- 隐藏的字段 -->
    <input name="id" type="hidden">
    
    <label>姓名:</label><input type="text" name="name"><br/>
    <label>性别:</label>
    <label>男：</label><input type="radio" name="sex" value="0" checked="checked">
    <label>女：</label><input type="radio" name="sex" value="1">
    <select name="hobbit">
		<option value="read">读书</option>
		<option value="movie">看电影</option>
	</select>
	<label>生日:</label><input type="text" name="birthday" value="1998-01-02"><br/>
</form>
<button type="button" onclick="submit()">提交</button>

<label>添加对象集合：</label>
<form id="listForm">
    <input name="list[0].id" type="hidden">
    <label>姓名:</label><input type="text" name="list[0].name"><br/>
    
    <input name="list[1].id" type="hidden">
    <label>姓名:</label><input type="text" name="list[1].name"><br/>
</form>
<button type="button" onclick="submit2()">提交</button><br/>
ajax post数组对象：<button type="button" onclick="submit3()">提交</button><br/>
post一段字符串到后台：<button type="button" onclick="submit4()">提交</button><br/>
</body>
<script type="text/javascript">
function submit(){
    alert($('#dataForm').serialize());
    $.ajax({
      method: "POST",
      dataType:"json",
      url: "<%=request.getContextPath() %>/data-controller/addObj",
      data: $('#dataForm').serialize(),
      success:function(data) {
        if(data.msg == "success"){
            alert("success id:" + data.data.id);
        }else{
        	alert("fail");
        }
      }
    });
}

//传递对象list到后台
function submit2(){
    alert($('#listForm').serialize());
    $.ajax({
      method: "POST",
      dataType:"json",
      url: "<%=request.getContextPath() %>/data-controller/addList",
      data: $('#listForm').serialize(),
      success:function(data) {
        alert("done");
      }
    });
}

//传递对象list到后台
function submit3(){
	var obj1 = {name:"test",sex:"1"};
	var obj2 = {name:"test2",sex:"1"};
	var list = [];
	list.push(obj1);
	list.push(obj2);
    $.ajax({
      method: "POST",
      dataType:"json",
      contentType:"application/json",
      url: "<%=request.getContextPath() %>/data-controller/addList2",
      data: JSON.stringify(list),
      success:function(data) {
        alert("done");
      }
    });
}

//post一段字符串到后台
function submit4(){
    $.ajax({
      method: "POST",
      dataType:"json",
      url: "<%=request.getContextPath() %>/data-controller/sendString",
      data: "text=1、能独立选型搭建应用程序的框架，可以设计数据模型。了解JVM的模型，知道如何调试解决疑难问题；",
      success:function(data) {
        alert("done");
      }
    });
}
</script>
</html>