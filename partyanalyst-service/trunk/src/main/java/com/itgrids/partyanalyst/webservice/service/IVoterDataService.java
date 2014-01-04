package com.itgrids.partyanalyst.webservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.webservice.utils.VoterInputVo;
import com.itgrids.partyanalyst.webservice.utils.VoterVO;

public interface IVoterDataService {
	
	
	public  <K,V>Map<K,V> getVotersByBoothIdsandConstituencyIds(VoterInputVo voterInputs);
	 public static class BuildVoterVo{
		 
		 public static  <K,V>Map<K,V>  buildVoterVo(List<?> voters)
		 {   
			 Map<String, Object> map =new HashMap<String, Object>();
			 VoterVO voterVo =new VoterVO();
			// voterVo.setTotalVoters(new Long(voters.size()));
			 map.put("totalVoters",new Long(voters.size()));
			 List<VoterVO> votersList = new ArrayList<VoterVO>();
			 for(Object vArray : voters){
				 Object[] voterArray = (Object[]) vArray;
			 Voter voter =(Voter) voterArray[0];
			 VoterVO voterVO =new VoterVO();
			 voterVO.setName(voter.getName());
			 voterVO.setAge(voter.getAge());
			 voterVO.setGender(voter.getGender());
			 voterVO.setHouseNo(voter.getHouseNo());
			 voterVO.setRelativeName(voter.getRelativeName());
			 voterVO.setRelationshipType(voter.getRelationshipType());
			 voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
			 voterVO.setVoterId(String.valueOf(voter.getVoterId()));
			 voterVO.setsNo((Long)voterArray[2]);
			 voterVO.setBoothId((Long)voterArray[1]);
			 voterVO.setPublicationName((String)voterArray[4]);
			 voterVO.setPublicationDateId((Long)voterArray[5]);
			 votersList.add(voterVO);
		 }
			// voterVo.setVotersList(votersList);
			 map.put("votersList", votersList);
			 return  (Map<K, V>) map;
		 }
	 }
		

}
