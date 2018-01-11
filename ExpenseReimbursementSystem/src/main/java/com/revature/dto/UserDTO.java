package com.revature.dto;

public class UserDTO {

	String test_field; 
	
	public UserDTO(){
		test_field = "hello world!"; 
	}

	public String getTest_field() {
		return test_field;
	}

	public void setTest_field(String test_field) {
		this.test_field = test_field;
	}
	
}
