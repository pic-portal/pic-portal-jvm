package tech.pic.portal.jvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PicPortalJvmApplication

fun main(args: Array<String>) {
	runApplication<PicPortalJvmApplication>(*args)
}
