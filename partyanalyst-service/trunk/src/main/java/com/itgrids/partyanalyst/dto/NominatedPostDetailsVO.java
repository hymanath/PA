package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class NominatedPostDetailsVO {

	private Long id;
	private String name;
	
	private List<NominatedPostCandidateDtlsVO> subList1 = new ArrayList<NominatedPostCandidateDtlsVO>();
	private List<NominatedPostCandidateDtlsVO> subList2 = new ArrayList<NominatedPostCandidateDtlsVO>();
	
	private List<NominatedPostDetailsVO> subList = new ArrayList<NominatedPostDetailsVO>();
	
	
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
	public List<NominatedPostCandidateDtlsVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<NominatedPostCandidateDtlsVO> subList1) {
		this.subList1 = subList1;
	}
	public List<NominatedPostCandidateDtlsVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<NominatedPostCandidateDtlsVO> subList2) {
		this.subList2 = subList2;
	}
	public List<NominatedPostDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NominatedPostDetailsVO> subList) {
		this.subList = subList;
	}
	
	
}
