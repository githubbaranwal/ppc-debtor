package uk.nhs.nhsbsa.hec.workflow.service;

import java.util.List;

import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

public interface IStatusService {

	List<DirectDebitDebtorStatus> getNextStatuses(DirectDebitDebtorStatus currentStatus);
	
	List<DirectDebitDebtorStatus> getNextStatuses(DirectDebitDebtorStatus currentStatus, WorkItemDto workItemdto);
}
