package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CardsValidateVO implements Serializable{
	
	/* DONT ADD EXTRA PARAMETERS HERE ,
	 *  BCOZ THIS CLASS HAS MULTIPLE DEPENDENCIES IN QA PRINT APP ANDROID DASHBOARD WEB SERVICE CALLS*/ 
	
	private static final long serialVersionUID = -7827216125611040067L;
	
	private Long id;
	private String name;
	private String boxNo;
	
	private Long boxesCount = 0l;
	private Long validatedCards = 0l;
	private Long acceptedCards = 0l;
	private Long rejectedCards = 0l;
	
	private Long acceptedBoxes = 0l;
	private Long rejectedBoxes = 0l;
	
	private float acceptedPer ;
	private float rejectedPer;
	
	private String status;
	private String message;
	
	private List<CardsValidateVO> subList;
	private Map<String,CardsValidateVO> subMap;
	
	public CardsValidateVO(){}
	
	public CardsValidateVO(Long id , String name){
		this.id = id;
		this.name = name;
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
	public Long getBoxesCount() {
		return boxesCount;
	}
	public void setBoxesCount(Long boxesCount) {
		this.boxesCount = boxesCount;
	}
	public Long getValidatedCards() {
		return validatedCards;
	}
	public void setValidatedCards(Long validatedCards) {
		this.validatedCards = validatedCards;
	}
	public Long getAcceptedCards() {
		return acceptedCards;
	}
	public void setAcceptedCards(Long acceptedCards) {
		this.acceptedCards = acceptedCards;
	}
	public Long getRejectedCards() {
		return rejectedCards;
	}
	public void setRejectedCards(Long rejectedCards) {
		this.rejectedCards = rejectedCards;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CardsValidateVO> getSubList() {
		return subList;
	}

	public void setSubList(List<CardsValidateVO> subList) {
		this.subList = subList;
	}

	public Long getAcceptedBoxes() {
		return acceptedBoxes;
	}

	public void setAcceptedBoxes(Long acceptedBoxes) {
		this.acceptedBoxes = acceptedBoxes;
	}

	public Long getRejectedBoxes() {
		return rejectedBoxes;
	}

	public void setRejectedBoxes(Long rejectedBoxes) {
		this.rejectedBoxes = rejectedBoxes;
	}

	public Map<String, CardsValidateVO> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<String, CardsValidateVO> subMap) {
		this.subMap = subMap;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public float getAcceptedPer() {
		return acceptedPer;
	}

	public void setAcceptedPer(float acceptedPer) {
		this.acceptedPer = acceptedPer;
	}

	public float getRejectedPer() {
		return rejectedPer;
	}

	public void setRejectedPer(float rejectedPer) {
		this.rejectedPer = rejectedPer;
	}
	
}
