package com.appleyk.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.appleyk.node.Location;

@Repository
public interface LocationRepository extends GraphRepository<Location>{

//	List<Location> getLocationsByName(@Param("LName") String LName);
	

}