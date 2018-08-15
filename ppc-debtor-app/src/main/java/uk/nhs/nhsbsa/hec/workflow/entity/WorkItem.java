package uk.nhs.nhsbsa.hec.workflow.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="S_WORKFLOW_ITEMS")
public class WorkItem implements Serializable {

	@Id
	@SequenceGenerator(name="debtor_workitem_seq", sequenceName="s_debtor_workitem_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="debtor_workitem_seq")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;
	
	@Column(name = "CERT_NUMBER", nullable = false)
	private long certNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRY_DATE", nullable = false)
	private Date expiryDate;
	
	@Column(nullable = false)
	private BigDecimal amount;
	
	@Column(name = "STATUS", nullable = false)
	private DirectDebitDebtorStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED", nullable = false)
	private Date lastModified;
	
	@Column(name = "USER_ID", nullable = false)
	private String userId;
	
	@Column(name = "ESCALATE", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date escalate;
	
	@OneToMany(mappedBy="workItem", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<WorkItemStatusHistory> workItemStatusHistory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(long certNumber) {
		this.certNumber = certNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public DirectDebitDebtorStatus getStatus() {
		return status;
	}

	public void setStatus(DirectDebitDebtorStatus status) {
		this.status = status;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<WorkItemStatusHistory> getWorkItemStatusHistory() {
		return workItemStatusHistory;
	}

	public void setWorkItemStatusHistory(List<WorkItemStatusHistory> workItemStatusHistory) {
		this.workItemStatusHistory = workItemStatusHistory;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getEscalate() {
		return escalate;
	}

	public void setEscalate(Date escalate) {
		this.escalate = escalate;
	}


	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(27,11)
				.append(this.getId())
				.append(this.getCreatedDate())
				.append(this.getExpiryDate())
				.append(this.getAmount())
				.append(this.getCertNumber())
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

		WorkItem workItem = (WorkItem)object;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		return equalsBuilder
				.appendSuper(super.equals(object))
				.append(this.getId(), workItem.getId())
				.append(this.getCreatedDate(), workItem.getCreatedDate())
				.append(this.getExpiryDate(), workItem.getExpiryDate())
				.append(this.getAmount(), workItem.getAmount())				
				.append(this.getCertNumber(), workItem.getCertNumber())
				.isEquals();
	}	
	
	@Override
	public String toString() {
		return "Workitem [id=" + id + ", createdDate=" + createdDate + ", certNumber=" + certNumber + ", expiryDate="
				+ expiryDate + ", amount=" + amount + ", status=" + status + ", lastModified=" + lastModified
				+ ", userId=" + userId + ", escalate=" + escalate + ", workItemStatusHistory=" + workItemStatusHistory
				+ "]";
	}	
}
