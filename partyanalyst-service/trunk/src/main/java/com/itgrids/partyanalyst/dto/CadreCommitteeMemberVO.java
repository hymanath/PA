package com.itgrids.partyanalyst.dto;

public class CadreCommitteeMemberVO {

	
	private Long id;
	private String name;
	private Long total;
	private String status;
	private Long level;
	private String imagePath;
	private String role;
	private String locationName;
	private String committe;
	
	
	
	public String getCommitte() {
		return committe;
	}
	public void setCommitte(String committe) {
		this.committe = committe;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
