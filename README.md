

# 文档

## 目录

[TOC]



## 技术栈框架

​	前端：vue2+elementUI

​	后端：Spring+MyBatis+SpringMVC，阿里巴巴连接池技术druid，

## 思想

​	采用模块化开发思想以及MVC思想

![](C:\Users\xwx\Desktop\1.png)

### 	模块介绍

#### 		re_biz

​			业务层，掌管对控制层和持久层数据整理，数据传输，业务逻辑的实现

#### 		re_dao

​			持久层，对数据库进行操作

#### 		re_web

​			控制层，与前端进行交互

## 准备工作

### 	配置

​			除了常规配置项以外，vue+ssm框架开发属于前后端分离开发，首先解决跨域问题

#### 	跨域配置

​			解决跨域问题有很多种解决方式，这里选择自己创建过滤器（Filter）去解决

> 在re_web/src/main/java/com/recruitment/global/下创建ServletFilter.java过滤器文件

​			![image-20220203124244937](C:\Users\xwx\AppData\Roaming\Typora\typora-user-images\image-20220203124244937.png)

```java
package com.recruitment.global;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ServletFilter")
public class ServletFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //转换ServletRequest，ServletResponse变为HttpServletRequest和HttpServletResponse
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //支持跨域，添加响应头
        
        httpResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "token,content-type");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        //检测OPTIONS请求
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return ;
        }
        chain.doFilter(request, response);
    }
}

```

​		书写完成后在re_web/src/main/webapp/WEB-INF/web.xml文件中书写过滤器配置

```xml
 	<filter>
        <filter-name>servletFilterTest</filter-name>
        <filter-class>com.recruitment.global.ServletFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>servletFilterTest</filter-name>
        <url-pattern>/*</url-pattern>//对所有地址进行过滤
    </filter-mapping>
```

#### 编码配置

> 在re_web/src/main/java/com/recruitment/global/EncodingFilter.java文件中对编码进行统一设置防止乱码

```java
package com.recruitment.global;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private String encoding="utf-8";
    //在初始化的时候就开始设置编码
    public void init(FilterConfig config) throws ServletException {
        if(config.getInitParameter("encoding")!=null){
            //获取初始值
            encoding=config.getInitParameter("encoding");
        }
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //转换ServletRequest，ServletResponse变为HttpServletRequest和HttpServletResponse
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        //防止拦截静态资源导致静态资源加载失败
        if (!request1.getServletPath().contains(".css") && !request1.getServletPath().contains(".js")) {
            request1.setCharacterEncoding(encoding);
            response1.setContentType("text/html;charset=" + encoding);
        }
        String method = request1.getMethod();
        //options请求直接放行
        if(method.equals("OPTIONS")){
            chain.doFilter(request,response);
        }
        chain.doFilter(request,response);
    }
}

```

re_web/src/main/webapp/WEB-INF/web.xml

```xml
<filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>com.recruitment.global.EncodingFilter</filter-class>
        <init-param>//自定义初始值
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/</url-pattern>//对所有路径进行拦截
    </filter-mapping>
```

## 开始写代码

### 用户界面

#### 第一个部分：登录注册页面

##### 登录逻辑

​		登录逻辑：验证手机号和密码是否正确，如果正确则告诉前端用户信息，往session中保存一份用户信息。

> 控制层：re_web/src/main/java/com/recruitment/controller/LoginController.java

```java
@Controller
@SessionAttributes("user")
public class LoginController {
    /*
    使用spring自动注入业务层类
    */
    @Autowired
    UsersServiceImpl usersService;

    /**
     * 登录验证
     * @param phone
     * @param password
     * @return 网址
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String phone, String password, HttpSession httpSession){
        Users users=usersService.login(phone,password);//交给业务层处理
        Map<String,Object> map = new HashMap<>();
        if(users!=null){//判断数据库车讯数据是否为空，如果为空则意味着密码或者手机账号不对
            //判断session里是否保存过user信息如果有就将其清除掉
            if(httpSession.getAttribute("user")!=null||httpSession.getAttribute("user")!="")			{
                httpSession.removeAttribute("user");
            }
            //添加session
            httpSession.setAttribute("user",users);
            //将数据进行打包
            map.put("flag","true");
            map.put("user",users);
            //向前端传递打包好的数据，通过json数据格式
            return JSON.toJSONString(map);
        }
        //查询失败告知前端
        map.put("flag",false);
        return JSON.toJSONString(map);
    }
}

```

>业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/UsersServiceImpl.java

```java
//spring自动注入持久层类
 @Autowired
    UsersMapper usersMapper;
    @Autowired
    JobSeekerMapper jobSeekerMapper;

    /**
     * 登录验证
     *
     * @param phone
     * @param password
     * @return Users
     */
    @Override
    public Users login(String phone, String password) {
        //数据封装
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(password);
        return usersMapper.login(users);//向数据库查询
    }

```

