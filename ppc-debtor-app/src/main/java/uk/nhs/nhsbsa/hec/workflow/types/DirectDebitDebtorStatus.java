package uk.nhs.nhsbsa.hec.workflow.types;

import java.util.HashMap;
import java.util.Map;

public enum DirectDebitDebtorStatus {

	InitialLetter(0,"Initial"),
	ReminderLetter(1,"Reminder"),
	Debtor(2,"Debtor"),
	ExpiryReminderLetter(3,"Expiry Reminder"),
	Finance(4,"Send to Finance"),
	Close(5, "Closed");
	
	// factory for getting status instance from integer
	private final static Map<Integer, DirectDebitDebtorStatus> statuses;
	static {
		statuses = new HashMap<Integer, DirectDebitDebtorStatus>();
		statuses.put(InitialLetter.statusId, InitialLetter);
		statuses.put(ReminderLetter.statusId, ReminderLetter);
		statuses.put(Debtor.statusId, Debtor);
		statuses.put(ExpiryReminderLetter.statusId, ExpiryReminderLetter);
		statuses.put(Finance.statusId, Finance);
		statuses.put(Close.statusId, Close);
	}
	
	private int statusId;
	private String statusText;
	
	private DirectDebitDebtorStatus(int statusId, String statusText) {
		this.statusId = statusId;
		this.statusText = statusText;
	}
	
	public int getStatusId() {
		return this.statusId;
	}

	public String getStatusText() {
		return this.statusText;
	}

	public static DirectDebitDebtorStatus getStatus(int statusId) {
		
		DirectDebitDebtorStatus status = statuses.get(statusId);
		
		if(status == null) {
			throw new IllegalArgumentException("Unknown status type: " + statusId);
		}
		
		return status;
	}
}
