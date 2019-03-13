package com.appleyk.RelationRepository;


import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.Reduce;



public interface ReduceRepository extends GraphRepository<Reduce>{

	
	
	@Query("MATCH(s:Service),(c:Context) WHERE s.lName=c.lName AND s.cType=c.cType "
			+ "AND s.effect='Reduce' "
			+ " CREATE p = (s)-[r:Reduce]->(c) return p ")
	List<Reduce> createReduce();
}
