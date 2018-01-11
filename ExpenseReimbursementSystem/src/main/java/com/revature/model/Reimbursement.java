package com.revature.model; 

import java.sql.Timestamp;

public class Reimbursement {
    int 		id; 	 	  //    R_ID          NUMBER(15)
    int 		ammount;      //    R_AMOUNT      NUMBER(20) 
    String 		description;  //    R_DESCRIPTION VARCHAR2(100 BYTE) 
    String 		blob; 		  //    R_RECEIPT     BLOB
    Timestamp 	timeSubmited; //    R_SUBMITTED   TIMESTAMP
    Timestamp 	timeResolved; //    R_RESOLVED    TIMESTAMP 
    int 		author_id; 	  //    U_ID_AUTHOR   NUMBER(15) REFERENCES ERS_USERS(U_ID)
    int 		resolver_id;  //    U_ID_RESOLVER NUMBER(15) 
    int 		type; 		  //    RT_TYPE       NUMBER(15) 
    int 		status; 	  //    RT_STATUS     NUMBER
	
    public Reimbursement(int id, int ammount, String description, String blob, Timestamp timeSubmited,
			Timestamp timeResolved, int author_id, int resolver_id, int type, int status) 
	{
		super();
		this.id = id;
		this.ammount = ammount;
		this.description = description;
		this.blob = blob;
		this.timeSubmited = timeSubmited;
		this.timeResolved = timeResolved;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.type = type;
		this.status = status;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlob() {
		return blob;
	}

	public void setBlob(String blob) {
		this.blob = blob;
	}

	public Timestamp getTimeSubmited() {
		return timeSubmited;
	}

	public void setTimeSubmited(Timestamp timeSubmited) {
		this.timeSubmited = timeSubmited;
	}

	public Timestamp getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", ammount=" + ammount + ", description=" + description + ", blob=" + blob
				+ ", timeSubmited=" + timeSubmited + ", timeResolved=" + timeResolved + ", author_id=" + author_id
				+ ", resolver_id=" + resolver_id + ", type=" + type + ", status=" + status + "]";
	}

}