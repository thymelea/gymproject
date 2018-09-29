package com.example.gymproject.service;

import com.example.gymproject.domain.Admin;
import com.example.gymproject.domain.Relation;
import com.example.gymproject.domain.repository.AdminReposityInter;
import com.example.gymproject.domain.repository.RelationReposityInter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service("relationService")
public class RelationService {
    @Autowired
    private RelationReposityInter relationReposityInter;

    @Transactional
    public void save(Relation relation){
        relationReposityInter.save(relation);
    }

}
