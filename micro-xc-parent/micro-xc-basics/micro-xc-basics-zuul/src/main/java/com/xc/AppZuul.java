package com.xc;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2Doc
@EnableEurekaClient
@EnableZuulProxy
//@EnableSwagger2
public class AppZuul {
    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class,args);
    }

    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider{

        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> list = new ArrayList<>();
            //网关使用别名获取远程swagger文档
            list.add(swaggerResource("xc-member","/xc-member/v2/api-docs","1.0"));
            list.add(swaggerResource("xc-wechat","/xc-wechat/v2/api-docs","1.0"));
            return list;
        }

        private SwaggerResource swaggerResource(String name,String local,String version){
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(local);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }
}
