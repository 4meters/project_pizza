package com.pizzaserver.repository;

import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserTest {
    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    void testReadingUser() {
        String u = userServiceImpl.readUser("admin");
    }

}
