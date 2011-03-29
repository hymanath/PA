package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ConstituencySearchService implements IConstituencySearchService{

	private IConstituencyDAO constituencyDAO;
	private IElectionScopeDAO electionScopeDAO;
	
	private static final Logger log = Logger.getLogger(ConstituencySearchService.class);
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}
	
	public List<SelectOptionVO> getConstituencyNamesAndIds(Long electionTypeId , Long stateId, String searchString){
		List<SelectOptionVO> constituencyNamesAndIdsList = null;
		Long constituencyId = null;
		String constituencyName = "";
		long beginTimeMillis = System.currentTimeMillis();
		if(constituencyNamesAndIdsList == null){
			//List constituencies = constituencyDAO.getAllConstituencyNamesAndIds();
			List constituencies = constituencyDAO.getConstituenciesBySearchString(electionTypeId, stateId,searchString);
			constituencyNamesAndIdsList = new ArrayList<SelectOptionVO>();
			for(int i=0; i<constituencies.size(); i++){
				Object[] values = (Object[]) constituencies.get(i);
				constituencyId = (Long)values[0];
				constituencyName = (String)values[1];
				constituencyNamesAndIdsList.add(new SelectOptionVO(constituencyId, constituencyName.toUpperCase()));
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
	
	public List<ConstituencyVO> getConstituencyDetails(String name, String constType){
		List<Constituency> constituencies = constituencyDAO.findByConstituencyNamePattern(constType, name);
		List<ConstituencyVO> constituencyVOs = new ArrayList<ConstituencyVO>();
		String districtName = "";
		for(Constituency constituency:constituencies){
			if(constituency.getDistrict() != null)
				districtName = constituency.getDistrict().getDistrictName();
			ConstituencyVO constituencyVO = new ConstituencyVO(constituency.getConstituencyId(),constituency.getName(),
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
		
		List<Constituency> constituencies = constituencyDAO.findByElectionScopeState(scopeID,stateID);
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
	
	/*
	 * Method Retrieves Constituencies List Matching The Search String (constituency name)
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IConstituencySearchService#getconstituencyinformation(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 * @Param searchText
	 * @Param sortOption
	 * 
	 * @Return Constituency Search Results List 
	 */
	public List<ConstituencyVO> getConstituencyInformation(String searchText,String constType,Long stateId,String sortoption,String order,Integer startIndex,Integer maxResult){
		List<ConstituencyVO> constituencyvos=new ArrayList<ConstituencyVO>();
		
		log.debug(" Starting Retrieving Constituency Search Results ..");
		String option= new String();
		String stateStr = new String();
		Long electionTypeId = 0l;
		Long totalSearchCount = 0l;
		
		if(constType.equalsIgnoreCase(IConstants.MLA))
		{
			electionTypeId = 2l;
			stateStr = " and model.state.stateId ="+stateId;
		}
			
		else if(constType.equalsIgnoreCase(IConstants.MP))
		{
			electionTypeId = 1l;
			stateStr = " and model.state.stateId ="+stateId;
		}
		
		if(sortoption.equalsIgnoreCase("id"))
		option="model.constituencyId";
		else if(sortoption.equalsIgnoreCase("name"))
		option="model.name";
		else if(sortoption.equalsIgnoreCase("electionType"))
		option="model.electionScope.electionType.electionType" ;
		else if(sortoption.equalsIgnoreCase("districtName"))
		{
			if(constType.equalsIgnoreCase(IConstants.MLA))
			option="model.district.districtName";
			else if(constType.equalsIgnoreCase(IConstants.MP))
			option="model.constituencyId";	
		}
		else if(sortoption.equalsIgnoreCase("stateName"))
		option="model.state.stateName";
		else if(sortoption.equalsIgnoreCase("delemitationInfoStr"))
		option="model.deformDate";
		
		List<Object[]> constituencies = new ArrayList<Object[]>();
		
		if(constType.equalsIgnoreCase(IConstants.MLA))
		{
			 constituencies = constituencyDAO.findByConstituencyNamesForAssembly(searchText,stateStr,option,order,startIndex,maxResult);
		}
		else if(constType.equalsIgnoreCase(IConstants.MP))
		{
			 constituencies = constituencyDAO.findByConstituencyNamesForPalriament(searchText,stateStr,option,order,startIndex,maxResult);
		}
		
		 totalSearchCount = (Long) constituencyDAO.totalSearchCount(searchText,electionTypeId,stateStr);
		
		Long count = new Long(startIndex);
		
		//Iterates DAO results List 
		for(int i=0;i<constituencies.size();i++)
		{
		  ConstituencyVO constituencyVO = new ConstituencyVO();
				Object[] params = (Object[])constituencies.get(i);
				
				constituencyVO.setId(++count);
				constituencyVO.setConstituencyId((Long)params[0]);
				constituencyVO.setName(params[1] != null ? (String)params[1] : "");
				constituencyVO.setElectionType(params[2] != null ? params[2].toString() : "");
				constituencyVO.setStateName(params[3] != null ? params[3].toString() : "");
				constituencyVO.setDelemitationInfoStr(params[4] != null ? params[4].toString() : "");						
								
				if(constType.equalsIgnoreCase(IConstants.MLA))
				{
					constituencyVO.setDistrictName(params[5] != null ? params[5].toString() : "");
					constituencyVO.setDistrictId(params[6] != null ? (Long)params[6]:0l);
				}
				else if(constType.equalsIgnoreCase(IConstants.MP))
					constituencyVO.setDistrictName("N/A");
				
				constituencyvos.add(constituencyVO);
				
		}
		
		if(constituencyvos.size() >0 )
	    constituencyvos.get(0).setTotalPolledVotes(totalSearchCount);
		
		return constituencyvos; 

		}		

	public Long getTotalConstituencySearchCount(String searchText,String electionType,Long stateId)
	{
		Long electionTypeId = 0l;
		String stateStr = new String();

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
		{
			electionTypeId = 2l;
			stateStr = " and model.state.stateId ="+stateId;
		}
		else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
		{
			electionTypeId = 1l;
			stateStr = " ";
		}
	
		Object obj = constituencyDAO.totalSearchCount(searchText,electionTypeId,stateStr);
		
		return (Long) obj;
	}

	
}
