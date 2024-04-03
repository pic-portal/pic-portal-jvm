package tech.pic.portal.jvm.services

import mu.KotlinLogging
import org.springframework.stereotype.Service
import tech.pic.portal.jvm.domain.Event
import tech.pic.portal.jvm.exceptions.ResourceNotFoundException
import tech.pic.portal.jvm.repositories.EventRepository
import java.util.UUID

@Service
class EventService(
    private val eventRepository: EventRepository,
) {

    private val logger = KotlinLogging.logger {}

    fun getById(id: UUID): Event {
        logger.info { "getById: $id" }
        return eventRepository.getById(id) ?: throw ResourceNotFoundException().also {
            logger.warn { "getById: $id not found" }
        }
    }
}
