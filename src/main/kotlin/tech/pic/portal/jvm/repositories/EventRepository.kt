package tech.pic.portal.jvm.repositories

import mu.KotlinLogging
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import tech.pic.portal.jvm.domain.Event
import tech.pic.portal.jvm.generated.tables.records.EventRecord
import tech.pic.portal.jvm.generated.tables.references.EVENT
import java.util.UUID

@Repository
class EventRepository(
    private val dsl: DSLContext,
) {

    private val logger = KotlinLogging.logger {}

    fun getById(id: UUID): Event? {
        logger.info { "getById: $id" }
        return dsl.select(DSL.asterisk())
            .from(EVENT)
            .where(EVENT.ID.eq(EVENT.ID))
            .fetchOne()
            ?.into(EVENT)
            ?.toDomain()
    }

    private fun EventRecord.toDomain(): Event {
        return Event(
            id = get(EVENT.ID)!!,
            eventDate = get(EVENT.EVENT_DATE)!!,
            eventName = get(EVENT.EVENT_NAME)!!,
            createdBy = get(EVENT.CREATED_BY)!!,
            createdOn = get(EVENT.CREATED_ON)!!,
            updatedBy = get(EVENT.CREATED_BY)!!,
            updatedOn = get(EVENT.UPDATED_ON)!!,
        )
    }
}
