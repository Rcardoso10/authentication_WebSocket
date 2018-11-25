package com.vtUnitri.Websocket;

import com.vtUnitri.Websocket.domain.User;
import com.vtUnitri.Websocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WebsocketApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		//User user = new User(null, "renato", "renatotio21@hotmail.com", bCryptPasswordEncoder.encode( "123" ) );
		//userRepository.save( user );
	}
}
