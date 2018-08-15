package uk.nhs.nhsbsa.hec.workflow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.nhs.nhsbsa.hec.workflow.config.WorkflowConfiguration;
import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.entity.WorkItem;
import uk.nhs.nhsbsa.hec.workflow.entity.WorkItemStatusHistory;
import uk.nhs.nhsbsa.hec.workflow.repository.WorkItemRepository;
import uk.nhs.nhsbsa.hec.workflow.repository.WorkitemStatusHistoryRepository;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;
import uk.nhs.nhsbsa.hec.workflow.util.IWorkflowUtil;
import uk.nhs.nhsbsa.hec.workflow.util.WorkflowUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class WorkflowJpaService implements IWorkflowService {

	private static final Logger logger = LoggerFactory.getLogger(WorkflowJpaService.class);
	
	@Autowired
	private WorkItemRepository workItemRepository;

	@Autowired
	private WorkitemStatusHistoryRepository workItemStatusHistoryRepository;

	@Autowired
	private WorkflowConfiguration config;
	
	@Autowired
	private IWorkflowUtil util;

	@Override
	public WorkItemDto getById(int workItemId) {
		WorkItem item = workItemRepository.findOne(workItemId);
		return util.convert(item);
	}

	@Override
	public List<WorkItemDto> getAll(int workstreamId, int page) {
		Pageable paging = new PageRequest(page, config.getDefaultPagesize());

		Iterable<WorkItem> items = workItemRepository.findAll(paging);

		List<WorkItemDto> workItems = combineWorkitems(items);
		return workItems;
	}

	@Override
	public List<WorkItemDto> getAll(int workstreamId, DirectDebitDebtorStatus workItemStatus, int page) {
		Pageable paging = new PageRequest(page, config.getDefaultPagesize());
		List<WorkItem> items = workItemRepository.findByStatus(workItemStatus, paging);

		List<WorkItemDto> workItems = new ArrayList<>();
		for (WorkItem item : items) {
			workItems.add(util.convert(item));
		}

		return workItems;
	}

	@Override
	public List<WorkItemDto> getAllEscalated(DirectDebitDebtorStatus workItemStatus, int page) {
		Date escalateFrom = LocalDate.now().toDate();
		logger.info("Find items escalated <= {}", escalateFrom);
		
		Pageable paging = new PageRequest(page, config.getDefaultPagesize());

		Iterable<WorkItem> items = workItemRepository.findByStatusAndEscalateLessThanEqualOrderByEscalateAsc(
				workItemStatus, 
				escalateFrom,
				paging);

		return combineWorkitems(items);
	}

	private List<WorkItemDto> combineWorkitems(Iterable<WorkItem> items) {
		List<WorkItemDto> workItems = new ArrayList<>();
		for (WorkItem item : items) {
			workItems.add(util.convert(item));
		}
		return workItems;
	}

	@Override
	public List<WorkItemDto> getByCertNumber(long certNo) {
		List<WorkItemDto> items = new ArrayList<>();

		WorkItemDto item = util.convert(workItemRepository.findByCertNumber(certNo));
		if(item != null) {
		    items.add(item);
		}

		return items;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public WorkItemDto updateWorkItem(WorkItemDto workItemUpdate, String user)
	{
		WorkItemDto workItemReturn = null;
		
		WorkItem workItem = workItemRepository.findOne(workItemUpdate.getId());
		if(workItem.getStatus() != null) 
		{
			workItem.setStatus(workItemUpdate.getWorkItemStatus());
			workItem.setLastModified(new DateTime().toDate());
			workItem.setEscalate(util.calculateEscalation(workItemUpdate.getWorkItemStatus(), workItem.getExpiryDate()));
			workItem.setUserId(user);
			workItem.getWorkItemStatusHistory().size(); // get the lazyily loaded data
			workItem.getWorkItemStatusHistory().add(util.createWorkItemStatusHistory(workItem));
			
			workItemReturn = util.convert(workItemRepository.save(workItem));
		}
		
		return workItemReturn;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public WorkItemDto addWorkItem(WorkItemDto dto, String user) 
	{

		//initalise a new Transfer object as the input one has come from a caller
		WorkItemDto workItemDto = new WorkItemDto();
		workItemDto.setAmount(dto.getAmount());
		workItemDto.setExpiryDate(dto.getExpiryDate());
		workItemDto.setReference(dto.getReference());
		workItemDto.setLastModified(new Date());
		workItemDto.setLastModifiedBy(user);
		WorkflowUtil.initNewWorkitem(workItemDto);
		
		WorkItem item = util.getWorkitem(workItemDto);	
		
		List<WorkItemStatusHistory> workItemStatutHistory = new ArrayList<>();
		workItemStatutHistory.add(util.createWorkItemStatusHistory(item));
		item.setWorkItemStatusHistory(workItemStatutHistory);		
		WorkItem itemSaved = workItemRepository.save(item);
		WorkItemDto itemReturn = util.convert(itemSaved);
		
		return itemReturn;
	}
	
}
