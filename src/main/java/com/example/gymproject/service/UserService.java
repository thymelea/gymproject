package com.example.gymproject.service;

import com.example.gymproject.domain.Admin;
import com.example.gymproject.domain.User;
import com.example.gymproject.domain.repository.UserReposityInter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service("userService")
public class UserService {
    private static final ObjectMapper mapper=new ObjectMapper();
    @Autowired
     private UserReposityInter userReposityInter;

    public User getUserById(String userId,String token){
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        return this.userReposityInter.findByFidAndAdminId(userId,token);
    }
    public List<User> findUIdIn(List<String> fids){
       return userReposityInter.findByFidIn(fids);
    }

    public List<User> getUserByAdmin(String adminId){
        return userReposityInter.findByAdminId(adminId);
    }
    @Transactional
    public void addUser(User user){
        userReposityInter.save(user);
    }

    public User findUserByNameAndAdminId(String name,String adminId){
        return userReposityInter.findByNameAndAdminId(name,adminId);
    }
    @Transactional
    public void delete(String  relation){
        userReposityInter.deleteById(relation);
    }
}
