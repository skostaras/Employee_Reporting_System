package com.stefanosk27.reporting.domain;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Employee")
@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 1776083447142151459L;
	
	@Id
	@GeneratedValue
	@Column(name="EmployeeId", updatable = false)
	private Integer employeeId;
	
	@Column(name="FirstName", nullable = false)
	private String firstName;
	
	@Column(name="LastName", nullable = false)
	private String lastName;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="gender", nullable = true)
	private LocalDate gender;
	
	@Column(name="title", nullable = true)
	private String title;
	
	

}
