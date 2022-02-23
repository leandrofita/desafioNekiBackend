package com.leandrofita.desafioNeki.service;

import java.util.Collections;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leandrofita.desafioNeki.DTO.ClientDTO;
import com.leandrofita.desafioNeki.DTO.LoginResponseDTO;
import com.leandrofita.desafioNeki.DTO.NewClientDTO;
import com.leandrofita.desafioNeki.model.Client;
import com.leandrofita.desafioNeki.repositories.ClientRepository;
import com.leandrofita.desafioNeki.security.ClientSpringSecurity;
import com.leandrofita.desafioNeki.security.JWTUtil;


@Service
public class ClientService {
	
	private static final String headerPrefix = "Bearer ";

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
	private ClientRepository repo;

	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName(), null));
	}

	public Client insert(Client client) {
		client.setId(null);

		String password = passwordEncoder.encode(client.getPassword());
		client.setPassword(password);

		return repo.save(client);

	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getLogin(), objDto.getPassword());
	}
	
	public Client fromDTO(NewClientDTO objDto) {
		Client cli = new Client(null, objDto.getLogin(), objDto.getPassword());
		return cli;
	}

	public LoginResponseDTO handleLogin(String login, String password) {

		Authentication autenticacao = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login, password, Collections.emptyList()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		String username = ((ClientSpringSecurity) autenticacao.getPrincipal()).getUsername();

		String token = headerPrefix + jwtService.generateToken(username);

		var client = repo.findByLogin(login);

		return new LoginResponseDTO(token, client);
	}
}
