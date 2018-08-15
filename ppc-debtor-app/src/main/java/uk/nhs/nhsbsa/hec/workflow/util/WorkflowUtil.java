package uk.nhs.nhsbsa.hec.workflow.util;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.nhs.nhsbsa.hec.workflow.config.WorkflowConfiguration;
import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.entity.WorkItem;
import uk.nhs.nhsbsa.hec.workflow.entity.WorkItemStatusHistory;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@Component
public class WorkflowUtil implements IWorkflowUtil {

	private static final Logger logger = LoggerFactory.getLogger(WorkflowUtil.class);
	
	@Autowired
	private WorkflowConfiguration config;
	
	public static void initNewWorkitem(WorkItemDto workItem) {
		workItem.setWorkitemStatus(DirectDebitDebtorStatus.InitialLetter);
		workItem.setCreatedDate(new Date());
	}
	
	/* (non-Javadoc)
	 * @see uk.nhs.nhsbsa.hec.workflow.util.IWorkflowUtil#convert(uk.nhs.nhsbsa.hec.workflow.domain.Workitem)
	 */
	@Override
	public WorkItemDto convert(WorkItem item) {

		if (item != null) {
			WorkItemDto workItem = new WorkItemDto();
			workItem.setId(item.getId());
			workItem.setCreatedDate(item.getCreatedDate());
			workItem.setLastModified(item.getLastModified());
			workItem.setLastModifiedBy(item.getUserId());
			workItem.setWorkitemStatus(item.getStatus());
			workItem.setReference(item.getCertNumber());
			workItem.setExpiryDate(item.getExpiryDate());
			workItem.setAmount(item.getAmount());
			workItem.setEscalateDate(item.getEscalate());

			return workItem;
		}
		
		return null;
	}
	
	public WorkItemStatusHistory createWorkItemStatusHistory(WorkItem workItem)
	{
		WorkItemStatusHistory workItemHistory = new WorkItemStatusHistory();
		workItemHistory.setWorkItem(workItem);
		workItemHistory.setDateEffective(workItem.getLastModified());
		workItemHistory.setUserId(workItem.getUserId());
		workItemHistory.setStatus(workItem.getStatus());
		return workItemHistory;
	}
	
	/* (non-Javadoc)
	 * @see uk.nhs.nhsbsa.hec.workflow.util.IWorkflowUtil#getWorkitem(uk.nhs.nhsbsa.hec.workflow.model.WorkItem)
	 */
	@Override
	public WorkItem getWorkitem(WorkItemDto workItemDto) {
		WorkItem item = new WorkItem();
		
		item.setId(workItemDto.getId());
		item.setCreatedDate(workItemDto.getCreatedDate());
		item.setCertNumber(workItemDto.getReference());
		item.setExpiryDate(workItemDto.getExpiryDate());
		item.setAmount(workItemDto.getAmount());
		item.setStatus(workItemDto.getWorkItemStatus());
		item.setUserId(workItemDto.getLastModifiedBy());
		item.setLastModified(new DateTime().toDate());
		item.setEscalate(calculateEscalation(workItemDto.getWorkItemStatus(), workItemDto.getExpiryDate()));

		logger.info("Item: {}", item);
		return item;
	}
	
	/*
	 * Determines what date the workItem should escalate on - time attribute not considered.
	 * 21 days for Initial, Reminder, ExpriryReminder and same day Debtor, Finance
	 */
	public Date calculateEscalation(DirectDebitDebtorStatus status, Date expiryDate) {

		LocalDate certExpiry = LocalDate.fromDateFields(expiryDate);

		LocalDate today = LocalDate.now();
		
		LocalDate escalate = today; 

		if( status == DirectDebitDebtorStatus.InitialLetter ||
			status == DirectDebitDebtorStatus.ReminderLetter ||
			status == DirectDebitDebtorStatus.ExpiryReminderLetter) {
			int days = getEscalationDays(status);
			escalate = today.plusDays(days);
		}
		else if ( status == DirectDebitDebtorStatus.Debtor ||
				status == DirectDebitDebtorStatus.Close) {
			escalate = today;
		}
		else if( status == DirectDebitDebtorStatus.Finance) {
			escalate = certExpiry;
		}
		
		return escalate.toDate();
	}
	
	private int getEscalationDays(DirectDebitDebtorStatus status) {
		int days = 0;
		
		if(status == DirectDebitDebtorStatus.InitialLetter ||
			status == DirectDebitDebtorStatus.ExpiryReminderLetter) {
			days = config.getDebtorInitialLetterSla();
		}
		else if(status == DirectDebitDebtorStatus.ReminderLetter) {
			days = config.getDebtorReminderLetterSla();
		}
		
		logger.debug("Escalation in [{}] days", days);
		return days;
	}
}
