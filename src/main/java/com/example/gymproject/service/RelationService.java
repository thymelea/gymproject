package com.example.gymproject.service;

import com.example.gymproject.domain.Relation;
import com.example.gymproject.domain.repository.RelationReposityInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("relationService")
public class RelationService {
    @Autowired
    private RelationReposityInter relationReposityInter;

    @Transactional
    public void save(Relation relation){
        relationReposityInter.save(relation);
    }
    @Transactional
    public void delRe(String relid){
        relationReposityInter.deleteById(relid);
    }
    public Relation findRe(String relid){
        return relationReposityInter.findByFid(relid);
    }

}
