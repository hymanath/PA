package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import com.itgrids.partyanalyst.dto.SelectOptionVO;;

public class ResultObjectVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> dataList;
	private CrossVotingConsolidateVO crossVotingConsolidateVO;
	
	public ResultObjectVO()	{}
	
	public ResultObjectVO(List<SelectOptionVO> dataList,
			CrossVotingConsolidateVO crossVotingConsolidateVO) {
		
		this.dataList = dataList;
		this.crossVotingConsolidateVO = crossVotingConsolidateVO;
	}

	public List<SelectOptionVO> getDataList() {
		return dataList;
	}

	public void setDataList(List<SelectOptionVO> dataList) {
		this.dataList = dataList;
	}

	public CrossVotingConsolidateVO getCrossVotingConsolidateVO() {
		return crossVotingConsolidateVO;
	}

	public void setCrossVotingConsolidateVO(
			CrossVotingConsolidateVO crossVotingConsolidateVO) {
		this.crossVotingConsolidateVO = crossVotingConsolidateVO;
	}
	
}
