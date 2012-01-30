package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IBasePartyResultsService;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyInfoComparator;

/**
 * 
 * @author Narender Akula
 *
 */
public class BasePartyResultsServiceImpl implements IBasePartyResultsService{
	

	private IElectionScopeDAO electionScopeDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IElectionDAO electionDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private INominationDAO nominationDAO;

	private final static Logger log = Logger.getLogger(BasePartyResultsServiceImpl.class);
	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}

	
	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO){
		this.electionScopeDAO = electionScopeDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO){
		this.electionDAO = electionDAO;
	}

	public IElectionDAO getElectionDAO(){
		return electionDAO;
	}
	
	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO){
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public void setConstituencyElectionResultDAO(IConstituencyElectionResultDAO constituencyElectionResultDAO){
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO){
		this.nominationDAO = nominationDAO;
	}

	public ElectionScope getElectionScope(Long typeId, Long countryID, Long stateID){
		
		List<ElectionScope> electionScopeList = null;
		ElectionType electionType = electionTypeDAO.get(typeId);
		if(electionType.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			electionScopeList = electionScopeDAO.getElectionScopeForAElectionType(electionType.getElectionType(), countryID);
		else
		 electionScopeList = electionScopeDAO.findByTypeIdCountryIdStateId(typeId, countryID,stateID);
		if(electionScopeList.size()==0)
			return new ElectionScope();
		else
			return electionScopeList.get(0);		
	}
	
	public List<Election> getElections(ElectionScope scope){
		List<Election> list = electionDAO.findByElectionScopeId(scope.getElectionScopeId(), IConstants.ELECTION_SUBTYPE_MAIN);
		return list;
	}

	/**
	 * @param election object
	 * @return sorted List of parties which contains party infomation(like... %
	 *         of votes, election year, seats win etc.) specific to the election
	 */
	
	@SuppressWarnings("unchecked")
	public List<PartyInfoVO> getPartyAndCompetetorsInfo(Election election, 
			String requiredPartyShortName, Long stateID, Long districtID, Long constituencyID, 
			int competetorSize,ElectionScopeLevelEnum level,Boolean hasAlliance,List<SelectOptionVO> allianceParties) {
		log.debug("BasePartyResultsServiceImpl.getPartyAndCompetetorsInfo() Start");
		String electionYear = election.getElectionYear();
		StringBuilder constituencyElectionIDs = new StringBuilder();
		//Map<String, PartyInfoVO> partyAndCompetetorsInfo = new HashMap<String, PartyInfoVO>();
		List ConstituencyElectionList = new ArrayList<ConstituencyElection>();
		if(level.equals(ElectionScopeLevelEnum.DISTRICT_LEVEL)){
			ConstituencyElectionList = constituencyElectionDAO.findByElectionAndDistrict(election.getElectionId(), districtID);
		} else if(level.equals(ElectionScopeLevelEnum.CONSTITUENCY_LEVEL)){
				ConstituencyElectionList = constituencyElectionDAO.findByElectionAndConstituency(election.getElectionId(), constituencyID);
		} else if(level.equals(ElectionScopeLevelEnum.COUNTRY_LEVEL)){
				ConstituencyElectionList = constituencyElectionDAO.findByElection(election.getElectionId());
		} else if(level.equals(ElectionScopeLevelEnum.STATE_LEVEL)){
			log.debug("BasePartyResultsServiceImpl.getPartyAndCompetetorsInfo() State Level");
				ConstituencyElectionList = constituencyElectionDAO.findByElectionAndState(election.getElectionId(),stateID);
		}
		log.debug("BasePartyResultsServiceImpl.getPartyAndCompetetorsInfo() ConstituencyElectionList.size()="+ConstituencyElectionList.size());
		if(ConstituencyElectionList.size()==0){
			return new ArrayList<PartyInfoVO>();
		}
		Long constituencyGrandTotalValidVotes = 0l;
		for(int i = 0; i< ConstituencyElectionList.size();i++){
			Object[] obj = (Object[])ConstituencyElectionList.get(i);
			constituencyElectionIDs.append(",").append(obj[0].toString());
			constituencyGrandTotalValidVotes += new Double(obj[1].toString()).longValue();
			
		}
		String strConstituencyElectionIDs = constituencyElectionIDs.substring(1);
		log.debug("Constituency ElectionIDs="+strConstituencyElectionIDs);
			
		Map<String, PartyInfoVO> partyAndCompetetorsInfo = getConstituencyPartyResults(ConstituencyElectionList, electionYear);
		
		PartyInfoVO requiredPartyInfoVO = partyAndCompetetorsInfo.get(requiredPartyShortName);
		List<PartyInfoVO> alliancePartysInfoVO = null;
		
		if(hasAlliance && allianceParties != null && allianceParties.size() > 0){
			alliancePartysInfoVO = new ArrayList<PartyInfoVO>();
			for(SelectOptionVO alliancParty:allianceParties){
				PartyInfoVO alliancPartyInfo = partyAndCompetetorsInfo.get(alliancParty.getName());
				if(alliancPartyInfo != null){
				partyAndCompetetorsInfo.remove(alliancParty.getName());
				alliancePartysInfoVO.add(alliancPartyInfo);
				}
			}
		}
		if(requiredPartyInfoVO==null){
			return new ArrayList<PartyInfoVO>();
		}
		partyAndCompetetorsInfo.remove(requiredPartyShortName);
		if(requiredPartyInfoVO!=null)
			requiredPartyInfoVO.setPercentageOfVotes(getPercentage(requiredPartyInfoVO,constituencyGrandTotalValidVotes));
		
		if(alliancePartysInfoVO != null)
		for(PartyInfoVO alliancePartysInfo:alliancePartysInfoVO){
		alliancePartysInfo.setPercentageOfVotes(getPercentage(alliancePartysInfo,constituencyGrandTotalValidVotes));
		}
		
		List<PartyInfoVO> partyAndCompetetorsInfoListResult = calculatePartyDetails(partyAndCompetetorsInfo, 
					competetorSize, constituencyGrandTotalValidVotes, requiredPartyInfoVO,hasAlliance,alliancePartysInfoVO);
		//partyAndCompetetorsInfoListResult.add(0,requiredPartyInfoVO);
		
		return partyAndCompetetorsInfoListResult;
		
	}
	//debug, info, error,fatel

	/**
	 * @param partyAndCompetetorsInfo - contains party raw information
	 * @param competetorSize - size opposition
	 * @param constituencyGrandTotalValidVotes - total valid votes of a constituency
	 * @param requiredPartyInfoVO - party information on which we are showing the Party Results Report
	 * @return partyAndCompetetorsInfoListResult - contains sorted parties with calculated %age of votes and 
	 * at the starting postion required party information will be available and remaining are the oppostion parties
	 */
	public List<PartyInfoVO> calculatePartyDetails(Map<String, PartyInfoVO> partyAndCompetetorsInfo, 
			int competetorSize, Long constituencyGrandTotalValidVotes,
			PartyInfoVO requiredPartyInfoVO,Boolean hasAlliance,List<PartyInfoVO> alliancePartysInfoVO){
		System.out.println("BasePartyResultService sart competetorSize competetorSize competetorSize competetorSize competetorSize :"+competetorSize);
		
		List<PartyInfoVO> partyAndCompetetorsInfoList = new ArrayList<PartyInfoVO>();
		Set<Map.Entry<String,PartyInfoVO>> set = partyAndCompetetorsInfo.entrySet();
		Iterator<Map.Entry<String,PartyInfoVO>> iterator = set.iterator();
		while(iterator.hasNext()){
			Map.Entry<String, PartyInfoVO> entry = iterator.next();
			PartyInfoVO partyInfoVO = entry.getValue();
			BigDecimal votesPercentage = getPercentage(partyInfoVO,constituencyGrandTotalValidVotes);
			partyInfoVO.setPercentageOfVotes(votesPercentage);
			//partyAndCompetetorsInfo.put(entry.getKey(),partyInfoVO);
			partyAndCompetetorsInfoList.add(partyInfoVO);
		}
		PartyInfoComparator comparator = new PartyInfoComparator();
		Collections.sort(partyAndCompetetorsInfoList, comparator);

		List<PartyInfoVO> partyAndCompetetorsInfoListResult;
		if(requiredPartyInfoVO==null){// this condition is for winning party
			int i=0;
			partyAndCompetetorsInfoListResult = new ArrayList<PartyInfoVO>();
			if(hasAlliance && alliancePartysInfoVO != null && alliancePartysInfoVO.size() > 0){
				for(PartyInfoVO alliancInfo:alliancePartysInfoVO){
				partyAndCompetetorsInfoListResult.add(i++, alliancInfo);
				}
			}
			if(competetorSize >partyAndCompetetorsInfoList.size())
				competetorSize = partyAndCompetetorsInfoList.size();
			for(int j=0;j<competetorSize;j++){
			partyAndCompetetorsInfoListResult.add(i++, partyAndCompetetorsInfoList.get(j));
			}
			//partyAndCompetetorsInfoListResult = partyAndCompetetorsInfoList;
		} else { // this condition is for the specific party result report
			partyAndCompetetorsInfoListResult = new ArrayList<PartyInfoVO>();
			partyAndCompetetorsInfoListResult.add(0, requiredPartyInfoVO);
			if(hasAlliance && alliancePartysInfoVO != null && alliancePartysInfoVO.size() > 0){
			int i=1;
			 for(PartyInfoVO alliancInfo:alliancePartysInfoVO){
			 partyAndCompetetorsInfoListResult.add(i++, alliancInfo);
			 }
			}
			if(competetorSize >partyAndCompetetorsInfoList.size())
				competetorSize = partyAndCompetetorsInfoList.size();
			for(int i=0;i<competetorSize;i++){
				partyAndCompetetorsInfoListResult.add(partyAndCompetetorsInfoList.get(i));
			}
		}
		System.out.println("BasePartyResultService last competetorSize competetorSize competetorSize competetorSize competetorSize :"+competetorSize);
		return partyAndCompetetorsInfoListResult;
	}
	/**
	 * @param ConstituencyElectionList list of ConstituencyElection object for a specific election
	 * @param electionYear 
	 * @return partyAndCompetetorsInfo java.util.Map object contain the party details which are participated in the list of
	 * constituencies.
	 */
	public Map<String, PartyInfoVO> getConstituencyPartyResults(List ConstituencyElectionList, String electionYear){
		Map<String, PartyInfoVO> partyAndCompetetorsInfo = new HashMap<String, PartyInfoVO>();
		for(int i=0;i<ConstituencyElectionList.size();i++){
			Object[] obj = (Object[])ConstituencyElectionList.get(i);

			List constituencyNominations = nominationDAO.findByConstituencyElection(Long.parseLong(obj[0].toString()));
			//StringBuilder nominationIDs = new StringBuilder();
			for(int j = 0; j< constituencyNominations.size();j++){
				Object[] nominationObj = (Object[])constituencyNominations.get(j);
				String partyShortName = nominationObj[0].toString();
				String partyLongName = nominationObj[1].toString();
				PartyInfoVO partyInfoVO;
				if(partyAndCompetetorsInfo.get(partyShortName)==null){
					partyInfoVO = new PartyInfoVO();
					partyInfoVO.setPartyId((Long)nominationObj[10]); 
					partyInfoVO.setPartyShortName(partyShortName);
					partyInfoVO.setPartyLongName(partyLongName);
					partyInfoVO.setElectionYear(electionYear);
					partyInfoVO.setSeatsWin(new Long(0));
					partyAndCompetetorsInfo.put(partyShortName, partyInfoVO);
				}
				
				partyInfoVO = partyAndCompetetorsInfo.get(partyShortName);
				long participatedSeats = partyInfoVO.getSeatsParticipated().longValue() + 1;
				partyInfoVO.setSeatsParticipated(new Long(participatedSeats));
				long totalVotes = new Double(nominationObj[2].toString()).longValue();
				long grandTotalVotes = partyInfoVO.getPartyTotalVotes().longValue();
				
				partyInfoVO.setPartyTotalVotes(new Long(totalVotes +grandTotalVotes));
				
				if(new Long(nominationObj[3].toString()).intValue() == 1){
					long winningSeats = partyInfoVO.getSeatsWin().longValue() +1;
					partyInfoVO.setSeatsWin(new Long(winningSeats));
				}
				partyAndCompetetorsInfo.put(partyShortName, partyInfoVO);
			}
		} 
		return partyAndCompetetorsInfo;
	}
	/**
	 * 
	 * @param partyInfoVO
	 * @param constituencyGrandTotalValidVotes is the total valid votes for the specific election
	 * @return votes percentage of the party for the specific election
	 */
	public BigDecimal getPercentage(PartyInfoVO partyInfoVO, long constituencyGrandTotalValidVotes){
		long grandTotalVotes = partyInfoVO.getPartyTotalVotes().longValue();
		BigDecimal result = new BigDecimal(0);
		if(constituencyGrandTotalValidVotes!=0){
			result = new BigDecimal((grandTotalVotes*100.0)/constituencyGrandTotalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return result;
	}
	
	/**
	 * 
	 * @param strConstituencyElectionIDs
	 * @return total valid votes for the list of constituencies(for the specific election)
	 */
	public Long getConstituencyGrandTotalValidVotes(String strConstituencyElectionIDs){
		List<ConstituencyElectionResult> constituencyElectionResultList = 
							constituencyElectionResultDAO.findByConstituencyElections(strConstituencyElectionIDs);
		long constituencyGrandTotalValidVotes = 0;
		for(ConstituencyElectionResult constituencyElectionResult : constituencyElectionResultList){
			if(constituencyElectionResult.getValidVotes()!=null)
				constituencyGrandTotalValidVotes += constituencyElectionResult.getValidVotes().longValue();
		}
		
		return new Long(constituencyGrandTotalValidVotes);
	}
}
