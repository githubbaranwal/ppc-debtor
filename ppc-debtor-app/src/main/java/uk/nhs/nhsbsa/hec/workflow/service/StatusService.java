
package uk.nhs.nhsbsa.hec.workflow.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import org.springframework.stereotype.Service;

import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@Service
public class StatusService implements IStatusService {

	@Override
	public List<DirectDebitDebtorStatus> getNextStatuses(DirectDebitDebtorStatus currentStatus) {
		List<DirectDebitDebtorStatus> statuses = new ArrayList<>();
		
		if(currentStatus == DirectDebitDebtorStatus.InitialLetter)
		{
			statuses.add(DirectDebitDebtorStatus.ReminderLetter);
			statuses.add(DirectDebitDebtorStatus.Close);
		}
		else if(currentStatus == DirectDebitDebtorStatus.ReminderLetter)
		{
			statuses.add(DirectDebitDebtorStatus.Debtor);
			statuses.add(DirectDebitDebtorStatus.Close);
		}
		else if(currentStatus == DirectDebitDebtorStatus.Debtor)
		{
			statuses.add(DirectDebitDebtorStatus.ExpiryReminderLetter);
			statuses.add(DirectDebitDebtorStatus.Finance);
			statuses.add(DirectDebitDebtorStatus.Close);
		}
		else if(currentStatus == DirectDebitDebtorStatus.ExpiryReminderLetter)
		{
			statuses.add(DirectDebitDebtorStatus.Finance);
			statuses.add(DirectDebitDebtorStatus.Close);
		}
		else if(currentStatus == DirectDebitDebtorStatus.Finance)
		{
			statuses.add(DirectDebitDebtorStatus.Close);
		}
		
		return statuses;
	}

	/**
	 * available statuses dependent on the escalate date and current status(non-Javadoc)
	 * @see uk.nhs.nhsbsa.hec.workflow.service.IStatusService#getNextStatuses(uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus, uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto)
	 */
	@Override
	public List<DirectDebitDebtorStatus> getNextStatuses(DirectDebitDebtorStatus currentStatus, WorkItemDto workItemdto) {
		List<DirectDebitDebtorStatus> statuses = new ArrayList<>();
		
		if(currentStatus == DirectDebitDebtorStatus.InitialLetter)
		{
			if(workItemdto.getEscalateDate().after(LocalDate.now().toDate())){
			    statuses.add(DirectDebitDebtorStatus.Close);
			}
			else {
				statuses.add(DirectDebitDebtorStatus.ReminderLetter);
				statuses.add(DirectDebitDebtorStatus.Close);
			}
		}
		else if(currentStatus == DirectDebitDebtorStatus.ReminderLetter)
		{
			if(workItemdto.getEscalateDate().after(LocalDate.now().toDate())){
			    statuses.add(DirectDebitDebtorStatus.Close);
			}
			else{
				statuses.add(DirectDebitDebtorStatus.Debtor);
				statuses.add(DirectDebitDebtorStatus.Close);
			}
			
		}
		else if(currentStatus == DirectDebitDebtorStatus.Debtor)
		{
			if(workItemdto.getEscalateDate().after(LocalDate.now().toDate())){
			    statuses.add(DirectDebitDebtorStatus.Close);
			}
			else{
				statuses.add(DirectDebitDebtorStatus.ExpiryReminderLetter);
				statuses.add(DirectDebitDebtorStatus.Finance);
				statuses.add(DirectDebitDebtorStatus.Close);
			}
		}
		else if(currentStatus == DirectDebitDebtorStatus.ExpiryReminderLetter)
		{
			if(workItemdto.getEscalateDate().after(LocalDate.now().toDate())){
			    statuses.add(DirectDebitDebtorStatus.Close);
			}
			else{
				statuses.add(DirectDebitDebtorStatus.Finance);
				statuses.add(DirectDebitDebtorStatus.Close);
			}
		}
		else if(currentStatus == DirectDebitDebtorStatus.Finance)
		{
			statuses.add(DirectDebitDebtorStatus.Close);
		}
		
		return statuses;
	}
}
