package com.appleyk.Proxy.runtime;

public class AirCleanerImpl implements AirCleaner{
	private String type;
	private float PM2_5;
	
	@Override
	public void purify() {
		// TODO Auto-generated method stub
//		PM2_5-=0.5;
	}

	@Override
	public void setPM2_5(float pm) {
		// TODO Auto-generated method stub
		PM2_5=pm;
	}

	@Override
	public float getPM2_5() {
		// TODO Auto-generated method stub
		return PM2_5;
		
	}
	
	

}
