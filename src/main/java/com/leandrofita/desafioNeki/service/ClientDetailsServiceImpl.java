package com.leandrofita.desafioNeki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leandrofita.desafioNeki.model.Client;
import com.leandrofita.desafioNeki.repositories.ClientRepository;
import com.leandrofita.desafioNeki.security.ClientSpringSecurity;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClientRepository repo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Client client = repo.findByLogin(login);
		if (client == null) {
			throw new UsernameNotFoundException(login);
		}
		
		return new ClientSpringSecurity(client.getId(), client.getLogin(), client.getPassword(), client.getProfile());
	}

}
