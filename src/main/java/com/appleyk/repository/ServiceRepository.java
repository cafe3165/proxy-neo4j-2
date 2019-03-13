package com.appleyk.repository;
import java.util.List;

//import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appleyk.node.Service;

@Repository
public interface ServiceRepository extends GraphRepository<Service>{

	List<Service> getServicesByName(@Param("name") String name);
	
	/**
	 * 根据年龄查询用户节点
	 * @param age
	 * @return
	 */
//	@Query("match(n:User) where n.age >{age} return n")
//	List<User> getUsers(@Param("age") Integer age);
}
