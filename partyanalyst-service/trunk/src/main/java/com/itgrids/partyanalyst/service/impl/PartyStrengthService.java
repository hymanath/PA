package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.PartiesStrenghInfoVO;
import com.itgrids.partyanalyst.dto.PartiesStrengthsInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartiesDetailsVOComparator;
import com.itgrids.partyanalyst.utils.SelectOptionVOByIdComparator;
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
	
	//Getters and Setters for Instance Variables.
	
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
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
		
		try{
			List result = constituencyElectionDAO.getConstituenciesHavingMaxSpan(electionSubType,electionType,stateId);				
			if(result!=null && result.size()>0){
				it = result.listIterator();
				while(it.hasNext()){
					Object[] parms = (Object[]) it.next();
					Long constituencyId =  (Long)parms[1];
					
					Long count = (Long)parms[0];					
					if(count.intValue() == selectedNoOfYears.intValue() && count!=1l){						
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
 	public ElectionInfoVO getPartiesData(String electionType,Long stateId,Long electionYearsCount,Long partyId){
 		List<Long> requiredConstituencies = new ArrayList<Long>(0);
		List<Long> latestConstituencies = new ArrayList<Long>(0);
		List<Long> remianingConstituencies = new ArrayList<Long>(0);
		List<Long> selectedParties = new ArrayList<Long>(0);
		ElectionInfoVO resultVo = new ElectionInfoVO();
		ResultStatus resultStatus = new ResultStatus();		
		
		List<SelectOptionVO> staticParties = staticDataService.getStaticParties();
 		Collections.sort(staticParties,new SelectOptionVOComparator());
 		
 		for(int i=0;i<staticParties.size();i++){ 
			if(staticParties.get(i).getId().intValue() == partyId.intValue())
				resultVo.setPartyName(staticParties.get(i).getName());
 		}
 		
 		if(resultVo.getPartyName()== null ||  resultVo.getPartyName()== "")
 			resultVo.setPartyName("All Parties"); 		
 		 		
		try{
			PartiesStrenghInfoVO partiesStrenghInfoVO = segregateAllConstituencies(electionYearsCount,electionType,IConstants.ELECTION_SUBTYPE_MAIN,stateId);
	 
			latestConstituencies = partiesStrenghInfoVO.getLatestConstituencies();
			requiredConstituencies = partiesStrenghInfoVO.getRequiredConstituencies();
			remianingConstituencies = partiesStrenghInfoVO.getRemianingConstituencies();
			
			if(partyId.intValue()==0){
				selectedParties = staticDataService.getStaticPartiesAsList();					 				
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
 	 
 	public void getDataForAllParties(ElectionInfoVO resultVo,List<Long> requiredConstituencies,List<Long> latestConstituencies,List<Long> remianingConstituencies,List<Long> selectedParties,Long electionYearsCount,String electionType,Long stateId){
 	try{
 			if(requiredConstituencies.size()!=0) 			
 				resultVo.setRequiredConstituenciesInfo(getAllElectionData(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties),electionYearsCount,IConstants.REQUIRED_CONSTITUENCIES,electionType,stateId));
			else
 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
 			
 			if(latestConstituencies.size()!=0)
 				resultVo.setLatestConstituenciesInfo(getAllElectionData(latestConstituencies,nominationDAO.getAllPartyResults(latestConstituencies,selectedParties),electionYearsCount,IConstants.LATEST_CONSTITUENCIES,electionType,stateId));	
			else
				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());

 			if(remianingConstituencies.size()!=0)	 				
 				resultVo.setRemainingConstituenciesInfo(getAllElectionData(remianingConstituencies,nominationDAO.getAllPartyResults(remianingConstituencies,selectedParties),electionYearsCount,IConstants.REMAINING_CONSTITUENCIES,electionType,stateId));
 			else
 				resultVo.setRemainingConstituenciesInfo(new ConstituencyElectionResults());
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
 	}
 	
 	
 	public void getDataForAParty(ElectionInfoVO resultVo,List<Long> requiredConstituencies,List<Long> latestConstituencies,List<Long> remianingConstituencies,List<Long> selectedParties,Long electionYearsCount,String electionType,Long stateId){
 	
 		try{
 			if(requiredConstituencies.size()!=0) 			
 				resultVo.setRequiredConstituenciesInfo(setElectionDataInToVo(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties)));
				else
 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
 			
 			if(latestConstituencies.size()!=0)
 				resultVo.setLatestConstituenciesInfo(setElectionDataInToVo(latestConstituencies,nominationDAO.getAllPartyResults(latestConstituencies,selectedParties)));	
				else
 				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());
 			
 			if(remianingConstituencies.size()!=0)	 				
 				resultVo.setRemainingConstituenciesInfo(setElectionDataInToVo(remianingConstituencies,nominationDAO.getAllPartyResults(remianingConstituencies,selectedParties)));
 			else
 				resultVo.setRemainingConstituenciesInfo(new ConstituencyElectionResults());
 		}catch(Exception e){
 			e.printStackTrace();
 		} 		
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
	 			constituencyElectionResults.setTotalNumberOfConstituencies(count);
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
 	
 	public List<SelectOptionVO> getCountOfElectionYears(Long stateId,String electionType){
 		List<SelectOptionVO> ids = new ArrayList<SelectOptionVO>(0);
 		List allYears = new ArrayList();
 		Long count=0l;
 		try{
 			
 			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
 				 allYears = electionDAO.getCountOfElectionYears(stateId,electionType);
 			}else{
 				 allYears = electionDAO.getCountOfElectionYearsForParliament(stateId,electionType);
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
 		
 		Map<Long,PartiesDetailsVO> partyOverview = new HashMap<Long,PartiesDetailsVO>();
 		//PartiesDetailsVO partyOverviewDetails = new PartiesDetailsVO(); 
 		
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		ResultStatus resultStatus = new ResultStatus();
 		 		
 		List<SelectOptionVO> staticParties = staticDataService.getStaticParties();
 		Collections.sort(staticParties,new SelectOptionVOComparator());
 		constituencyElectionResults.setAllPartiesData(staticParties);
 		 		
 		for(int i=0;i<staticParties.size();i++){ 
			partyIds.add(staticParties.get(i).getId());
			map.put(staticParties.get(i).getId(), staticParties.get(i).getName());
		}
 		try{  			
 			if(result!=null && result.size()>0){
	 			for(int i=0; i<result.size(); i++){
	 				Object[] parms = (Object[])result.get(i);
	 				Long constituencyId = (Long)parms[0];
	 				Long partyId = (Long)parms[4];
	 				Long count = (Long)parms[1];
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
	 				
	 				if(partyOverview.containsKey(partyId)){
	 					PartiesDetailsVO partyOverviewDetails = partyOverview.get(partyId);
	 					partyOverviewDetails.setCount(count+partyOverviewDetails.getCount());
	 					List<SelectOptionVO> slectVo = partyOverviewDetails.getDetails();
	 					if(slectVo!=null){
	 						slectVo.add(new SelectOptionVO(constituencyId,constiName));
	 					}else{
	 						slectVo = new ArrayList<SelectOptionVO>(0);
	 						slectVo.add(new SelectOptionVO(constituencyId,constiName));
	 					}	 					
	 					partyOverviewDetails.setDetails(slectVo);
	 					
	 					Map<Long,Long>  partyStrenghCount = partyOverviewDetails.getPartyStrenghCount();
	 					
	 					if(partyStrenghCount==null){
	 						partyStrenghCount = new HashMap<Long,Long>();
	 						partyStrenghCount.put(count,1l);	 			
	 					}else{
	 						if(partyStrenghCount.containsKey(count)){	 	
	 							Long temp = partyStrenghCount.get(count);
	 							partyStrenghCount.put(count,temp+=1);	
		 					}else{
		 						partyStrenghCount.put(count,1l);	 				
		 					}	
	 					}
	 					partyOverviewDetails.setPartyStrenghCount(partyStrenghCount);
	 					
	 					partyOverview.put(partyId,partyOverviewDetails);	
	 				}else{	 
	 					PartiesDetailsVO partyDetailsVO = new PartiesDetailsVO();
	 					partyDetailsVO.setCount(count);
	 					
	 					List<SelectOptionVO> slectVo = new ArrayList<SelectOptionVO>(0);
	 					slectVo.add(new SelectOptionVO(constituencyId,constiName));
	 					partyDetailsVO.setDetails(slectVo);
	 					
	 					Map<Long,Long>  partyStrenghCount = new HashMap<Long,Long>();	 					
	 					partyStrenghCount.put(count,1l);	 					
	 					partyDetailsVO.setPartyStrenghCount(partyStrenghCount);	 					
	 					
	 					partyOverview.put(partyId,partyDetailsVO);
	 				}
	 				
	 			}	
 			}
 			
 			Map<Long,String> auxMap = new HashMap<Long,String>();
 	 		
 			List<Long> allYears = getListOfElections(electionType,stateId);
 			
			for(Long m=0l;m<allYears.size();m++){
				auxMap.put(m,IConstants.FALSE);				
			}
			
 			List<PartiesDetailsVO> partiesDetailsVO = new ArrayList<PartiesDetailsVO>();
 			for(Map.Entry<Long,PartiesDetailsVO> resultIterator : partyOverview.entrySet()){
 				PartiesDetailsVO partyVO = new PartiesDetailsVO();
 				partyVO.setPartyId(resultIterator.getKey()); 				
 				partyVO.setCount(resultIterator.getValue().getCount());
 				partyVO.setDetails(resultIterator.getValue().getDetails());
 				partyVO.setPartyName(map.get(resultIterator.getKey()));
 				
 				List<SelectOptionVO> seVo = new ArrayList<SelectOptionVO>();
 				for(Map.Entry<Long,Long> resuIterator : resultIterator.getValue().getPartyStrenghCount().entrySet()){ 					
 					seVo.add(new SelectOptionVO(resuIterator.getKey(),resuIterator.getValue().toString())); 
 					auxMap.put(resuIterator.getKey(),IConstants.TRUE);	
 				}
 				for(Map.Entry<Long,String> resultData: auxMap.entrySet()){
 					if(resultData.getValue().equalsIgnoreCase(IConstants.FALSE)){
 						seVo.add(new SelectOptionVO(resultData.getKey(),"0")); 
 					}		
 				}
 				Collections.sort(seVo,new SelectOptionVOByIdComparator());
 				Collections.reverse(seVo);
 				partyVO.setPartyDetails(seVo);
 				partiesDetailsVO.add(partyVO);
 			}
 			
 			constituencyElectionResults.setPartiesDetailsVO(partiesDetailsVO);
 			
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
 			constituencyElectionResults.setTotalNumberOfConstituencies(new Long(map2.size()));
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
 	
 	public List<Long> getListOfElections(String electionType,Long stateId){
 		Long count=0l;
 		List allYears = new ArrayList();
 		List<Long> ids = new ArrayList<Long>();
 		try{
 			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
 				 allYears = electionDAO.getCountOfElectionYears(stateId,electionType);
 			}else{
 				 allYears = electionDAO.getCountOfElectionYearsForParliament(stateId,electionType);
 			}	 		
	 		count = new Long(allYears.get(0).toString());
	 		for(Long i=count;i>0;i--){
	 			ids.add(i);	
	 		}
 		}catch(Exception e){
 			e.printStackTrace();
 		}	
 		return ids;
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
					if(type.equalsIgnoreCase(IConstants.REQUIRED_CONSTITUENCIES)){
						PartiesDetailsVO vo = new PartiesDetailsVO();
						vo.setConstituencyId(constId);
						vo.setCount(selectedYearsCount-totalCount);
						vo.setPartyName(IConstants.OTHERS);
						vo.setConstituencyName(constName); 		
						result.add(vo);
					}		
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
 	
 	
 	public List<SelectOptionVO> getAllPartiesData(String electionType,Long stateId){
 		
 		List<SelectOptionVO> partiesList = staticDataService.getStaticParties();
 		//partiesList.add(new SelectOptionVO(0l,"All Parties")); 		
 		return 	partiesList;
 	}

		
}
