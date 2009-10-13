package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;

public class ConstituencySearchService implements IConstituencySearchService{

	private IConstituencyDAO constituencyDAO;
	private List<SelectOptionVO> constituencyNamesAndIdsList;
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public List<SelectOptionVO> getConstituencyNamesAndIds(){
		long beginTimeMillis = System.currentTimeMillis();
		if(constituencyNamesAndIdsList == null){
			List<Constituency> constituencies = constituencyDAO.getAll();
			this.constituencyNamesAndIdsList = new ArrayList<SelectOptionVO>();
			for(Constituency constituency:constituencies){
				SelectOptionVO constituencyNameAndId = new SelectOptionVO();
				constituencyNameAndId.setId(constituency.getConstituencyId());
				constituencyNameAndId.setName(constituency.getName());			
				constituencyNamesAndIdsList.add(constituencyNameAndId);
			}
			System.out.println("Entered into if for constituencySearchResult");
		}else{
			System.out.println("Entered into else for constituencySearchResult");
		}
		long endTimeMillis = System.currentTimeMillis();
		System.out.println("beginTimeMillis:"+beginTimeMillis);
		System.out.println("endTimeMillis:"+endTimeMillis);
		System.out.println("Total time taken:" + (beginTimeMillis-endTimeMillis)/1000);
		//System.out.println("constituencyNamesList:"+constituencyNamesAndIdsList);
		return constituencyNamesAndIdsList;
	}
	
	public List<ConstituencyVO> getConstituencyDetails(String name){
		List<Constituency> constituencies = constituencyDAO.findByConstituencyNamePattern(name);
		List<ConstituencyVO> constituencyVOs = new ArrayList<ConstituencyVO>();
		
		for(Constituency constituency:constituencies){
			ConstituencyVO constituencyVO =new ConstituencyVO(constituency.getConstituencyId(), constituency.getName(),
								constituency.getState().getStateName(), constituency.getDistrict().getDistrictName(),
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
	
}
