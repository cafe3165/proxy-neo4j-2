package com.appleyk.RelationRepository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.Increase;



public interface IncreaseRepository extends GraphRepository<Increase>{

	@Query("MATCH(s:Service),(c:Context) WHERE s.lName=c.lName AND s.cType=c.cType "
	+ "AND s.effect='Increase' "
	+ " CREATE p = (s)-[r:Increase]->(c) return p ")

	List<Increase> createIncrease();
}


