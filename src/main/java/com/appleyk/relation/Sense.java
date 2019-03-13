package com.appleyk.relation;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.appleyk.node.Context;
import com.appleyk.node.User;
@RelationshipEntity(type = "Sense")

public class Sense {
	@GraphId
	private Long id;
	@StartNode
	private User startNode;
	@EndNode
	private Context endNode;
	
	public Sense() {
		
	}
	public Sense(User startNode,Context endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getStartNode() {
		return startNode;
	}
	public void setStartNode(User startNode) {
		this.startNode = startNode;
	}
	public Context getEndNode() {
		return endNode;
	}
	public void setEndNode(Context endNode) {
		this.endNode = endNode;
	}
	
	
	
}