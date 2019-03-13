package com.appleyk.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.appleyk.node.User;


@Repository
public interface UserRepository extends GraphRepository<User>{

//	List<Device> getDevicesByName(@Param("DName") String DName);
	
}