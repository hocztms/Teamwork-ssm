package cn.hocztms.controller.cn.hocztms.intercepto;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AccountIntercepto implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=UTF-8");


        try{
            HttpSession session = request.getSession();
            String user = (String) request.getSession().getAttribute("username");
            if (user == null){
                response.getWriter().printf("未登录");
                return false;
            }
            else return true;
        }catch (Exception e){
            response.getWriter().printf("登入出错");
            return false;
        }
    }
}
