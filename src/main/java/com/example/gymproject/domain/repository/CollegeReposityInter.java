package com.example.gymproject.domain.repository;

import com.example.gymproject.domain.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CollegeReposityInter  extends JpaRepository<College,String>, JpaSpecificationExecutor<College> {
    College findByFidAndAdminid(String id,String adId);
    List<College> findByCollegenameIsLikeAndAdminid(String name,String adId);
    List<College> findByAddressIsLikeAndAdminid(String address,String adId);
    List<College> findByFidAndCollegenameIsLikeAndAdminid(String id,String name,String adId);
    List<College> findByCollegenameIsLikeAndAddressIsLikeAndAdminid(String name,String address,String adId);
    List<College> findByFidAndAddressIsLikeAndAdminid(String id,String address,String adId);
    List<College> findByFidAndCollegenameIsLikeAndAddressIsLikeAndAdminid(String id,String name,String address,String adId);

    List<College> findByFidIn(List<String> ids);
}
