/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration factory.
 *
 * @author Yadickson Soto
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Path base.
     */
    private static final String BASE_PACKAGE = "cl.github.yadickson.redis";

    /**
     * Build swagger documentation.
     *
     * @return documentation.
     */
    @Bean
    @SuppressWarnings({"rawtypes"})
    public Docket productApi() {

        List<VendorExtension> vendorExtensions = new ArrayList<>();

        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Redis  Example API",
                "Spring Boot redis",
                "1.0",
                "learning",
                new Contact("thirstybrain", "", ""),
                "",
                "",
                vendorExtensions
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.regex("/service/redis/.*"))
                .build()
                .apiInfo(apiInfo);
    }

}