>持久层:re_dao/src/main/java/com/recruitment/dao/mapper/UsersMapper.java

​					re_dao/src/main/resources/com/recruitment/dao/mapper/UsersMapper.xml

```xml
 	/**
     * 登录验证
     */
    Users login(Users users);
--------------------------------------------------------------------------------------------
查询语句
<select id="login" resultType="com.recruitment.dao.domain.Users">
        select * from users where phone = #{phone} and password = #{password}
</select>
```

##### 注册逻辑

> ps：注册用户需要检验手机账号是否被注册过

​		注册用户涉及到对多个表进行操作，如图，我们要对用户表和求职者信息表进行操作

<img src="C:\Users\xwx\AppData\Roaming\Typora\typora-user-images\image-20220203133840450.png" alt="image-20220203133840450" style="zoom:80%;" />

![image-20220203134001250](C:\Users\xwx\AppData\Roaming\Typora\typora-user-images\image-20220203134001250.png)

所以我们需要一个dto类来帮助我们对数据进行封装

> DTO：re_dao/src/main/java/com/recruitment/dao/dto/UserDTO.java

```java
package com.recruitment.dao.dto;

import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;

public class UserDTO {
    private Users users  = new Users();//对用户进行封装
    private JobSeeker jobSeeker = new JobSeeker();//对求职者信息进行封装

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }
}

```

> 控制层：re_web/src/main/java/com/recruitment/controller/UsersConller.java  第一个控制层控制注册

```java
 	/**
     * 用户注册
     */
    @RequestMapping("/user_insert")
    @ResponseBody
    public String userInsert(String name, String phone, String password, String sex, String email) {
        UserDTO userDTO = new UserDTO();//new一个对象
        //传入用户注册数据
        userDTO.getUsers().setUser_name(name);
        userDTO.getUsers().setPassword(password);
        userDTO.getUsers().setPhone(phone);
        //性别转换
        if (sex.equals("女")) {
            userDTO.getJobSeeker().setSex("1");
        } else {
            userDTO.getJobSeeker().setSex("0");
        }
        //获取现在时间
        Date date = new Date();
        userDTO.getUsers().setAddtime(date);
        userDTO.getJobSeeker().setAddtime(date);
        userDTO.getUsers().setRole_id(0L);
        userDTO.getJobSeeker().setEmail(email);
        //是否注册成功
        Boolean flag = usersService.insertUser(userDTO);
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", flag);
        return JSON.toJSONString(map);
    }

```

> 第二个控制层控制手机账号重复检测

```java
 	/**
     * 用户手机号检测
     *
     * @return
     */
    @RequestMapping("/phone")
    @ResponseBody
    public String selectPhone(String phone) {
        Map<String, Boolean> map = new HashMap<>();
        //查询数据库是否有这个手机账号
        if(usersService.selectPhoneByPhone(phone)!=null){
            map.put("flag", true);
            return JSON.toJSONString(map);
        }
        map.put("flag", false);
        return JSON.toJSONString(map);
    }
```



> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/UsersServiceImpl.java

```java
	/**
     * 注册用户
     * @param userDTO
     * @return
     *这里分两步走第一步注册用户，第二步对求职者信息进行更新
     */
    @Override
    public boolean insertUser(UserDTO userDTO) {
        //第一步注册用户
        //向数据库传递参数
        usersMapper.insertAll(userDTO);
        //获取注册后的user信息根据手机号
        Users users = usersMapper.selectPhone(userDTO);
        //保存用户信息
        userDTO.setUsers(users);
        //对求职者信息进行更新
        return jobSeekerMapper.updateUid(userDTO);
    }

    /**
     * 查询手机号是否注册
     *
     * @param phone
     * @return
     */
    @Override
    public String selectPhoneByPhone(String phone) {
        返回查询信息
        return usersMapper.selectPhoneByPhone(phone);
    }
```

> 持久层：re_dao/src/main/resources/com/recruitment/dao/mapper/UsersMapper.xml

```xml
	/**
     * 注册用户
     */
	<insert id="insertAll">
        INSERT INTO
    		`re`.`users` (`user_name`, `password`, `role_id`, `phone`, `addtime`) 			
    	VALUES 
    (#{userDTO.users.user_name}, #{userDTO.users.password}, 0, #{userDTO.users.phone}, #{userDTO.users.addtime})
    </insert>
	/**
     * 查询用户id
     */
	<select id="selectPhone" resultType="com.recruitment.dao.domain.Users">
        select * from users where phone = #{userDTO.users.phone}
    </select>
	/**
     * 验证手机号
     */
	<select id="selectPhoneByPhone" resultType="java.lang.String">
        select phone from users where phone =#{phone}
    </select>
```

#### 第二个部分：个人信息

##### 个人信息更改，文件上传

![image-20220203135820774](C:\Users\xwx\AppData\Roaming\Typora\typora-user-images\image-20220203135820774.png)

