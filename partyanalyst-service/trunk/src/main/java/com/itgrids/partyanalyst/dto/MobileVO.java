package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MobileVO implements Serializable{
	
	private Long id;
	private Long count = 0l;
	private String name;
	private String status;
	private List<MobileVO> list = new ArrayList();
	private int resultCode ;
	private Long total;
	private List<MobileVO> tehsilList = new ArrayList();
	private List<MobileVO> panchayatList = new ArrayList();
	private List<MobileVO> constituencyList = new ArrayList();
	
	
	
	public List<MobileVO> getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(List<MobileVO> tehsilList) {
		this.tehsilList = tehsilList;
	}
	public List<MobileVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<MobileVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public List<MobileVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<MobileVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public List<MobileVO> getList() {
		return list;
	}
	public void setList(List<MobileVO> list) {
		this.list = list;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
