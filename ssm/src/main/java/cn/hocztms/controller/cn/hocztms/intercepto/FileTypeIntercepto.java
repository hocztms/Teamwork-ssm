package cn.hocztms.controller.cn.hocztms.intercepto;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileTypeIntercepto implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("FileTypeIntercepto执行了。。。。");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("upload");
        String filename=multipartFile.getOriginalFilename();
        if (checkFileType(filename)) {
            return true;
        }
        else {
            System.out.println("FileTypeIntercepto拦截了.。。。");
            response.getWriter().println("无效文件类型");
            return false;
        }
    }


    private  boolean checkFileType(String filename){
        String suffixList = "JPG,JPEG,PNG,GIF,BMP,jpg,jpeg,png,gif,bmp";
        String suffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());
        System.out.println("文件格式"+suffix);
        if (suffixList.indexOf(suffix)>=0){
            return true;
        }
        return false;
    }
}
