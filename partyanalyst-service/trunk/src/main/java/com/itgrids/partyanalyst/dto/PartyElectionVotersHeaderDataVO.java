package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

public class PartyElectionVotersHeaderDataVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045937726021556278L;
	private Set<String> header;
	private List<PartyElectionVotersListVO> data;
	
	public Set<String> getHeader() {
		return header;
	}
	public void setHeader(Set<String> header) {
		this.header = header;
	}
	public List<PartyElectionVotersListVO> getData() {
		return data;
	}
	public void setData(List<PartyElectionVotersListVO> data) {
		this.data = data;
	}
}