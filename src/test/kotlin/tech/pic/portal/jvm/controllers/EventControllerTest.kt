package tech.pic.portal.jvm.controllers

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import tech.pic.portal.jvm.controllers.response.toEventResponse
import tech.pic.portal.jvm.defaultEvent
import tech.pic.portal.jvm.services.EventService
import java.util.UUID

class EventControllerTest {

    @Test
    fun getById() {
        val eventId = UUID.randomUUID()
        val eventService = mockk<EventService>(relaxed = true)
        val eventController = EventController(eventService)
        every { eventService.getById(any()) } returns defaultEvent

        val actual = eventController.getById(eventId.toString())
        actual shouldBe defaultEvent.toEventResponse()
        verify(exactly = 1) { eventService.getById(eventId) }
    }
}
