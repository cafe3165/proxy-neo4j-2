package com.appleyk.Proxy.virtualObejct;

import java.util.List;

public class Location {
	private String LId;
	private String LName;
	private List<String> uList;
	private List<String> sList;
	private List<String> cList;
	private List<String> dList;
	public String getLId() {
		return LId;
	}
	public void setLId(String lId) {
		LId = lId;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	public List<String> getuList() {
		return uList;
	}
	public void setuList(List<String> uList) {
		this.uList = uList;
	}
	public List<String> getsList() {
		return sList;
	}
	public void setsList(List<String> sList) {
		this.sList = sList;
	}
	public List<String> getcList() {
		return cList;
	}
	public void setcList(List<String> cList) {
		this.cList = cList;
	}
	public List<String> getdList() {
		return dList;
	}
	public void setdList(List<String> dList) {
		this.dList = dList;
	}
	
	
}
