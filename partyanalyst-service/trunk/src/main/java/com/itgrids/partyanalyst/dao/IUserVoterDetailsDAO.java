/* 
 * Copyright (c) 2009 IT Grids.

 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.hibernate.UserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;

public interface IUserVoterDetailsDAO extends GenericDao<UserVoterDetails, Long>{

	public List<UserVoterDetails> getUserVoterDetails(Long voterId,Long userId);
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId);
	
	public List<Object[]> getCasteByVoterId(Long userId,List<Long> voterId);
	
	public void updateUserVoterPartyDetails(Long voterId,Long userId,Long partyId,Long castStateId);
	
	public void updateUserVoterCastDetails(Long voterId,Long userId,Long partyId,Long castStateId);

	public List<UserVoterDetails> getAllUserVoterDetails(List<Long> voterIds,Long userId);
	
	public List<Object[]> getUserVoterDetailsByVoterIds(List<Long> voterIds,Long userId);
	
	public List<UserVoterDetails> getUserVoterDtlsVoterIds(List<Long> voterIds,Long userId);
	
	public List<Object[]> getPartyAndCasteDetails(Long voterId,Long userId);
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long hamletId);
	public List<Object[]> getHamletsIdsForUser(Long panchayatId , Long userId);
    

	
	
	/** This Method is used to get VoterIds based on hamletId and UserId  */
	

	  public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId);
	
	
	
	public List<Object[]> getVotersCountByGenderForHamlet(Long userId , Long hamletId);
	
	public List<Voter> getVoterDetailsByHamletId(Long hamletId , Long userId);
	
	public List<Long> getUserHamletsByPanchayatId(Long userId , Long panchayatId);
	public void updateUserVoterDetails2(Long voterId,Long userId,Long partyId,Long localitityId,Long hamletId);
	public void updateUserVoterDetails3(Long voterId,Long userId,Long castStateId,Long localitityId,Long hamletId);
	public void updateUserVoterDetailsForLocality(Long voterId,Long userId,Long localitityId, Long hamletId);
   
public List<Long> getVotersCountForALocality(Long hamletId,Long id,Long userId);
	
	public List<Object[]> getVotersCountByGenderForLocalityInHamlet(Long userId , Long hamletId , Long localityId);
	public List<Long> getVoterIdsBasedOnHamletAndLocality(Long hamletId ,Long localityId , Long userId);
	
	public Long getTotalVotersCountInALocality(Long userId ,Long hamletId,Long localityId,Long publicationDateId);
	public List<Long> getVoterIdsForuserByHamletIdsByCaste(Long userId ,Long hamletId,Long casteStateId);
	
	public List<Object[]> getVotersCountByGenderForLocalAreas(List<?> voterIds);
	public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds);
	public List<?> getVoterIdsBasedOnVoterIdsAndPublication(
			 Long publicationDateId , List<?> voterIds);
	public List<?> getVotersIdsByHamletId(Long hamletId,Long userId);
	
	public List<Long> getVoterIdsByLocalityForUser(Long localisyId,Long hamletId,Long userId,Long casteStateId);
	public List<Object[]> getAgeWiseInfoForUser(List<?> voterIds);
	public List<Object> getHamletsIdsForUserByPanchayat(Long panchayatId , Long userId );
	public List<Object[]> getAgeDataForPanchayatUser(List<?> voterIds);
	 public List<?> getVotersDetailsByHamletPublication(Long hamletId, Long userId, Integer startIndex,
				Integer maxRecords, String order, String columnName);
	 public List<?> getVotersBasedOnVoterIdsAndPublication(Long publicationDateId , List<?> voterIds,String coloumName , String order);
	 public List<?> getVotersCountByHamlet(Long hamletId,Long userID);
	 
	 public List<Object[]> getTotalVotersCountInABooth(Long userId ,Long boothId,Long publicationDateId);
	 public List<Long> getUserHamletsByBoothId(Long userId , Long boothId , Long pubId);
	 public List<Object[]> getTotalVotersCountInABoothForHamlet(Long userId ,Long hamleId,Long publicationDateId);
	 public List<Long> getUserBoothsByHamletId(Long userId , Long hamletId , Long pubId);
	 public List<Voter> getVoterIdsForuserinHamletByBoothsandByCasteId(Long userId ,Long hamletId,Long casteStateId ,long boothId,long publicationId);
	 public List<Object[]> getAgeDataForBoothByHamlets(Long userId,Long publicationDateId,Long boothId,String type );
	 public List<Long> getHamletsExistedInABoothForUser(Long userId,Long  id,Long  publicationDateId,Long  constituencyId);
	 
	 public List<Object[]> getVoterDataForHamlet(Long hamletId , Long publicationId ,Long startIndex, Long maxIndex , String sort,String order );

	 public List<Object[]> getAgeDataForPanchayatUser(List<?> voterIds,String male,String female,long ...age);
	 
	 public List<Object[]> getAgeDataForBoothByHamlets(Long userId,Long publicationDateId,Long boothId ,String type,String male,String female,long ...ages);
	 
	 public List<Object[]> getAgeWiseInfoForUser(List<?> voterIds,String male,String female,long ...ages);
	 
	 public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds,String male,String female,long ...ages);

	 public List<Object[]> getWardsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId);
}
