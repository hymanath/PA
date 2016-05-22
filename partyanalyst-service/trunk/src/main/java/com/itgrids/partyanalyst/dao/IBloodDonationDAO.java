package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.model.BloodDonation;

public interface IBloodDonationDAO extends GenericDao<BloodDonation, Long> {
	
	public List<Object[]> getCadreDetailsOfRegistered(String memberShipId);
	public List<Object[]> getBleedingCadreDetails(List<Long> statusIds,Long campId);
	public List<Object[]> gettotalCollectedBloodDetails(Date fromDate,Date toDate);
	public Long getBloodDonatedOtherThanBloodBank();
	public Long getBloodDonarCountInEmergency();
	public Long getCalledForDonationCount();
	public List<Object[]> gettotalCollectedBloodBagsInfo(Long campId);
	public List<Object[]> getBloodDonorDetailsByAgeGroupingInfo(Date fromDate,Date toDate);
	public Integer updateBloodDonationDetailsByMemberShip(BloodBankVO bloodBankVO);
	public String isTdpCadreExistOrNot(String memberShipNO);
	public List<Object[]> getNumberOfTimesCollectedBlood(Long campId);
	public List<Object[]> getBloodDonorDayWiseCounts(Date campFromDate,Date campToDate );
	//public Object[] getCampDates(Long campId);
	public List<Object[]> getBloodDonorCounts(Date campFromDate,Date campToDate );
}
