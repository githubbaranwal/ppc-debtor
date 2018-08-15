package uk.nhs.nhsbsa.hec.workflow.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import uk.nhs.nhsbsa.hec.workflow.entity.WorkItem;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;


@Repository
public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Integer> {

	WorkItem findByCertNumber(long certNumber);
	
	List<WorkItem> findByStatus(DirectDebitDebtorStatus status, Pageable paging);

	List<WorkItem> findByStatusAndLastModifiedLessThanEqual(
			DirectDebitDebtorStatus workItemStatus,
			Date escalateFrom, 
			Pageable paging);
	
	List<WorkItem> findByStatusAndEscalateLessThanEqualOrderByEscalateAsc(
			DirectDebitDebtorStatus workItemStatus,
			Date escalateFrom, 
			Pageable paging);
	
	
}
