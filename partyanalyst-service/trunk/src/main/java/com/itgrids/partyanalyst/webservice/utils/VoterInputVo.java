package com.itgrids.partyanalyst.webservice.utils;

import java.util.List;

/**
 * @author Anilkumar Ravula
 *
 */
public class VoterInputVo {
	
	private long   boothId;
	private long   constituencyId;
	private String publicationDateId;
	private String  publicationName;
	private String type;
	private List<Long>   boothIds;
	


	public String getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(String publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public String getPublicationName() {
		return publicationName;
	}
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getBoothId() {
		return boothId;
	}
	public void setBoothId(long boothId) {
		this.boothId = boothId;
	}
	public List<Long> getBoothIds() {
		return boothIds;
	}
	public void setBoothIds(List<Long> boothIds) {
		this.boothIds = boothIds;
	}
	public long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	
	

}
