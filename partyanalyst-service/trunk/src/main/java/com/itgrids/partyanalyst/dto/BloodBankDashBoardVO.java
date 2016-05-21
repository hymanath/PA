package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class BloodBankDashBoardVO {
	
	private Long totalApplicants;
	private Long totalAccepted;
	private Long totalRejected;
	private Long dayWiseCount;
	private List<BloodBankDashBoardVO> bloodBankDashBoardVO = new ArrayList<BloodBankDashBoardVO>(0);
	public Long getTotalApplicants() {
		return totalApplicants;
	}
	public void setTotalApplicants(Long totalApplicants) {
		this.totalApplicants = totalApplicants;
	}
	public Long getTotalAccepted() {
		return totalAccepted;
	}
	public void setTotalAccepted(Long totalAccepted) {
		this.totalAccepted = totalAccepted;
	}
	public Long getTotalRejected() {
		return totalRejected;
	}
	public void setTotalRejected(Long totalRejected) {
		this.totalRejected = totalRejected;
	}
	public Long getDayWiseCount() {
		return dayWiseCount;
	}
	public void setDayWiseCount(Long dayWiseCount) {
		this.dayWiseCount = dayWiseCount;
	}
	public List<BloodBankDashBoardVO> getBloodBankDashBoardVO() {
		return bloodBankDashBoardVO;
	}
	public void setBloodBankDashBoardVO(
			List<BloodBankDashBoardVO> bloodBankDashBoardVO) {
		this.bloodBankDashBoardVO = bloodBankDashBoardVO;
	}
	
	
}
