package com.stefanosk27.reporting.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stefanosk27.reporting.enums.Priority;

@Table(name = "Reports")
@Entity
public class Report implements Serializable {

	private static final long serialVersionUID = -9006913712525503650L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReportId", updatable = false)
	private Integer reportId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", referencedColumnName = "username")
	@JsonBackReference
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employee;

	@Column(name = "Title", nullable = false)
	private String title;

	@Column(name = "Description", nullable = false)
	private String description;

	@Column(name = "Priority", nullable = false)
	private String priority;

	public Report(Integer reportId, Employee employee, String title, String description, String priority) {
		super();
		this.reportId = reportId;
		this.employee = employee;
		this.title = title;
		this.description = description;
		this.priority = priority;
	}

	public Report() {
		super();
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

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

	public String getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		if (priority != null) {
			this.priority = priority.getPriorityValue();
		} else {
			this.priority = null;
		}
	}

}
