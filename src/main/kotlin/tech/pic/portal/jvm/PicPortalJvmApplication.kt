package tech.pic.portal.jvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@ComponentScan
class PicPortalJvmApplication

@SuppressWarnings("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<PicPortalJvmApplication>(*args)
}
