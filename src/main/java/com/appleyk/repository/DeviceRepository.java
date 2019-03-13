package com.appleyk.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.appleyk.node.Device;

@Repository
public interface DeviceRepository extends GraphRepository<Device>{

//	List<Device> getDevicesByName(@Param("DName") String DName);
	
}
