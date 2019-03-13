
package com.appleyk.Proxy.runtime;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:38
 **/
public class AirConditionImpl implements AirCondition {

    //空调类型
    private String type;
    private double Temperature ;
    private String ID;
    private String locationName;
	private String deviceName;
	private String status;
	

    @Override
    public void cool() {
    
    }


	@Override
	public void setT(double temperature) {
		// TODO Auto-generated method stub
		Temperature=temperature;
	}


	@Override
	public double getT() {
		// TODO Auto-generated method stub
		return Temperature;
	}


	@Override
	public void setID(String ID) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}


	@Override
	public void setLName(String LName) {
		// TODO Auto-generated method stub
		locationName=LName;
	}


	@Override
	public String getLName() {
		// TODO Auto-generated method stub
		return locationName;
	}


	@Override
	public void setDName(String DName) {
		// TODO Auto-generated method stub
		deviceName=DName;
	}


	@Override
	public String getDName() {
		// TODO Auto-generated method stub
		return deviceName;
	}


	@Override
	public void setStatus(String Status) {
		// TODO Auto-generated method stub
		status=Status;
	}


	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	
	
	
}