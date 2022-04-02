package com.example.crudbasic;

import com.example.crudbasic.user.User;
import com.example.crudbasic.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase  (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew (){
        User user = new User();
        user.setEmail("mancuong2002@gmail.com");
        user.setPassword("12345");
        user.setFirstName("Phan Manh");
        user.setLastName("Cuong");

        User savedUser = repo.save(user);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser= repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("150402");
        repo.save(user);

        User updateUser = repo.findById(userId).get();
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);
        Optional<User> optionalUser= repo.findById(userId);
    }

}