同样是对多个表进行操作,所以我们需要个dto去对我们的数据再来个封装

> DTO：re_dao/src/main/java/com/recruitment/dao/dto/JobSeekerDTO.java

```java
package com.recruitment.dao.dto;

import com.recruitment.dao.domain.JobSeeker;
import com.recruitment.dao.domain.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class JobSeekerDTO {
    private JobSeeker jobSeeker =new JobSeeker();//求职者信息
    private String jobName;//工作名称
    private Users users;//用户信息
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
```


> 控制层：re_web/src/main/java/com/recruitment/controller/SeekerConller.java

```java
 	/**
     * 更新用户信息
     */
    @RequestMapping("/seeker_update")
    @ResponseBody
    public String updateSeeker(String name, String education, String sex, String email, String job_type, HttpSession httpSession) {
        //从sessio中获取求职者原来的信息
        JobSeekerDTO jobSeekerDTO = (JobSeekerDTO) httpSession.getAttribute("jobSeeker");
        Users users = (Users) httpSession.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        //所有信息比对，对需要更改的值进行替换，重新进行封装
        if(!jobSeekerDTO.getJobSeeker().getName().equals(name)||jobSeekerDTO.getJobSeeker().getName()==null) {
            jobSeekerDTO.getJobSeeker().setName(name);
        }
        if (!jobSeekerDTO.getJobSeeker().getEducation().equals(education)) {
            jobSeekerDTO.getJobSeeker().setEducation(education);
        }
        if (jobSeekerDTO.getJobSeeker().getSex().equals("女")) {
            jobSeekerDTO.getJobSeeker().setSex("1");
            if(!jobSeekerDTO.getJobSeeker().getSex().equals(sex)){
                jobSeekerDTO.getJobSeeker().setSex("0");
            }
        }
        if (jobSeekerDTO.getJobSeeker().getSex().equals("男")) {
            jobSeekerDTO.getJobSeeker().setSex("0");
            if(!jobSeekerDTO.getJobSeeker().getSex().equals(sex)){
                jobSeekerDTO.getJobSeeker().setSex("1");
            }
        }
        if(!jobSeekerDTO.getJobName().equals(job_type)){
            jobSeekerDTO.setJobName(job_type);
        }
        if (!jobSeekerDTO.getJobSeeker().getEmail().equals(email)){
            jobSeekerDTO.getJobSeeker().setEmail(email);
        }
        jobSeekerDTO.setUsers(users);
        //更新完成后重新往session里保存
        if(jobSeekerService.seekerUpdate(jobSeekerDTO)){
            httpSession.setAttribute("user",users);
            httpSession.setAttribute("jobSeeker",jobSeekerDTO);
            map.put("flag","true");
            return JSON.toJSONString(map);
        }
        return JSON.toJSONString(map.put("flag","false"));
    }
	/**
     * 查询求职者信息
     * @return
     */
    @RequestMapping("/seeker_select")
    @ResponseBody
    public String seekerSelect(HttpSession httpSession) {
        //获取session里保存的用户信息
        Users users = (Users) httpSession.getAttribute("user");
        //通过用户id获取求职者信息
        JobSeekerDTO jobSeekerDTO = jobSeekerService.seekerSelect(users.getUid());
        Map<String, Object> map = new HashMap<>();
        if (jobSeekerDTO != null) {
            if (jobSeekerDTO.getJobSeeker().getSex().equals("1")) {
                jobSeekerDTO.getJobSeeker().setSex("女");
            } else {
                jobSeekerDTO.getJobSeeker().setSex("男");
            }
            //分别向前端传求职者信息和往session中保存求职者信息
            map.put("flag", "true");
            map.put("jobSeekerDTO", jobSeekerDTO);
            httpSession.setAttribute("jobSeeker", jobSeekerDTO);
            return JSON.toJSONString(map);
        }
        map.put("flag", "false");
        map.put("jobSeeker", "null");
        return JSON.toJSONString(map);
    }
```

> 照片上传：re_web/src/main/java/com/recruitment/controller/UpController.java

​		文件上传理论上能够同时接收，但有可能会出现个别文件不一定会被上传，所以区分了三个接口，理论上要对方发进行封装，这里直接进行了复制。

```java
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
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName = "";
        if ("jpg".equals(type)) {
            imgName = sdf.format(new Date()) +users.getUser_name() + "head.jpg";
        } else if ("png".equals(type)) {
            imgName = sdf.format(new Date()) +users.getUser_name()+ "head.png";
        } else if ("jpeg".equals(type)) {
            imgName = sdf.format(new Date())+users.getUser_name()+ "head.jpeg";
        } else if ("gif".equals(type)) {
            imgName = sdf.format(new Date()) +users.getUser_name()+ "head.gif";
        } else {
            return null;
        }
        //将文件流写入到磁盘中
        FileUtils.writeByteArrayToFile(new File(dir, imgName), file.getBytes());
        //返回文件路径
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("uploadUrl","http://localhost:8080/upload/"+imgName);
        jobSeekerService.seekerFileUpdate("head","http://localhost:8080/upload/"+imgName,users );
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
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
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
        jobSeekerService.seekerFileUpdate("resume","http://localhost:8080/upload/"+imgName,users );
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
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
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
        jobSeekerService.seekerFileUpdate("education","http://localhost:8080/upload/"+imgName, users);
        return JSON.toJSONString(map);
    }
```



> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/JobSeekerServiceImpl.java

```java
	/**
     * 根据id查询求职者信息
     * @param uid
     * @return
     */
    @Override
    public JobSeekerDTO seekerSelect(long uid) {
        return jobSeekerMapper.seekerSelect(uid);
    }
	/**
     * 根据id修改数据
     *
     * @param jobSeeker@return
     */
    @Override
    public boolean seekerUpdate(JobSeekerDTO jobSeeker) {
        Contant contant =new Contant();
    	jobSeeker.getJobSeeker().setJob_type(contant.getPost2M().get(jobSeeker.getJobName()));
        jobSeekerMapper.updateAll(jobSeeker);
        return true;
    }
	/**
     * 文件上传
     * @param type
     * @param address
     * @param users
     * @return
     */
    @Override
    public boolean seekerFileUpdate(String type, String address, Users users) {
        jobSeekerMapper.updatePhotoAndEducationAndResume(address,type, users.getUid());
        return true;
    }
```

> 持久层：re_dao/src/main/java/com/recruitment/dao/mapper/JobSeekerMapper.java

```xml
MyBatis联合查询
	/**
     * 根据用户id查询求职者信息
     * @param uid
     * @return
     */
	<resultMap id="seekerIDMap" type="com.recruitment.dao.dto.JobSeekerDTO">
        <id property="jobSeeker.sid" column="sid" jdbcType="BIGINT"/>
        <result property="jobSeeker.photo" column="photo" jdbcType="VARCHAR"/>
        <result property="jobSeeker.education" column="education" jdbcType="VARCHAR"/>
        <result property="jobSeeker.resume" column="resume" jdbcType="VARCHAR"/>
        <result property="jobSeeker.edu_phone" column="edu_phone" jdbcType="VARCHAR"/>
        <result property="jobSeeker.name" column="name" jdbcType="VARCHAR"/>
        <result property="jobSeeker.sex" column="sex" jdbcType="VARCHAR"/>
        <result property="jobSeeker.email" column="email" jdbcType="VARCHAR"/>
        <result property="jobName" column="job2_name" jdbcType="VARCHAR"/>
    </resultMap>
    <select id="seekerSelect" resultMap="seekerIDMap">
        SELECT
            job_seeker.sid,
            job_seeker.photo,
            job_seeker.education,
            job_seeker.resume,
            job_seeker.edu_phone,
            job_seeker.`name`,
            job_seeker.sex,
            job_seeker.email,
            job2.job2_name
        FROM
            job_seeker
                INNER JOIN
            job2
            ON
                job_seeker.job_type = job2.j_id
        where uid=#{uid}
    </select>
	/**
     * 应聘者信息更新
     * @param jobSeeker
     */
	<update id="updateAll">
        UPDATE 
        `re`.`job_seeker`
        SET 
        `education` = #{jobSeeker.education}, 
        `job_type` = #{jobSeeker.job_type},
        `name` = #{jobSeeker.name},
        `sex` = #{jobSeeker.sex}, 
        `email` = #{jobSeeker.email}
        WHERE 
        `sid` = #{jobSeeker.sid}
    </update>
	/**
     * 招聘者文件更新
     */
 	<update id="updatePhotoAndEducationAndResume">
        UPDATE `re`.`job_seeker`
        <set>
            <if test="type=='head'">//判断类型进行sql拼接
                photo=#{address} where uid=#{uid}
            </if>
            <if test="type=='resume'">
                resume=#{address} where uid=#{uid}
            </if>
            <if test="type == 'education'">
                edu_phone=#{address} where uid=#{uid}
            </if>
        </set>
    </update>
```

##### 修改密码

> 控制层：re_web/src/main/java/com/recruitment/controller/UsersConller.java

```java
 /**
     * 更新密码
     *
     * @param newPassword
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping("/password_update")
    @ResponseBody
    public String updatePassword(String newPassword, String password, HttpSession httpSession) {
        //交给业务层处理
        Boolean flag = usersService.updatePassword(newPassword, password, (Users)httpSession.getAttribute("user"));
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", flag);
        return JSON.toJSONString(map);
    }
```

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/UsersServiceImpl.java

```java
	/**
     * 更新密码
     *
     * @param newPassword
     * @param password
     * @param users
     * @return boolean
     */
    @Override
    public boolean updatePassword(String newPassword, String password, Users users) {
        //判断旧密码是否一致
        if (password.equals(users.getPassword())) {
            //一致更新密码
            usersMapper.updatePassword(users, newPassword);
            return true;
        }
        return false;
    }
```



