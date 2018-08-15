CREATE TABLE workflow_status
(
    id            number(2) NOT NULL,
    name          VARCHAR2(30) NOT NULL,
    constraint pk_workflow_status primary key (id)
);

CREATE TABLE workflow_items
(
    id               number(15) NOT NULL,
    created_date     date NOT NULL,
    cert_number      number(11) NOT NULL,
    expiry_date      date NOT NULL,
    amount           number(5,2) NOT NULL,
    user_id          VARCHAR2(10) NOT NULL,
    last_modified    date NOT NULL,
    status           number(2) NOT NULL,
    escalate         date,
    constraint pk_workflow_items primary key (id)
);

CREATE TABLE workflow_item_status_history
(
    id               number(15) NOT NULL,
    workflow_id      number(15) NOT NULL,
    date_effective   date NOT NULL,
    user_id          VARCHAR2(10) NOT NULL,
    status           number(2) NOT NULL,
    constraint pk_workflow_status_history primary key (id)
);

CREATE SEQUENCE DEBTOR_WORKITEM_SEQ;
CREATE SEQUENCE DEBTOR_HISTORY_SEQ;

create synonym s_DEBTOR_WORKITEM_SEQ for DEBTOR_WORKITEM_SEQ;
create synonym s_DEBTOR_HISTORY_SEQ for DEBTOR_HISTORY_SEQ;
create synonym s_workflow_item_status_history for workflow_item_status_history;
create synonym s_workflow_items for workflow_items;
create synonym s_workflow_status for workflow_status;