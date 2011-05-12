package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
import com.itgrids.partyanalyst.utils.IConstants;
/**
 * 
 * @author Narender Akula
 *
 */
public class PartyResultService extends BasePartyResultsServiceImpl{
	
	public IStaticDataService staticDataService;
	public IPartyDAO partyDAO;
	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	private final static Logger log = Logger.getLogger(PartyResultService.class);
	
	public List<Election> getElections(ElectionScope scope){
		List<Election> list = getElectionDAO().findByElectionScopeId(scope.getElectionScopeId(), IConstants.ELECTION_SUBTYPE_MAIN);
		return list;
	}

	/**
	 * 
	 * @param partyId
	 * @param typeId
	 * @param countryID
	 * @param stateID
	 * @param districtID
	 * @param constituencyID
	 * @param level
	 * @return PartyResultInfoVO object which contains a party & opposition party information related 
	 * to all elections for the specific election type.
	 */
	@SuppressWarnings("unchecked")
	public List<PartyResultInfoVO> getPartyResultsInfo(String partyShortName, Long selectdPartyId, Long typeId, Long countryID, Long stateID, 
			Long districtID, Long constituencyID, ElectionScopeLevelEnum level,Boolean hasAlliance){
		List<PartyResultInfoVO> result = new ArrayList<PartyResultInfoVO>();
		List<Election> elections = null;
		//Long selectdPartyId = null;
		if(stateID==null){

			elections = getElectionDAO().findByElectionTypeCountry(typeId, countryID);
		}else{

			ElectionScope scope = getElectionScope(typeId, countryID, stateID);
			elections = getElections(scope);
		}
		
		// competetorSize value should be taken from the configuration 
		int competetorSize = 3;
		
		/*List selectedParty = partyDAO.findPartyIdByShortName(partyShortName);
		if(selectedParty != null && selectedParty.size() > 0){
			Object selectdParty = (Object)selectedParty.get(0);
			selectdPartyId = (Long)selectdParty;
		}*/
		
		for(Election election : elections){
			
			List<SelectOptionVO> alliancePartys = null;
						
			if(hasAlliance && selectdPartyId != null)
			alliancePartys = staticDataService.getAlliancePartiesAsVO(election.getElectionYear(), election.getElectionScope().getElectionType().getElectionTypeId(), selectdPartyId,stateID);
			
			if(alliancePartys != null && alliancePartys.size() > 0){
				for(int i=0;i<alliancePartys.size();i++){
					if(alliancePartys.get(i).getName().equals(partyShortName))
					alliancePartys.remove(i);
				}
			}
			
			List<PartyInfoVO> partyInfoList = getPartyAndCompetetorsInfo(election, partyShortName,stateID, districtID, constituencyID, competetorSize, level,hasAlliance,alliancePartys);

			if(partyInfoList==null || partyInfoList.size()==0) 
				continue;
			
			PartyResultInfoVO partyResultInfoVO = new PartyResultInfoVO();
			PartyInfoVO requiredParty = partyInfoList.get(0);
			if(requiredParty.getPartyShortName().equals(partyShortName)){
				partyResultInfoVO.setPartyInfoVO(requiredParty);
				partyInfoList.remove(0);
			}
			else if(!requiredParty.getPartyShortName().equals(partyShortName)){
				partyResultInfoVO.setPartyInfoVO(null);
			}
			if(hasAlliance && alliancePartys != null && alliancePartys.size() > 0){
				List<PartyInfoVO> alliancPartiesInfoVO = new ArrayList<PartyInfoVO>();
				for(int i=0;i<alliancePartys.size();i++){
					for(int j=0;j<partyInfoList.size();j++){
						if(partyInfoList.get(j).getPartyShortName().equals(alliancePartys.get(i).getName())){
							alliancPartiesInfoVO.add(partyInfoList.get(j));
							partyInfoList.remove(j);
							break;
						}
					}
				}
				partyResultInfoVO.setAlliancePartysInfo(alliancPartiesInfoVO);
			}
			else if(!hasAlliance || alliancePartys == null || alliancePartys.size() == 0){
				partyResultInfoVO.setAlliancePartysInfo(null);
			}
			partyResultInfoVO.setOppositionPartyInfo(partyInfoList);
			result.add(partyResultInfoVO);
		}
		return result;
		
	}
}
