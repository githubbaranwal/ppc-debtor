package uk.nhs.nhsbsa.hec.workflow.repository;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@ActiveProfiles(value="local")
public class TestWorkitemStatusHistoryRepository {

	@Autowired
	private WorkitemStatusHistoryRepository repo;
	
	private static final int DEFAULT_PAGE = 5;
	
	private Pageable paging = new PageRequest(0, DEFAULT_PAGE);
	
	@Test
	public void testFindByWorkflowIdEmpty() {
		
//		List<WorkitemStatusHistory> statuses = repo.findByIdWorkflowIdOrderByIdDateEffectiveAsc(9999, paging);
//		assertNotNull(statuses);
//		assertTrue(statuses.isEmpty());
	}
	
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts={"testdata-WorkitemStatusHistoryRepository0.sql","testdata-WorkitemStatusHistoryRepository1.sql"})
	@Test
	public void testFindByWorkflowId() {
		
//		List<WorkitemStatusHistory> statuses = repo.findByIdWorkflowIdOrderByIdDateEffectiveAsc(9998, paging);
//		assertNotNull(statuses);
//		assertFalse(statuses.isEmpty());
//		assertEquals(1, statuses.size());
	}
	
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts={"testdata-WorkitemStatusHistoryRepository0.sql","testdata-WorkitemStatusHistoryRepository2.sql"})
	@Test
	public void testFindByWorkflowIdPaging() {
		
//		List<WorkitemStatusHistory> statuses = repo.findByIdWorkflowIdOrderByIdDateEffectiveAsc(9997, paging);
//		assertNotNull(statuses);
//		assertFalse(statuses.isEmpty());
//		assertEquals(5, statuses.size());
	}
}
