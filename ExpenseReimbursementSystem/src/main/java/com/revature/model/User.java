package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String user_role;
	
	public User() {
		
	}
	
	public User(int id, String username, String firstName, String lastName, String password, String user_role) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstName;
		this.lastname = lastName;
		this.password = password;
		this.user_role = user_role; 
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public User(String username, String password) {
		// TODO Auto-generated constructor stub
		this.username = username; 
		this.password = password; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", firstName=" + firstname + ", lastName=" + lastname
				+ ", password=" + password + ", user_role=" + user_role + "]";
	}

}
