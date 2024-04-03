package tech.pic.portal.jvm.controllers

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.pic.portal.jvm.controllers.response.EventResponse
import tech.pic.portal.jvm.controllers.response.toEventResponse
import tech.pic.portal.jvm.services.EventService
import java.util.UUID

@RestController
@RequestMapping("event")
class EventController(
    private val eventService: EventService,
) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/{id}", produces = ["application/json"])
    fun getById(@PathVariable id: String): EventResponse {
        logger.info { "getById: $id" }
        return eventService.getById(UUID.fromString(id)).toEventResponse()
    }
}
