package com.appleyk.repository;


import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.appleyk.node.Context;

@Repository
public interface ContextRepository extends GraphRepository<Context>{

//	List<Device> getDevicesByName(@Param("DName") String DName);
	
}
