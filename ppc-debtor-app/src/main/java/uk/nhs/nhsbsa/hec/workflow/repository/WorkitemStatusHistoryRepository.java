package uk.nhs.nhsbsa.hec.workflow.repository;

import org.springframework.data.repository.CrudRepository;

import uk.nhs.nhsbsa.hec.workflow.entity.WorkItemStatusHistory;

public interface WorkitemStatusHistoryRepository extends CrudRepository<WorkItemStatusHistory, Integer> {

}
