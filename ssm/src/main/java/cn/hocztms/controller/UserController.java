package cn.hocztms.controller;

import cn.hocztms.domin.Account;
import cn.hocztms.domin.Folder;
import cn.hocztms.domin.Picture;
import cn.hocztms.service.AccountService;
import cn.hocztms.service.FolderService;
import cn.hocztms.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private FolderService folderService;

    //用户注册方法 已经测试
    @RequestMapping("/register")
    public void register(@RequestBody Account account, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");

        System.out.println("-------------------------------------------------------");
        System.out.println(account.getUsername()+"正在注册...");
        System.out.println("-------------------------------------------------------");


        try {
            System.out.println(account.toString());
            String example = accountService.findusername(account.getUsername());
            if (example != null) {
                response.getWriter().println("注册失败已经有该用户");
            } else {
                accountService.saveAccount(account);
                response.getWriter().printf("ok");
            }
        }catch (Exception e){
            response.getWriter().printf("注册失败");
        }
        System.out.println();

    }

    //用户登录方法 已经测试
    @RequestMapping("/signin")
    public void signin(@RequestBody Account account, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println(account.getUsername()+"正在登录...");
        System.out.println(account.toString());
        System.out.println("-------------------------------------------------------");


        try {
            String example = accountService.findpassword(account.getUsername());
            if (example.equals(account.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("username", account.getUsername());
                String user = (String) request.getSession().getAttribute("username");
                System.out.println(user + "已登录");
                response.getWriter().printf("ok");
            } else {
                System.out.println("用户:" + account.getUsername() + "登录失败");
                response.getWriter().printf("登入失败用户名错误");
            }
        }catch (Exception e){
            response.getWriter().printf("登入失败没有该用户");
        }

        System.out.println();
    }

    //用户注销   已经测试
    @RequestMapping("/signout")
    public void  signout(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String user = (String) request.getSession().getAttribute("username");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println(user + "正在注销");
        System.out.println("-------------------------------------------------------");


        try {
            response.getWriter().printf("ok");
            request.getSession().removeAttribute("username");
        }catch (Exception e){
            response.getWriter().printf("注销失败");
        }

        System.out.println();
    }

    //用户文件上传  已经测试
    @RequestMapping("/upload")
    public void upload(String id,String username,HttpServletRequest request, HttpServletResponse response, MultipartFile upload,String filename_upload) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println(username+"文件上传");


        try {
            String path = request.getSession().getServletContext().getRealPath("/uploads/");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String filename = upload.getOriginalFilename();

            //设置唯一文件名
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + "_" + filename;

            upload.transferTo(new File(path, filename));

            //获取现在时间
            LocalDate date = LocalDate.now();
            Picture picture = new Picture(id,username,date,filename,0);
            System.out.println(picture.toString());
            pictureService.insert(picture);
            response.getWriter().printf("ok");
        }
        catch (Exception e){
            response.getWriter().printf("上传失败");
        }


        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    //用户文件下载  已经测试
    @RequestMapping("/download")
    public void download(String picturename, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println(picturename+"正在下载");
        System.out.println("-------------------------------------------------------");



        if (picturename ==null) {
            response.getWriter().printf("无效文件名");
            return;
        }
        try {
            String path = "C:\\Users\\11075\\IdeaProjects\\ssm\\target\\ssm\\uploads"+"\\"+picturename;
            InputStream bis = new BufferedInputStream(new FileInputStream(path));
            picturename = URLEncoder.encode(picturename, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + picturename);
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len;
            while ((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
            out.close();
        }catch (Exception e){
            response.getWriter().printf("下载失败");
        }

        System.out.println();
    }

    //用户删除图片
    @RequestMapping("/deletep")
    public void deletep(String picturename, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("deletep执行了。。。。");
        System.out.println(picturename);
        System.out.println("-------------------------------------------------------");


        try {
            pictureService.delete(picturename);
            response.getWriter().printf("ok");
        } catch (Exception e) {
            response.getWriter().printf("删除失败");
        }

        System.out.println();
    }


    //用户删除文件夹
    @RequestMapping("/deletec")
    public void deletec(String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("deletec执行了。。。。。。");
        System.out.println(id);
        System.out.println("-------------------------------------------------------");


        try {
            Folder folder = folderService.findfolder_id(id);
            folderService.deleteFolder(folder);
            response.getWriter().printf("ok");
        } catch (Exception e) {
            response.getWriter().printf("删除失败");
        }

        System.out.println();
    }

    //用户创建文件夹
    @RequestMapping("/cfile")
    public void cfile(@RequestBody Folder folder,HttpServletResponse response) throws  Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("cfile执行了。。。。");
        System.out.println(folder.toString());
        System.out.println("-------------------------------------------------------");


        try{
            String checkfolder = folderService.checkfolder(folder.getUsername(), folder.getFid(), folder.getFilename());
            if (checkfolder==null) {
                folderService.insertfolder(folder);
                response.getWriter().printf("ok");
            }
            else {
                response.getWriter().printf("已经有该文件名");
            }
        }catch (Exception e){
            response.getWriter().printf("创建失败");
        }

        System.out.println();
    }

    //用户更改文件夹名
    @RequestMapping("/updatefile")
    public void updatefile(@RequestBody Folder folder,HttpServletResponse response) throws  Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("updatefile执行了。。。。");
        System.out.println(folder.toString());
        System.out.println("-------------------------------------------------------");


        try{
            String checkfolder = folderService.checkfolder(folder.getUsername(), folder.getFid(), folder.getFilename());
            if (checkfolder==null) {
                folderService.updatefilename(folder.getId(),folder.getFilename());
                response.getWriter().printf("ok");
            }
            else {
                response.getWriter().printf("已经有该文件名");
            }
        }catch (Exception e){
            response.getWriter().printf("更新失败");
        }

        System.out.println();
    }


    //用户获取 某一文件夹下的全部文件夹  如果未一级目录 则fid应为0
    @RequestMapping(value = "/getf",produces = "application/json; charset=utf-8")
    public @ResponseBody List<Folder> getf(String username,String fid, HttpServletResponse response) throws  Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("getf执行了。。。。");
        System.out.println("username:"+username);
        System.out.println("fid:"+fid);
        System.out.println("-------------------------------------------------------");

        System.out.println();


        try{
            return folderService.finduserfolder(username,fid);
        }catch (Exception e){
            response.getWriter().printf("查找失败");
            return null;
        }


    }

    //用户获取某一文件夹下全部图片
    @RequestMapping("/getp")
    public  @ResponseBody List<String> getp(String id,HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        System.out.println("-------------------------------------------------------");
        System.out.println("getp执行了。。。。");
        System.out.println("id="+ id);
        System.out.println("-------------------------------------------------------");

        System.out.println();

        try{
            return  pictureService.getp3(id);
        }catch (Exception e){
            response.getWriter().printf("失败");
            return  null;
        }
    }
}
