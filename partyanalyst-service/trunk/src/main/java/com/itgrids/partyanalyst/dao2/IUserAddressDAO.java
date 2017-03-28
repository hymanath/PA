package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAddress;

public interface IUserAddressDAO extends GenericDao<UserAddress, Long>  {
	
	public Integer deleteInfluencingPeopleById(Long userAddressId);
	
	public List<UserAddress> getUserAddressList();
	
	public List<UserAddress> getUserAddressByUserAddressId(Long userAddressId);
	public List<Object[]> getUserAddressDetails(List<Long> candidateIdsList);
	public List<Object[]> getGrievanceStatusCountsByTypeOfIssue(Long id,String searchType);
	public List<Object[]> getGrievanceStatusWiseCountsByTypeOfIssueAndStatus(Long id,String searchType);
	public List<String> getCompletedStatus();
	public List<Object[]> getGrievanceRequestCountsByTypeOfIssue(Long id,String searchType);
	public Long getLocalElectionBodyByUserAddress(Long userAddressId);
	public List<Object[]> getUserAddressDetailsByMinuteId(Long userAddressId);
	public List<Object[]> getUserTypeWiseLocationName(Long stateId,Long userType);
	public List<Object[]> getLocationTypeWiseLocationName(Long stateId,String LocationType,Long accessLevelId,List<Long> accessLevelValue,String isKuppamExcluded);
	public List<Object[]> getConstituencyIdAndName(Long stateId,String LocationType);
}
