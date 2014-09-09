
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HouseHoldVoter;
import com.itgrids.partyanalyst.model.HouseHoldsFamilyDetails;

public interface IHouseHoldVoterDAO extends GenericDao<HouseHoldVoter, Long>{
	public List<Object[]> getVoterRelationsByVoterIds(List<Long> voterIds);
	public List<Object[]> getHouseHoldIdOfFamilyHeadForVoter(String houseNo,Long panchayatId,Long localBodyId);
	public List<Object[]> getHouseHoldIdOfVoter(String houseNo);
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId(Long houseHoldsId);
	
	public Long getHouseHoldIdForVoter(Long voterId);
	public List<HouseHoldsFamilyDetails> getFamilyMembersDetailsByHouseHoldsId(Long houseHoldsId);
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId1(Long houseHoldsId);
	public List<HouseHoldVoter> getHouseHoldVoterDetailsByFamilyMemberId(Long houseHoldFamilyMemberId);
	public List<Object[]> getOwnerMobileAndLeaderIdForVoterId(Long voterId);
	public List<HouseHoldVoter> checkExistanceOfVoterInHouseHoldsVoter(Long voterId);
	
	public int childMembersDelete(Long voterFamilyId);
	public int updateStatusIfVoterIdExist(Long voterId);
	
	public List<Long> getVoterIdsExistByVoterIds(List<Long> voterIds);
	
	public List<HouseHoldVoter> getTotalVoters();
	public List<HHSurveyAnswers> getAllSurveyAnswers(List<Long> houseHoldIds);
	public List<Object[]> getAllQuestions();
	public List<Long> getHouseHoldsOfLeader(Long leaderId);
	public List<Long> getBooksOfHouseHoldsOfLeader(Long leaderId,Long constituencyId);
	
	public Long getBookIdOfVoter(Long voterId);
	public List<Object[]> getAllLeadersBooksFamilies(Long constituencyId);
	public List<Object[]> getFamilyHeadsInPanchayat(Long PanchayatId);
	public List<Object[]> getLeadersAndCountInLocality(Long locationId);
	
	public List<Object[]> getFamilyAndVotersCountInHouseHolds(Long val,int type);
	public List<Object[]> getFamilyHeadsUnderLeader(Long leaderId);
	
	public List<Object[]> getAllPanchayatsInHouseHoldsOfConstituency(Long constituencyId);
	public List<Object[]> getVoterAndNonVoterCountInConstituency(Long constituencyId);
	public List<Object[]> getHouseHoldsCountInConstituency(Long constituencyId);
	
	public List<Object[]> getConstituenciesOfHouseHolds();
	public List<Object[]> getActiveLeadersOfConstituency(Long constituencyId);
	
	public List<Object[]> getFamilyHeadsUnderBook(Long bookId);
	public List<Object[]> getNonVotersAgeGroupInHouseHolds(Long val,int type, Long fromAge,Long toAge);
	public List<Object[]> getNonVoterAgeRangesInConstituency(Long constituencyId,Long fromAge,Long toAge);
	
	public List<Object[]> getBooksOfHouseHolds(Long constituencyId);
	public List<Object[]> getNonVotersInConstituencyWithAgeRange(Long constituencyId,Long fromAge,Long toAge);
	public List<Object[]> getVoterAndNonVoterCountInConstituency1(Long constituencyId);
	
	public List<Object[]> getBooksOfHouseHoldsNV(Long constituencyId);
	public List<Object[]> getBooksOfHouseHoldsHHCount(Long constituencyId);
	
	public List<Object[]> getHouseHoldsCountInConstituency1(Long constituencyId);
	
	public List<Object[]> getBooks(Long constituencyId);
	public List<Object[]> getBookWiseHouseHolds(Long constituencyId);
	public List<Object[]> getFamilyAndVotersCountInHouseHoldsNew(Long val,int type);
	public List<Object[]> getFamilyAndVotersCountInHouseHoldsNew1(Long val,Long type);
}