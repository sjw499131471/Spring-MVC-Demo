<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/JSON.js"></script>
<script type="text/javascript">
    function testJson() {
    	console.log(JSON.stringify({name:'test'}));
    	var cfg =     {
   	        type: 'POST', 
   	        data: JSON.stringify({name:'test'}), 
   	        dataType: 'json',
   	        contentType:'application/json;charse=UTF-8',        
   	        success: function(result) { 
   	            alert(result.obj.name); 
   	        } 
   	    };
    	cfg.url = "hello-world/test-json";
        $.ajax(cfg);
	}
</script>
</head>
<body>
	<a href="hello-world/hello">helloworld</a><br/>
	
	<a href="request-mapping/test">@PathVariable</a><br/>
	
	<a href="handler-methods/request-param?name=test">request-param</a><br/>
	<a href="handler-methods/request-header">request-header</a><br/>
	<a href="handler-methods/cookie-value">cookie-value</a><br/>
	<a href="handler-methods/model-attribute?name=test&index=1">model-attribute</a><br/>
	
	<a href="hello-world">helloworld</a><br/>
	<input type="button" value="testJson" onclick="testJson()"/><br/>
	
	<a href="data-manage.jsp">数据管理</a><br/>
	<a href="upload.jsp">文件上传</a><br/>
	<a href="download.jsp">文件下载</a><br/>
</body>
</html>