package uk.nhs.nhsbsa.hec.workflow.service;

import java.util.List;

import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

public interface IWorkflowService {
	public WorkItemDto getById(int workItemId);

	public List<WorkItemDto> getAll(int workstreamId, int page);
	
	public List<WorkItemDto> getAll(int workstreamId, DirectDebitDebtorStatus workItemStatus, int page);
	
	public List<WorkItemDto> getAllEscalated(DirectDebitDebtorStatus workItemStatus, int page);
	
	public WorkItemDto updateWorkItem(WorkItemDto workItemDto, String user);
	
	public WorkItemDto addWorkItem(WorkItemDto workItemDto, String user);

	public List<WorkItemDto> getByCertNumber(long certNo);
}
