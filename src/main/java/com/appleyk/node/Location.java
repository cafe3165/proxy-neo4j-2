package com.appleyk.node;

import org.neo4j.ogm.annotation.GraphId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public  class Location  {

	@GraphId
	private Long id;
	private String NN;
	private String LName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNN() {
		return NN;
	}
	public void setNN(String nN) {
		NN = nN;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	
	
}