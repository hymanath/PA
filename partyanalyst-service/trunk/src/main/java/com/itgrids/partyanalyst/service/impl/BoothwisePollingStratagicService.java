package com.itgrids.partyanalyst.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.service.IBoothwisePollingStratagicService;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothwisePollingStratagicService  implements IBoothwisePollingStratagicService{

	public IElectionDAO electionDAO;
	public IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	public IBoothDAO boothDAO;
	public IHamletBoothElectionDAO hamletBoothElectionDAO;
	public IVoterInfoDAO voterInfoDAO;
	public IVoterModificationDAO voterModificationDAO;
	public IConstituencyElectionResultDAO constituencyElectionResultDAO;
	public INominationDAO nominationDAO;
	public ICandidateBoothResultDAO candidateBoothResultDAO;
	public IPartyDAO partyDAO;
	
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}


	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}


	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}


	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}


	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}


	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}


	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}


	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}


	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}


	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}


	public IVoterModificationDAO getVoterModificationDAO() {
		return voterModificationDAO;
	}


	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}


	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}


	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}


	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}


	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}


	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}


	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}


	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}


	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}


	public List<PartyPositionVO> getPollingPercentagesByParty(Long constituenycId,Long partyId,Long electionId,Long electionId1)
	 {
		 Long latestPublictaionId = 0l;
		 Long TotalVotesInConstituency = 0l;
		 Long TotalPolledVotesInConstituency = 0l;
		 Double partyPercentage = 0.0;
		 Map<Long,Map<Long,Long>> boothMap = new HashMap<Long, Map<Long,Long>>();//<boothId,PartyMap<PartyId,polledVotes>)
		 List<PartyPositionVO> result = new ArrayList<PartyPositionVO>();
		 List<Object[]> totalVotesForBooth = null;
		 List<Object[]> totalPolledVotesForBooth = null;
		 Map<Long,Object[]> totalVotersMap = new HashMap<Long, Object[]>();
		 Map<Long,String> panchayatMap = new HashMap<Long, String>();
		 Map<Long,Long> addedVotersMap = new HashMap<Long, Long>();
		 List<Long> electionIdsList = new ArrayList<Long>();
		 DecimalFormat decimalFormat = new DecimalFormat("#.##");
		 try{
			 electionIdsList.add(electionId);
			 electionIdsList.add(electionId1);
			 electionId = electionDAO.getSortedElectionIds(electionIdsList).get(0);
			 List<Long> boothIds = boothConstituencyElectionDAO.getBoothIdsByConstituencyId(constituenycId,electionId);
			 List<Object[]> totalVotersInBooth =boothDAO.getTotalVotesForBooth(boothIds);
			 List<Object[]> panchayatnames = hamletBoothElectionDAO.getPanchayatNamesByBoothIds(boothIds);
			 //latestPublictaionId = publicationDateDAO.getLatestPublicationId();
			 latestPublictaionId = voterInfoDAO.getLatestPublicationDate(constituenycId);
			 List<Object[]> addedVotersCount = voterModificationDAO.getAddedVotersByBoothIds(boothIds,latestPublictaionId,constituenycId);
			 Set<Long> booths = new HashSet<Long>(boothIds);
			 
			 List<Object[]> constituencyInfo = null;
			 Long partyPolledVotesInConstituency = null;
			 if(electionDAO.get(electionId).getElectionScope().getElectionType().getElectionType().equalsIgnoreCase("Assembly")){
			     constituencyInfo = constituencyElectionResultDAO.findTotalVotesAndPolledVotesAndVotesPercentage(electionId,constituenycId);
			     partyPolledVotesInConstituency = nominationDAO.getPartyPercentage(constituenycId, electionId, partyId).longValue();
			 }else{
				 TotalVotesInConstituency = candidateBoothResultDAO.findTotalVotesForAssembInAParliament(booths, electionId);
				 TotalPolledVotesInConstituency = candidateBoothResultDAO.findPolledVotesForAssembInAParliament(booths, electionId);
				 partyPolledVotesInConstituency = candidateBoothResultDAO.findTotalVotesPolledForCandidateAssembInAParliament(booths, electionId, partyId);
			 }
			 if(constituencyInfo != null && constituencyInfo.size() > 0)
				for(Object[] params : constituencyInfo)
				{
					TotalVotesInConstituency = new Double(params[0].toString()).longValue();
					TotalPolledVotesInConstituency =new Double(params[1].toString()).longValue();
				}
			 
			 Double pollingPerForConstituency = (TotalPolledVotesInConstituency * 100.0)/TotalVotesInConstituency;
			 
			 Double partyPerInConstituency = (partyPolledVotesInConstituency * 100.0)/TotalPolledVotesInConstituency;
			
			
			 totalVotesForBooth =candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(booths,electionId); 
			 
			 if(totalVotersInBooth != null && totalVotersInBooth.size() > 0)
			 {
				 for(Object[] params : totalVotersInBooth)
				 {
					
				 totalVotersMap.put((Long)params[0],params);
				 }
			 }
			 if(addedVotersCount != null && addedVotersCount.size() > 0)
			 {
				 for(Object[] params : addedVotersCount)
				 {
				 if(params[1] != null && ((Long)params[1]).longValue() >= IConstants.MIN_ADDED_VOTERS)
				 addedVotersMap.put((Long)params[0],(Long)params[1]);
				 }
			 }
			 if(panchayatnames !=null &&panchayatnames.size() > 0)
				 for(Object[] params : panchayatnames)
					 panchayatMap.put((Long)params[0],params[1].toString()); 
			 
			 if(totalVotesForBooth != null && totalVotesForBooth.size() > 0)
			 {
				 for(Object[] params : totalVotesForBooth)
				 {
					Map<Long,Long> partyMap =  boothMap.get((Long)params[0]);
					if(partyMap == null)
					{
						partyMap = new HashMap<Long, Long>();
						boothMap.put((Long)params[0], partyMap);
					}
					Long polledVotes = partyMap.get((Long)params[1]);
					if(polledVotes == null)
						
						partyMap.put((Long)params[1],(Long)params[2]);
					else
					partyMap.put((Long)params[1], polledVotes+(Long)params[2]);
				 }
		 }
			 List<PartyPositionVO> PollingHighboothResultList = new ArrayList<PartyPositionVO>();
			 List<PartyPositionVO> PollingLowboothResultList = new ArrayList<PartyPositionVO>();
			 PartyPositionVO mainVo = new PartyPositionVO();
			 for(Long boothId :boothMap.keySet())
			 {
				 PartyPositionVO partyPositionVO = new PartyPositionVO();
				 partyPositionVO.setId(boothId);
				 partyPositionVO.setName(boothDAO.getBoothPartNoByBoothId(boothId));
				 if(panchayatMap.get(boothId) == null || panchayatMap.get(boothId).equals(""))
					 partyPositionVO.setLocalbodyName("");
				 else
				 partyPositionVO.setLocalbodyName(panchayatMap.get(boothId));
				 Long totalValidVotes = 0l;
				 List<PartyPositionVO> StrongpartyInfo = new ArrayList<PartyPositionVO>();
				 List<PartyPositionVO> WeakpartyInfo = new ArrayList<PartyPositionVO>();
				 Map<Long,Long> partyMap =  boothMap.get(boothId);
				 for(Long partyId1 : partyMap.keySet())
					 totalValidVotes +=partyMap.get(partyId1);
				 for(Long partyId1 : partyMap.keySet())
				 {
					 if(partyId.equals(partyId1))
					 {
					 PartyPositionVO partyVo = new PartyPositionVO();
					 partyVo.setPartyName(partyDAO.getPartyShortNameById(partyId1));
					 partyVo.setPartyTotalvotes(partyMap.get(partyId1));
					 partyVo.setPartyPercentage(partyVo.getPartyTotalvotes() * 100.0 /totalValidVotes);
					 partyVo.setSelectedParty(true);
					 
					 if(partyPerInConstituency > partyVo.getPartyPercentage())
						 WeakpartyInfo.add(partyVo); 
					if(partyPerInConstituency < partyVo.getPartyPercentage())
						 StrongpartyInfo.add(partyVo);
					 }
				}
				
				 partyPositionVO.setTotalValidVotes(totalValidVotes);
				 Object[] params1 = totalVotersMap.get(boothId);
				
				 partyPositionVO.setTotalVoters((Long)params1[1]);
				 //partyPositionVO.setLocation(params1[2].toString());
				 //partyPositionVO.setVillagesCovered(params1[3].toString());
				 partyPositionVO.setPollingPercentage((partyPositionVO.getTotalValidVotes() * 100.0 )/ partyPositionVO.getTotalVoters());
				 //partyPositionVO.setRangePercentage((long)(pollingPerForConstituency * partyPositionVO.getTotalVoters())/100 );
				
				
				 if(pollingPerForConstituency < partyPositionVO.getPollingPercentage())
				 {
					 if(WeakpartyInfo != null && WeakpartyInfo.size() >0)
					 {
					 partyPositionVO.setMinValue(partyPositionVO.getPollingPercentage()-pollingPerForConstituency) ;
					 //partyPositionVO.setLostSeats(new Double((partyPositionVO.getMinValue()* partyPositionVO.getTotalVoters())/100).longValue());
					 partyPositionVO.setWeakPollingPercentVOList(WeakpartyInfo);
					 if(addedVotersMap.get(boothId) == null || addedVotersMap.get(boothId).equals(""))
						 partyPositionVO.setAddedVotersCount(0l);
					 else
						partyPositionVO.setAddedVotersCount(addedVotersMap.get(boothId));
					 PollingHighboothResultList.add(partyPositionVO);
					 }
				 }
				 else
				 {
					 if(StrongpartyInfo != null && StrongpartyInfo.size() >0)
					 {
					 partyPositionVO.setMaxValue(pollingPerForConstituency-partyPositionVO.getPollingPercentage());
					 //partyPositionVO.setToTarget(new Double((partyPositionVO.getMaxValue() * partyPositionVO.getTotalVoters())/100).longValue());
					 //partyPositionVO.setToImprove(new Double((partyPositionVO.getToTarget() * StrongpartyInfo.get(0).getPartyPercentage())/100).longValue());
					 partyPositionVO.setStrongPollingPercentVOList(StrongpartyInfo);
					 PollingLowboothResultList.add(partyPositionVO);
					 }
				 }
				 }
			
			 mainVo.setPartyPercentage(Double.valueOf(decimalFormat.format(partyPerInConstituency)));
			 mainVo.setPollingPercentage(Double.valueOf(decimalFormat.format(pollingPerForConstituency)));
			 if(PollingHighboothResultList != null && PollingHighboothResultList.size() >0)
			 mainVo.setStrongPollingPercentVOList(PollingHighboothResultList);
			 if(PollingLowboothResultList != null && PollingLowboothResultList.size() > 0)
			 mainVo.setWeakPollingPercentVOList(PollingLowboothResultList);
			 result.add(mainVo);
	 }
		 catch(Exception e)
		 {
			 
			 e.printStackTrace();
		 }
		 result.get(0).setTitle("Polling Stations - increase polling % ");
		 result.get(0).setHedding("We need to try & improve the polling percentages where we are very strong."+ 
		 "The following are the polling booths where we are strong and had low polling percentages");
		return result;
	 }
	
}
