INSERT INTO s_workflow_status (ID,NAME) SELECT 0,'Initial' WHERE not exists (select 1 from s_workflow_status where id = 0);
INSERT INTO s_workflow_status (ID,NAME) SELECT 1,'Reminder' WHERE not exists (select 1 from s_workflow_status where id = 1);
INSERT INTO s_workflow_status (ID,NAME) SELECT 2,'Debtor' WHERE not exists (select 1 from s_workflow_status where id = 2);
INSERT INTO s_workflow_status (ID,NAME) SELECT 3,'Expiry Reminder' WHERE not exists (select 1 from s_workflow_status where id = 3);
INSERT INTO s_workflow_status (ID,NAME) SELECT 4,'Finance' WHERE not exists (select 1 from s_workflow_status where id = 4);
INSERT INTO s_workflow_status (ID,NAME) SELECT 5,'Closed' WHERE not exists (select 1 from s_workflow_status where id = 5);

insert into s_workflow_items (ID, CREATED_DATE, CERT_NUMBER, EXPIRY_DATE, AMOUNT, USER_ID, LAST_MODIFIED, ESCALATE, STATUS) 
SELECT 93451,CURRENT_DATE-22,100236346,CURRENT_DATE+120,10.4,'USERA',CURRENT_DATE-22,CURRENT_DATE-1,0 
WHERE not exists (select 1 from s_workflow_items where id = 93451);

insert into s_workflow_items (ID, CREATED_DATE, CERT_NUMBER, EXPIRY_DATE, AMOUNT, USER_ID, LAST_MODIFIED, ESCALATE, STATUS) 
SELECT 93452,CURRENT_DATE-22,100234235,CURRENT_DATE+134,10.4,'USERA',CURRENT_DATE-22,CURRENT_DATE-1,0 
WHERE not exists (select 1 from s_workflow_items where id = 93452);

insert into s_workflow_items (ID, CREATED_DATE, CERT_NUMBER, EXPIRY_DATE, AMOUNT, USER_ID, LAST_MODIFIED, ESCALATE, STATUS) 
SELECT 93453,CURRENT_DATE-22,100234565,CURRENT_DATE+114,10.4,'USERA',CURRENT_DATE-22,CURRENT_DATE-1,1 
WHERE not exists (select 1 from s_workflow_items where id = 93453);
