package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SinkVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long 	fid;
	private Long 	vid;
	private String 	uid;
	
	
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Long getVid() {
		return vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
