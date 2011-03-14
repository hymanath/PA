package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.hibernate.hql.ast.tree.CountNode;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ContenetTransferVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.PartiesStrenghInfoVO;
import com.itgrids.partyanalyst.dto.PartiesStrengthsInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ContenetTransferVOIdComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartiesDetailsVOComparator;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;

/**
 *  @author Ravi Kiran.Y
 *  @version 20-02-11
 *  The purpose of this class is to provide services to caluculate
 *  the strengths and weakness of all the parties or a particular
 *  party that had participated in a constituency or a list of
 *  constituency's.
 */
public class PartyStrengthService implements IPartyStrengthService {

	
	private static final long serialVersionUID = 4250832773268698296L;
	
	
	//Instance Variables
	
	private IConstituencyElectionDAO constituencyElectionDAO;	
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IStaticDataService staticDataService;
	private IElectionScopeDAO electionScopeDAO;
	private IStateDAO stateDAO;
	private IPartyDAO partyDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	
	//Getters and Setters for Instance Variables.
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public IElectionAllianceDAO getElectionAllianceDAO() {
		return electionAllianceDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}
	
	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO){
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	
	
	
	/**
	 * This method can be used to segregate all the constituencies.
	 * 
	 * @author Y.Ravi Kiran
	 * @param selectedNoOfYears
	 * @param electionScopeId
	 * @param electionSubType
	 * @return PartiesStrenghInfoVO
	 */
	public PartiesStrenghInfoVO segregateAllConstituencies(Long selectedNoOfYears,String electionType,String electionSubType,Long stateId){
		
		PartiesStrenghInfoVO partiesStrenghInfoVO = new PartiesStrenghInfoVO();
		List<Long> requiredConstituencies = new ArrayList<Long>(0);
		List<Long> latestConstituencies = new ArrayList<Long>(0);
		List<Long> remianingConstituencies = new ArrayList<Long>(0);
		ListIterator it = null;
		List<Long> requiredList = null;
		try{
			requiredList = getElectionIds(stateId,electionType,electionSubType,selectedNoOfYears);
			List result = constituencyElectionDAO.getConstituenciesHavingMaxSpan(electionSubType,electionType,stateId,requiredList);				
			if(result!=null && result.size()>0){
				it = result.listIterator();
				while(it.hasNext()){
					Object[] parms = (Object[]) it.next();
					Long count = (Long)parms[0];					
					if(selectedNoOfYears==1 || (count.intValue() == selectedNoOfYears.intValue() && count!=1l)){						
						requiredConstituencies.add((Long)parms[1]);
					}else if(count.intValue()==1){
						latestConstituencies.add((Long)parms[1]);
					}else{
						remianingConstituencies.add((Long)parms[1]);
					}
				}
			}
			partiesStrenghInfoVO.setLatestConstituencies(latestConstituencies);
			partiesStrenghInfoVO.setRemianingConstituencies(remianingConstituencies);
			partiesStrenghInfoVO.setRequiredConstituencies(requiredConstituencies);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			it = null;
		}		
		return partiesStrenghInfoVO;
	}
	
	
	/**
	 * This method can be used to get all the election years based on election type in a state.
	 * @author Y.Ravi Kiran 
	 * @param electionType
	 * @param stateId
	 * @return List<Long>
	 */
 	public List<Long> getAllElectionYears(String electionType,Long stateId){
 		List<Long> allYears = electionDAO.getAllElectionYearsBasedOnElectionType(electionType,IConstants.ELECTION_SUBTYPE_MAIN,stateId);
 		return allYears;
 	}
 	
 	
 	/**
 	 * This method can be used to know the strengths and weakness of parties in a constituency
 	 * @author Y.Ravi Kiran 
	 * @param electionType
	 * @param stateId	 
	 * @param electionYearsCount
	 * @param type
	 * @param partyId
	 * @return ElectionInfoVO
 	 */
 	public ElectionInfoVO getPartiesData(String electionType,Long stateId,Long electionYearsCount,Long partyId,String alliance,Long electionId,String partyName){
 		List<Long> requiredConstituencies = new ArrayList<Long>(0);
		List<Long> latestConstituencies = new ArrayList<Long>(0);
		List<Long> remianingConstituencies = new ArrayList<Long>(0);
		List<Long> selectedParties = new ArrayList<Long>(0);
		ElectionInfoVO resultVo = new ElectionInfoVO();
		ResultStatus resultStatus = new ResultStatus();		
		List allianceData = new ArrayList(0);
		resultVo.setSelectedYearsCount(electionYearsCount);
		List<SelectOptionVO> staticParties = getAllPartiesData(stateId);
 		Collections.sort(staticParties,new SelectOptionVOComparator());
 		
 		for(int i=0;i<staticParties.size();i++){ 
			if(staticParties.get(i).getId().intValue() == partyId.intValue()){
				resultVo.setPartyName(staticParties.get(i).getName());
				resultVo.setPartyId(staticParties.get(i).getId());
			}			
 		}
 		
 		if(resultVo.getPartyName()== null ||  resultVo.getPartyName()== "")
 			resultVo.setPartyName("All Parties"); 		
 		 		
		try{
			PartiesStrenghInfoVO partiesStrenghInfoVO = segregateAllConstituencies(electionYearsCount,electionType,IConstants.ELECTION_SUBTYPE_MAIN,stateId);
	 
			latestConstituencies = partiesStrenghInfoVO.getLatestConstituencies();
			requiredConstituencies = partiesStrenghInfoVO.getRequiredConstituencies();
			remianingConstituencies = partiesStrenghInfoVO.getRemianingConstituencies();
			
			if(alliance.equalsIgnoreCase(IConstants.TRUE))
				allianceData = getIncludingAllianceData(electionType,stateId,partyId,electionYearsCount,electionId,partyName);	
			
			resultVo.setAllPartiesDetails(getPartyStrengthsAndWeaknessDetails(electionType,stateId,partyId,electionYearsCount,allianceData));
			
			if(partyId.intValue()==0){
				selectedParties = staticDataService.getStaticPartiesAsList(stateId);					 				
				getDataForAllParties(resultVo,requiredConstituencies,latestConstituencies,remianingConstituencies,selectedParties,electionYearsCount,electionType,stateId);				
			}else{
				selectedParties.add(partyId);
				
				
				getDataForAParty(resultVo,requiredConstituencies,latestConstituencies,remianingConstituencies,selectedParties,electionYearsCount,electionType,stateId);
			}
	 		resultStatus.setResultCode(ResultCodeMapper.SUCCESS); 	
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
 			resultStatus.setExceptionEncountered(e); 
		}
 		return resultVo;
 	}
 	 
 	/**
 	 * This method can can be used in order to get the details of all parties. 
 	 * @param resultVo
 	 * @param requiredConstituencies
 	 * @param latestConstituencies
 	 * @param remianingConstituencies
 	 * @param selectedParties
 	 * @param electionYearsCount
 	 * @param electionType
 	 * @param stateId
 	 */
 	public void getDataForAllParties(ElectionInfoVO resultVo,List<Long> requiredConstituencies,List<Long> latestConstituencies,List<Long> remianingConstituencies,List<Long> selectedParties,Long electionYearsCount,String electionType,Long stateId){
 	try{
 			if(requiredConstituencies.size()!=0) 			
 				resultVo.setRequiredConstituenciesInfo(getAllElectionData(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES),electionYearsCount,IConstants.REQUIRED_CONSTITUENCIES,electionType,stateId));
			else
 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
 			
 			if(latestConstituencies.size()!=0)
 				resultVo.setLatestConstituenciesInfo(getAllElectionData(latestConstituencies,nominationDAO.getAllLatestPartyResults(latestConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES),electionYearsCount,IConstants.LATEST_CONSTITUENCIES,electionType,stateId));	
			else
				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());

 			if(remianingConstituencies.size()!=0)	 				
 				resultVo.setRemainingConstituenciesInfo(getAllElectionData(remianingConstituencies,nominationDAO.getAllPartyResults(remianingConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES),electionYearsCount,IConstants.REMAINING_CONSTITUENCIES,electionType,stateId));
 			else
 				resultVo.setRemainingConstituenciesInfo(new ConstituencyElectionResults());
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
 	}
 	
 	
 	/**
 	 * This method can can be used in order to get the details of a particular party. 
 	 * @param resultVo
 	 * @param requiredConstituencies
 	 * @param latestConstituencies
 	 * @param remianingConstituencies
 	 * @param selectedParties
 	 * @param electionYearsCount
 	 * @param electionType
 	 * @param stateId
 	 */
 	public void getDataForAParty(ElectionInfoVO resultVo,List<Long> requiredConstituencies,List<Long> latestConstituencies,List<Long> remianingConstituencies,List<Long> selectedParties,Long electionYearsCount,String electionType,Long stateId){
  		try{
 			if(requiredConstituencies.size()!=0) 			
 				resultVo.setRequiredConstituenciesInfo(setElectionDataInToVo(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES)));
				else
 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
 			
 			if(latestConstituencies.size()!=0)
 				resultVo.setLatestConstituenciesInfo(setElectionDataInToVo(latestConstituencies,nominationDAO.getAllPartyResults(latestConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES)));	
				else
 				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());
 			
 			if(remianingConstituencies.size()!=0)	 				
 				resultVo.setRemainingConstituenciesInfo(setElectionDataInToVo(remianingConstituencies,nominationDAO.getAllPartyResults(remianingConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES)));
 			else
 				resultVo.setRemainingConstituenciesInfo(new ConstituencyElectionResults());
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
 	}
 	
 	

	public void getAllRequiredData(List electionIds,Long partyId,List<Long> allConstituencies,List result){
		 	try{
		 		for(int i=0;i<electionIds.size();i++){
					Object[] params = (Object[])electionIds.get(i);
					String electionYear =  params[0].toString();
					Long obtainedElectionId = (Long)params[1];
					Long electionTypeId = (Long)params[2];
					List<Party> allianceParties = staticDataService.getAllianceParties(electionYear,electionTypeId,partyId);
					for(Party eachParty : allianceParties){
						if(partyId.intValue()!=eachParty.getPartyId().intValue()){
							List allianceResult = nominationDAO.getPartyResultsForAParty(allConstituencies,eachParty.getPartyId(),IConstants.ELECTION_SUBTYPE_MAIN,obtainedElectionId,IConstants.WINNER_CANDIDATES); 				
							result.addAll(allianceResult);
						}					
					} 				
				} 
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
	 	}

 	public List getIncludingAllianceData(String electionType,Long stateId,Long partyId,Long totalElectionYears,Long electionId,String partyName){
 	 		PartiesDetailsVO partiesDetails = new PartiesDetailsVO();
 	 		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();
 	 		List result = new ArrayList(); 		
 	 		List<Long> allConstituencies = new ArrayList<Long>(0); 		
 	 		List electionIds = new ArrayList(0);
 	 		List<SelectOptionVO> details = new ArrayList<SelectOptionVO>();
 			try{
 	 			allConstituencies = getAllConstituencies(electionType,stateId,totalElectionYears);  		
 	 			result = nominationDAO.getPartyResultsForAParty(allConstituencies,partyId,IConstants.ELECTION_SUBTYPE_MAIN,null,IConstants.WINNER_CANDIDATES);
 	 			if(electionId.intValue()!=0){
 	 				electionIds = electionDAO.getElectionDetailsForAnElection(electionId);
 	 				getAllRequiredData(electionIds,partyId,allConstituencies,result);
 	 			}else{
 	 				electionIds = electionAllianceDAO.getAllAllianceElectionYearsForAParty(partyId,IConstants.ELECTION_SUBTYPE_MAIN,stateId);
 	 				getAllRequiredData(electionIds,partyId,allConstituencies,result); 	 				
 	 			} 				
 	 			/*details = caluculatePartiesStrengthBasedOnConstituencyId(result,totalElectionYears);
 	 			partiesDetails.setDetails(details);
 	 			partiesDetails.setPartyName(partyName);
 	 			partiesDetails.setPartyId(partyId);*/
 	 		}catch(Exception e){
 	 			e.printStackTrace();
 	 		}
 	 		return result;
 	 	}
 	 	
 	 
 	
 	/**
 	 * This method can be used to get the strength of a party in that particular constituency.
 	 * @author Y.Ravi Kiran 
	 * @param constituencyIds
	 * @param result
	 * @return ConstituencyElectionResults
 	 */
 	public ConstituencyElectionResults setElectionDataInToVo(List<Long> constituencyIds,List result){
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		ResultStatus resultStatus = new ResultStatus();
 		Long count=0l;	
 		List<PartiesStrengthsInfoVO> partiesStrengthsInfoVO = new ArrayList<PartiesStrengthsInfoVO>();
 		
 		
 		try{ 			
 			if(result!=null && result.size()>0){
	 			for(int i=0; i<result.size(); i++){
	 				Object[] parms = (Object[])result.get(i);	 
	 				
	 				List<PartiesDetailsVO> partyDetails = new ArrayList<PartiesDetailsVO>(0);  
	 				
	 				PartiesStrengthsInfoVO resultVo = new PartiesStrengthsInfoVO();
					resultVo.setConstituencyId((Long)parms[0]);
					resultVo.setConstituencyName(parms[3].toString());
					partyDetails.add(setDataIntoPartiesDetailsVO(parms));
					
					resultVo.setPartyResults(partyDetails);	 
					count+= (Long)parms[1];
					partiesStrengthsInfoVO.add(resultVo);
					
	 			}
	 			constituencyElectionResults.setTotalNumberOfConstituencies(partiesStrengthsInfoVO.size()+0l);
 			}
	 	}catch(Exception e){
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e); 			
			e.printStackTrace();
		}
	 	constituencyElectionResults.setPartiesStrengthsInfoVO(partiesStrengthsInfoVO);
		constituencyElectionResults.setResultStatus(resultStatus);
		return constituencyElectionResults;
 	}
 	
 	
 	
 	/**
 	 * This method can be used to get number of election years that are present matching 
 	 * the criteria like stateId,electionType
 	 * @author Y.Ravi Kiran 
	 * @param stateId
	 * @param electionType
	 * @return List<SelectOptionVO>
 	 */
 	public List<SelectOptionVO> getCountOfElectionYears(Long stateId,String electionType){
 		List<SelectOptionVO> ids = new ArrayList<SelectOptionVO>(0);
 		List allYears = new ArrayList();
 		Long count=0l;
 		try{
 			
 			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
 				 allYears = electionDAO.getCountOfElectionYears(stateId,electionType,IConstants.ELECTION_SUBTYPE_MAIN);
 			}else{
 				 allYears = electionDAO.getCountOfElectionYearsForParliament(stateId,electionType,IConstants.ELECTION_SUBTYPE_MAIN);
 			}	 		
	 		count = new Long(allYears.get(0).toString());
	 		//ids.add(0,new SelectOptionVO(0l,"Select Year"));	 		
	 		for(Long i=count;i>0;i--){
	 			ids.add(new SelectOptionVO(new Long(i),i.toString()));
	 		}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return ids;
 	}
 	
 	
 	
 	
 	/**
 	 * This method can be used to get all the states based on the election type.
 	 * @author Y.Ravi Kiran 
	 * @param electionType
	 * @return List<SelectOptionVO>
 	 */
 	public List<SelectOptionVO> getAllStatesHavinElectionData(String electionType){
 		List<SelectOptionVO> ids = new ArrayList<SelectOptionVO>(0);
 		try{
 			//ids.add(0,new SelectOptionVO(0l,"Select State"));	 
 			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
 				List list = electionScopeDAO.getAllStatesAndTheirIds(electionType);
 	 			for(int i=0;i<list.size();i++){
 	 				Object[] parms = (Object[])list.get(i); 				
 	 				ids.add(new SelectOptionVO(new Long(parms[0].toString()),parms[1].toString()));
 	 			} 
 			}else{
 				List<State> state = stateDAO.getAll();
 				for(int j=0;j<state.size();j++){
 					ids.add(new SelectOptionVO(state.get(j).getStateId(),state.get(j).getStateName()));
 				}
 			} 						
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return ids;
 	}
 	
 	
 	
	/**
 	 * This method can be used to get the strength of all the party's that are participated in particular constituency.
 	 * @author Y.Ravi Kiran 
	 * @param constituencyIds
	 * @param result
	 * @return ConstituencyElectionResults
 	 */
 	public ConstituencyElectionResults getAllElectionData(List<Long> constituencyIds,List result,Long selectedYearsCount,String type,String electionType,Long stateId){
		
 		List<Long> partyIds = new ArrayList<Long>();	 		
 		List<PartiesStrengthsInfoVO> partiesStrengthsInfoVO = new ArrayList<PartiesStrengthsInfoVO>();
 		List<PartiesDetailsVO> partyDetails = new ArrayList<PartiesDetailsVO>(0);  	
 		
 		Map<Long,String> map1 = new HashMap<Long,String>();
 		Map<Long,List<PartiesDetailsVO>> map2 = new HashMap<Long,List<PartiesDetailsVO>>();
 		Map<Long,String> map = new HashMap<Long,String>();
 		
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		ResultStatus resultStatus = new ResultStatus();
 	
 		try{   		
	 		List<SelectOptionVO> staticParties = getAllPartiesData(stateId);
	 		Collections.sort(staticParties,new SelectOptionVOComparator());
	 		constituencyElectionResults.setAllPartiesData(staticParties);
	 		 		
	 		for(int i=0;i<staticParties.size();i++){ 
				partyIds.add(staticParties.get(i).getId());
				map.put(staticParties.get(i).getId(), staticParties.get(i).getName());
			}
 					
 			if(result!=null && result.size()>0){
	 			for(int i=0; i<result.size(); i++){
	 				Object[] parms = (Object[])result.get(i);
	 				Long constituencyId = (Long)parms[0];
	 				String constiName = parms[3].toString();
	 				
	 				map1.put(constituencyId, constiName);
	 				if(map2.containsKey(constituencyId)){
	 					partyDetails = map2.get(constituencyId);
	 					partyDetails.add(setDataIntoPartiesDetailsVO(parms));
	 					map2.put(constituencyId,partyDetails);	
	 				}else{
	 					partyDetails = new ArrayList<PartiesDetailsVO>(0);
	 					partyDetails.add(setDataIntoPartiesDetailsVO(parms));
	 					map2.put(constituencyId,partyDetails);	
	 				}
	 			}	
 			} 			
 			for(Map.Entry<Long,List<PartiesDetailsVO>> resultIterator : map2.entrySet()){
 				Long constId = resultIterator.getKey();
 				String conName = map1.get(constId);	
				List<PartiesDetailsVO> dataObtained = generateTempData(partyIds,map,resultIterator.getValue(),resultIterator.getKey(),conName,selectedYearsCount,type);
				if(dataObtained!=null){
					PartiesStrengthsInfoVO infoVo = new PartiesStrengthsInfoVO();
	 				infoVo.setConstituencyId(constId);
	 				infoVo.setConstituencyName(conName);
	 				infoVo.setPartyResults(dataObtained);
	 				partiesStrengthsInfoVO.add(infoVo); 
				}			
 			}
 			constituencyElectionResults.setTotalNumberOfConstituencies(partiesStrengthsInfoVO.size()+0l);
 			constituencyElectionResults.setPartiesStrengthsInfoVO(partiesStrengthsInfoVO);
 			resultStatus.setResultCode(ResultCodeMapper.SUCCESS); 	
 		}catch(Exception e){
 			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
 			resultStatus.setExceptionEncountered(e); 			
 			e.printStackTrace();
 		}finally{
 			partyDetails = null;
 		}
 		constituencyElectionResults.setResultStatus(resultStatus);
 		return constituencyElectionResults;
 	}
 	
 	
 	
 	/**
 	 * This method can be used to know all parties strengths and weakness based on the given inputs.
 	 * 
 	 * @author Y.Ravi Kiran 
	 * @param electionType	
	 * @param stateId
	 * @param partieId
	 * @param totalElectionYears
	 * @return ConstituencyElectionResults
 	 */
 	public ConstituencyElectionResults getPartyStrengthsAndWeaknessDetails(String electionType,Long stateId,Long partieId,Long totalElectionYears,List result){
 		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();
 		Map<Long,String> map = new HashMap<Long,String>();
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		Map<Long,PartiesDetailsVO> data = new HashMap<Long,PartiesDetailsVO>();
 		try{
 			constituencyElectionResults.setElectionYears(getElectionYears(stateId,electionType,IConstants.ELECTION_SUBTYPE_MAIN,totalElectionYears));
 			constituencyElectionResults.setSelectedYearsCount(totalElectionYears);
 		 			
 			
 			
 			List<SelectOptionVO> staticParties = getAllPartiesData(stateId);
	 		Collections.sort(staticParties,new SelectOptionVOComparator());
	 		constituencyElectionResults.setAllPartiesData(staticParties);
		 		 		
	 		for(int i=0;i<staticParties.size();i++) 					
				map.put(staticParties.get(i).getId(), staticParties.get(i).getName());
		 		
		 	
	 		ContenetTransferVO contentVo = getStrengthsOfParties(electionType,stateId,partieId,totalElectionYears,result);
	 		partyOverview = contentVo.getPartyOverview();
	 		
	 		data = contentVo.getData();
	 		
 			List<PartiesDetailsVO> partiesDetailsVO = new ArrayList<PartiesDetailsVO>();
 			
 			if(result.size()>0)
 				partiesDetailsVO =  storeDataIntoVO(partyOverview,totalElectionYears,map,data,IConstants.TRUE);
 			else
 				partiesDetailsVO =  storeDataIntoVO(partyOverview,totalElectionYears,map,data,IConstants.FALSE);
 				
 			if(result.size()>0){
 				constituencyElectionResults.setPartiesDetailsVO(partiesDetailsVO);
 				List<PartiesDetailsVO> details = populateTotalCount(partiesDetailsVO);
 				constituencyElectionResults.setPartiesDetailsVO(details);
 			}else{
 				constituencyElectionResults.setPartiesDetailsVO(partiesDetailsVO);
 			}
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
 		return constituencyElectionResults;
 	}
 	 	
 	
 	public List<PartiesDetailsVO> populateTotalCount(List<PartiesDetailsVO> partiesDetailsVO){
 		List<PartiesDetailsVO> details =new ArrayList<PartiesDetailsVO>();
 		Map<Long,PartiesDetailsVO> map = new HashMap<Long,PartiesDetailsVO>();
 		try{
 			for(PartiesDetailsVO result : partiesDetailsVO){ 				
 				List<ContenetTransferVO> contentVO = result.getPartyDetails();
 				for(ContenetTransferVO conVO : contentVO){
 					Long count = conVO.getCount();
 					Long won = conVO.getWon();
 					Long wonRecently = conVO.getWonRecently();
 					Long lostRecently = conVO.getLostRecently();
 					PartiesDetailsVO partiesDetails = new PartiesDetailsVO();
 					if(map.containsKey(count)){
 	 					PartiesDetailsVO vo = map.get(count); 
 	 					partiesDetails.setWon(vo.getWon()+won);
 	 					partiesDetails.setWonTimes(wonRecently+vo.getWonTimes());
 	 					partiesDetails.setLostTimes(lostRecently+vo.getLostTimes());
 	 				}else{
 	 					partiesDetails.setWon(won);
 	 					partiesDetails.setCount(count);
 	 					partiesDetails.setWonTimes(wonRecently);
 	 					partiesDetails.setLostTimes(lostRecently); 	 					
 	 				}
 					map.put(count,partiesDetails);
 				}
 			}
 			
 			for(Map.Entry<Long,PartiesDetailsVO> mapDetails : map.entrySet()){
 				PartiesDetailsVO detailsVO = new PartiesDetailsVO();
 				detailsVO.setCount(mapDetails.getKey());
 				PartiesDetailsVO pvo = mapDetails.getValue();
 				detailsVO.setWon(pvo.getWon());
 				detailsVO.setWonTimes(pvo.getWonTimes());
 				detailsVO.setLostTimes(pvo.getLostTimes());
 				details.add(detailsVO);
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return details;
 	}
 	
 	
 	public List getDetails(String electionType,Long stateId,Long partieId,Long totalElectionYears){
 		List<Long> allConstituencies = new ArrayList<Long>(0);
		List<Long> selectedParties = new ArrayList<Long>(0);
		List result = new ArrayList();	
		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();		
		try{
 			allConstituencies = getAllConstituencies(electionType,stateId,totalElectionYears); 
			
 			if(partieId.intValue()==0){
 				selectedParties = staticDataService.getStaticPartiesAsList(stateId); 				
 				result = nominationDAO.getAllPartyStrengthsResults(allConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES);
 			}else{ 				
 				result = nominationDAO.getPartyResultsForAParty(allConstituencies,partieId,IConstants.ELECTION_SUBTYPE_MAIN,null,IConstants.WINNER_CANDIDATES);	
 			}
 			partyOverview = caluculatePartiesStrength(partyOverview,result,totalElectionYears,IConstants.FALSE);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return result;
 	}
 	
 	
 	public ContenetTransferVO getStrengthsOfParties(String electionType,Long stateId,Long partieId,Long totalElectionYears,List allianceData){
 		ContenetTransferVO contentVo = new ContenetTransferVO(); 		
		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();
		List result = new ArrayList();	
		Long latestElecId =0l;
		List<Long> allConstituencies = new ArrayList<Long>(0);
		List<Long> selectedParties = new ArrayList<Long>(0);
		Map<Long,PartiesDetailsVO>  data = new HashMap<Long,PartiesDetailsVO>(0);
		try{
 			allConstituencies = getAllConstituencies(electionType,stateId,totalElectionYears); 
			
 			if(partieId.intValue()==0){
 				selectedParties = staticDataService.getStaticPartiesAsList(stateId); 				
 				result = nominationDAO.getAllPartyStrengthsResults(allConstituencies,selectedParties,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.WINNER_CANDIDATES);
 			}else{ 				
 				result = nominationDAO.getPartyResultsForAParty(allConstituencies,partieId,IConstants.ELECTION_SUBTYPE_MAIN,null,IConstants.WINNER_CANDIDATES);	
 			}
 			latestElecId = getLatestElecId(electionType,stateId);
 			if(allianceData.size()>0){
 				partyOverview = caluculatePartiesStrength(partyOverview,allianceData,totalElectionYears,IConstants.TRUE);
 				data = generateDataForShowingPartyWeakness(allianceData,totalElectionYears,latestElecId);
 			}else{
 				partyOverview = caluculatePartiesStrength(partyOverview,result,totalElectionYears,IConstants.FALSE);
 				data = generateDataForShowingPartyWeakness(result,totalElectionYears,latestElecId);
 			}
 			contentVo.setData(data);
 			contentVo.setPartyOverview(partyOverview);
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return contentVo;
 	}
 	
 	public Long getLatestElecId(String electionType,Long stateId){
 		Long latestElecId =0l;
 		try{
 			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
 				List assElecId = electionDAO.findLatestElectionIdAndYearForAnElection(electionType,stateId,IConstants.ELECTION_SUBTYPE_MAIN);
 				latestElecId = new Long(assElecId.get(0).toString());
 			}else{
 				List parElecId = electionDAO.findLatestElectionIdAndYearForAnElection(electionType,stateId,IConstants.ELECTION_SUBTYPE_MAIN);
 				latestElecId = new Long(parElecId.get(0).toString());
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return latestElecId;
 	}
 	
 	public List<PartiesDetailsVO> getWeaknessConstituenceisDetails(String electionType,Long stateId,String type,Long partyId,Long colId,Long totalElectionYears,Long electionId,String partyName){
 		List result = new ArrayList();	
		Long latestElecId =0l;
		Map<Long,Map<Long,List<Long>>> data = new HashMap<Long,Map<Long,List<Long>>>(0);
		List<PartiesDetailsVO> partiesDetailsVO = new ArrayList<PartiesDetailsVO>(0);
		List constiDetails = new ArrayList(0);		
		List<Long> constIds = new ArrayList<Long>(0); 
		try{			
			latestElecId = getLatestElecId(electionType,stateId);
			result = getIncludingAllianceData(electionType,stateId,partyId,totalElectionYears,electionId,partyName);
 			
 			data = getPartyWiseConstituencies(result,totalElectionYears);
 			System.out.println(data.size());
 			Map<Long,List<Long>> colData = data.get(colId);
 			System.out.println(colData.size());
 			for(Map.Entry<Long, List<Long>> conIds : colData.entrySet()){
 				constIds.addAll(colData.get(conIds.getKey()));
 			}
 			System.out.println(constIds.size());
 			if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES))
 				type = IConstants.ALL_PARTIES;
 			constiDetails = nominationDAO.getAllAllianceCandidateDetailsForAConstituency(constIds,partyId,latestElecId,type);
 			
 			partiesDetailsVO = populateDataIntoVO(constiDetails,null);
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return partiesDetailsVO;
 	}
 	
 	public Map<Long,Map<Long,List<Long>>> getPartyWiseConstituencies(List result,Long totalElectionYears){
 		Map<Long,Map<Long,List<Long>>> data = new HashMap<Long,Map<Long,List<Long>>>(0);
 		try{
 			for(int i=0;i<result.size();i++){
 				Object[] parms = (Object[])result.get(i);
 				Long count = (Long)parms[1];
 				Long conId = (Long)parms[0];
 				Long partyId = (Long)parms[4];
 				
 				if(count<=totalElectionYears){ 	
 					List<Long> details = new ArrayList<Long>(0);
 					Map<Long,List<Long>> partyDetails = new HashMap<Long,List<Long>>(0);
	 				if(data.containsKey(count)){
	 					partyDetails = data.get(count);	 
	 					if(partyDetails.containsKey(partyId))
	 						details = partyDetails.get(partyId);	 						
	 					else
	 						partyDetails.put(partyId, details);	 					
	 				}
 					details.add(conId);
 					partyDetails.put(partyId, details);
 					data.put(count, partyDetails);	 				
 				}
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return data;
 	}
 
 
 	
 	public List<Long> getAllConstituencies(String electionType,Long stateId,Long totalElectionYears){
 		List<Long> allonstituencies = new ArrayList<Long>(0);
		ListIterator it = null;
		List<Long> requiredList = new ArrayList<Long>(0);
 		try{
 			requiredList = getElectionIds(stateId,electionType,IConstants.ELECTION_SUBTYPE_MAIN,totalElectionYears);
 			List constituencies = constituencyElectionDAO.getConstituenciesHavingMaxSpan(IConstants.ELECTION_SUBTYPE_MAIN,electionType,stateId,requiredList);				
 			it = constituencies.listIterator(); 			
 			while(it.hasNext()){
				Object[] parms = (Object[]) it.next();
				allonstituencies.add((Long)parms[1]);			
 			} 
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return allonstituencies;
 	}
 	
 	
	public Map<Long,PartiesDetailsVO> caluculatePartiesStrength(Map<Long,PartiesDetailsVO> partyOverview,List result,Long totalElectionYears,String hasAlliance){
 		try{
 			for(int i=0; i<result.size(); i++){
				Object[] parms = (Object[])result.get(i); 				
				Long partyId = (Long)parms[4];
				Long count = (Long)parms[1];
				if(count<=totalElectionYears){
					if(partyOverview.containsKey(partyId)){
	 					PartiesDetailsVO partyOverviewDetails = partyOverview.get(partyId);
	 					Map<Long,Long>  partyStrenghCount = partyOverviewDetails.getPartyStrenghCount(); 					
							if(partyStrenghCount.containsKey(count)){	 	
								Long temp = partyStrenghCount.get(count);							
								partyStrenghCount.put(count,temp+=1);	
	 					}else{
	 						partyStrenghCount.put(count,1l);	 			
	 					}	
	 					partyOverviewDetails.setPartyStrenghCount(partyStrenghCount);
	 					
	 					partyOverview.put(partyId,partyOverviewDetails);	
	 				}else{	 
	 					PartiesDetailsVO partyDetailsVO = new PartiesDetailsVO(); 					
	 					Map<Long,Long>  partyStrenghCount = new HashMap<Long,Long>();	 					
	 					partyStrenghCount.put(count,1l);	 					
	 					partyDetailsVO.setPartyStrenghCount(partyStrenghCount);	 					
	 					
	 					partyOverview.put(partyId,partyDetailsVO);
	 				}
				}
			}	
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return partyOverview;
 	}
 	
	
	public List<SelectOptionVO> caluculatePartiesStrengthBasedOnConstituencyId(List result,Long totalElectionYears){
 		
		Map<Long,List<SelectOptionVO>> overview = new HashMap<Long,List<SelectOptionVO>>();
		List<SelectOptionVO> details = new ArrayList<SelectOptionVO>(0);
		Map<Long,Long> exactDetails = new HashMap<Long, Long>(0);
		Map<Long,List<Long>> otherDetails = new HashMap<Long,List<Long>>(0);
		try{			
			getConstituencyStrength(result,overview);
			for(Map.Entry<Long,List<SelectOptionVO>> entry : overview.entrySet()){
				List<SelectOptionVO> selList = entry.getValue();
				Long constId = entry.getKey();
				for(int k=0;k<selList.size();k++){
					Long id = selList.get(k).getId();
					Long count = new Long(selList.get(k).getName());
					if(exactDetails.containsKey(id)){
						Long tempCount = exactDetails.get(id);
						tempCount += count;
						exactDetails.put(id,tempCount);
						List<Long> conIds = new ArrayList<Long>(0);
						if(otherDetails.containsKey(constId)){							
							conIds = otherDetails.get(constId);							
						}
						conIds.add(constId);
						otherDetails.put(id,conIds);
					}else{
						exactDetails.put(id,count);
						List<Long> conIds = new ArrayList<Long>(0);
						conIds.add(constId);
						otherDetails.put(id,conIds);
					}
				}
			}		
			
			for(Map.Entry<Long,Long> entry : exactDetails.entrySet()){
				details.add(new SelectOptionVO(entry.getKey(),entry.getValue().toString()));
			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return details;
 	}
	
	
	public Map<Long,PartiesDetailsVO> generateDataForShowingPartyWeakness(List result,Long totalElectionYears,Long elecId){
 		Map<Long,Map<Long,List<Long>>> data = new HashMap<Long,Map<Long,List<Long>>>(0);
 		Map<Long,PartiesDetailsVO>  weaknessOverview = new HashMap<Long,PartiesDetailsVO>(0);
 		
 		try{
 			data = getPartyWiseConstituencies(result,totalElectionYears);
 			 			
 			for(Map.Entry<Long,Map<Long,List<Long>>> iteratedData : data.entrySet()){
 				Long countKey = iteratedData.getKey();
 				Map<Long,List<Long>> partyDetails = iteratedData.getValue();
 				for(Map.Entry<Long,List<Long>> countData : partyDetails.entrySet()){
 					
 					Long partyId = countData.getKey();
 					List<Long> consIds = countData.getValue();
 					
 					List count = nominationDAO.getCountOfWonConstituency(consIds,partyId,elecId);
 					Long wonTimes = new Long(count.get(0).toString());
 					Long lostTimes = new Long(consIds.size()-wonTimes.intValue());
 					
 					if(weaknessOverview.containsKey(partyId)){
 						PartiesDetailsVO partyOverviewDetails = weaknessOverview.get(partyId);
		 					Map<Long,SelectOptionVO>  partyStrenghCount = partyOverviewDetails.getPartyWeaknessCount();					
							partyStrenghCount.put(countKey,new SelectOptionVO(wonTimes,lostTimes.toString()));
		 					partyOverviewDetails.setPartyWeaknessCount(partyStrenghCount);		 					
		 					weaknessOverview.put(partyId,partyOverviewDetails);	
 					}else{
 						PartiesDetailsVO partyDetailsVO = new PartiesDetailsVO();
 						Map<Long,SelectOptionVO>  partyStrenghCount = new HashMap<Long,SelectOptionVO>();	 					
 						partyStrenghCount.put(countKey,new SelectOptionVO(wonTimes,lostTimes.toString()));	 					
		 				partyDetailsVO.setPartyWeaknessCount(partyStrenghCount);	
 						weaknessOverview.put(partyId,partyDetailsVO);
 					}
 				}
 			}
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return weaknessOverview;
 	}
	
	
	public void getConstituencyStrength(List result,Map<Long,List<SelectOptionVO>> overview){
		try{
			for(int i=0; i<result.size(); i++){
				Object[] parms = (Object[])result.get(i);
				Long constId = (Long)parms[0];
				Long count = (Long)parms[1];
				List<SelectOptionVO> list = new ArrayList<SelectOptionVO>(0);
				if(overview.containsKey(constId)){ 
					list = overview.get(constId);
					for(int j=0;j<list.size();j++){
						if(list.get(j).getId().intValue() == count.intValue()){
							Long tempCount = new Long(list.get(j).getName())+1l;							
							list.get(j).setName(tempCount.toString());
						}
					}
				}else{					
					Long tempCount = 1l;
					list.add(new SelectOptionVO(count,tempCount.toString()));
					overview.put(constId,list);
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
 	public List<PartiesDetailsVO> storeDataIntoVO(Map<Long,PartiesDetailsVO> partyOverview,Long totalElectionYears,Map<Long,String> map,Map<Long,PartiesDetailsVO> data,String hasAlliance){
 		Map<Long,String> auxMap = new HashMap<Long,String>();
 		List<PartiesDetailsVO> partiesDetailsVO = new ArrayList<PartiesDetailsVO>();

 		try{
				for(Map.Entry<Long,PartiesDetailsVO> resultIterator : partyOverview.entrySet()){
					PartiesDetailsVO partyVO = new PartiesDetailsVO();
					Long partyId = resultIterator.getKey();
					
					
					partyVO.setPartyId(partyId); 				
					partyVO.setCount(resultIterator.getValue().getCount());
					partyVO.setDetails(resultIterator.getValue().getDetails());
					partyVO.setPartyName(map.get(resultIterator.getKey()));
					
					for(Long m=1l;m<=totalElectionYears;m++){
						auxMap.put(m,IConstants.FALSE);				
					}
										
					List<ContenetTransferVO> seVo = new ArrayList<ContenetTransferVO>();
					for(Map.Entry<Long,Long> resuIterator : resultIterator.getValue().getPartyStrenghCount().entrySet()){ 					
						Long count = resuIterator.getKey();
						Long won = resuIterator.getValue();
						
						ContenetTransferVO vo = new ContenetTransferVO();
						vo.setCount(count);
						vo.setWon(won);
						
						for(Map.Entry<Long,PartiesDetailsVO> dataIterator : data.entrySet()){ 					
							if(dataIterator.getKey().intValue()==partyId.intValue()){
								for(Map.Entry<Long,SelectOptionVO> weakness : dataIterator.getValue().getPartyWeaknessCount().entrySet()){
									if(weakness.getKey().intValue()==count.intValue()){
										vo.setWonRecently(weakness.getValue().getId());
										vo.setLostRecently(Long.parseLong(weakness.getValue().getName()));
									}
								}																
							}
						}
						seVo.add(vo); 
						auxMap.put(resuIterator.getKey(),IConstants.TRUE);
					}
					
					for(Map.Entry<Long,String> resultData: auxMap.entrySet()){
						if(resultData.getValue().equalsIgnoreCase(IConstants.FALSE)){
							seVo.add(new ContenetTransferVO(resultData.getKey(),0L,0L,0L)); 
						}		
					}
					Collections.sort(seVo,new ContenetTransferVOIdComparator());
					
					
					partyVO.setPartyDetails(seVo);
					
					partiesDetailsVO.add(partyVO);
				} 	
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return partiesDetailsVO;
 	}
 	
 	public List<PartiesDetailsVO> generateTempData(List<Long> partyIds,Map<Long,String> map,List<PartiesDetailsVO> partyDetails,Long constId,String constName,Long selectedYearsCount,String type){
 		List<PartiesDetailsVO> result = new ArrayList<PartiesDetailsVO>(0); 
 		Map<String,String> auxMap = new HashMap<String,String>();
 		int totalCount=0;
 		try{
			for(Map.Entry<Long,String> resultData: map.entrySet()){
				auxMap.put(resultData.getValue(),IConstants.FALSE);				
			}
			auxMap.put(IConstants.OTHERS,IConstants.FALSE);	
			
			for(int i=0;i<partyDetails.size();i++){
				if(auxMap.containsKey(partyDetails.get(i).getPartyName())){
					auxMap.put(partyDetails.get(i).getPartyName(),IConstants.TRUE);
				}
				totalCount += partyDetails.get(i).getCount().intValue();
			}			

			for(Map.Entry<String,String> resultData : auxMap.entrySet()){
				if(resultData.getValue().equalsIgnoreCase(IConstants.FALSE) && !resultData.getKey().equalsIgnoreCase(IConstants.OTHERS)){
					PartiesDetailsVO vo = new PartiesDetailsVO();
					vo.setConstituencyId(constId);
					vo.setCount(0L);
					vo.setPartyName(resultData.getKey());
					vo.setConstituencyName(constName); 		
					result.add(vo);
				}			
			}
			
			result.addAll(partyDetails); 
						
			Collections.sort(result, new PartiesDetailsVOComparator());		
			
			//Code for storing others count	
				if(selectedYearsCount.intValue()-totalCount<0){
					return null;
				}else{
					//if(type.equalsIgnoreCase(IConstants.REQUIRED_CONSTITUENCIES)){
						PartiesDetailsVO vo = new PartiesDetailsVO();
						vo.setConstituencyId(constId);
						vo.setCount(selectedYearsCount-totalCount);
						vo.setPartyName(IConstants.OTHERS);
						vo.setConstituencyName(constName); 		
						result.add(vo);
					//}		
				}						
			//ends here...
						
	 	}catch(Exception e){
			e.printStackTrace();
		}
	 	return result;
 	}

 
 	/**
 	 * This method is used to set Party related information data in to a DTO.
 	 * @param parms
 	 * @param type
 	 * @return
 	 */
 	public PartiesDetailsVO setDataIntoPartiesDetailsVO(Object[] parms){
 		PartiesDetailsVO partyDetails = new PartiesDetailsVO();
 		try{
			partyDetails.setConstituencyId((Long)parms[0]);
			partyDetails.setCount((Long)parms[1]);
			partyDetails.setPartyName(parms[2].toString());
			partyDetails.setConstituencyName(parms[3].toString()); 	
			partyDetails.setPartyId((Long)parms[4]);
	 	}catch(Exception e){
			e.printStackTrace();
		}
	 	return partyDetails;
 	}
 	
 	
 	public List<SelectOptionVO> getAllPartiesData(Long stateId){
 		List<Party> parties = null;
 		List<SelectOptionVO> staticParties = new ArrayList<SelectOptionVO>();
 		try{
 			if(stateId.intValue()==1){
 				parties = partyDAO.findByShortNames(IConstants.STATIC_PARTIES);
 			}else if(stateId.intValue()==24){//tamilnadu
 				parties = partyDAO.findByShortNames(IConstants.STATIC_TAMIL_NADU_PARTIES);
 			}else if(stateId.intValue()==12){//karnataka
 				parties = partyDAO.findByShortNames(IConstants.STATIC_KARNATAKA_PARTIES);
 			}else{
 				parties = partyDAO.findByShortNames(IConstants.STATIC_PARTIES);
 			}			
			for(Party party : parties){
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(party.getPartyId());
				selectOptionVO.setName(party.getShortName());
				staticParties.add(selectOptionVO);
			}
			return staticParties;
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return 	staticParties;
 	}

 	public List<Long> getElectionIds(Long stateId,String electionType,String elecSubType,Long electionYears){ 		
		List<Long> requiredList = new ArrayList<Long>(0);
		try{
			List electIds = electionDAO.getElectionIds(stateId,electionType,elecSubType);	 		
			for(int i=0;i<electIds.size();i++){
				if(i<electionYears.intValue())
				 requiredList.add((Long)electIds.get(i));
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
 		return requiredList;
 	}
 	
 	public List<String> getElectionYears(Long stateId,String electionType,String elecSubType,Long electionYears){ 		
		List<String> requiredList = new ArrayList<String>(0);
		try{
			List electIds = electionDAO.getElectionYears(stateId,electionType,elecSubType);	 		
			for(int i=0;i<electIds.size();i++){
				if(i<electionYears.intValue())
				 requiredList.add((String)electIds.get(i));
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
 		return requiredList;
 	}
		
 	public List<PartiesDetailsVO> getAllConstituenciesData(String electionType,Long stateId,String partyName,Long totalElectionYears,Long resultCount){
 		List<PartiesDetailsVO> partyDetailsList = new ArrayList<PartiesDetailsVO>(0);
 		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();
 		Map<Long,List<Long>> details = new HashMap<Long,List<Long>>();
 		List<Long> allonstituencies = new ArrayList<Long>(0);
		ListIterator it = null;		
		List result = null;	
		List<Long> requiredList = null;
		List partieDetails;
		Long partieId=0l;
 		try{ 
 			
 			partieDetails = partyDAO.findPartyIdByShortName(partyName);
 			partieId = (Long)partieDetails.get(0);
 			requiredList = getElectionIds(stateId,electionType,IConstants.ELECTION_SUBTYPE_MAIN,totalElectionYears);
 			List constituencies = constituencyElectionDAO.getConstituenciesHavingMaxSpan(IConstants.ELECTION_SUBTYPE_MAIN,electionType,stateId,requiredList);				
 			it = constituencies.listIterator();
 			while(it.hasNext()){
				Object[] parms = (Object[]) it.next();
				allonstituencies.add((Long)parms[1]);			
 			}				
			result = nominationDAO.getPartyResultsForAParty(allonstituencies,partieId,IConstants.ELECTION_SUBTYPE_MAIN,null,IConstants.WINNER_CANDIDATES);
			 		 
 			for(int i=0; i<result.size(); i++){
 				Object[] parms = (Object[])result.get(i); 				
 				Long partyId = (Long)parms[4];
 				Long count = (Long)parms[1];
 				Long constId = (Long)parms[0]; 				
 					if(partyOverview.containsKey(partyId)){
 	 					PartiesDetailsVO partyOverviewDetails = partyOverview.get(partyId);
 	 					Map<Long,Long>  partyStrenghCount = partyOverviewDetails.getPartyStrenghCount(); 					
 							if(partyStrenghCount.containsKey(count)){	 	
 								Long temp = partyStrenghCount.get(count);							
 								partyStrenghCount.put(count,temp+=1);	
 	 					}else{
 	 						partyStrenghCount.put(count,1l);	 			
 	 					}	
 	 					partyOverviewDetails.setPartyStrenghCount(partyStrenghCount);
 	 				
 	 					List<Long> conIds = new ArrayList<Long>();
 	 					if(details.get(count)!=null){
 	 						conIds.addAll(details.get(count)); 						
 	 					}
 	 					conIds.add(constId);
 	 					details.put(count,conIds);
 	 					
 	 					partyOverview.put(partyId,partyOverviewDetails);	
 	 				}else{	 
 	 					PartiesDetailsVO partyDetailsVO = new PartiesDetailsVO(); 					
 	 					Map<Long,Long>  partyStrenghCount = new HashMap<Long,Long>();	 					
 	 					partyStrenghCount.put(count,1l);	 					
 	 					partyDetailsVO.setPartyStrenghCount(partyStrenghCount);	
 	 					
 	 					List<Long> conIds = new ArrayList<Long>();
 	 					conIds.add(constId);
 	 					details.put(count,conIds);
 	 					
 	 					partyOverview.put(partyId,partyDetailsVO);
 	 				} 							
 			}			
 		 	 
 			List<Long> conIds = details.get(resultCount);
 			List list = nominationDAO.getAllCandidateDetailsForAConstituency(conIds,partieId,IConstants.ELECTION_SUBTYPE_MAIN,null);
 			partyDetailsList = populateDataIntoVO(list,null);
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		
 		return partyDetailsList;
 	}
 	
 	
 	
 	
 	
 	
 	public List<PartiesDetailsVO> getAllCandidatesDetailsForAllianceData(String electionType,Long stateId,Long partyId,Long totalElectionYears,Long electionId,String partyName,Long columnId){
 		List<PartiesDetailsVO> partiesDetails = new ArrayList<PartiesDetailsVO>(); 		
 		List electionIds = new ArrayList(0);
 		List result = new ArrayList(); 		
 		List<Long> allConstituencies = new ArrayList<Long>(0);
 		Map<Long,Long> overview = new HashMap<Long,Long>(0);
 		List<Long> requiredConsts = new ArrayList<Long>(0);
 		try{ 	
 			allConstituencies = getAllConstituencies(electionType,stateId,totalElectionYears);  
 			
 			partiesDetails.addAll(getAllConstituenciesData(electionType,stateId,partyName,totalElectionYears,columnId));
 			if(electionId.intValue()!=0){
 				electionIds = electionDAO.getElectionDetailsForAnElection(electionId); 				
 			}else{
 				electionIds = electionAllianceDAO.getAllAllianceElectionYearsForAParty(partyId,IConstants.ELECTION_SUBTYPE_MAIN,stateId); 				 	 				
 			} 
 			for(int i=0;i<electionIds.size();i++){
				Object[] params = (Object[])electionIds.get(i);
				String electionYear =  params[0].toString();
				Long elecId = (Long)params[1];
				Long electionTypeId = (Long)params[2];
				List<Party> allianceParties = staticDataService.getAllianceParties(electionYear,electionTypeId,partyId);
				for(Party eachParty : allianceParties){		
					if(eachParty.getPartyId().intValue()!=partyId.intValue()){
						List list = nominationDAO.getAllCandidateDetailsForAConstituency(allConstituencies,eachParty.getPartyId(),IConstants.ELECTION_SUBTYPE_MAIN,elecId);					 				
						result.addAll(list);	
					}					
				}
 			}	
			for(int k=0; k<result.size(); k++){
				Object[] parms = (Object[])result.get(k);
				Long constId = (Long)parms[0];
				if(overview.containsKey(constId)){ 
					Long temp = overview.get(constId);
					overview.put(constId,temp+1);
				}else{					
					Long tempCount = 1l;
					overview.put(constId,tempCount);
				}				
			}
			
			for(Map.Entry<Long,Long> data : overview.entrySet()){
				if(data.getValue().intValue() ==  columnId.intValue()){
					requiredConsts.add(data.getKey());
				}					
			}
			if(requiredConsts!=null && requiredConsts.size()!=0 )
				partiesDetails.addAll(populateDataIntoVO(result,requiredConsts));
			
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return partiesDetails;
 	}
 	
 	
 	
 	public void saveDataIntoVo(Map<Long,PartiesDetailsVO> partyOverview,List<SelectOptionVO> details){
 		for(Map.Entry<Long,PartiesDetailsVO> entry : partyOverview.entrySet()){
			for(Map.Entry<Long,Long>  partyVO : entry.getValue().getPartyStrenghCount().entrySet()){
				details.add(new SelectOptionVO(partyVO.getKey(),partyVO.getValue().toString()));
			} 				
		}
 	}
 	public List<PartiesDetailsVO> populateDataIntoVO(List list,List requiredConstituencies){
 		List<PartiesDetailsVO> partyDetailsList = new ArrayList<PartiesDetailsVO>(0);
 		try{ 			
 			for(int i=0; i<list.size(); i++){
 				Object[] parms = (Object[])list.get(i); 				
 				Long constId = (Long)parms[0];
				PartiesDetailsVO partyDetailsVo = new PartiesDetailsVO();
				partyDetailsVo.setConstituencyId(constId);
 				partyDetailsVo.setConstituencyName(parms[1].toString());
 				partyDetailsVo.setPartyName(parms[3].toString());
 				partyDetailsVo.setElectionYear(parms[4].toString()); 				
 				partyDetailsVo.setVotesEarned(new BigDecimal(parms[5].toString()).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
 				partyDetailsVo.setCandidateName(parms[6].toString()); 	 				
 				if(requiredConstituencies!=null ){ 					
 	 				if(requiredConstituencies.contains(constId))
 	 					partyDetailsList.add(partyDetailsVo); 	
 				}else{
 					partyDetailsList.add(partyDetailsVo); 	
 				}							
 			}		
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return partyDetailsList;
 	}
  	
 /*	public List<PartiesDetailsVO> iterateOverGivenData(List result){
 		List<PartiesDetailsVO> partiesDetailsVO = new ArrayList<PartiesDetailsVO>(0);
 		try{
 			if(result!=null && result.size()!=0){
 				for(int i=0;i<result.size();i++){
 					Object[] params = (Object[])result.get(i);
 					PartiesDetailsVO vo = new PartiesDetailsVO(); 					
 					vo.setPartyName(params[3].toString());
 					vo.setConstituencyName(params[1].toString());
 					vo.setElectionYear(params[4].toString());
 					vo.setVotesEarned(params[5].toString());
 					vo.setCandidateName(params[6].toString());
 					partiesDetailsVO.add(vo);
 				}
 			} 			
		}catch(Exception e){
			e.printStackTrace();
		}
		return partiesDetailsVO;
 	}*/
 	
 	/*public PartiesDetailsVO getExcludingAllianceData(String electionType,Long stateId,Long partyId,Long totalElectionYears,String partyName){
		PartiesDetailsVO partiesDetails = new PartiesDetailsVO();
 		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();	
 		List<SelectOptionVO> details = new ArrayList<SelectOptionVO>();
 		Map<Long,PartiesDetailsVO> data = new HashMap<Long,PartiesDetailsVO>();
 		try{
 			ContenetTransferVO contentVo = getStrengthsOfParties(electionType,stateId,partyId,totalElectionYears);
	 		partyOverview = contentVo.getPartyOverview();
	 		
	 		data = contentVo.getData();
 			saveDataIntoVo(partyOverview,details);
 			partiesDetails.setDetails(details);
 			partiesDetails.setPartyName(partyName);
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
 		return partiesDetails;
 	}*/
 	
 	public PartiesDetailsVO getAllElectionAllianceYearsForAParty(Long partyId,Long stateId,String partyName){
 		PartiesDetailsVO details = new PartiesDetailsVO();
 		List<SelectOptionVO> elecYearsAndIds = new ArrayList<SelectOptionVO>();
 		try{
 			List list = electionAllianceDAO.getAllAllianceElectionYearsForAParty(partyId,IConstants.ELECTION_SUBTYPE_MAIN,stateId);		
 			for(int i=0;i<list.size();i++){
 				Object[] params = (Object[])list.get(i);
 				SelectOptionVO selectOptionVO = new SelectOptionVO();
 				selectOptionVO.setId((Long)params[1]);	
 				selectOptionVO.setName(params[0].toString());
 				elecYearsAndIds.add(selectOptionVO);
 			}
 			details.setPartyId(partyId);
 			details.setPartyName(partyName);
 			details.setDetails(elecYearsAndIds);
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
		return details;
 	}

}
