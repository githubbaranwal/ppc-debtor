CREATE TABLE IF NOT EXISTS s_workflow_status
(
    id            smallint NOT NULL PRIMARY KEY,
    name          VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS s_workflow_items
(
    id            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_date  timestamp NOT NULL,
    cert_number   bigint(11) NOT NULL,
    expiry_date   date NOT NULL,
    amount        decimal(5,2) NOT NULL,
    user_id       VARCHAR(10) NOT NULL,
    last_modified timestamp NOT NULL,
    escalate      date,
    status        smallint NOT NULL,
    constraint WORKFLOW_STATUS_FK foreign key (status) references s_workflow_status(id)
);

CREATE TABLE IF NOT EXISTS s_workflow_item_status_history
(
    id               int NOT NULL PRIMARY KEY,
    workflow_id      int NOT NULL,
    date_effective   timestamp NOT NULL,
    user_id          VARCHAR(10) NOT NULL,
    status           smallint NOT NULL,
    constraint WORKFLOW_STATUS_HISTORY_FK foreign key (status) references s_workflow_status(id)
);

CREATE SEQUENCE IF NOT EXISTS S_DEBTOR_WORKITEM_SEQ;

CREATE SEQUENCE IF NOT EXISTS S_DEBTOR_HISTORY_SEQ;