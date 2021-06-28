package com.sjw.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class FileUploadController {
    //上传文件会自动绑定到MultipartFile中
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public String upload(HttpServletRequest request,@RequestParam("description") String description,@RequestParam("file") MultipartFile file) throws Exception {
       System.out.println(description);
       //如果文件不为空，写入上传路径
       if(!file.isEmpty()) {
           //上传文件路径
           String path = request.getServletContext().getRealPath("/images/");
           //上传文件名
           String filename = file.getOriginalFilename();
           File filepath = new File(path,filename);
           //判断路径是否存在，如果不存在就创建一个
           if (!filepath.getParentFile().exists()) { 
               filepath.getParentFile().mkdirs();
           }
           //将上传文件保存到一个目标文件当中
           //file.transferTo(new File(path + File.separator + filename));
           InputStream iStream = file.getInputStream();
           OutputStream out = new FileOutputStream(new File(path + File.separator + filename));
           //文件缓存区
           byte[] bytes2= new byte[1024];
           //将文件流信息读取文件缓存区，如果读取结果不为-1就代表文件没有读取完毕，反之已经读取完毕
           while(iStream.read(bytes2)!=-1){
        	   //将缓存区中的内容写到afterfile文件中
        	   out.write(bytes2);
           }
    	   out.flush();
    	   out.close();
           return "success";
       } else {
           return "error";
       }

    }
    
    @RequestMapping(value="/upload2",method=RequestMethod.POST)
    @ResponseBody
    public String upload2(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception {
       //如果文件不为空，写入上传路径
       if(!file.isEmpty()) {
           //上传文件路径
           String path = request.getServletContext().getRealPath("/images/");
           //上传文件名
           String filename = file.getOriginalFilename();
           File filepath = new File(path,filename);
           //判断路径是否存在，如果不存在就创建一个
           if (!filepath.getParentFile().exists()) { 
               filepath.getParentFile().mkdirs();
           }
           //将上传文件保存到一个目标文件当中
           //file.transferTo(new File(path + File.separator + filename));
           InputStream iStream = file.getInputStream();
           byte[] bytes = new byte[100];
           iStream.read(bytes);
           InputStream iStream2 = file.getInputStream();
           OutputStream out = new FileOutputStream(new File(path + File.separator + filename));
           //文件缓存区
           byte[] bytes2= new byte[1024];
           //将文件流信息读取文件缓存区，如果读取结果不为-1就代表文件没有读取完毕，反之已经读取完毕
           while(iStream.read(bytes2)!=-1){
        	   //将缓存区中的内容写到afterfile文件中
        	   out.write(bytes2);
           }
    	   out.flush();
    	   out.close();
           return "success";
       } else {
           return "error";
       }

    }
    
    /**
     * 基于ResponseEntity的下载：
     * 基于ResponseEntity的实现的局限性还是很大，从代码中可以看出这种下载方式是一种一次性读取的下载方式，
     * 在文件较大的时候会直接抛出内存溢出（我自己亲测一个1.8G的文件在执行下载操作的时候直接抛出了内存溢出）。
     * 还有就是这种方式在进行下载统计的时候也存在局限性，无法统计在下载失败的情况已完成下载量，因此限制了对下载的功能扩展。
     * 虽然这种实现方式有局限性，但是也有着优点——简洁。在很多时候我们并不需要那么复杂的下载功能时，这种实现就应该是首选了。
     * @param request
     * @param filename
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename,Model model)throws Exception {
       //下载文件路径
       String path = request.getServletContext().getRealPath("/images/");
       File file = new File(path + File.separator + filename);
       HttpHeaders headers = new HttpHeaders();  
       //下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
       //通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
               headers, HttpStatus.CREATED);  
    }
    
    @RequestMapping("/download2")
    @ResponseBody
    public Map<String, Object> download2(HttpServletRequest request,HttpServletResponse response,@RequestParam("filename") String filename,Model model){
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            //下载显示的文件名，解决中文名称乱码问题  
            String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFielName + "\"");  
            response.setContentType("application/octet-stream;charset=UTF-8");  
            
            //打开本地文件流
            String path = request.getServletContext().getRealPath("/images/");
            InputStream inputStream = new FileInputStream(path + File.separator + filename);
            //激活下载操作
            OutputStream os = response.getOutputStream();
            long downloadedLength = 0l;
            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                downloadedLength += b.length;
            }
            response.addHeader("Content-Length", "" + downloadedLength);  
            os.close();
            inputStream.close();
            
            result.put("status", "success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
