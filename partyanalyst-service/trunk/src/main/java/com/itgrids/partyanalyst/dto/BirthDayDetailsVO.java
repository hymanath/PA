package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BirthDayDetailsVO implements Serializable {

	private List<IdNameVO> idNameVOList = new ArrayList<IdNameVO>(0);
	private List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>(0);
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	public List<TdpCadreVO> getTdpCadreVOList() {
		return tdpCadreVOList;
	}
	public void setTdpCadreVOList(List<TdpCadreVO> tdpCadreVOList) {
		this.tdpCadreVOList = tdpCadreVOList;
	}

	
}
