package com.blogpessoal.blogpessoal.repository;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.blogpessoal.model.Usuario;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)//Se a porta estiver sendo usada,ele cria uma aleatória
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//Mostra que a vida da classe teste, acaba quando o teste é finalizado

public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();//se tiver algo, ele apaga. Quando encerra, ele apaga tudo.
		
		usuarioRepository.save(new Usuario(0L, "João da Silva",  "joao@email.com.br", "13465278", "https://i.imgur.com/h4t8loa.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva",  "manuela@email.com.br", "13465278", "https://i.imgur.com/NtyGneo.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva",  "adriana@email.com.br", "13465278", "https://i.imgur.com/5M2p5Wb.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Paulo Antunes",  "paulo@email.com.br", "13465278","https://i.imgur.com/FETvs20.jpg"));
	}
	@Test
	@DisplayName("Retornar 1 usuário")
	public void deveRetornarUmUsuario() {
		
		Optional <Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	
	}
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}


		
	}


