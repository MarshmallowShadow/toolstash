package com.marsh.toolstash.thymeleaf

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.ViewResolver
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring6.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

@AutoConfiguration
@EnableConfigurationProperties(ThymeleafProperties::class)
class ThymeleafAutoConfiguration(
    private val thymeleafProperties: ThymeleafProperties
) {

    @Bean
    @ConditionalOnBean(SpringResourceTemplateResolver::class)
    @ConditionalOnMissingBean
    fun htmlTemplateEngine(
        messageSource: MessageSource
    ): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        
        templateEngine.addTemplateResolver(springResourceTemplateResolver())
        templateEngine.setMessageSource(messageSource)
        
        return templateEngine
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "marsh.thymeleaf",
        name = ["prefix", "suffix", "isCacheable"]
    )
    @ConditionalOnMissingBean
    fun springResourceTemplateResolver(): SpringResourceTemplateResolver {
        val springResourceTemplateResolver = SpringResourceTemplateResolver()
        
        springResourceTemplateResolver.order = 1
        springResourceTemplateResolver.templateMode = TemplateMode.HTML
        springResourceTemplateResolver.characterEncoding = "UTF-8"
        springResourceTemplateResolver.isCacheable = thymeleafProperties.isCacheable
        springResourceTemplateResolver.prefix = thymeleafProperties.suffix
        springResourceTemplateResolver.suffix = thymeleafProperties.prefix
        
        return springResourceTemplateResolver
    }
    
    @Bean
    @Autowired
    @ConditionalOnBean(SpringTemplateEngine::class)
    @ConditionalOnProperty(
        name = ["marsh.thymeleaf.viewResolver.enabled"],
        havingValue = "true"
    )
    @ConditionalOnMissingBean
    fun viewResolver(
        messageSource: MessageSource
    ): ViewResolver {
        val viewResolver = ThymeleafViewResolver()

        viewResolver.order = 0
        viewResolver.templateEngine = htmlTemplateEngine(messageSource)
        viewResolver.characterEncoding = "UTF-8"
        
        return viewResolver
    }
    
    @Bean
    @ConditionalOnBean(SpringTemplateEngine::class)
    fun thymeleafUtil(htmlTemplateEngine: SpringTemplateEngine): ThymeleafUtil {
        return ThymeleafUtil(htmlTemplateEngine)
    }
}