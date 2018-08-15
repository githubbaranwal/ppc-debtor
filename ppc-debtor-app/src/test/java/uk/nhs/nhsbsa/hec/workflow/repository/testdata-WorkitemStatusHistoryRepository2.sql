INSERT INTO workflow_items (ID, CREATED_DATE, CERT_NUMBER, EXPIRY_DATE, AMOUNT, USER_ID, LAST_MODIFIED, ESCALATE, STATUS) VALUES (9997,CURRENT_TIMESTAMP()-6, 1004327531, CURRENT_DATE()+110, 10.4, 'GARBR', CURRENT_TIMESTAMP(), CURRENT_DATE()+21, 1);
INSERT INTO workflow_item_status_history (workflow_id, date_effective, user_id, status) VALUES(9997,CURRENT_TIMESTAMP()-1, 'GARBR', 0);
INSERT INTO workflow_item_status_history (workflow_id, date_effective, user_id, status) VALUES(9997,CURRENT_TIMESTAMP()-2, 'GARBR', 0);
INSERT INTO workflow_item_status_history (workflow_id, date_effective, user_id, status) VALUES(9997,CURRENT_TIMESTAMP()-3, 'GARBR', 0);
INSERT INTO workflow_item_status_history (workflow_id, date_effective, user_id, status) VALUES(9997,CURRENT_TIMESTAMP()-4, 'GARBR', 0);
INSERT INTO workflow_item_status_history (workflow_id, date_effective, user_id, status) VALUES(9997,CURRENT_TIMESTAMP()-5, 'GARBR', 0);
INSERT INTO workflow_item_status_history (workflow_id, date_effective, user_id, status) VALUES(9997,CURRENT_TIMESTAMP()-6, 'GARBR', 0);
