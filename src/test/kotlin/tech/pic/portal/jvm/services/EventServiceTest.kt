package tech.pic.portal.jvm.services

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import tech.pic.portal.jvm.defaultEvent
import tech.pic.portal.jvm.repositories.EventRepository
import java.util.UUID

class EventServiceTest {

    @Test
    fun getById() {
        val eventId = UUID.randomUUID()
        val eventRepo = mockk<EventRepository>(relaxed = true)
        val eventService = EventService(eventRepo)
        every { eventRepo.getById(any()) } returns defaultEvent

        val actual = eventService.getById(eventId)
        actual shouldBe defaultEvent
        verify(exactly = 1) { eventRepo.getById(eventId) }
    }
}
