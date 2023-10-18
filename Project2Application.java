package com.example.project2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project2Application {

    @Bean
    public CommandLineRunner databaseSeeder(EmployeeRepository repository){
        return (args) -> {
            repository.save(new Employee("Joe", "He's a guy named Joe", "Joe.jpg"));
            repository.save(new Employee("Steve", "He's a guy named Steve", "Steve.jpg"));
            repository.save(new Employee("Tim", "He's a guy named Tim", "Tim.jpg"));
            repository.save(new Employee("Jane", "She's a girl named Jane", "Jane.jpg"));
            repository.save(new Employee("Lisa", "She's a girl named Lisa", "Lisa.jpg"));
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

}
