
package com.appleyk.Proxy.runtime;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:37
 **/
public interface AirCondition {
    void cool();
    
	void setT(double temperature);
	double getT();
	
	void setID(String ID);
	String getID();
	
	void setLName(String LName);
	String getLName();
	
	void setDName(String DName);
	String getDName();
	
	void setStatus(String Status);
	String getStatus();
	
    
}