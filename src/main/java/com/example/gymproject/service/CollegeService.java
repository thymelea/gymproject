package com.example.gymproject.service;

import com.example.gymproject.domain.College;
import com.example.gymproject.domain.repository.CollegeReposityInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("collegeService")
public class CollegeService {
@Autowired
private CollegeReposityInter collegeReposityInter;


    public List<College> findCollege(String id,String name,String address,String adID){
        if(!StringUtils.isEmpty(id)&&StringUtils.isEmpty(name)&&StringUtils.isEmpty(address)){
            List<College> collegeList =new ArrayList<>();
            collegeList.add(collegeReposityInter.findByFidAndAdminid(id,adID));
            return collegeList;
        }else if(!StringUtils.isEmpty(id)&&!StringUtils.isEmpty(name)&&StringUtils.isEmpty(address)){
            return collegeReposityInter.findByFidAndCollegenameIsLikeAndAdminid(id,name,adID);
        }else if(!StringUtils.isEmpty(id)&&!StringUtils.isEmpty(name)&&!StringUtils.isEmpty(address)){
            return collegeReposityInter.findByFidAndCollegenameIsLikeAndAddressIsLikeAndAdminid(id,name,address,adID);
        }else if(!StringUtils.isEmpty(id)&&StringUtils.isEmpty(name)&&!StringUtils.isEmpty(address)){
            return collegeReposityInter.findByFidAndAddressIsLikeAndAdminid(id,address,adID);
        }else if(StringUtils.isEmpty(id)&&!StringUtils.isEmpty(name)&&StringUtils.isEmpty(address)){
            return collegeReposityInter.findByCollegenameIsLikeAndAdminid(name,adID);
        }else if(StringUtils.isEmpty(id)&&!StringUtils.isEmpty(name)&&!StringUtils.isEmpty(address)){
            return collegeReposityInter.findByCollegenameIsLikeAndAddressIsLikeAndAdminid(name,address,adID);
        }else if(StringUtils.isEmpty(id)&&StringUtils.isEmpty(name)&&!StringUtils.isEmpty(address)){
            return collegeReposityInter.findByAddressIsLikeAndAdminid(address,adID);
        }else if(StringUtils.isEmpty(id)&&StringUtils.isEmpty(name)&&StringUtils.isEmpty(address)){
            return collegeReposityInter.findAll();
        }else{
            return null;
        }
    }
    public void save(College collegeList){
        collegeReposityInter.save(collegeList);
    }
}
