package com.xc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class discoveryServer {

    public static void main(String[] args) {
        SpringApplication.run(discoveryServer.class,args);
    }
}