> 持久层：re_dao/src/main/resources/com/recruitment/dao/mapper/UsersMapper.xml

```xml
 	<update id="updatePassword">
        UPDATE `re`.`users` SET `password` = #{newPassword} WHERE `uid` = #{users.uid}
    </update>
```



#### 第三部分：企业排行

企业排行是可以从数据库开始查询数据自动根据评价分数进行排序，只不过需要自己重新给他们标个序号

> DTO：re_dao/src/main/java/com/recruitment/dao/dto/EnterpriseDTO.java

```java
package com.recruitment.dao.dto;

import com.recruitment.dao.domain.Enterprise;

public class EnterpriseDTO {
    private Enterprise enterprise;//企业信息
    private String number;//排序后的序号

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

```




> 控制层：re_web/src/main/java/com/recruitment/controller/EnterpriseController.java

```java
	/**
     * 企业排名查询
     *
     * @return
     */
    @RequestMapping("/enterprise_paiming_select")
    @ResponseBody
    public String selectByScale() {
        //根据分数排序获得
        List<Enterprise> list = enterpriseService.selectByScale();
        Map<String, Object> map = new HashMap<>();
        List<EnterpriseDTO> list1 = new ArrayList<>();
        int i = 0;
        //循环封装数据，对排序号的数据进行标号
        for (Enterprise enterprise : list) {
            i++;
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setEnterprise(enterprise);
            enterpriseDTO.setNumber(String.valueOf(i));//标号
            list1.add(enterpriseDTO);
            map.put("data", list1);
        }
        return JSON.toJSONString(map);
    }
```

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/EnterpriseServiceImpl.java

```java
 	/**
     * 根据得分排序获得
     * @return
     */
    @Override
    public List<Enterprise> selectByScale() {
        return enterpriseMapper.selectByEvaluation();//查询数据库
    }
```

> 持久层：re_dao/src/main/resources/com/recruitment/dao/mapper/EnterpriseMapper.xml

```xml
<select id="selectByEvaluation" resultType="com.recruitment.dao.domain.Enterprise">
    SELECT
        enterprise.ename,
        enterprise.address,
        enterprise.eva_scale,
        enterprise.eid
    FROM
        enterprise
    ORDER BY
        enterprise.eva_scale DESC
</select>
```

#### 综合搜索

综合搜索对应着两个部分一个是企业搜索，一个是职位的搜索

其实两个控制层逻辑相同，我们只需要让前端传一个type来区分用户使用是哪一种搜索模式

> 控制层：re_web/src/main/java/com/recruitment/controller/QueryController.java

```java
@Autowired
Queryimpl queryimpl;
@RequestMapping("/query")
@ResponseBody
public String query(String message,String se_type,String city ){
    List<QueryAll> list = queryimpl.select(message,se_type,city);//se_type来区分用户搜索方式，交给业务层调用不同的持久层获取数据
   return JSON.toJSONString(list);
}
```


##### 企业搜索
> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/Queryimpl.java

```java
/**
 * 综合查询
 * @param message
 * @param se_type
 * @param type
 * @return
 */
@Override
public List<QueryAll> select(String message, String se_type, String type) {

    List<QueryAll> list = new ArrayList<>();
    if(se_type.equals("1")){//判断为企业搜索
       for(Enterprise enterprise: enterpriseMapper.selectAllByEnameAndEtype(message,type)){
           QueryAll queryAll = new QueryAll();
           //对企业搜索信息进行封装
           queryAll.setName(enterprise.getEname());
           queryAll.setAddress(enterprise.getAddress());
           queryAll.setOther(enterprise.getE_email());
           queryAll.setEid(enterprise.getEid());
           list.add(queryAll);
       }
       return list;
    }else{//否则为职位搜索
        for(ReinfoDTO reInfo :reInfoMapper.selectAll(message,type,null)) {
            //对职位信息进行封装
            QueryAll queryAll = new QueryAll();
            queryAll.setName(reInfo.getReInfo().getJob_name());
            queryAll.setAddress(reInfo.getReInfo().getFull_path());
            queryAll.setOther(String.valueOf(reInfo.getReInfo().getScale()));
            queryAll.setRid(reInfo.getReInfo().getRid());
            list.add(queryAll);
        }
    }
    return list;
}
```

> 持久层：re_dao/src/main/resources/com/recruitment/dao/mapper/EnterpriseMapper.xml

```xml
	<select id="selectAllByEnameAndEtype" resultType="com.recruitment.dao.domain.Enterprise">
        SELECT
            enterprise.ename,
            enterprise.e_email,
            enterprise.address,
            enterprise.eid
        FROM
            enterprise
        <where> 
            <!----三种企业搜索模式：1.根据企业名称模糊查询 2.企业地址查询 3.两者都用的查询模式----->
            <if test="message != null and message != ''">
                <!----根据企业名称模糊查询----->
                AND enterprise.ename LIKE concat('%',#{message},'%')
            </if>
            <if test="type != null and type != ''">
               	<!-------企业地址查询--------->
                AND enterprise.address = #{type}
            </if>
        </where>
    </select>
```

