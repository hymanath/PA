package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SubscriptionsVO implements Serializable{

	private Long userId;
	private String name;
	private String type;
	private String imageURL;
	private Long id;
	private String description;
	private boolean isSubscribed;
	private String title;
	private List<SpecialPageVO> specialPageVOList;
	private SpecialPageVO specialPageVO;
	private String tempVar;
	private boolean flag = false;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isSubscribed() {
		return isSubscribed;
	}
	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<SpecialPageVO> getSpecialPageVOList() {
		return specialPageVOList;
	}
	public void setSpecialPageVOList(List<SpecialPageVO> specialPageVOList) {
		this.specialPageVOList = specialPageVOList;
	}
	public SpecialPageVO getSpecialPageVO() {
		return specialPageVO;
	}
	public void setSpecialPageVO(SpecialPageVO specialPageVO) {
		this.specialPageVO = specialPageVO;
	}
	public String getTempVar() {
		return tempVar;
	}
	public void setTempVar(String tempVar) {
		this.tempVar = tempVar;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
