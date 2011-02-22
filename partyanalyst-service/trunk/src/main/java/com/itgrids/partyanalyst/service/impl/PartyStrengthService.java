package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.PartiesStrenghInfoVO;
import com.itgrids.partyanalyst.dto.PartiesStrengthsInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	
	//Getters and Setters for Instance Variables.
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
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
					if(count >= selectedNoOfYears && count!=1l){
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
	 * @return List<Long>
 	 */
 	public ElectionInfoVO getPartiesData(String electionType,Long stateId,Long electionYearsCount,String type,Long partyId){
 		List<Long> requiredConstituencies = new ArrayList<Long>(0);
		List<Long> latestConstituencies = new ArrayList<Long>(0);
		List<Long> remianingConstituencies = new ArrayList<Long>(0);
		List<Long> selectedParties = new ArrayList<Long>(0);
		ElectionInfoVO resultVo = new ElectionInfoVO();
		ResultStatus resultStatus = new ResultStatus();			
		try{
			PartiesStrenghInfoVO partiesStrenghInfoVO = segregateAllConstituencies(electionYearsCount,electionType,IConstants.ELECTION_SUBTYPE_MAIN,stateId);
	 
			latestConstituencies = partiesStrenghInfoVO.getLatestConstituencies();
			requiredConstituencies = partiesStrenghInfoVO.getRequiredConstituencies();
			remianingConstituencies = partiesStrenghInfoVO.getRemianingConstituencies();
			
			if(partyId.intValue()==0){
				selectedParties =staticDataService.getStaticPartiesAsList();
				if(latestConstituencies.size()!=0)
	 				resultVo.setLatestConstituenciesInfo(getAllElectionData(latestConstituencies,nominationDAO.getAllPartyResults(latestConstituencies,selectedParties)));	
 				else
	 				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());
	 				
	 			if(requiredConstituencies.size()!=0) 			
	 				resultVo.setRequiredConstituenciesInfo(getAllElectionData(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties)));
 				else
	 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
	 			
	 			if(remianingConstituencies.size()!=0)	 				
	 				resultVo.setRemainingConstituenciesInfo(getAllElectionData(remianingConstituencies,nominationDAO.getAllPartyResults(remianingConstituencies,selectedParties)));
	 			else
	 				resultVo.setRemainingConstituenciesInfo(new ConstituencyElectionResults());
			}else{
				selectedParties.add(partyId);
				if(latestConstituencies.size()!=0)
	 				resultVo.setLatestConstituenciesInfo(setElectionDataInToVo(latestConstituencies,nominationDAO.getAllPartyResults(latestConstituencies,selectedParties)));	
 				else
	 				resultVo.setLatestConstituenciesInfo(new ConstituencyElectionResults());
	 				
	 			if(requiredConstituencies.size()!=0) 			
	 				resultVo.setRequiredConstituenciesInfo(setElectionDataInToVo(requiredConstituencies,nominationDAO.getAllPartyResults(requiredConstituencies,selectedParties)));
 				else
	 				resultVo.setRequiredConstituenciesInfo(new ConstituencyElectionResults());
	 			
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
 	
 	
	/**
 	 * This method can be used to get the strength of all the party's that are participated in particular constituency.
 	 * @author Y.Ravi Kiran 
	 * @param constituencyIds
	 * @param result
	 * @return ConstituencyElectionResults
 	 */
 	public ConstituencyElectionResults getAllElectionData(List<Long> constituencyIds,List result){
		
 		Long tempX = 0l,tempY = 0l,tempZ = 0l;
 		
 		List<Long> uniqueItems = new ArrayList<Long>(0); 	
 		List<PartiesDetailsVO> partyDetails = new ArrayList<PartiesDetailsVO>(0);  		
 		Set<Long> linkedSet = new LinkedHashSet<Long>(0); 		
 		List<PartiesStrengthsInfoVO> partiesStrengthsInfoVO = new ArrayList<PartiesStrengthsInfoVO>();
 		ConstituencyElectionResults constituencyElectionResults = new ConstituencyElectionResults();
 		ResultStatus resultStatus = new ResultStatus();
 		String previousConstituencyName = new String();
 		try{ 			
 			if(result!=null && result.size()>0){
	 			for(int i=0; i<result.size(); i++){
	 				Object[] parms = (Object[])result.get(i);
	 				Long constituencyId = (Long)parms[0];
	 				linkedSet.add(constituencyId);
	 				if(tempX==0l){
	 					tempZ = tempY = tempX = constituencyId;
	 					previousConstituencyName = parms[3].toString();
	 					partyDetails.add(setDataIntoPartiesDetailsVO(parms));
	 				}else{	 				
	 					if(tempX.intValue()==constituencyId.intValue()){
	 						tempZ = constituencyId;
	 						previousConstituencyName = parms[3].toString();
	 						partyDetails.add(setDataIntoPartiesDetailsVO(parms));
	 					}else{
	 						tempX = constituencyId; 
	 						
	 						PartiesStrengthsInfoVO resultVo = new PartiesStrengthsInfoVO();
	 						resultVo.setConstituencyId(tempZ);
	 						resultVo.setConstituencyName(previousConstituencyName);
	 						resultVo.setPartyResults(partyDetails);	 
	 						partiesStrengthsInfoVO.add(resultVo);
	 						partyDetails = new ArrayList<PartiesDetailsVO>(0);
	 						tempZ = constituencyId;
	 						partyDetails.add(setDataIntoPartiesDetailsVO(parms));
	 					}
	 				} 				
	 			}
	 			uniqueItems.addAll(linkedSet);
	 			partyDetails.clear();
	 			Long lastId = uniqueItems.get(uniqueItems.size()-1);
	 			String conName = new String();
	 			for(int i=0; i<result.size(); i++){
	 				Object[] parms = (Object[])result.get(i);
	 				if(new Long(parms[0].toString()).intValue()==lastId){
	 					conName = parms[3].toString();
	 					partyDetails.add(setDataIntoPartiesDetailsVO(parms));
	 				}
	 			}
	 			PartiesStrengthsInfoVO resultVo = new PartiesStrengthsInfoVO();
				resultVo.setConstituencyId(tempZ);
				resultVo.setConstituencyName(previousConstituencyName);
				resultVo.setPartyResults(partyDetails);	 
				partiesStrengthsInfoVO.add(resultVo);
	 			constituencyElectionResults.setPartiesStrengthsInfoVO(partiesStrengthsInfoVO);
	 			resultStatus.setResultCode(ResultCodeMapper.SUCCESS); 	
 			}	
 		}catch(Exception e){
 			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
 			resultStatus.setExceptionEncountered(e); 			
 			e.printStackTrace();
 		}finally{
 			tempX = null;
 			tempY = null;
 			tempZ = null; 
 			uniqueItems = null;
 			partyDetails = null;
 			linkedSet = null;
 		}
 		constituencyElectionResults.setResultStatus(resultStatus);
 		return constituencyElectionResults;
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
	 	}catch(Exception e){
			e.printStackTrace();
		}
	 	return partyDetails;
 	}

}
