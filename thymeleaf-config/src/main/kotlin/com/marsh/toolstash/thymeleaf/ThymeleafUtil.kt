package com.marsh.toolstash.thymeleaf

import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Component
class ThymeleafUtil(
    private val htmlTemplateEngine: SpringTemplateEngine
) {
    fun templateToString(htmlPath: String, contextMap: Map<String, Any>?): String {
        val context = Context()

        contextMap?.forEach {
            context.setVariable(it.key, it.value)
        }
        
        return htmlTemplateEngine.process(htmlPath, context)
    }
}