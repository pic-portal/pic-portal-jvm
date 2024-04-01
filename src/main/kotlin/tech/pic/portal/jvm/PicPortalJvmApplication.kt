package tech.pic.portal.jvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class PicPortalJvmApplication

fun main(args: Array<String>) {
    runApplication<PicPortalJvmApplication>(*args)
}
