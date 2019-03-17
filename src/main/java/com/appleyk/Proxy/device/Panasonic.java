
package com.appleyk.Proxy.device;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:36
 **/
public class Panasonic {

	private double Temperature;
	private String id;
	private String location;
	private String deviceName;
	private String status;
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if(status.equals("on")) {
			System.out.println("松下空调打开。");
		}
		else {
			System.out.println("松下空调关闭。");
		}

		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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


	public void down() {
    	Temperature-=0.5;
        System.out.println("松下空调开始进行降温操作。");
        System.out.println("目标温度值为： "+Temperature);
    }
    
	public void up() {
		Temperature+=0.5;
        System.out.println("松下空调开始进行升温操作。");
        System.out.println("目标温度值为： "+Temperature);
	}
   
	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double t) {
		System.out.println("松下空调温度设置为： "+t);
		Temperature = t;
	}
}