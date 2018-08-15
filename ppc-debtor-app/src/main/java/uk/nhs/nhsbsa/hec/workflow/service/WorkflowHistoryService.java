package uk.nhs.nhsbsa.hec.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.nhs.nhsbsa.hec.workflow.entity.WorkItemStatusHistory;
import uk.nhs.nhsbsa.hec.workflow.repository.WorkitemStatusHistoryRepository;

@Service
public class WorkflowHistoryService implements IWorkflowHistoryService {
	
	@Autowired
	private WorkitemStatusHistoryRepository repository;
	
	@Override
	@Transactional
	public WorkItemStatusHistory addWorkitemHistory(WorkItemStatusHistory historything) {
	
//		WorkItemStatusHistory itemHistory = new WorkItemStatusHistory();
//		itemHistory.setWorkflowId(historything.getWorkflowId());
//		itemHistory.setDateEffective(historything.getDateEffective());
//		itemHistory.setUserId(historything.getUserId());
//		itemHistory.setStatus(historything.getStatus());
//		itemHistory = repository.save(itemHistory);
//		return historything;
		return null;
	}

}
