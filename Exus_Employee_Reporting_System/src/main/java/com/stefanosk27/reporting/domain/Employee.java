package com.stefanosk27.reporting.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.stefanosk27.reporting.enums.Gender;

@Table(name = "Employee")
@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 1776083447142151459L;

	@Id
	@GeneratedValue
	@Column(name = "EmployeeId", updatable = false)
	private Integer employeeId;

	@Column(name = "FirstName", nullable = false)
	private String firstName;

	@Column(name = "LastName", nullable = false)
	private String lastName;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "gender", nullable = true)
	private String gender;

	@Column(name = "title", nullable = true)
	private String title;

	public Employee(Integer employeeId, String firstName, String lastName, String username, String email, String gender,
			String title) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.title = title;
	}

	public Employee(Employee employee) {
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.username = employee.getUsername();
		this.email = employee.getEmail();
		this.gender = employee.getGender();
		this.title = employee.getTitle();
	}

	public Employee() {
		super();
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		if (gender != null) {
			this.gender = gender.getGenderValue();
		} else {
			this.gender = null;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
