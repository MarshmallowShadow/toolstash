package com.marsh.toolstash.swagger

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

@AutoConfiguration
@EnableOpenApi
@EnableConfigurationProperties(SwaggerProperties::class)
class SwaggerAutoConfiguration(
    private val swaggerProperties: SwaggerProperties
) {
    private val contact = Contact(swaggerProperties.contactName, swaggerProperties.contactUrl, swaggerProperties.email)

    @Bean
    @ConditionalOnProperty(
        prefix = "marsh.swagger",
        name = ["basePackage", "title", "version", "description", "termsOfServiceUrl", "contactName", "contactUrl", "email"]
    )
    fun autApi(): Docket =
        Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(swaggerProperties.basePackage))
            .build()
            .securitySchemes(listOf(apiKey()))
            .securityContexts(listOf(securityContext()))

    private fun securityContext() =
        SecurityContext.builder().securityReferences(defaultAuth())
            .operationSelector { true }
            .build()

    private fun apiKey() =
        ApiKey("Authorization", "Bearer", "header")

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "access All")
        return listOf(SecurityReference("Authorization", arrayOf(authorizationScope)))
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title(swaggerProperties.title)
            .version(swaggerProperties.version)
            .description(swaggerProperties.description)
            .termsOfServiceUrl(swaggerProperties.termsOfServiceUrl)
            .contact(contact)
            .build()
    }
}
