package uk.nhs.nhsbsa.hec.workflow.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import uk.nhs.nhsbsa.hec.workflow.entity.WorkItem;
import uk.nhs.nhsbsa.hec.workflow.repository.WorkItemRepository;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@ActiveProfiles(value="local")
public class TestWorkitemRepository {

	@Autowired
	private WorkItemRepository repo;
	
	private static final int DEFAULT_PAGE = 5;
	
	private Pageable paging = new PageRequest(0, DEFAULT_PAGE);
	
	@Test
	public void testFindByStatusEmpty() {
		
		List<WorkItem> items = repo.findByStatus(DirectDebitDebtorStatus.Close, paging);
		assertNotNull(items);
		assertTrue(items.isEmpty());
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="testdata-WorkitemRepository1.sql")
	public void testFindLatestByCertNumber() {
		
		WorkItem item = repo.findByCertNumber(1004327531L);
		assertNotNull(item);
	}
	
	@Test
	public void testFindLatestByCertNumber_NotExist() {
		
		WorkItem item = repo.findByCertNumber(1019999999L);
		assertNull(item);
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="testdata-WorkitemRepository2.sql")
	public void testFindByStatusPaging() {
		
		List<WorkItem> items = repo.findByStatus(DirectDebitDebtorStatus.ReminderLetter, paging);
		assertNotNull(items);
		assertEquals(DEFAULT_PAGE, items.size());
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="testdata-WorkitemRepository3.sql")
	public void testFindByStatusAndEscalated() {
		
		// verify test case
		WorkItem item = repo.findOne(9985);
		assertNotNull(item);
		assertEquals(DirectDebitDebtorStatus.InitialLetter, item.getStatus());
		assertEquals(LocalDate.now().minusDays(5).toDate(), item.getEscalate());
		
		List<WorkItem> items = repo.findByStatusAndEscalateLessThanEqualOrderByEscalateAsc(
				DirectDebitDebtorStatus.InitialLetter,
				DateTime.now().toDate(),
				paging);
		
		assertNotNull(items);
		assertEquals(1, items.size());
	}
}
