package com.leandrofita.desafioNeki.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrofita.desafioNeki.model.ClientSkill;



/*Acessa dados e salva um objeto*/

@Repository
public interface ClientSkillRepository extends JpaRepository<ClientSkill, Integer>{

}