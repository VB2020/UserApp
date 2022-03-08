package com.example.useroperationsapp;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = UserOperationsAppApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:postgresql:13.3-alpine:///testDb",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
})
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {
}