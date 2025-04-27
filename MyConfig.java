package com.tkachenkopetr.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.tkachenkopetr.spring")
@PropertySource("classpath:taskManager.properties")
public class MyConfig {

    @Bean
    @Scope("prototype")
    public User user(){
        return new User();
    }

    @Bean
    @Scope("prototype")
    public Task task(){
        return new Task();
    }

    @Bean
    @Scope("singleton")
    public TaskManager taskManager(){
        return new TaskManager();
    }

}