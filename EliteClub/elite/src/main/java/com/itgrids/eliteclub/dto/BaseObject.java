package com.itgrids.eliteclub.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class BaseObject implements Serializable {
	
	private int  id;
	
	private String name;

	public int getId() {
		return id;
	}
	 @XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	 @XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
