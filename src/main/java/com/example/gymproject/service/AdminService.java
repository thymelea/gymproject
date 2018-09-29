package com.example.gymproject.service;

import com.example.gymproject.domain.Admin;
import com.example.gymproject.domain.repository.AdminReposityInter;
import com.example.gymproject.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service("adminService")
public class AdminService {
    @Autowired
    private AdminReposityInter adminReposityInter;


    private static final org.slf4j.Logger logger=LoggerFactory.getLogger(AdminService.class);
    private static final ObjectMapper mapper=new ObjectMapper();
    public Admin findAdminByName(String userName){
        return adminReposityInter.findByUsername(userName);
    }
    public Admin findAdminByFid(String fid) {
        return adminReposityInter.findByFid(fid);
    }
    @Transactional
    public String register(String date){
        if(StringUtils.isEmpty(date)){
            return "{\"msg\":\"注册失败\",\"code\":\"9\"}";
        }
        logger.info("注册"+date);
//            Map<String,Object> data = mapper.readValue(date,Map.class);
            Map<String,Object> data = Utils.paramToMap(date);
            Admin admin=new Admin();
            String userName=(String)data.get("account");
            if(adminReposityInter.findByUsername(userName)!=null){
                logger.error("注册失败，用户已存在");
                return "{\"msg\":\"注册失败，用户已存在\",\"code\":\"6\"}";
            }
            String passWord=(String)data.get("password");
            admin.setFid(UUID.randomUUID().toString());
            admin.setUsername(userName);
            admin.setPassword(passWord);
            adminReposityInter.save(admin);
            return "{\"msg\":\"注册成功\",\"code\":\"0\"}";
    }
}
