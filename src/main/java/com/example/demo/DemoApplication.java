package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.execute("CREATE TABLE jpa.users (id BIGSERIAL PRIMARY KEY, " +
                "username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL)");

        SpringApplication.run(DemoApplication.class, args);
    }

}
