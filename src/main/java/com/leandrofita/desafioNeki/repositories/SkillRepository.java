package com.leandrofita.desafioNeki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrofita.desafioNeki.model.Skill;



/*Acessa dados e salva um objeto*/

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{

}