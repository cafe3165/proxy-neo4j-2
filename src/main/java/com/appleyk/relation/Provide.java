package com.appleyk.relation;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.appleyk.node.Device;
import com.appleyk.node.Service;
@RelationshipEntity(type = "Provide")

public class Provide {
	@GraphId
	private Long id;
	@StartNode
	private Device startNode;
	@EndNode
	private Service endNode;
	
	public Provide() {
		
	}
	
	public Provide(Device startNode,Service endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Device getStartNode() {
		return startNode;
	}

	public void setStartNode(Device startNode) {
		this.startNode = startNode;
	}

	public Service getEndNode() {
		return endNode;
	}

	public void setEndNode(Service endNode) {
		this.endNode = endNode;
	}
	
	
	

}
