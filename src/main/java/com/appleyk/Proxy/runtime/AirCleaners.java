package com.appleyk.Proxy.runtime;

import java.util.HashMap;
import java.util.List;

public interface AirCleaners {
	List<String> list(Object...objects);
	String add(HashMap<String, String> params);
	void remove(String id);
	String get(String id,String attribute);
	void set(String id,String key,String value);

}
