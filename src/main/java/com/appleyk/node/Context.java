package com.appleyk.node;

import org.neo4j.ogm.annotation.GraphId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Context {
	@GraphId
	private Long id;
	private String NN;
	private String UName;
	private String LName;
	private String CType;
	private double Value;
	private double RMin;
	private double RMax;
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
	public String getUName() {
		return UName;
	}
	public void setUName(String uName) {
		UName = uName;
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
	public double getValue() {
		return Value;
	}
	public void setValue(double value) {
		Value = value;
	}
	public double getRMin() {
		return RMin;
	}
	public void setRMin(double rMin) {
		RMin = rMin;
	}
	public double getRMax() {
		return RMax;
	}
	public void setRMax(double rMax) {
		RMax = rMax;
	}
	
	
}