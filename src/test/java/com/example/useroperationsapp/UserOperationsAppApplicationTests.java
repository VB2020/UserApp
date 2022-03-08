package com.example.useroperationsapp;

import com.example.useroperationsapp.entity.UserEntity;
import com.example.useroperationsapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserOperationsAppApplicationTests extends AbstractIntegrationTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void saving_entity_to_database_is_ok() {
        var entity = new UserEntity();
        entity.setEMail("test_eMail");
        entity.setName("test_user");
        entity.setAge(50);
        var savedEntity = userRepository.save(entity);
        Assertions.assertNotNull(savedEntity.getId());
    }

    @Test
    @Transactional
    void saving_entity_to_database_is_not_ok() {
        var entity = new UserEntity();
        entity.setEMail(null);
        entity.setName(null);
        entity.setAge(666);
        var savedEntity = userRepository.save(entity);
        Assertions.assertNotNull(savedEntity.getId());
    }

    @Test
    void contextLoads() {
    }
}