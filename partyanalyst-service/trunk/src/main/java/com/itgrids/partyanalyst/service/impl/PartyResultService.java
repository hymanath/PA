package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultInfoVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
/**
 * 
 * @author Narender Akula
 *
 */
public class PartyResultService extends BasePartyResultsServiceImpl{
	
	public List<Election> getElections(ElectionScope scope){
		List<Election> list = getElectionDAO().findByElectionScope(scope.getElectionScopeId());
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
	public List<PartyResultInfoVO> getPartyResultsInfo(String partyShortName, Long typeId, Long countryID, Long stateID, 
			Long districtID, Long constituencyID, ElectionScopeLevelEnum level){
		List<PartyResultInfoVO> result = new ArrayList<PartyResultInfoVO>();
		ElectionScope scope = getElectionScope(typeId, countryID, stateID);
		List<Election> elections = getElections(scope);
		
		// competetorSize value should be taken from the configuration 
		int competetorSize = 3;
		
		for(Election election : elections){
			List<PartyInfoVO> partyInfoList = getPartyAndCompetetorsInfo(election, partyShortName,stateID, districtID, constituencyID, competetorSize, level);

			if(partyInfoList==null || partyInfoList.size()==0) 
				continue;
			
			PartyInfoVO requiredParty = partyInfoList.get(0);
			partyInfoList.remove(0);
			PartyResultInfoVO partyResultInfoVO = new PartyResultInfoVO();
			partyResultInfoVO.setPartyInfoVO(requiredParty);
			partyResultInfoVO.setOppositionPartyInfo(partyInfoList);
			result.add(partyResultInfoVO);
		}
		return result;
		
	}
}
