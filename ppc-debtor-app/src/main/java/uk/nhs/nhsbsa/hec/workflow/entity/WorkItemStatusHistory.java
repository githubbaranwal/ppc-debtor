package uk.nhs.nhsbsa.hec.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="S_WORKFLOW_ITEM_STATUS_HISTORY")
public class WorkItemStatusHistory implements Serializable {
	
	@Id
	@SequenceGenerator(name="debtor_history_seq", sequenceName="S_DEBTOR_HISTORY_SEQ", allocationSize=1) // sequence needs changing to correct sequence
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="debtor_history_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="WORKFLOW_ID")
	private WorkItem workItem;

	@Column(name = "STATUS", nullable = false)
	private DirectDebitDebtorStatus status;
	
	@Column(name = "USER_ID", nullable = false)
	private String userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_EFFECTIVE", nullable = false)
	private Date dateEffective;
	
	public Date getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(Date dateEffective) {
		this.dateEffective = dateEffective;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	
	public WorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}
	
	public DirectDebitDebtorStatus getStatus() {
		return status;
	}

	public void setStatus(DirectDebitDebtorStatus status) {
		this.status = status;
	}
	
	public void setID(Integer id){
		this.id = id;
	}
	
	public Integer getID(){
		return id;
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(27,11)
				.append(this.getID())
				.append(this.getWorkItem().getId())
				.append(this.getDateEffective())
				.append(this.getUserId())
				.append(this.getStatus())
				.toHashCode();
	}
	
	@Override 
	public boolean equals(Object object)
	{
		if (object == null || (object.getClass() != this.getClass()))
		{
			return false;
		}
		
		if (this == object)
		{
			return true;
		}

		WorkItemStatusHistory workItemStatusHistory = (WorkItemStatusHistory)object;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		return equalsBuilder
				.appendSuper(super.equals(object))
				.append(this.getID(), workItemStatusHistory.getID())
				.append(this.getWorkItem().getId(), workItemStatusHistory.getWorkItem().getId())
				.append(this.getDateEffective(), workItemStatusHistory.getDateEffective())
				.append(this.getUserId(), workItemStatusHistory.getUserId())
				.append(this.getStatus(), workItemStatusHistory.getStatus())				
				.isEquals();
	}
	
	@Override
	public String toString() {
		return "WorkItemHistory [workItemStatus=" + status + ", date_effective="
				+ dateEffective + ", user_id=" + userId +  " id = "+ id +"]";
	}
}
