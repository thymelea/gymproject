package com.example.gymproject.domain.repository;

import com.example.gymproject.domain.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RelationReposityInter extends JpaRepository<Relation,String>, JpaSpecificationExecutor<Relation> {
}
