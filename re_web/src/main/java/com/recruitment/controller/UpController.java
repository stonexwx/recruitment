package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.JobSeekerServiceImpl;
import com.recruitment.dao.domain.Users;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class UpController {
    /**
     * 求职者头像上传
     * @param request
     * @param file
     */
    @Autowired
    JobSeekerServiceImpl jobSeekerService;
    @RequestMapping("/seeker_head_up")
    @ResponseBody
    public String headUp(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws IOException {
        ServletContext sc = request.getSession().getServletContext();
        Users users = (Users) request.getSession().getAttribute("user");
        String dir = sc.getRealPath("/upload");
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName = "";
        if ("jpg".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "head.jpg";
        } else if ("png".equals(type)) {
            imgName = sdf.format(new Date()) +users.getUser_name()+r.nextInt(100) + "head.png";
        } else if ("jpeg".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "head.jpeg";
        } else if ("gif".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "head.gif";
        } else {
            return null;
        }
        //将文件流写入到磁盘中
        FileUtils.writeByteArrayToFile(new File(dir, imgName), file.getBytes());
        //返回文件路径
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("uploadUrl","http://localhost:8080/upload/"+imgName);
        jobSeekerService.seekerFileUpdate("head","http://localhost:8080/upload/"+imgName);
        return JSON.toJSONString(map);
    }
    /**
     * 求职者简历上传
     * @param request
     * @param file
     */
    @RequestMapping("/seeker_resume_up")
    @ResponseBody
    public String resumeUp(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws IOException {
        ServletContext sc = request.getSession().getServletContext();
        Users users = (Users) request.getSession().getAttribute("user");
        String dir = sc.getRealPath("/upload");
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName = "";
        if ("jpg".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "resume.jpg";
        } else if ("png".equals(type)) {
            imgName = sdf.format(new Date()) +users.getUser_name()+r.nextInt(100) + "resume.png";
        } else if ("jpeg".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "resume.jpeg";
        } else if ("gif".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "resume.gif";
        } else {
            return null;
        }
        //将文件流写入到磁盘中
        FileUtils.writeByteArrayToFile(new File(dir, imgName), file.getBytes());
        //返回文件路径
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("uploadUrl","http://localhost:8080/upload/"+imgName);
        jobSeekerService.seekerFileUpdate("resume","http://localhost:8080/upload/"+imgName);
        return JSON.toJSONString(map);
    }
    /**
     * 求职者学历上传
     * @param request
     * @param file
     */
    @RequestMapping("/seeker_books_up")
    @ResponseBody
    public String booksUp(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws IOException {
        ServletContext sc = request.getSession().getServletContext();
        Users users = (Users) request.getSession().getAttribute("user");
        String dir = sc.getRealPath("/upload");
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName = "";
        if ("jpg".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "books.jpg";
        } else if ("png".equals(type)) {
            imgName = sdf.format(new Date()) +users.getUser_name()+r.nextInt(100) + "books.png";
        } else if ("jpeg".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "books.jpeg";
        } else if ("gif".equals(type)) {
            imgName = sdf.format(new Date()) + users.getUser_name()+r.nextInt(100) + "books.gif";
        } else {
            return null;
        }
        //将文件流写入到磁盘中
        FileUtils.writeByteArrayToFile(new File(dir, imgName), file.getBytes());
        //返回文件路径
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("uploadUrl","http://localhost:8080/upload/"+imgName);
        jobSeekerService.seekerFileUpdate("education","http://localhost:8080/upload/"+imgName);
        return JSON.toJSONString(map);
    }
}
