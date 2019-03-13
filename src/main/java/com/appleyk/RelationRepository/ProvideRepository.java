package com.appleyk.RelationRepository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.appleyk.relation.Provide;

public interface ProvideRepository extends GraphRepository<Provide>{

	/**
	 * 查询关系
	 * @param relationName
	 * @return
	 */
	@Query("match p = (n)-[r:Provide]-(b) return p")
	List<Provide> getProvides();
	
	
	/**
	 * 为两个已经存在的节点添加关系
	 * @param startNodeID -- 起始节点
	 * @param endNodeID   -- 终止节点
	 * @param rID         -- 关系的ID
	 * @param year        -- 关系的开始年限
	 * @param reason	  -- 关系产生的原因
	 * @return
	 */
	@Query("match(a:Device),(b:Service) where a.dName={0} and b.dName = {1}"
			+ " create p = (a)-[r:Provide]->(b) return p ")
	List<Provide> createLikes(String s,String e);
	
	@Query("MATCH(d:Device),(s:Service) WHERE d.dName=s.dName AND d.lName=s.lName"
			+ " CREATE p = (d)-[r:Provide]->(s) return p ")
	List<Provide> createProvide();
}
