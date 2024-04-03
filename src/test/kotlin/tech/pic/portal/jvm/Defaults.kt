package tech.pic.portal.jvm

import tech.pic.portal.jvm.domain.Event
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

val defaultEvent by lazy {
    Event(
        id = UUID.randomUUID(),
        createdBy = "test-user@gmail.com",
        createdOn = OffsetDateTime.now(),
        eventDate = LocalDate.now(),
        eventName = "Test Event",
        updatedBy = "test-user@gmail.com",
        updatedOn = OffsetDateTime.now(),
    )
}
