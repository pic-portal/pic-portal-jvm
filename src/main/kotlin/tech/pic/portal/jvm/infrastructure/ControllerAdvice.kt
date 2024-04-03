package tech.pic.portal.jvm.infrastructure

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import tech.pic.portal.jvm.exceptions.ResourceNotFoundException

@ControllerAdvice
class ControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ResourceNotFoundException::class])
    fun handleNotFound(
        ex: RuntimeException?,
        request: WebRequest?,
    ): ResponseEntity<Any>? {
        return handleExceptionInternal(
            ex!!,
            "Not Found",
            HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request!!,
        )
    }
}
