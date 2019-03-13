package com.appleyk.RelationRepository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.LocatedIn;



public interface ServiceLocatedIn extends GraphRepository<LocatedIn>{

	
	
	@Query("MATCH(s:Service),(l:Location) WHERE s.lName=l.lName"
			+ " CREATE p = (s)-[r:LocatedIn]->(l) return p ")
	List<LocatedIn> createservicelocatedIn();
}
