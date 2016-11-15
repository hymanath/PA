package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class TdpCadrePrintDetailsVO implements Serializable{

	private static final long serialVersionUID = 4299162116552754346L;

	private Long   tdpCadreId;
	private String memberShipId;
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	
}
