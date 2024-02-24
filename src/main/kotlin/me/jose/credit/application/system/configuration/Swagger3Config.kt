package me.jose.credit.application.system.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {
    //Anota como Bean
    @Bean
    fun publicApi(): GroupedOpenApi?{
        return GroupedOpenApi.builder().group("springcredditapplicationonsystem-public")
            .pathsToMatch("/api/customer/**", "/api/credits/**")
            .build()
    }
}