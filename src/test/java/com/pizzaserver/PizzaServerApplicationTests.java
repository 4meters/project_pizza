package com.pizzaserver;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class PizzaServerApplicationTests {

	/*public static void main(String[] args) {
		SpringApplication.run(PizzaServerApplication.class, args);
	}*/
	/*@Mock
	private TestEntityManager manager;

	@Mock
	UserRepository mockUserRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;
*/
	@Test
	void contextLoads() {
	}

	/*@Test
	void testAddingUser() {
		User2 user = new User2("chuck","233123213",null);
		UserRepositoryOld userRepositoryOld =new UserRepositoryOld();
		userRepositoryOld.registerUser(user);
	}*/
/*
	@Test
	void testReadingUser() {
		System.out.println(userServiceImpl.readUser());
	}
*/
}
