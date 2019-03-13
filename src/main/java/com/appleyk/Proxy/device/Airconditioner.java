package com.appleyk.Proxy.device;

import java.util.ArrayList;
import java.util.List;

public class Airconditioner {
	List<Object> l=new ArrayList<Object>();
	public void addlist(Object o) {
		l.add(o);
	}
	
	public List<Object> list(){
		
		
		return l;
	}

}
