package com.tkachenkopetr.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.tkachenkopetr.spring")
@PropertySource("classpath:taskManager.properties")
public class MyConfig {
}