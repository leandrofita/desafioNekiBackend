package com.leandrofita.desafioNeki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandrofita.desafioNeki.DTO.LoginRequestDTO;
import com.leandrofita.desafioNeki.DTO.LoginResponseDTO;
import com.leandrofita.desafioNeki.service.ClientService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ClientService clientService;
	
		
	@PostMapping
	public LoginResponseDTO login (@RequestBody LoginRequestDTO request) {		
		return clientService.handleLogin(request.getLogin(), request.getPassword());
	}
		
}
