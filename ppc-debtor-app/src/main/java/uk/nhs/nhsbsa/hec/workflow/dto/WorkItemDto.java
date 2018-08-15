package uk.nhs.nhsbsa.hec.workflow.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;

@SuppressWarnings("serial")
public class WorkItemDto implements Serializable {

	private Integer id;

	private DirectDebitDebtorStatus workItemStatus;

	private Long reference;

	private BigDecimal amount;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date createdDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date lastModified;

	private String lastModifiedBy;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date escalateDate;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date expiryDate;

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

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public DirectDebitDebtorStatus getWorkItemStatus() {
		return workItemStatus;
	}

	public void setWorkitemStatus(DirectDebitDebtorStatus workItemStatus) {
		this.workItemStatus = workItemStatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getEscalateDate() {
		return escalateDate;
	}

	public void setEscalateDate(Date escalateDate) {
		this.escalateDate = escalateDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "WorkItem [id=" + id + ", workItemStatus=" + workItemStatus
				+ ", reference=" + reference + ", amount=" + amount + ", createdDate=" + createdDate + ", lastModified="
				+ lastModified + ", lastModifiedBy=" + lastModifiedBy + ", escalateDate="
				+ escalateDate + ", expiryDate=" + expiryDate + "]";
	}
}
