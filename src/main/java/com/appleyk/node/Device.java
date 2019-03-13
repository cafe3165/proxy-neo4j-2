package com.appleyk.node;

import org.neo4j.ogm.annotation.GraphId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Device {
	@GraphId
	private Long id;
	private String NN;
	private String describe;
	private String key;
	private double Value;
	private String DName;
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public double getValue() {
		return Value;
	}
	public void setValue(double value) {
		Value = value;
	}
	public String getDName() {
		return DName;
	}
	public void setDName(String dName) {
		DName = dName;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	
	
	
	
	
// 	@RelatedTo(type="device_has_function")
// 	Set<Function> function=new HashSet<Function>();

//	@Override
//	public String toString() {
//		return "Device [id=" + id + ", deviceName=" + deviceName + ", describe=" + describe + "]";
//	}
//	
	
	
	
}