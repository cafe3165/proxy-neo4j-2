
package com.appleyk.Proxy.device;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:34
 **/
public class Gree {
	
	private double Temperature;
	private String id;
	private String locationName;
	private String deviceName;
	private String status;
    
	public String getLocation() {
		return locationName;
	}

	public void setLocation(String location) {
		this.locationName = location;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void cool() {
		Temperature-=0.5;
        System.out.println("格力空调降温");
        System.out.println(Temperature);
    }
 
	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double t) {
		Temperature = t;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}