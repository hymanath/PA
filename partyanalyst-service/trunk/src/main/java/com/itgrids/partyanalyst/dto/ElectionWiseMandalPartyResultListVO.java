package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionWiseMandalPartyResultListVO extends ResultStatus {

	List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList;

	public List<ElectionWiseMandalPartyResultVO> getElectionWiseMandalPartyResultVOList() {
		return electionWiseMandalPartyResultVOList;
	}

	public void setElectionWiseMandalPartyResultVOList(
			List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList) {
		this.electionWiseMandalPartyResultVOList = electionWiseMandalPartyResultVOList;
	}
}
