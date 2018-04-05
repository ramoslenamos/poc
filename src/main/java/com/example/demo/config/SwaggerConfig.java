package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  /**
   * Fonction qui crée le Docket et le retourne.
   *
   * @return Docket : docket.
   */
  @Bean
  public Docket newsApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(regex("/api.*"))
            .build();
  }

  /**
   * Fonction qui retourne les informations sur l'API
   * utilise Sawgger. ../swagger-ui/index.html
   *
   * @return ApiInfo : info de l'api.
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("API Documentation")
            .description("ISTIC Datahub API documentation")
            .version("0.1")
            .build();
  }
}

