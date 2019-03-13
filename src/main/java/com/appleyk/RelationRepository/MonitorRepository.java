package com.appleyk.RelationRepository;


import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.Monitor;



public interface MonitorRepository extends GraphRepository<Monitor>{

	
	
	@Query("MATCH(s:Service),(c:Context) WHERE s.lName=c.lName AND s.cType=c.cType "
			+ "AND s.effect='Monitor' "
			+ " CREATE p = (s)-[r:Monitor]->(c) return p ")
	List<Monitor> createMonitor();
}