##### 职位搜索

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/Queryimpl.java

```java
/**
 * 综合查询
 * @param message
 * @param se_type
 * @param type
 * @return
 */
@Override
public List<QueryAll> select(String message, String se_type, String type) {

    List<QueryAll> list = new ArrayList<>();
    if(!type.equals("*")) {//判断用户是否选择全国
           type="";//让type内容为空
       }
    if(se_type.equals("1")){//判断为企业搜索
       for(Enterprise enterprise: enterpriseMapper.selectAllByEnameAndEtype(message,type)){
           QueryAll queryAll = new QueryAll();
           //对企业搜索信息进行封装
           queryAll.setName(enterprise.getEname());
           queryAll.setAddress(enterprise.getAddress());
           queryAll.setOther(enterprise.getE_email());
           queryAll.setEid(enterprise.getEid());
           list.add(queryAll);
       }
    }else{//否则为职位搜索
        for(ReinfoDTO reInfo :reInfoMapper.selectAll(message,type,null)) {
            //对职位信息进行封装
            QueryAll queryAll = new QueryAll();
            queryAll.setName(reInfo.getReInfo().getJob_name());
            queryAll.setAddress(reInfo.getReInfo().getFull_path());
            queryAll.setOther(String.valueOf(reInfo.getReInfo().getScale()));
            queryAll.setRid(reInfo.getReInfo().getRid());
            list.add(queryAll);
        }
    }
    return list;
}
```

> 持久层:re_dao/src/main/resources/com/recruitment/dao/mapper/ReInfoMapper.xml

> 注意：这里涉及多表联合查询,此搜索包含了根据名称搜索，根据地址搜索，根据id搜索，不带条件查询

```xml
<resultMap id="ReindoMap" type="com.recruitment.dao.dto.ReinfoDTO">
    <id property="reInfo.rid" column="re_info.rid" jdbcType="BIGINT"/>
    <result property="reInfo.rid" column="rid" jdbcType="BIGINT"/>
    <result property="reInfo.eid" column="eid" jdbcType="BIGINT"/>
    <result property="reInfo.addtime" column="addtime" jdbcType="TIMESTAMP"/>
    <result property="reInfo.job_name" column="job_name" jdbcType="VARCHAR"/>
    <result property="reInfo.re_number" column="re_number" jdbcType="VARCHAR"/>
    <result property="reInfo.re_education" column="re_education" jdbcType="VARCHAR"/>
    <result property="reInfo.requirement" column="requirement" jdbcType="VARCHAR"/>
    <result property="reInfo.full_path" column="full_path" jdbcType="VARCHAR"/>
    <result property="reInfo.job_welfare" column="job_welfare" jdbcType="VARCHAR"/>
    <result property="reInfo.job_time" column="job_time" jdbcType="VARCHAR"/>
    <result property="reInfo.scale" column="scale" jdbcType="DECIMAL"/>
    <result property="job2Name" column="job2_name" jdbcType="VARCHAR"/>
    <result property="ename" column="ename" jdbcType="VARCHAR"/>
</resultMap>
<select id="selectAll" resultMap="ReindoMap">
    SELECT
        re_info.rid,
        re_info.eid,
        re_info.addtime,
        re_info.job_name,
        re_info.re_number,
        re_info.re_education,
        re_info.requirement,
        re_info.full_path,
        re_info.job_welfare,
        re_info.job_time,
        re_info.scale,
        re_info.experience,
        job2.job2_name,
        enterprise.ename
    FROM
        re_info
            INNER JOIN
        job2
        ON
            re_info.job2_type = job2.j_id
            INNER JOIN
        enterprise
        ON
            re_info.eid = enterprise.eid
        <where>
            <!---名称模糊搜索--->
            <if test="name != null and name != ''">
                AND re_info.job_name LIKE concat('%',#{name},'%')
            </if>
            <!---地域搜索--->
            <if test="address != null and address != ''">
                AND re_info.full_path = #{address}
            </if>
   			 <!---通过id搜索--->
   		 	<if test="rid !=null and rid !='' ">
        		AND re_info.rid = #{rid}
    		</if>
    	</where>
</select>
```



#### 第四部分：首页推荐

首页推荐将数据发给前端即可

> 控制层：re_web/src/main/java/com/recruitment/controller/ReinfoConller.java

```java
/**
 * 查询全部
 * @return
 */
@RequestMapping(value = "/reinfoAll",method = RequestMethod.POST)
@ResponseBody
public String reinfoSelect(){
    //获取数据
    List<ReinfoDTO> list =reInfoService.selectALL();
    return JSON.toJSONString(list);
}
```

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/ReInfoServiceImpl.java

