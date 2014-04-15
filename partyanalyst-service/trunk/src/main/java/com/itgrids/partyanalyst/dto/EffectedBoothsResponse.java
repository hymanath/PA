package com.itgrids.partyanalyst.dto;
import java.util.List;

public class EffectedBoothsResponse {
private List<PanchayatCountVo> panchayats;
	
	
	private String url;
	public List<PanchayatCountVo> getPanchayats() {
		return panchayats;
	}




	
	public String getUrl() {
		return url;
	}





	public void setUrl(String url) {
		this.url = url;
	}





	public void setPanchayats(List<PanchayatCountVo> panchayats) {
		this.panchayats = panchayats;
	}
}
