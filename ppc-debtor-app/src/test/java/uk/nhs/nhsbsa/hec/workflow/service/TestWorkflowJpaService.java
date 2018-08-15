package uk.nhs.nhsbsa.hec.workflow.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@ActiveProfiles(value="local")
public class TestWorkflowJpaService {

	@Autowired
	private IWorkflowService service;
	
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, 
			scripts="testdata-WorkflowJpaService1.sql")
	@Test
	public void testFindByStatusEscalatedEmpty() {
		
		List<WorkItemDto> items = service.getAllEscalated(DirectDebitDebtorStatus.InitialLetter, 0);
		assertNotNull(items);
		assertTrue(items.isEmpty());
	}
	
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, 
			scripts={"testdata-WorkflowJpaService1.sql","testdata-WorkflowJpaService2.sql"})
	@Test
	public void testFindByStatusEscalated() {
		
		List<WorkItemDto> items = service.getAllEscalated(DirectDebitDebtorStatus.InitialLetter, 0);
		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(1, items.size());
	}
	
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, 
			scripts={"testdata-WorkflowJpaService1.sql","testdata-WorkflowJpaService3.sql"})
	@Test
	public void testFindByStatusEscalatedNotReady() {
		
		List<WorkItemDto> items = service.getAllEscalated(DirectDebitDebtorStatus.InitialLetter, 0);
		assertNotNull(items);
		assertTrue(items.isEmpty());
		assertEquals(0, items.size());
	}
}
