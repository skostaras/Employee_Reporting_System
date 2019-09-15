package com.stefanosk27.reporting.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stefanosk27.reporting.Priority;

@Table(name = "Report")
@Entity
public class Report implements Serializable {

	private static final long serialVersionUID = -9006913712525503650L;
	
	@Id
	@GeneratedValue
	@Column(name="ReportId", updatable = false)
	private Integer reportId;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;
	
	@Column(name="Title", nullable = false)
	private String title;

	@Column(name="Description", nullable = false)
	private String description;
	
	@Column(name="Priority", nullable = false)
	private Priority priority;
	

	public Integer getReportId() {
		return reportId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	

}
