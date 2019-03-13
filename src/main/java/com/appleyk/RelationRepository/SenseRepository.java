package com.appleyk.RelationRepository;


import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.Sense;



public interface SenseRepository extends GraphRepository<Sense>{

	
	
	@Query("MATCH(u:User),(c:Context) WHERE u.uName=c.uName AND u.lName=c.lName "
			+ " CREATE p = (u)-[r:Sense]->(c) return p ")
	List<Sense> createSense();
}
