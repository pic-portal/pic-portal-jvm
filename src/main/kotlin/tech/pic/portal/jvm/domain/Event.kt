package tech.pic.portal.jvm.domain

import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

data class Event(
    val id: UUID,
    val eventDate: LocalDate,
    val eventName: String,
    val createdBy: String,
    val createdOn: OffsetDateTime,
    val updatedBy: String,
    val updatedOn: OffsetDateTime,
)
