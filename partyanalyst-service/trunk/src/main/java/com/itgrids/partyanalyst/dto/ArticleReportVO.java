package com.itgrids.partyanalyst.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticleReportVO implements Serializable{

	
	
	private Long districtId;
	private String districtName;
	private Long newsPaperId;
	private String newsPaper;
	private List<Long> editionIds;
	private String aliasName;
	private Long partyId;
	private String partyName;
	private Long count;
	private Long mainColSpanCount;
	private Long colSpanCount;
	private Long id;
	private Long totalCount;
	 
	private List<ArticleReportVO> districtList = new ArrayList<ArticleReportVO>();
	private List<ArticleReportVO>  papersList;
	private List<ArticleReportVO> typeList;
	private List<ArticleReportVO> partiesList;
    private List<ArticleReportVO> oppenentsList;
	
     private ArticleReportVO  replicaVO;
	 
	
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<ArticleReportVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<ArticleReportVO> districtList) {
		this.districtList = districtList;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<ArticleReportVO> getPapersList() {
		return papersList;
	}

	public void setPapersList(List<ArticleReportVO> papersList) {
		this.papersList = papersList;
	}

	public Long getNewsPaperId() {
		return newsPaperId;
	}

	public void setNewsPaperId(Long newsPaperId) {
		this.newsPaperId = newsPaperId;
	}

	public String getNewsPaper() {
		return newsPaper;
	}

	public void setNewsPaper(String newsPaper) {
		this.newsPaper = newsPaper;
	}

	public List<Long> getEditionIds() {
		return editionIds;
	}

	public void setEditionIds(List<Long> editionIds) {
		this.editionIds = editionIds;
	}

	

	public List<ArticleReportVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<ArticleReportVO> partiesList) {
		this.partiesList = partiesList;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<ArticleReportVO> getOppenentsList() {
		return oppenentsList;
	}

	public void setOppenentsList(List<ArticleReportVO> oppenentsList) {
		this.oppenentsList = oppenentsList;
	}

	public Long getMainColSpanCount() {
		return mainColSpanCount;
	}

	public void setMainColSpanCount(Long mainColSpanCount) {
		this.mainColSpanCount = mainColSpanCount;
	}

	public Long getColSpanCount() {
		return colSpanCount;
	}

	public void setColSpanCount(Long colSpanCount) {
		this.colSpanCount = colSpanCount;
	}

	public ArticleReportVO getReplicaVO() {
		return replicaVO;
	}

	public void setReplicaVO(ArticleReportVO replicaVO) {
		this.replicaVO = replicaVO;
	}

	public List<ArticleReportVO> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<ArticleReportVO> typeList) {
		this.typeList = typeList;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
