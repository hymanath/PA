package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class NominatedPostDetailsVO {

	private Long id;
	private String name;
	
	private Long totalCount = 0l;
	private Long maleCount = 0l;
	private Long femaleCount = 0l;
	private List<NominatedPostCandidateDtlsVO> subList1 = new ArrayList<NominatedPostCandidateDtlsVO>();
	private List<NominatedPostCandidateDtlsVO> subList2 = new ArrayList<NominatedPostCandidateDtlsVO>();
	
	private List<NominatedPostDetailsVO> subList = new ArrayList<NominatedPostDetailsVO>();
	private Long count = 0l;
	public String Perc= "";
	private List<NominatedPostDetailsVO> list = new ArrayList<NominatedPostDetailsVO>();
	
	
	public List<NominatedPostDetailsVO> getList() {
		return list;
	}
	public void setList(List<NominatedPostDetailsVO> list) {
		this.list = list;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getPerc() {
		return Perc;
	}
	public void setPerc(String perc) {
		Perc = perc;
	}
	
	
}
