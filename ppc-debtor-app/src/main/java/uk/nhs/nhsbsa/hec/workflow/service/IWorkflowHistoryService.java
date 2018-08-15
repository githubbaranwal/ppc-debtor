package uk.nhs.nhsbsa.hec.workflow.service;

import uk.nhs.nhsbsa.hec.workflow.entity.WorkItemStatusHistory;

public interface IWorkflowHistoryService {

	public WorkItemStatusHistory addWorkitemHistory(WorkItemStatusHistory WorkItemHistory);
}
