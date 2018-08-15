package uk.nhs.nhsbsa.hec.workflow.util;

import java.util.Date;

import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.entity.WorkItem;
import uk.nhs.nhsbsa.hec.workflow.entity.WorkItemStatusHistory;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

public interface IWorkflowUtil {

	WorkItemDto convert(WorkItem item);

	WorkItem getWorkitem(WorkItemDto workItem);
	
	WorkItemStatusHistory createWorkItemStatusHistory(WorkItem workItem);

	public Date calculateEscalation(DirectDebitDebtorStatus status, Date expiryDate);
}