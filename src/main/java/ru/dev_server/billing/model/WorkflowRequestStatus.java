package ru.dev_server.billing.model;

/*
 * Статусы заявок, обрабатываемых конвейером
 */
public enum WorkflowRequestStatus {

    CREATED, REGISTERED, CONFIGURED, ENQUEUED, PROCESSED, REPORTED
}
