package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ImageCheckVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long tdpCadreId;
	private String image;
	private String name;
	
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
