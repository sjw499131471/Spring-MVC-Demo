<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
</head>
<body>
    <h2>文件上传</h2>
    <form action="upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file" accept=".xls,.xlsx">
        <input type="submit" value="上传">
    </form>
    
    <form id="uploadForm" enctype="multipart/form-data" method="post">
        <input id="file" type="file" name="file"  multiple="multiple"/><br/>
        <input type="button" value="插件异步上传" onclick="upload()"/>
    </form>
    
    <script>
    function upload(){
        if($('#file').val() == ""){
            alert('你没有选中文件!');
            return ;
        }
        var options = {
              type: "post",
               url : "upload2",
               dataType: "text",
               success : function(data) {
                  if(data == "success"){
                      alert("上传成功!");
                  }else{
                      alert("上传失败!");
                  }
               }
         }; 
         //jquery-form用插件异步提交
          $('#uploadForm').ajaxSubmit(options);  
    }
</script>
</body>
</html>