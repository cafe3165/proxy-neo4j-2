package com.appleyk.RelationRepository;


import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.Assign;



public interface AssignRepository extends GraphRepository<Assign>{

	
	
	@Query("MATCH(s:Service),(c:Context) WHERE s.lName=c.lName AND s.cType=c.cType "
			+ "AND s.effect='Assign' "
			+ " CREATE p = (s)-[r:Assign]->(c) return p ")
	List<Assign> createAssign();
}


