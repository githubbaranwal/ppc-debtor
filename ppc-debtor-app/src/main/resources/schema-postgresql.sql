CREATE TABLE IF NOT EXISTS s_workflow_status
(
    id            smallint NOT NULL,
    name          VARCHAR(30) NOT NULL,
    constraint pk_workflow_status primary key (id)
);

CREATE TABLE IF NOT EXISTS s_workflow_items
(
    id               int NOT NULL,
    created_date     timestamp NOT NULL,
    cert_number      bigint NOT NULL,
    expiry_date      date NOT NULL,
    amount           decimal(5,2) NOT NULL,
    user_id          VARCHAR(10) NOT NULL,
    last_modified    timestamp NOT NULL,
    status           smallint NOT NULL,
    escalate         date,
    constraint pk_workflow_items primary key (id),
    constraint WORKFLOW_STATUS_FK foreign key (status) references s_workflow_status(id)
);

CREATE TABLE IF NOT EXISTS s_workflow_item_status_history
(
    id               int NOT NULL,
    workflow_id      int NOT NULL,
    date_effective   timestamp NOT NULL,
    user_id          VARCHAR(10) NOT NULL,
    status           smallint NOT NULL,
    constraint pk_workflow_status_history primary key (id),
    constraint WORKFLOW_STATUS_HISTORY_FK foreign key (status) references s_workflow_status(id)
);

CREATE SEQUENCE IF NOT EXISTS S_DEBTOR_WORKITEM_SEQ NO CYCLE;