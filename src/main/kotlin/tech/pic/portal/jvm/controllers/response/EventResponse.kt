package tech.pic.portal.jvm.controllers.response

import tech.pic.portal.jvm.domain.Event
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

data class EventResponse(
    val id: UUID,
    val eventDate: LocalDate,
    val eventName: String,
    val createdBy: String,
    val createdOn: OffsetDateTime,
    val updatedBy: String,
    val updatedOn: OffsetDateTime,
)

fun Event.toEventResponse(): EventResponse {
    return EventResponse(
        id = id,
        eventDate = eventDate,
        eventName = eventName,
        createdBy = createdBy,
        createdOn = createdOn,
        updatedBy = updatedBy,
        updatedOn = updatedOn,
    )
}
