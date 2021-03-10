package cn.hocztms.controller;

import cn.hocztms.domin.Account;
import cn.hocztms.service.AccountService;
import cn.hocztms.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private AccountService accountService;

    //管理员登录
    @RequestMapping("/signin")
    public void signin(@RequestBody Account account, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("admian/signin执行了。。。。。");
        System.out.println("-------------------------------------------------------");
        System.out.println();


        try {
            if (account.getUsername().equals("666666") && account.getPassword().equals("666666")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", account.getUsername());
                response.getWriter().printf("ok");
            } else response.getWriter().printf("管理员账号密码错误");
        }catch (Exception e){
            response.getWriter().printf("管理员登入失败");
        }

    }

    //管理员注销
    @RequestMapping("/signout")
    public void signout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("admian/signout执行了。。。。。");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");

        try {
            response.getWriter().printf("ok");
            request.getSession().removeAttribute("username");
        }catch (Exception e){
            response.getWriter().printf("管理员注销失败");
        }
    }

    //管理员获取全部用户名字 进行以名字来选定 获取未审核图片
    @RequestMapping(value = "/getn", produces = "application/json; charset=utf-8")
    public @ResponseBody List<String> getname() {


        System.out.println("-------------------------------------------------------");
        System.out.println();System.out.println("admian/getname执行了。。。。。");
        System.out.println("-------------------------------------------------------");
        System.out.println();


        try{
            return accountService.findallusername();
        }catch (Exception e){
            return null;
        }
    }

    //管理员获取某个用户全部未审核图片
    @RequestMapping(value = "/getp", produces = "application/json; charset=utf-8")
    public @ResponseBody List<String> getpicture(String username, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("admian/getpicture执行了。。。。。");
        System.out.println(username+"图片获取中");
        System.out.println("-------------------------------------------------------");
        System.out.println();


        int tag = 0;
        try {
            return pictureService.getp2(username, tag);
        } catch (Exception e) {
            response.getWriter().printf("失败");
            return null;
        }
    }

    //管理员删除审核不成功图片
    @RequestMapping("/delete")
    public void delete(String picturename, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("admian/delete执行了。。。。");
        System.out.println(picturename);
        System.out.println("-------------------------------------------------------");
        System.out.println();


        String path = "C:\\Users\\11075\\IdeaProjects\\ssm\\target\\ssm\\uploads" + "\\" + picturename;
        try {
            File file = new File(path);
            file.delete();
            pictureService.delete(picturename);
            response.getWriter().printf("ok");
        } catch (Exception e) {
            response.getWriter().printf("删除失败");
        }
    }

    //更新审核通过的图片
    @RequestMapping("/update")
    public void update(String picturename, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("admian/update执行了。。。。");
        System.out.println(picturename);
        System.out.println("-------------------------------------------------------");
        System.out.println();


        try {
            pictureService.updatetag(picturename,1);
            response.getWriter().printf("ok");
        } catch (Exception e) {
            response.getWriter().printf("更新失败");
        }
    }
}
