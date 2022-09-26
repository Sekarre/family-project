package com.si.familymemberapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FamilyMemberAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyMemberAppApplication.class, args);
    }

}