```java
/**
 * 不带条件全部查询
 * @return
 */
@Override
public List<ReinfoDTO> selectALL() {
    //不带条件查询
    return reInfoMapper.selectAll("","",null);
}
```

> 持久层:re_dao/src/main/resources/com/recruitment/dao/mapper/ReInfoMapper.xml

> 注意：这里涉及多表联合查询,此搜索包含了根据名称搜索，根据地址搜索，根据id搜索,不带条件查询

```xml
<resultMap id="ReindoMap" type="com.recruitment.dao.dto.ReinfoDTO">
    <id property="reInfo.rid" column="re_info.rid" jdbcType="BIGINT"/>
    <result property="reInfo.rid" column="rid" jdbcType="BIGINT"/>
    <result property="reInfo.eid" column="eid" jdbcType="BIGINT"/>
    <result property="reInfo.addtime" column="addtime" jdbcType="TIMESTAMP"/>
    <result property="reInfo.job_name" column="job_name" jdbcType="VARCHAR"/>
    <result property="reInfo.re_number" column="re_number" jdbcType="VARCHAR"/>
    <result property="reInfo.re_education" column="re_education" jdbcType="VARCHAR"/>
    <result property="reInfo.requirement" column="requirement" jdbcType="VARCHAR"/>
    <result property="reInfo.full_path" column="full_path" jdbcType="VARCHAR"/>
    <result property="reInfo.job_welfare" column="job_welfare" jdbcType="VARCHAR"/>
    <result property="reInfo.job_time" column="job_time" jdbcType="VARCHAR"/>
    <result property="reInfo.scale" column="scale" jdbcType="DECIMAL"/>
    <result property="job2Name" column="job2_name" jdbcType="VARCHAR"/>
    <result property="ename" column="ename" jdbcType="VARCHAR"/>
</resultMap>
<select id="selectAll" resultMap="ReindoMap">
    SELECT
        re_info.rid,
        re_info.eid,
        re_info.addtime,
        re_info.job_name,
        re_info.re_number,
        re_info.re_education,
        re_info.requirement,
        re_info.full_path,
        re_info.job_welfare,
        re_info.job_time,
        re_info.scale,
        re_info.experience,
        job2.job2_name,
        enterprise.ename
    FROM
        re_info
            INNER JOIN
        job2
        ON
            re_info.job2_type = job2.j_id
            INNER JOIN
        enterprise
        ON
            re_info.eid = enterprise.eid
        <where>
            <!---名称模糊搜索--->
            <if test="name != null and name != ''">
                AND re_info.job_name LIKE concat('%',#{name},'%')
            </if>
            <!---地域搜索--->
            <if test="address != null and address != ''">
                AND re_info.full_path = #{address}
            </if>
   			 <!---通过id搜索--->
   		 	<if test="rid !=null and rid !='' ">
        		AND re_info.rid = #{rid}
    		</if>
    	</where>
</select>
```

#### 第五部分：职位详情页

此部分是依托于id查询

> 控制层:re_web/src/main/java/com/recruitment/controller/ReinfoConller.java

```java
/**
 * 根据id查询
 */
@RequestMapping(value = "/reinfoAllByID")
@ResponseBody
public String reinfoSelectById(Long rid){
    //将id传递给业务层
    List<ReinfoDTO> list =reInfoService.selectALLById(rid);
    //数据肯定只有一条所以使用list.get(0)获取list第一条数据
    return JSON.toJSONString(list.get(0));
}
```

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/ReInfoServiceImpl.java

```java
/**
 * id查询
 */
@Override
public List<ReinfoDTO> selectALLById(Long rid) {
    return reInfoMapper.selectAll("","",rid);
}
```

> 持久层：跟第四部分，第三部分的职位搜索共用一套代码

#### 第六部分：公司详情页

此部分分为，企业详情，企业评价

##### 企业详情

> 控制层：re_web/src/main/java/com/recruitment/controller/EnterpriseController.java

```java
@RequestMapping("/enterprise_select")
@ResponseBody
public String selectByRid(String rid) {
    String type;
    String a;
    //由于前端没办法分离数据只能靠一些特殊标识符来确定使用那种查询
    //在id前带e的都是靠企业id进行查询，否则都是靠招聘信息id进行查询
    if (rid.contains("e")) {
        a=rid.substring(1);
        type="teshu";
    }else {
        a=rid;
        type="putong";
    }
    Enterprise enterprise = enterpriseService.selectByRid(Long.valueOf(a), type);
    return JSON.toJSONString(enterprise);
}
```

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/EnterpriseServiceImpl.java

```java
@Override
public Enterprise selectByRid(Long rid,String type) {
    return enterpriseMapper.selectAllByRid(rid,type);
}
```

> 持久层：

