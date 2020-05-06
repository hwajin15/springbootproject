package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class FirstSpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringProjectApplication.class, args);
    }

    @Bean

    public LocaleResolver localeResolver() {
     SessionLocaleResolver localeResolver = new SessionLocaleResolver();
     localeResolver.setDefaultLocale(Locale.US);
     return localeResolver;

    }
}
