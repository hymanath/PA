package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.IConstants;


public class RegionServiceDataImp implements IRegionServiceData {

	private static final Logger log = Logger.getLogger(RegionServiceDataImp.class);
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IElectionDAO electionDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}	

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}	

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}	

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}	

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public List<SelectOptionVO> getDistrictsByStateID(Long stateID) {
		List<SelectOptionVO> formattedDistricts = new ArrayList<SelectOptionVO>();
		List<District> districts = districtDAO.findByStateId(stateID);
		for(District district : districts){
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(district.getDistrictId());
			objVO.setName(district.getDistrictName());
			formattedDistricts.add(objVO);
		}
		return formattedDistricts;
	}
	
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID){
		List<Constituency> constituencies = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(districtID);
		List<SelectOptionVO> constituencyNames=new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies)
			constituencyNames.add(new SelectOptionVO(constituency.getConstituencyId(), constituency.getName()));		
		return constituencyNames;
	}
	

	public List<SelectOptionVO> getMandalsByConstituencyID(
		Long constituencyID){List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyID);
		Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
		List<Tehsil> mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
	
		List<SelectOptionVO> mandalNames=new ArrayList<SelectOptionVO>();
		
		for(Tehsil tehsil : mandals){
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(tehsil.getTehsilId());
			objVO.setName(tehsil.getTehsilName());
			mandalNames.add(objVO);
		}
		
				
		return mandalNames;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getStateDistrictByConstituencyID(Long constituencyID){
		List stateDistrictConstituency = constituencyDAO.getStateDistrictConstituency(constituencyID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		SelectOptionVO state = new SelectOptionVO();
		SelectOptionVO district = new SelectOptionVO();
		SelectOptionVO constituency = new SelectOptionVO();
		Object[] objVO = (Object[])stateDistrictConstituency.get(0);

		state.setId(new Long(objVO[0].toString()));
		state.setName(objVO[1].toString());

		district.setId(new Long(objVO[2].toString()));
		district.setName(objVO[3].toString());

		constituency.setId(constituencyID);
		constituency.setName(objVO[4].toString());
		
		result.add(state);
		result.add(district);
		result.add(constituency);
		
		return result;
		
	}
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getStateDistrictByDistrictID(Long districtID){
		List stateDistrictConstituency = districtDAO.getStateDistrictByDistrictID(districtID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		SelectOptionVO state = new SelectOptionVO();
		SelectOptionVO district = new SelectOptionVO();
		Object[] objVO = (Object[])stateDistrictConstituency.get(0);

		state.setId(new Long(objVO[0].toString()));
		state.setName(objVO[1].toString());

		district.setId(districtID);
		district.setName(objVO[2].toString());

		result.add(state);
		result.add(district);
		
		return result;
	}
	

	public List<SelectOptionVO> getStatesByCountry(Long countryID){
		List<State> states = stateDAO.findByCountryId(countryID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(State state : states){
			result.add(new SelectOptionVO(state.getStateId(),state.getStateName()));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getStatesByCountryFromBooth(Long countryID){
		List list = boothConstituencyElectionDAO.getStatesByCountryFromBooth(countryID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDistrictsByStateIDFromBooth(Long stateID) {
		List list = boothConstituencyElectionDAO.getDistrictsByStateIDFromBooth(stateID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByDistrictIDFromBooth(Long districtID) {
		List list = boothConstituencyElectionDAO.getConstituenciesByDistrictIDFromBooth(districtID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getMandalsByConstituencyIDFromBooth(Long constituencyID) {
		List list = boothConstituencyElectionDAO.getMandalsByConstituencyIDFromBooth(constituencyID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getStateByParliamentConstituencyID(Long constituencyID){
		List state = constituencyDAO.getStateForParliamentConstituency(constituencyID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		if(state!=null && state.size()==1){
			Object[] object = (Object[]) state.get(0);
			result.add(new SelectOptionVO(new Long(object[0].toString()), object[1].toString()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Long getLatestParliamentElectionYear(Long stateID){
		List list = electionDAO.findLatestParliamentaryElectionYear(stateID);
		Long year = null;
		if(list!=null && list.size()==1)
			year = new Long(list.get(0).toString());
		return year;
	}


	public List<SelectOptionVO> getTownshipsHamletsWards(String type, Long id) {
		List<SelectOptionVO> results = new ArrayList<SelectOptionVO>();
		if("Township".equalsIgnoreCase(type)){
			List<Township> townships = townshipDAO.findByTehsilID(id);
			for(Township township : townships){
				SelectOptionVO obj = new SelectOptionVO();
				obj.setId(township.getTownshipId());
				obj.setName(township.getTownshipName());
				results.add(obj);
			}
		}else if("Hamlet".equalsIgnoreCase(type)){
			List<Hamlet> hamlets = hamletDAO.findByTownshipId(id);
			for(Hamlet hamlet : hamlets){
				SelectOptionVO obj = new SelectOptionVO();
				obj.setId(hamlet.getHamletId());
				obj.setName(hamlet.getHamletName());
				results.add(obj);
			}
		}else if("Ward".equalsIgnoreCase(type)){
		// TODO task to be completed for the ward....
		}
		return results;
	}

	public List<SelectOptionVO> getAllParliamentConstituencies(
			Long electionScopeId , Long countryId) {
		List<SelectOptionVO> constsList = new ArrayList<SelectOptionVO>();
		List<Constituency> list =  constituencyDAO.getAllParliamentConstituenciesInCountry(electionScopeId, countryId);
		for(Constituency constituency: list)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(constituency.getConstituencyId());
			selectOptionVO.setName(constituency.getName());
			
			constsList.add(selectOptionVO);
 		}
		return constsList;
	}
/**
 * This method returns all the sub-regions in a constituency based on its area type.If it is Rural it returns list of tehsils, If Urban returns municipalities, corporations, gmc's
 * if UrbanRural then it returns tehsils, municipalities, corporations, etc.  
 */
	public List<SelectOptionVO> getSubRegionsInConstituency(Long constituencyId, String year, String scope) {
		Constituency constituency = constituencyDAO.get(constituencyId);
		List<SelectOptionVO> subRegionsList = new ArrayList<SelectOptionVO>();
		
		if(constituency.getAreaType() == null)
			return subRegionsList;			
		String areaType = constituency.getAreaType();
		
		
		if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
		{
			subRegionsList = getTehsilsInConstituency(constituencyId);
		} else if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
		{
			subRegionsList = getLocalElectionBodies(constituencyId, year);
			
			
		} else if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
		{
			if(scope != null && scope.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
			{
				subRegionsList = getTehsilsInConstituency(constituencyId);
			} else if(scope != null && scope.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
			{
				subRegionsList = getLocalElectionBodies(constituencyId, year);
			} else {
				subRegionsList = getTehsilsInConstituency(constituencyId);
				List<SelectOptionVO> localElectionBodiesList = getLocalElectionBodies(constituencyId, year);
				if(localElectionBodiesList.size() != 0)
				{
					subRegionsList.addAll(localElectionBodiesList);				
				}
			} 						
		}
		return subRegionsList;
	}
	
	/**
	 * This method returns all the local election bodies in a constituency.This method is invoked from getSubRegionsInConstituency() method
	 * 
	 */
	@SuppressWarnings("unchecked")
	private List<SelectOptionVO> getLocalElectionBodies(Long constituencyId, String year)
	{
		log.debug("Inside getLocalElectionBodies method in RegionServiceDataImp Class");
		List<SelectOptionVO> localElectionBodiesList = new ArrayList<SelectOptionVO>(); 
		try
		{
			List result = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			for(int i=0; i<result.size(); i++){
				Object[] obj = (Object[]) result.get(i);
				localElectionBodiesList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+obj[0].toString()),obj[1].toString().toUpperCase()+" "+ (obj[2])));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception arised while retrieving local election bodies");
		}
		
		return localElectionBodiesList;
	}
	
	/**
	 * This method returns all the local election bodies in a constituency.This method is invoked from getSubRegionsInConstituency() method
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getLocalElectionBodiesInConstituency(Long constituencyId, String year)
	{
		log.debug("Inside getLocalElectionBodies method in RegionServiceDataImp Class");
		List<SelectOptionVO> localElectionBodiesList = new ArrayList<SelectOptionVO>(); 
		try
		{
			List result = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			for(int i=0; i<result.size(); i++){
				Object[] obj = (Object[]) result.get(i);
				localElectionBodiesList.add(new SelectOptionVO((Long)obj[4],obj[1].toString().toUpperCase()+" "+ (obj[2])));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception arised while retrieving local election bodies");
		}
		
		return localElectionBodiesList;
	}
	
	@SuppressWarnings("unchecked")
	public String getLocalBodyElectionTypeInConstituency(Long constituencyId){
		
		String type = "";
		try
		{
			List result = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			if(result != null && result.size() > 0){
				Object[] obj = (Object[]) result.get(0);
				type = (String)obj[2];
			}
				
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception arised while retrieving local election bodies");
		}
	 
	 return type;	
	}
	
	private List<SelectOptionVO> getTehsilsInConstituency(Long constituencyId)
	{
		List<SelectOptionVO> list = getMandalsByConstituencyID(constituencyId);
		List<SelectOptionVO> tehsilsList = new ArrayList<SelectOptionVO>();
		for(SelectOptionVO selectOptionVO:list)
		{
			tehsilsList.add(new SelectOptionVO(new Long(IConstants.RURAL_TYPE+selectOptionVO.getId()),selectOptionVO.getName().toUpperCase()+ " " + IConstants.TEHSIL));
		}
		return tehsilsList;
	}
	
	public List<SelectOptionVO> getTehsilsInAConstituency(Long constituencyId)
	{
		List<SelectOptionVO> list = getMandalsByConstituencyID(constituencyId);
		List<SelectOptionVO> tehsilsList = new ArrayList<SelectOptionVO>();
		for(SelectOptionVO selectOptionVO:list)
		{
			tehsilsList.add(new SelectOptionVO(new Long(selectOptionVO.getId()),selectOptionVO.getName().toUpperCase()+ " " + IConstants.TEHSIL));
		}
		return tehsilsList;
	}

	/**
	 * This method retrieves all hamlets if the location is of type Rural and all wards if the location is of type Urban 
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getHamletsOrWards(Long locationId, String year) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		String areaFlag = locationId.toString().substring(0,1);
		Long id = new Long(locationId.toString().substring(1));
		
		if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE))
		{
			//fetch the wards in a municipal/corporation/greater corp region if it is partial
			//List wardsList = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(id, year);
			List wardsList = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBody(id, year);
			if(wardsList.size() == 0)
			{	
				// fetch the local election body id to retrieve wards from the constituency table
				List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(id);
				if(localElectionBodies.size() != 0)
				{
					Object obj = (Object)localElectionBodies.get(0);
					Long localElectionBodyId = (Long)obj; 
					List<Constituency> wards = constituencyDAO.findWardsAndIdsInMuncipality(localElectionBodyId);
					for(Constituency constituency:wards)
					{
						regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+constituency.getConstituencyId()),constituency.getName().toUpperCase()));
					}
				}								
			}
			//fetch the wards in a municipal/corporation/greater corp region if it is not partial
			else if(wardsList.size()>0)
			{
				for(int j=0;j<wardsList.size();j++)
				{
					Object[] obj = (Object[])wardsList.get(j);
					regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+ obj[0].toString()),obj[2].toString().concat("( ").concat(obj[1].toString().toUpperCase()).concat(" )")));
				}
			}
			
		} else if(areaFlag.equalsIgnoreCase(IConstants.RURAL_TYPE))
		{
			List resultsList = hamletDAO.findHamletsByTehsilId(id);
			for(int i = 0; i<resultsList.size();i++)
			{
				Object[] obj = (Object[])resultsList.get(i);
				regionsList.add(new SelectOptionVO(new Long(IConstants.RURAL_TYPE+obj[0].toString()),obj[1].toString().toUpperCase()));				
			}
		}
		return regionsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getHamletsInATehsil(Long tehsilId){
		
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		List resultsList = hamletDAO.findHamletsByTehsilId(tehsilId);
		for(int i = 0; i<resultsList.size();i++)
		{
			Object[] obj = (Object[])resultsList.get(i);
			regionsList.add(new SelectOptionVO((Long)obj[0],obj[1].toString().toUpperCase()));				
		}
	 return regionsList;
	}
	
	public List<SelectOptionVO> getLocalElectionBodiesOfADistrict(Long districtId){
		List<SelectOptionVO> localBodies = new ArrayList<SelectOptionVO>();
		List rawData = localElectionBodyDAO.findByDistrictId(districtId);
		for(Object[] values:(List<Object[]>)rawData)
			localBodies.add(new SelectOptionVO(Long.parseLong(values[0].toString()), values[1].toString().toUpperCase()+" "+values[2].toString()));
		return localBodies;		
	}
	
	public List<SelectOptionVO> getWardsInALocalElectionBody(Long localElectionBodyId){
		
		List<SelectOptionVO> wards = new ArrayList<SelectOptionVO>();
		List<Constituency> wardObjs = constituencyDAO.findWardsAndIdsInMuncipality(localElectionBodyId);
		for(Constituency ward:wardObjs)
			wards.add(new SelectOptionVO(ward.getConstituencyId(), ward.getName()));
		
	 return wards;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getWardsInALocalElectionBody(Long localElectionBodyId,Long constituencyId,String year){
		
		List<SelectOptionVO> wards = new ArrayList<SelectOptionVO>();
		List wardsList = null;
		
		List assemblyLocalElecBodyId = assemblyLocalElectionBodyDAO.findAssemblyLocalElectionBodyByLocalBodyAndConstituency(localElectionBodyId, constituencyId);
		if(assemblyLocalElecBodyId != null && assemblyLocalElecBodyId.size() > 0){
			Object values = (Object)assemblyLocalElecBodyId.get(0);
			Long asmblyLocalBodyId = (Long)values;
			
			wardsList = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBody(asmblyLocalBodyId, year);
			for(int j=0;j<wardsList.size();j++)
			{
				Object[] obj = (Object[])wardsList.get(j);
				wards.add(new SelectOptionVO((Long)obj[0],obj[2].toString().concat("( ").concat(obj[1].toString().toUpperCase()).concat(" )")));
			}
			
		}
		
		if(wardsList == null || wardsList.size() == 0){
			List<Constituency> wardObjs = constituencyDAO.findWardsAndIdsInMuncipality(localElectionBodyId);
			for(Constituency ward:wardObjs)
				wards.add(new SelectOptionVO(ward.getConstituencyId(), ward.getName()));
		}
		
	 return wards;
	}
	/**
	 * This method fetches constituencies from delimitation constituency table.
	 * This method wont fetch the constituencies  
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByAreaTypeInDistrict(
			Long districtId, String areaType) {
		List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
		List rawData = delimitationConstituencyDAO.getConstituenciesByAreaTypeInDist(districtId, areaType);
		for(int i = 0; i<rawData.size();i++)
		{
			Object[] obj = (Object[])rawData.get(i);
			constituencies.add(new SelectOptionVO(Long.parseLong(obj[0].toString()), obj[1].toString().toUpperCase()));
		}	
		return constituencies;	
	}

	public List<SelectOptionVO> getBoothsInTehsilOrMunicipality(Long tehsilId,
			Long year, Long constituencyId) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		String areaFlag = tehsilId.toString().substring(0,1);
		Long id = new Long(tehsilId.toString().substring(1));
		
		if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE))
		{
			List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(id);
			Object object = (Object)localElectionBodies.get(0);
			Long localElectionBodyId = (Long)object; 
			List boothsList = boothDAO.findBoothsInfoForALocalElectionBodyByConstituencyAndYear(localElectionBodyId, year, constituencyId);
			 if(boothsList.size()>0)
				{
					for(int i=0;i<boothsList.size();i++)
					{
						Object[] obj = (Object[])boothsList.get(i);
						regionsList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No "+ obj[1]));
					}
				}	
		} else if(areaFlag.equalsIgnoreCase(IConstants.RURAL_TYPE))
		{
			List boothsList =  boothDAO.findBoothsInfoForAMandalByConstituencyAndYear(id, year, constituencyId);
			if(boothsList.size()>0)
			{
				for(int j=0;j<boothsList.size();j++)
				{
					Object[] obj = (Object[])boothsList.get(j);
					regionsList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No "+obj[1]));
				}
			}
		}	
		return regionsList;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getboothsInWard(Long wardId, Long year,
			Long constituencyId) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		String id = wardId.toString().substring(1);
		List boothsList =  boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(new Long(id), year, constituencyId);
		if(boothsList.size()>0)
		{
			for(int j=0;j<boothsList.size();j++)
			{
				Object[] obj = (Object[])boothsList.get(j);
				regionsList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No" + obj[1]));
			}
		}
		return regionsList;		
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getboothDetailsInWard(Long wardId, Long year,
			Long constituencyId) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		List boothsList =  boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(wardId, year, constituencyId);
		if(boothsList.size()>0)
		{
			for(int j=0;j<boothsList.size();j++)
			{
				Object[] obj = (Object[])boothsList.get(j);
				regionsList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No" + obj[1]));
			}
		}
		return regionsList;		
	}
	
	public List<SelectOptionVO> getAllConstituenciesByElectionTypeInState(
			Long electionTypeId, Long stateId) {
		
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		List list =  delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeInState(electionTypeId, stateId);
		if(list.size()>0)
		{
			for(int j=0;j<list.size();j++)
			{
				Object[] obj = (Object[])list.get(j);
				regionsList.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString().toUpperCase()));
			}
		}
		return regionsList;		
	}
	/**
	 * This method takes the booth ids in string form and retrieves complete details for each booth.
	 */
	public List<BoothInfo> getBoothCompleteDetails(String areaType,
			String boothIds) {
		List resultsList = null;
		List<BoothInfo> boothsInfo = new ArrayList<BoothInfo>();
		if(IConstants.URBAN_TYPE.equals(areaType))
		{
			// 0-localBodyId, 1-localBodyName, 2-boothId, 3 -partNo, 4-location, 5-village_covered
			resultsList = boothDAO.getLocalElectionBodyToBoothByBooths(boothIds);
			
		} else if(IConstants.RURAL_TYPE.equals(areaType))
		{
			// 0-tehsilId, 1-tehsilName, 2-boothId, 3 -partNo, 4-location, 5-village_covered
			resultsList = boothDAO.getVillageToBoothByBooths(boothIds);
		}
		
		if(resultsList != null && resultsList.size() > 0)
		{
			for (int i = 0; i < resultsList.size(); i++) {
				Object[] obj = (Object[]) resultsList.get(i);
				BoothInfo boothInfo = new BoothInfo();
				boothInfo.setPartNo(obj[3].toString());
				boothInfo.setLocation(obj[4].toString());
				boothInfo.setVillagesCovered(obj[4].toString());
				boothInfo.setMandalName(obj[1].toString());
				boothsInfo.add(boothInfo);			
			}		
		}
		return boothsInfo;
		}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IRegionServiceData#getBoothsInLocalBodysByConstituency(java.lang.Long, java.lang.Long, java.lang.Long)
	 * Method that retrieves Booths in Local Body Election
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getBoothsInLocalBodysByConstituency(
			Long localBodyId, Long year, Long constituencyId) {
		
		List<SelectOptionVO> boothDataList = new ArrayList<SelectOptionVO>();
		if(localBodyId != null && constituencyId != null && year != null){
			
			List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(localBodyId);
			if(localElectionBodies != null && localElectionBodies.size() > 0){
				Object object = (Object)localElectionBodies.get(0);
				Long localElectionBodyId = (Long)object; 
				List boothsList = boothDAO.findBoothsInfoForALocalElectionBodyByConstituencyAndYear(localElectionBodyId, year, constituencyId);
				 if(boothsList.size()>0)
					{
						for(int i=0;i<boothsList.size();i++)
						{
							Object[] obj = (Object[])boothsList.get(i);
							boothDataList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No "+ obj[1]));
						}
					}
			}
		}
		
	 return boothDataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getBoothsInLocalBodysByConstituencyId(
			Long localBodyId, Long year, Long constituencyId) {
		
		List<SelectOptionVO> boothDataList = new ArrayList<SelectOptionVO>();
		if(localBodyId != null && constituencyId != null && year != null){
			
			List boothsList = boothDAO.findBoothsInfoForALocalElectionBodyByConstituencyAndYear(localBodyId, year, constituencyId);
		    if(boothsList.size()>0)
			{
				for(int i=0;i<boothsList.size();i++)
				{
					Object[] obj = (Object[])boothsList.get(i);
					boothDataList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No "+ obj[1]));
				}
			}
			
		}
		
	 return boothDataList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IRegionServiceData#getBoothsInTehsilByConstituency(java.lang.Long, java.lang.Long, java.lang.Long)
	 * Method that retrieves Booths in Tehsil
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getBoothsInTehsilByConstituency(Long tehsilId,
			Long year, Long constituencyId) {
		
		List<SelectOptionVO> boothDataList = new ArrayList<SelectOptionVO>();
		if(tehsilId != null && year != null && constituencyId != null){
			
			List boothsList =  boothDAO.findBoothsInfoForAMandalByConstituencyAndYear(tehsilId, year, constituencyId);
			if(boothsList.size()>0)
			{
				for(int j=0;j<boothsList.size();j++)
				{
					Object[] obj = (Object[])boothsList.get(j);
					boothDataList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No "+obj[1]));
				}
			}
		}
		
	 return boothDataList;
	}
	
}
