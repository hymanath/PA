package com.itgrids.partyanalyst.dto;

import java.util.List;

import com.itgrids.partyanalyst.excel.booth.VoterVO;

public class ConstituencyManagementVO {

	private ProblemManagementVO problemManagementVO;
	private List<VoterVO> voterDetails;
	private VoterCastInfoVO VoterCastInfodetails;
	private List<VoterHouseInfoVO> votersByHouseNos;
	private List<LocalLeadersVO> localLeaders;

	public ProblemManagementVO getProblemManagementVO() {
		return problemManagementVO;
	}

	public void setProblemManagementVO(ProblemManagementVO problemManagementVO) {
		this.problemManagementVO = problemManagementVO;
	}
	
	public List<VoterVO> getVoterDetails() {
		return voterDetails;
	}

	public void setVoterDetails(List<VoterVO> voterDetails) {
		this.voterDetails = voterDetails;
	}

	public VoterCastInfoVO getVoterCastInfodetails() {
		return VoterCastInfodetails;
	}
	
	public void setVoterCastInfodetails(VoterCastInfoVO voterCastInfodetails) {
		VoterCastInfodetails = voterCastInfodetails;
	}

	public List<VoterHouseInfoVO> getVotersByHouseNos() {
		return votersByHouseNos;
	}

	public void setVotersByHouseNos(List<VoterHouseInfoVO> votersByHouseNos) {
		this.votersByHouseNos = votersByHouseNos;
	}

	public List<LocalLeadersVO> getLocalLeaders() {
		return localLeaders;
	}

	public void setLocalLeaders(List<LocalLeadersVO> localLeaders) {
		this.localLeaders = localLeaders;
	}
	
	

	
}
