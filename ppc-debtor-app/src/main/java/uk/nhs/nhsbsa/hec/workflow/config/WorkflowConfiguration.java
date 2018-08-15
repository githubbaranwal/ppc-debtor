package uk.nhs.nhsbsa.hec.workflow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowConfiguration {

	@Value("${debtor.initialLetter.sla:21}")
	private int debtorInitialLetterSla;
	
	@Value("${debtor.reminderLetter.sla:21}")
	private int debtorReminderLetterSla;

	@Value("${debtor.escalateBeforeExpiry.sla:21}")
	private int debtorEscalateBeforeExpirySla;
	
	@Value("${debtor.default.pagesize:20}")
	private int defaultPagesize;

	public int getDebtorInitialLetterSla() {
		return debtorInitialLetterSla;
	}

	public int getDebtorReminderLetterSla() {
		return debtorReminderLetterSla;
	}

	public int getDefaultPagesize() {
		return defaultPagesize;
	}

	public int getDebtorEscalateBeforeExpirySla() {
		return debtorEscalateBeforeExpirySla;
	}
	
}
