/*
 * This file is generated by jOOQ.
 */
package tech.pic.portal.jvm.generated


import kotlin.collections.List

import org.jooq.Catalog
import org.jooq.Table
import org.jooq.impl.SchemaImpl

import tech.pic.portal.jvm.generated.tables.Databasechangelog
import tech.pic.portal.jvm.generated.tables.Databasechangeloglock
import tech.pic.portal.jvm.generated.tables.Event


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Public : SchemaImpl("public", DefaultCatalog.DEFAULT_CATALOG) {
    public companion object {

        /**
         * The reference instance of <code>public</code>
         */
        val PUBLIC: Public = Public()
    }

    /**
     * The table <code>public.databasechangelog</code>.
     */
    val DATABASECHANGELOG: Databasechangelog get() = Databasechangelog.DATABASECHANGELOG

    /**
     * The table <code>public.databasechangeloglock</code>.
     */
    val DATABASECHANGELOGLOCK: Databasechangeloglock get() = Databasechangeloglock.DATABASECHANGELOGLOCK

    /**
     * The table <code>public.event</code>.
     */
    val EVENT: Event get() = Event.EVENT

    override fun getCatalog(): Catalog = DefaultCatalog.DEFAULT_CATALOG

    override fun getTables(): List<Table<*>> = listOf(
        Databasechangelog.DATABASECHANGELOG,
        Databasechangeloglock.DATABASECHANGELOGLOCK,
        Event.EVENT
    )
}