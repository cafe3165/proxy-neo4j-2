package com.appleyk.RelationRepository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.LocatedIn;



public interface DeviceLocatedInRepository extends GraphRepository<LocatedIn>{

	
	
	@Query("MATCH(d:Device),(l:Location) WHERE d.lName=l.lName"
			+ " CREATE p = (d)-[r:LocatedIn]->(l) return p ")
	List<LocatedIn> createlocatedIn();
}