```xml
<select id="selectAllByRid" resultType="com.recruitment.dao.domain.Enterprise">
    SELECT
        enterprise.ename,
        enterprise.scale,
        enterprise.media,
        enterprise.e_introduce,
        enterprise.e_email,
        enterprise.ephone,
        enterprise.photo,
        enterprise.address,
        enterprise.eid
    FROM
        enterprise
			<!---使用rid做联合查询---->
          <if test="type == 'putong'.toString()">
              LEFT JOIN
              re_info
              ON
              enterprise.eid = re_info.eid

              where re_info.rid = #{rid}

          </if>
   			<!---使用rid做eid查询---->
          <if test="type == 'teshu'.toString()">
             where enterprise.eid = #{rid}
          </if>
</select>
```

##### 评论信息

分为两个部分一个是用来保存评论以及计算分数的还有一个就是查询

>控制层：re_web/src/main/java/com/recruitment/controller/EvaluationController.java

```java
/**
 * 查询
 */
@RequestMapping("/evaluation_select")
@ResponseBody
public String eva_select(String eid){
    String a;
    //和企业详情一样，这次获取的是企业id
    if (eid.contains("e")) {
        a=eid.substring(1);
    }else {
        Enterprise enterprise = enterpriseService.selectByRid(Long.valueOf(eid), "putong");
        a= String.valueOf(enterprise.getEid());
    }
    //利用企业id进行查询
    List<EvaluationDTO> evaluations = evaluationService.evaAllSelect(Long.valueOf(a));
    return JSON.toJSONString(evaluations);
}
/**
 * 添加平均分数
 * @return
 */
@RequestMapping("/evaluation_insert")
@ResponseBody
public String eva_insert(String ename, String score, String textarea, HttpSession httpSession){
    //利用session取得用户信息
    Users users = (Users) httpSession.getAttribute("user");
    //开始对数据进行封装
    Evaluation evaluation =new Evaluation();
    Date date = new Date();
    evaluation.setContent(textarea);
    evaluation.setScore(score);
    evaluation.setType("0");
    evaluation.setAddtime(date);
    evaluation.setUid(users.getUid());
    //获取eid
    Long eid=queryimpl.select(ename,"1",null).get(0).getEid();
    //插入评论信息
    evaluationService.evaAllInsert(eid,evaluation);
    //插入平均分
    evaluationService.avg(eid);
    Map<String,Boolean> map = new HashMap<>();
    map.put("flag",true);
    return JSON.toJSONString(map);
}
```

> 业务层：re_biz/src/main/java/com/recruitment/biz/service/impl/EvaluationServiceImpl.java

```java
/**
 * 根据eid查询评论信息
 * @param eid
 * @return
 */
@Override
public List<EvaluationDTO> evaAllSelect(Long eid) {
    return evaluationMapper.selectAllByEid(eid);
}

/**
 * 保存评论信息
 * @param eid
 * @param evaluation
 */
@Override
public void evaAllInsert(Long eid, Evaluation evaluation) {
    evaluationMapper.insertAll(eid,evaluation);
}

/**
 * 计算平均分并保存
 * @param eid
 */
@Override
public void avg(Long eid) {
    String c = evaluationMapper.selectAvg();//获取平均分
    evaluationMapper.updateAvg(c.substring(0,4),eid);//截取平均分并保存
}
```

> 持久层:re_biz/src/main/java/com/recruitment/biz/service/impl/EvaluationServiceImpl.java

```xml
<!---查询--->
<resultMap id="selectEvaluation" type="com.recruitment.dao.dto.EvaluationDTO">
    <id property="evaluation.id" column="id" jdbcType="BIGINT"/>
    <result property="evaluation.content" column="content" jdbcType="VARCHAR"/>
    <result jdbcType="VARCHAR" property="evaluation.type" column="type"/>
    <result jdbcType="BIGINT" column="id" property="evaluation.id"/>
    <result property="userName" column="user_name"/>
    <result property="evaluation.score" column="score"/>
</resultMap>
<select id="selectAllByEid" resultMap="selectEvaluation">
    SELECT
        users.user_name,
        evaluation.content,
        evaluation.type,
        evaluation.score
    FROM
        evaluation
            INNER JOIN
        users
        ON
            evaluation.uid = users.uid
    WHERE
        evaluation.eid = #{eid} AND evaluation.type="1"
</select>
<!---录入---->
<insert id="insertAll">
        INSERT INTO `re`.`evaluation` (`eid`, `content`, `score`, `addtime`, `uid`,`type`) VALUES (#{eid},#{evaluation.content},#{evaluation.score},#{evaluation.addtime},#{evaluation.uid},#{evaluation.type})
</insert>
<!-----计算平均值----->
<select id="selectAvg" resultType="java.lang.String">
        SELECT AVG(evaluation.score) FROM evaluation
</select>
<!----保存平均值------>
<update id="updateAvg">
        UPDATE `re`.`enterprise` SET `eva_scale` = #{avg} WHERE `eid` = #{eid}
</update>
```



