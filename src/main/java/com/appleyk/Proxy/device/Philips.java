package com.appleyk.Proxy.device;

public class Philips {
	private float PM2_5;

	public float getPM2_5() {
		return PM2_5;
	}

	public void setPM2_5(float pM2_5) {
		PM2_5 = pM2_5;
	}
	
	public void ReducePM2_5() {
		PM2_5-=10.0;
	}

}
