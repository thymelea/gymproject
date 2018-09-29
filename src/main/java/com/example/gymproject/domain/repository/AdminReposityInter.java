package com.example.gymproject.domain.repository;

import com.example.gymproject.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminReposityInter  extends JpaRepository<Admin,String>, JpaSpecificationExecutor<Admin> {

    public Admin findByUsername(String userName);
    Admin findByFid(String fid);
}
