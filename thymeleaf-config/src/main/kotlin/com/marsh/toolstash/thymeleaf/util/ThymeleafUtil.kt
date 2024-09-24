package com.marsh.toolstash.thymeleaf.util

import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Component
class ThymeleafUtil(
    private val htmlTemplateEngine: SpringTemplateEngine
) {
    fun templateToString(htmlPath: String, context: Context = Context()): String {
        return htmlTemplateEngine.process(htmlPath, context)
    }
}