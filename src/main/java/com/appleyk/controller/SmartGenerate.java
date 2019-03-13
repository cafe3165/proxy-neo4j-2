package com.appleyk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmartGenerate {
	@RequestMapping("/GenD")
	public String GenerateDevices() {
		boolean flag=true;
		
		
		
		
		if(flag) {
			return ("Success");
		}
		else {
			return ("Wrong");
		}
	}
	

}
