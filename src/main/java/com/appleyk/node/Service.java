package com.appleyk.node;

import org.neo4j.ogm.annotation.GraphId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public  class Service  {

	@GraphId
	private Long id;
	private String name;
	private String LName;
	private String CType;
	private String State;
	private String Effect;
	private String DName;
	private double Value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getCType() {
		return CType;
	}

	public void setCType(String cType) {
		CType = cType;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getEffect() {
		return Effect;
	}

	public void setEffect(String effect) {
		this.Effect = effect;
	}

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public double getValue() {
		return Value;
	}

	public void setValue(double value) {
		Value = value;
	}

	public Service () {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
