package com.appleyk.relation;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.appleyk.node.Context;
import com.appleyk.node.Service;
@RelationshipEntity(type = "Reduce")

public class Reduce {
	@GraphId
	private Long id;
	@StartNode
	private Service startNode;
	@EndNode
	private Context endNode;
	
	public Reduce() {
		
	}
	public Reduce(Service startNode,Context endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Service getStartNode() {
		return startNode;
	}
	public void setStartNode(Service startNode) {
		this.startNode = startNode;
	}
	public Context getEndNode() {
		return endNode;
	}
	public void setEndNode(Context endNode) {
		this.endNode = endNode;
	}
	
	
	
	
}