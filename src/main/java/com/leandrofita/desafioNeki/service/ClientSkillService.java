package com.leandrofita.desafioNeki.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrofita.desafioNeki.DTO.ClientSkillDTOPut;
import com.leandrofita.desafioNeki.DTO.ClientSkillDTORequest;
import com.leandrofita.desafioNeki.model.ClientSkill;
import com.leandrofita.desafioNeki.repositories.ClientRepository;
import com.leandrofita.desafioNeki.repositories.ClientSkillRepository;
import com.leandrofita.desafioNeki.repositories.SkillRepository;

@Service
public class ClientSkillService {

	@Autowired
	private ClientSkillRepository repo;
	
	@Autowired
	private ClientRepository repoCli;
	
	@Autowired
	private SkillRepository repoSkill;

	public ClientSkill find(Integer id) {
		Optional<ClientSkill> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ClientSkill.class.getName(), null));
	}
	
	public List<ClientSkill> findAll() {
		return repo.findAll();
		
	}

	public ClientSkill insert(ClientSkillDTORequest obj) {
		ClientSkill clientSkill = new ClientSkill();
		clientSkill.setClient(repoCli.getById(obj.getIdClient()));
		clientSkill.setSkill(repoSkill.getById(obj.getIdSkill()));
		clientSkill.setKnowledgeLevel(obj.getKnowledgeLevel());;
		return repo.save(clientSkill);

	}
	
	public ClientSkill update(ClientSkill obj) {
		ClientSkill newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(ClientSkill newObj, ClientSkill obj) {
		newObj.setKnowledgeLevel(obj.getKnowledgeLevel());
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
		
	}
	
	public ClientSkill fromDTO(ClientSkillDTOPut objDto) {
		return new ClientSkill(objDto.getId(), null, null, objDto.getKnowledgeLevel());
	}


}