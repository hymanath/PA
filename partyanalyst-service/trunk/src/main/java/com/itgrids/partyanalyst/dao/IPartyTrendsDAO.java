/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.entity.Country;
import com.itgrids.partyanalyst.model.PartyTrainingCamps;
import com.itgrids.partyanalyst.model.PartyTrends;


/**
 * Interface for CountryDAO.
 * 
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a>
 */

public interface IPartyTrendsDAO extends GenericDao<PartyTrends, Long> {
	
	public List<?> loadConst();
	public List<?> loadEntitiesForXl(List<Long> constIds );
	public List<Object[]> findAssemblyConstituenciesForSimaAndra(Long electionYear,Long staetId,List<String> areas,List<Long> districIds);
	public List<?> loadConst(List<Long> constIds);
	public List<Long> getConstituencyIds();
	public List<PartyTrends> getAllTrends(Long constiId);
    public List<Object[]> getPreviousTrendsData(Long constId);
    public List<Object[]> getPreviousForPrp(Long constId);
    public List<Object[]> getParliamentCountForPrpAndYsr(Long constId);
    public List<Object[]> getParliamentCountForInc(Long constId);
    public List<?> getPreviousTrendsData(List<Long> partyIds,Long constId);
    
    /**
     * @author Anilkumar 
     * @param partyIds
     * @param constId
     * @return
     */
    public List<?> getPreviousTrendsDataForParleament(List<Long> partyIds,Long constId);
    public List<Object[]> getTotalVotersForConst(Long constId);
   // public List<?> getTotalCountForParleament(Long constId,Long year);
    public List<Object[]> getTotalVotersForConstFormBooth(Long constId,Long year);
   
    //get aliances 
    public List<Long> getWithAlliance(Long partyIds,Long electionId);
    public List<?> getPreviousTrendsDataWithAlliance(List<Long> partyIds,Long constId,Long year);
    
    public List<Object[]> getPanchayatIds(Long districtId);


    public List<?> callStoredProcedure();

//2014 results  anil
    
    public List<?> loadConStituencyIdsWithNo(Long electionId,Long stateId,Long electionScopeId,Long year) ;
    public int updateCandidateReult(Long partyId,Long  electionId,Double votesEarned,Long constituencyId,String votesPercentage,Double marginVotes,String marginPercentage,Long rank);
    public int updateCandidateReultForIndependents(String lastname,Long  electionId,Double votesEarned,Long constituencyId,String votesPercentage,Double marginVotes,String marginPercentage,Long rank);
    public List<Object[]> getVotesPolledForConst(Long  electionId,Long stateId);
    public int updateCandidateElection(Long  electionId,int status,Long constituencyId);
    public int updateNotaVotesInConstElectResult(Long  electionId,long notaVotes,Long constituencyId);
    public List<Object[]> getVotesPolledForConstForParliament(Long  electionId);
    
    public List<Object[]> getVotesPolledAndTotalVotesForConst(Long  electionId,Long stateId,Long constituencyId);
    public List<Object[]> getGendercountBetweenAgeGroup(Long constituencyId,Long publicationDateId,long minAge ,long maxAge);












}