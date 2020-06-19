package com.xc;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@EnableAsync
@EnableSwagger2Doc
@EnableHystrix
//@ComponentScan("com.xc.service.Impl")
public class SpikeApp {
    public static void main(String[] args) {
        SpringApplication.run(SpikeApp.class);
    }
}
