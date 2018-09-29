package com.example.gymproject.web;

import com.example.gymproject.domain.Admin;
import com.example.gymproject.service.AdminService;
import com.example.gymproject.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;

    private static final Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        if(!StringUtils.isEmpty(json)){
            logger.info("登陆"+json);
            ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> data = Utils.paramToMap(json);
                String username=(String)data.get("account");
                String password=(String)data.get("password");
                if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)){
                    Admin admin=adminService.findAdminByName(username);
                    if(admin!=null&&admin.getPassword().equals(password)){
                        Utils.tokenMap.put(admin.getFid(),true);
                        return "{\"msg\":\"登陆成功\",\"code\":\"0\",\"token\":\""+admin.getFid()+"\"}";
                    }
                }
            return "{\"msg\":\"登陆失败\",\"code\":\"9\"}";
        }
        return "{\"msg\":\"登陆失败\",\"code\":\"9\"}";
    }
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String registerFun(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
//        json="{\"account\":\"zhang\",\"password\":\"123\"}";
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return adminService.register(json);
//        return "{msg:\"注册成功\",code:\"0\"}";
    }
    @RequestMapping(value = {"/err"},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String errFun(HttpServletResponse response, HttpServletRequest request){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return "{\"msg\":\"操作失败\",\"code\":\"9\"}";
    }
    @RequestMapping(value = {"/logOut"}, method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String outFun(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        Map<String,Object> data = Utils.paramToMap(json);
        String token=(String)data.get("token");
        Admin admin=adminService.findAdminByFid(token);
        if(admin!=null&&Utils.tokenMap!=null&&Utils.tokenMap.get(token)!=null&&Utils.tokenMap.get(token)){
            Utils.tokenMap.remove(admin.getFid());
            return "{\"msg\":\"操作成功\",\"code\":\"0\"}";
        }

        return "{\"msg\":\"操作失败\",\"code\":\"9\"}";
    }
}
