package com.ranking.presentation.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewUserDto {

	@NotEmpty(message = "Field email is required")
	private String email;

	@NotEmpty(message = "Field firstName is required")
	private String firstName;

	@NotEmpty(message = "Field lastName is required")
	private String lastName;

	@NotEmpty(message = "Field gender is required")
	private String gender;

	@NotNull(message = "Field birthdayDate is required")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthdayDate;

	@NotEmpty(message = "Field password is required")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
