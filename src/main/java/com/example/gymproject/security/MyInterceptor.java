package com.example.gymproject.security;

import com.example.gymproject.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class MyInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取session
        String id="";
        Map<String, String[]> tokenMap=request.getParameterMap();
        for (String key : tokenMap.keySet()) {
            if("token".equals(key)){
                String[] values = tokenMap.get(key);
                for (int i = 0; i < values.length; i++) {
                    id = values[i];
                }
            }

        }
        if(Utils.tokenMap!=null&&Utils.tokenMap.get(id)!=null&&Utils.tokenMap.get(id)){
            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/err");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
