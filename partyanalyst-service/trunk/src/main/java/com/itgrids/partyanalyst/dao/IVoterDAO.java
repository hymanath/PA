package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Voter;

public interface IVoterDAO extends GenericDao<Voter, Long>{

	List<Voter> findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo(
			String firstName, String lastName, String relativeFirstName,
			String relativeLastName, String voterIDCardNo);

	public List findCastWiseVotersForMandal(Long mandalID);
	
	public List findGenderAgeWiseVotersForMandal(Long mandalID,Long minAge, Long maxAge);
	
	public List getCastCatageory();
	
	public List getCastSubatageroryDetails(String castCatageory);
	
	public List getsubCastCatageoryCastDetails(String castCatageory,String subCastCatageory);
	
	public List getCastDetails(String castCatageory);
}
