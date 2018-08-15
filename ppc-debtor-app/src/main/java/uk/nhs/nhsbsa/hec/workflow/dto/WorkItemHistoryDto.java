package uk.nhs.nhsbsa.hec.workflow.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@SuppressWarnings("serial")
public class WorkItemHistoryDto implements Serializable {

	private int workflowId;
	
	private int id;

	private DirectDebitDebtorStatus status;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date date_effective;

	private String userid;

	public int getWorkflow_Id() {
		return workflowId;
	}

	public void setWorkflow_id(int id) {
		id = workflowId;
	}

	public Date getDateEffective() {
		return date_effective;
	}

	public void setDateEffective(Date date_effective) {
		this.date_effective = date_effective;
	}

	public String getuser_id() {
		return userid;
	}

	public void setuser_id(String userid) {
		this.userid = userid;
	}

	public DirectDebitDebtorStatus getstatus() {
		return status;
	}

	public void setstatus(DirectDebitDebtorStatus status) {
		this.status = status;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	@Override
	public String toString() {
		return "WorkItemHistory [workflow_id=" + workflowId + ", workItemStatus=" + status + ", date_effective="
				+ date_effective + ", user_id=" + userid +  " id = "+ id +"]";
	}
}
