package com.itgrids.cardprint.dto;

import java.io.Serializable;
import java.util.List;


public class CardPrintVO implements Serializable{

	private Long id;
	private String name;
	private Long count = 0l;
	private Long cardsCount = 0l;
	private String todayDate;
	
	private List<CardPrintVO> overAllList;
	private List<CardPrintVO> todayList;
	
	public CardPrintVO(){}
	public CardPrintVO(Long id,String name){
		this.id=id;
		this.name=name;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<CardPrintVO> getOverAllList() {
		return overAllList;
	}
	public void setOverAllList(List<CardPrintVO> overAllList) {
		this.overAllList = overAllList;
	}
	public List<CardPrintVO> getTodayList() {
		return todayList;
	}
	public void setTodayList(List<CardPrintVO> todayList) {
		this.todayList = todayList;
	}
	public Long getCardsCount() {
		return cardsCount;
	}
	public void setCardsCount(Long cardsCount) {
		this.cardsCount = cardsCount;
	}
	public String getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}
}
