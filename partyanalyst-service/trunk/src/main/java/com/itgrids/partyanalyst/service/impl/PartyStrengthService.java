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
					Long count = (Long)parms[0];					
					if(count.intValue() >= selectedNoOfYears.intValue() && count!=1l){
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
					 				
	 			if(requiredConstituencies.size()!=0) 			
	 				resultVo.setRequiredConstituenciesInfo(getAllElectionData(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties)));
 				else
	 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
	 			
	 			if(latestConstituencies.size()!=0)
	 				resultVo.setLatestConstituenciesInfo(getAllElectionData(latestConstituencies,nominationDAO.getAllPartyResults(latestConstituencies,selectedParties)));	
 				else
	 				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());

	 			if(remianingConstituencies.size()!=0)	 				
	 				resultVo.setRemainingConstituenciesInfo(getAllElectionData(remianingConstituencies,nominationDAO.getAllPartyResults(remianingConstituencies,selectedParties)));
	 			else
	 				resultVo.setRemainingConstituenciesInfo(new ConstituencyElectionResults());
			}else{
				selectedParties.add(partyId);
					 				
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
 	 * This method can be used to get the strength of a party in that particular constituency.
 	 * @author Y.Ravi Kiran 
	 * @param constituencyIds
	 * @param result
	 * @return ConstituencyElectionResults
 	 */
 	public ConstituencyElectionResults setElectionDataInToVo(List<Long> constituencyIds,List result){
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		ResultStatus resultStatus = new ResultStatus();
 			
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
				
					partiesStrengthsInfoVO.add(resultVo);
					
	 			}
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
	 		ids.add(0,new SelectOptionVO(0l,"Select Year"));	 		
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
 			ids.add(0,new SelectOptionVO(0l,"Select State"));	 
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
 	public ConstituencyElectionResults getAllElectionData(List<Long> constituencyIds,List result){
		
 			 		
 		List<PartiesStrengthsInfoVO> partiesStrengthsInfoVO = new ArrayList<PartiesStrengthsInfoVO>();
 		 		
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		ResultStatus resultStatus = new ResultStatus();
 		
 		List<SelectOptionVO> staticParties = staticDataService.getStaticParties();
 		Collections.sort(staticParties,new SelectOptionVOComparator());
 		constituencyElectionResults.setAllPartiesData(staticParties);
 		Map<Long,String> map1 = new HashMap<Long,String>();
 		Map<Long,List<PartiesDetailsVO>> map2 = new HashMap<Long,List<PartiesDetailsVO>>();
 		List<PartiesDetailsVO> partyDetails = new ArrayList<PartiesDetailsVO>(0);  	
 		Map<Long,String> map = new HashMap<Long,String>();
 		
 		List<Long> partyIds = new ArrayList<Long>();
 		for(int i=0;i<staticParties.size();i++){ 
			partyIds.add(staticParties.get(i).getId());
			map.put(staticParties.get(i).getId(), staticParties.get(i).getName());
		}
 		try{  			
 			if(result!=null && result.size()>0){
	 			for(int i=0; i<result.size(); i++){
	 				Object[] parms = (Object[])result.get(i);
	 				Long constituencyId = (Long)parms[0];
	 				map1.put(constituencyId, parms[3].toString());
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
 				List<PartiesDetailsVO> dataObtained = generateTempData(partyIds,map,resultIterator.getValue(),resultIterator.getKey(),conName);
 				PartiesStrengthsInfoVO infoVo = new PartiesStrengthsInfoVO();
 				infoVo.setConstituencyId(constId);
 				infoVo.setConstituencyName(conName);
 				infoVo.setPartyResults(dataObtained);
 				partiesStrengthsInfoVO.add(infoVo);
 			}
 			
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
 	
 	
 	public List<PartiesDetailsVO> generateTempData(List<Long> partyIds,Map<Long,String> map,List<PartiesDetailsVO> partyDetails,Long constId,String constName){
 		List<PartiesDetailsVO> result = new ArrayList<PartiesDetailsVO>(0); 
 		Map<String,String> auxMap = new HashMap<String,String>();
 		try{
			for(Map.Entry<Long,String> resultData: map.entrySet()){
				auxMap.put(resultData.getValue(),"false");				
			}
			for(int i=0;i<partyDetails.size();i++){
				if(auxMap.containsKey(partyDetails.get(i).getPartyName())){
					auxMap.put(partyDetails.get(i).getPartyName(),"true");
				}
			}			

			for(Map.Entry<String,String> resultData : auxMap.entrySet()){
				if(resultData.getValue().equalsIgnoreCase("false")){
					PartiesDetailsVO vo = new PartiesDetailsVO();
					vo.setConstituencyId(constId);
					vo.setCount(0L);
					vo.setPartyName(resultData.getKey());
					vo.setConstituencyName(constName); 		
					result.add(vo);
				}			
			}
				
				
			result.addAll(partyDetails); 
			if(result.size()!=8){
				
			}
			
			Collections.sort(result, new PartiesDetailsVOComparator());		
			
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
 		partiesList.add(new SelectOptionVO(0l,"All Parties"));
 		
 		return 	partiesList;
 	}

}
