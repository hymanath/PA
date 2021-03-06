package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.service.IConstituencySearchService;

public class ConstituencySearchService implements IConstituencySearchService{

	private IConstituencyDAO constituencyDAO;
	private IElectionScopeDAO electionScopeDAO;
	

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}
	
	public List<SelectOptionVO> getConstituencyNamesAndIds(){
		List<SelectOptionVO> constituencyNamesAndIdsList = null;
		long beginTimeMillis = System.currentTimeMillis();
		if(constituencyNamesAndIdsList == null){
			List<Constituency> constituencies = constituencyDAO.getAll();
			constituencyNamesAndIdsList = new ArrayList<SelectOptionVO>();
			for(Constituency constituency:constituencies){
				SelectOptionVO constituencyNameAndId = new SelectOptionVO();
				constituencyNameAndId.setId(constituency.getConstituencyId());
				constituencyNameAndId.setName(constituency.getName());			
				constituencyNamesAndIdsList.add(constituencyNameAndId);
			}
		}else{
			System.out.println("Entered into else for constituencySearchResult");
		}
		long endTimeMillis = System.currentTimeMillis();
		System.out.println("beginTimeMillis:"+beginTimeMillis);
		System.out.println("endTimeMillis:"+endTimeMillis);
		System.out.println("Total time taken:" + (beginTimeMillis-endTimeMillis)/1000);
		return constituencyNamesAndIdsList;
	}
	
	public List<ConstituencyVO> getConstituencyDetails(String name){
		List<Constituency> constituencies = constituencyDAO.findByConstituencyNamePattern(name);
		List<ConstituencyVO> constituencyVOs = new ArrayList<ConstituencyVO>();
		String districtName = "";
		for(Constituency constituency:constituencies){
			if(constituency.getDistrict() != null)
				districtName = constituency.getDistrict().getDistrictName();
			ConstituencyVO constituencyVO =new ConstituencyVO(constituency.getConstituencyId(), constituency.getName(),
								constituency.getState().getStateName(), districtName ,
								constituency.getElectionScope().getElectionType().getElectionType(),constituency.getDeformDate());
			constituencyVOs.add(constituencyVO);
		}
		
		return constituencyVOs;
	}
	
	public List<SelectOptionVO> getConstituencyNames(Long stateId)
	{
		List<Constituency> constituencies = constituencyDAO.findByStateId(stateId);
		List<SelectOptionVO> selectOptionList=new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(constituency.getConstituencyId());
			selectOptionVO.setName(constituency.getName());
			selectOptionList.add(selectOptionVO);			
		}
		
		return selectOptionList;
	}
	

	public List<SelectOptionVO> getConstituencyNamesByElectionScope(Long countryID, Long stateID, Long typeID){
		List<ElectionScope> scopes = electionScopeDAO.findByTypeIdCountryIdStateId(typeID, countryID, stateID);
		Long scopeID = 0L;
		if(scopes!=null && scopes.size()>0){
			ElectionScope scope = scopes.get(0);
			scopeID = scope.getElectionScopeId();
		}
		
		List<Constituency> constituencies = constituencyDAO.findByElectionScope(scopeID);
		List<SelectOptionVO> selectOptionList=new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(constituency.getConstituencyId());
			selectOptionVO.setName(constituency.getName());
			selectOptionList.add(selectOptionVO);			
		}
		
		return selectOptionList;
	}
	
}
