--liquibase formatted sql

--changeset event:1

CREATE TABLE IF NOT EXISTS event (
    id UUID NOT NULL PRIMARY KEY,
    event_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    event_name TEXT NOT NULL,
    created_by TEXT NOT NULL,
    created_on TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by TEXT NOT NULL,
    updated_on TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

--rollback select 1

--changeset event-date:1

ALTER TABLE event ALTER COLUMN event_date TYPE DATE USING event_date::DATE;

--rollback select 1
