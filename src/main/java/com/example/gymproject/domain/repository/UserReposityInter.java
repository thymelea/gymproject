package com.example.gymproject.domain.repository;

import com.example.gymproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserReposityInter extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
        List<User> findByAdminId(String adminId);
        User findByNameAndAdminId(String name,String adminId);
        User findByFidAndAdminId(String fid,String aid);
        List<User> findByFidIn(List<String> ids);

}
