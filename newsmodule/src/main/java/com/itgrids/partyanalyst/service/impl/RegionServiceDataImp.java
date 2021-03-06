package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.RegionalMappingInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.IConstants;


public class RegionServiceDataImp implements IRegionServiceData {

	private static final Logger log = Logger.getLogger(RegionServiceDataImp.class);
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	//private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IHamletDAO hamletDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private ITownshipDAO townshipDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	/*private IModuleRegionScopesDAO moduleRegionScopesDAO;
	private IModuleDetailsDAO moduleDetailsDAO;
	
	private IRegionScopesProblemTypeDAO regionScopesProblemTypeDAO;
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IPanchayatDAO panchayatDAO;
	*/
	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	/*public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
*/
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
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

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDistrictsByStateID(Long stateID) {
		try
		{
			List<SelectOptionVO> formattedDistricts = new ArrayList<SelectOptionVO>();
			List<Object[]> param = districtDAO.getDistrictIdAndNameByState(stateID);
			for(Object[] obj : param){
				SelectOptionVO objVO = new SelectOptionVO();
				objVO.setId((Long)obj[0]);
				objVO.setName(WordUtils.capitalize(obj[1].toString().toLowerCase()));
				formattedDistricts.add(objVO);
			}
		return formattedDistricts;
		}
		catch(Exception e){
			log.error("Exception Occured During fetching Districts from state with stateId = "+ stateID + " Exception is -- "+e);
			return null;
		}
	}

	public List<SelectOptionVO> getMandalsByConstituencyID(Long constituencyID){
		List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyID);
		Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
		List<Tehsil> mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
		Constituency constituency = constituencyDAO.get(constituencyID);
		
		List<SelectOptionVO> mandalNames=new ArrayList<SelectOptionVO>();
		
		if(constituency.getAreaType() == null)
		return mandalNames;			
		String areaType = constituency.getAreaType();
				
		if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
		{
			mandalNames = getLocalElectionBodies(constituencyID, IConstants.PRESENT_ELECTION_YEAR);
		}
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
		constituency.setName(WordUtils.capitalize(objVO[4].toString().toLowerCase()));
		
		result.add(state);
		result.add(district);
		result.add(constituency);
		
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByStateID(Long stateID){
		List<Constituency> constituencies = constituencyDAO.findByStateId(stateID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(Constituency constituency : constituencies){
			result.add(new SelectOptionVO(constituency.getConstituencyId(),WordUtils.capitalize(constituency.getName().toLowerCase())));
		}
		Collections.sort(result);
		return result;
	}
	
	//modified
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByStateIDForUser(Long stateID){
		List<Constituency> constituencies = constituencyDAO.findByStateIdForUser(stateID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(Constituency constituency : constituencies){
			result.add(new SelectOptionVO(constituency.getConstituencyId(),WordUtils.capitalize(constituency.getName().toLowerCase())));
		}
		Collections.sort(result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByDistrictDForUser(Long districtID){
		List<Constituency> constituencies = constituencyDAO.findByDistrictIdForUser(districtID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(Constituency constituency : constituencies){
			result.add(new SelectOptionVO(constituency.getConstituencyId(),WordUtils.capitalize(constituency.getName().toLowerCase())));
		}
		Collections.sort(result);
		return result;
	}
	
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID){
		List<Constituency> constituencies = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(districtID);
		List<SelectOptionVO> constituencyNames=new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies)
			constituencyNames.add(new SelectOptionVO(constituency.getConstituencyId(),WordUtils.capitalize(constituency.getName().toLowerCase())));		
		return constituencyNames;
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
			result.add(new SelectOptionVO(state.getStateId(),WordUtils.capitalize(state.getStateName().toLowerCase())));
		}
		Collections.sort(result);
		return result;
		
	}
	
	public List<SelectOptionVO> getStatesByCountryForSearch(Long countryID){
		List<State> states = stateDAO.findByCountryIdForSearch(countryID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(State state : states){
			result.add(new SelectOptionVO(state.getStateId(),state.getStateName()));
		}
		return result;
	}
	/*
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
			result.add(new SelectOptionVO(new Long(obj[0].toString()),WordUtils.capitalize(obj[1].toString().toLowerCase())));
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
		List list = null;//electionDAO.findLatestParliamentaryElectionYear(stateID);
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
			selectOptionVO.setName(WordUtils.capitalize(constituency.getName().toLowerCase()));
			
			constsList.add(selectOptionVO);
 		}
		return constsList;
	}
*/
	public List<SelectOptionVO> getSubRegionsInConstituency(Long constituencyId, String year, String scope) {
		Constituency constituency = constituencyDAO.get(constituencyId);
		List<SelectOptionVO> subRegionsList = new ArrayList<SelectOptionVO>();

		if(constituency.getAreaType() == null)
		return subRegionsList;
		String areaType = constituency.getAreaType();


		if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
		{
		if(scope.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
		subRegionsList = getTehsilsInConstituency(constituencyId);
		} else if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
		{
			if(scope.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
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

	

	@SuppressWarnings("unchecked")
	public  List<SelectOptionVO> getLocalElectionBodies(Long constituencyId, String year)
	{
		log.debug("Inside getLocalElectionBodies method in RegionServiceDataImp Class");
		List<SelectOptionVO> localElectionBodiesList = new ArrayList<SelectOptionVO>(); 
		try
		{
			List result = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			for(int i=0; i<result.size(); i++){
				Object[] obj = (Object[]) result.get(i);
				localElectionBodiesList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+obj[0].toString()),obj[1].toString()+" "+ (obj[2])));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception arised while retrieving local election bodies");
		}
		
		return localElectionBodiesList;
	}
	
	
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
				localElectionBodiesList.add(new SelectOptionVO((Long)obj[4],WordUtils.capitalize(obj[1].toString().toLowerCase())+" "+ (obj[2])));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception arised while retrieving local election bodies");
		}
		
		return localElectionBodiesList;
	}
	/*
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
	*/
	public List<SelectOptionVO> getTehsilsInConstituency(Long constituencyId)
	{
		List<SelectOptionVO> list = getMandalsByConstituencyID(constituencyId);
		List<SelectOptionVO> tehsilsList = new ArrayList<SelectOptionVO>();
		for(SelectOptionVO selectOptionVO:list)
		{
			tehsilsList.add(new SelectOptionVO(new Long(IConstants.RURAL_TYPE+selectOptionVO.getId()),WordUtils.capitalize(selectOptionVO.getName().toLowerCase()+ " " + IConstants.MANDAL)));
		}
		return tehsilsList;
	}
	/*
	public List<SelectOptionVO> getTehsilsInAConstituency(Long constituencyId)
	{
		List<SelectOptionVO> list = getMandalsByConstituencyID(constituencyId);
		List<SelectOptionVO> tehsilsList = new ArrayList<SelectOptionVO>();
		for(SelectOptionVO selectOptionVO:list)
		{
			tehsilsList.add(new SelectOptionVO(new Long(selectOptionVO.getId()),selectOptionVO.getName().toUpperCase()+ " " + IConstants.MANDAL));
		}
		return tehsilsList;
	}
	

	@SuppressWarnings("unchecked")
	public Boolean checkForHamletsAvailability(Long locationId){
		
		Long id = new Long(locationId.toString().substring(1));
		List resultsList = hamletDAO.findHamletsByTehsilId(id);
		
		if(resultsList != null && resultsList.size() > 0)
			return true;
		
	 return false;
	}
	

	public Boolean checkForAreaRuralType(Long locationId){
		
		String areaFlag = locationId.toString().substring(0,1);
		if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE))
			return false;
		
	 return true;
	}

	
	 
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
						regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+constituency.getConstituencyId()),WordUtils.capitalize(constituency.getName().toLowerCase())));
					}
				}								
			}
			//fetch the wards in a municipal/corporation/greater corp region if it is not partial
			else if(wardsList.size()>0)
			{
				for(int j=0;j<wardsList.size();j++)
				{
					Object[] obj = (Object[])wardsList.get(j);
					Constituency constituency = (Constituency)obj[1];
					String wardName = constituency.getLocalElectionBodyWard() != null?constituency.getLocalElectionBodyWard().getWardName().
							concat("( ").concat(constituency.getName().toUpperCase()).concat(" )"):constituency.getName().toUpperCase();
					
					regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+ obj[0].toString()),WordUtils.capitalize(wardName.toLowerCase())));
				}
			}
			
		} else if(areaFlag.equalsIgnoreCase(IConstants.RURAL_TYPE))
		{
			List resultsList = hamletDAO.findHamletsByTehsilId(id);
			
			//If hamlets data is not available then go with townships
			if(resultsList == null || resultsList.size() == 0){
				resultsList = townshipDAO.findTownshipsByTehsilId(id);
			}
			
			for(int i = 0; i<resultsList.size();i++)
			{
				Object[] obj = (Object[])resultsList.get(i);
				regionsList.add(new SelectOptionVO(new Long(IConstants.RURAL_TYPE+obj[0].toString()),WordUtils.capitalize(obj[1].toString().toLowerCase())));				
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
			regionsList.add(new SelectOptionVO((Long)obj[0],obj[1].toString()));				
		}
	 return regionsList;
	}
	*/
	public List<SelectOptionVO> getLocalElectionBodiesOfADistrict(Long districtId){
		List<SelectOptionVO> localBodies = new ArrayList<SelectOptionVO>();
		List rawData = localElectionBodyDAO.findByDistrictId(districtId);
		for(Object[] values:(List<Object[]>)rawData)
			localBodies.add(new SelectOptionVO(Long.parseLong(values[0].toString()),WordUtils.capitalize(values[1].toString().toLowerCase()) +" "+values[2].toString()));
		return localBodies;		
	}

	public List<SelectOptionVO> getWardsInALocalElectionBody(Long localElectionBodyId){
		
		List<SelectOptionVO> wards = new ArrayList<SelectOptionVO>();
		List<Constituency> wardObjs = constituencyDAO.findWardsAndIdsInMuncipality(localElectionBodyId);
		for(Constituency ward:wardObjs)
			wards.add(new SelectOptionVO(ward.getConstituencyId(), ward.getName()));
		
	 return wards;
	}
	/*
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
				Constituency constituency = (Constituency)obj[1];
				String wardName = constituency.getLocalElectionBodyWard() != null?constituency.getLocalElectionBodyWard().getWardName().
						concat("( ").concat(constituency.getName().toUpperCase()).concat(" )"):constituency.getName().toUpperCase();
				
				wards.add(new SelectOptionVO((Long)obj[0],wardName));
			}
			
		}
		
		if(wardsList == null || wardsList.size() == 0){
			List<Constituency> wardObjs = constituencyDAO.findWardsAndIdsInMuncipality(localElectionBodyId);
			for(Constituency ward:wardObjs)
				wards.add(new SelectOptionVO(ward.getConstituencyId(), ward.getName()));
		}
		
	 return wards;
	}
*/
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByAreaTypeInDistrict(
			Long districtId, String areaType) {
		List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
		List rawData = delimitationConstituencyDAO.getConstituenciesByAreaTypeInDist(districtId, areaType);
		for(int i = 0; i<rawData.size();i++)
		{
			Object[] obj = (Object[])rawData.get(i);
			constituencies.add(new SelectOptionVO(Long.parseLong(obj[0].toString()),WordUtils.capitalize(obj[1].toString().toLowerCase())));
			Collections.sort(constituencies);
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
		List boothsList = null;// boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(new Long(id), year, constituencyId);
		if(boothsList.size()>0)
		{
			for(int j=0;j<boothsList.size();j++)
			{
				Object[] obj = (Object[])boothsList.get(j);
				regionsList.add(new SelectOptionVO(new Long(obj[0].toString()),"Booth No " + obj[1]));
			}
		}
		return regionsList;		
	}
	

	public List<SelectOptionVO> getboothsInWardByPublicationId(Long wardId, Long constituencyId , Long publicationId) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		String id = wardId.toString().substring(1);
		List<Object[]> boothsList = null;// boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndPublicationId(new Long(id), constituencyId ,  publicationId);
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
	/*
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getboothDetailsInWard(Long wardId, Long year,
			Long constituencyId) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		List boothsList =  null;//boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(wardId, year, constituencyId);
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
	*/
	@SuppressWarnings("unused")
	public List<BoothInfo> getBoothCompleteDetails(String areaType,
			String boothIds) {
		List resultsList = null;
		List<BoothInfo> boothsInfo = new ArrayList<BoothInfo>();
		if(IConstants.URBAN_TYPE.equals(areaType))
		{
			// 0-localBodyId, 1-localBodyName, 2-boothId, 3 -partNo, 4-location, 5-village_covered
			resultsList = null;//boothDAO.getLocalElectionBodyToBoothByBooths(boothIds);
			
		} else if(IConstants.RURAL_TYPE.equals(areaType))
		{
			// 0-tehsilId, 1-tehsilName, 2-boothId, 3 -partNo, 4-location, 5-village_covered
			resultsList = null;//boothDAO.getVillageToBoothByBooths(boothIds);
		}
		
		if(resultsList != null && resultsList.size() > 0)
		{
			for (int i = 0; i < resultsList.size(); i++) {
				Object[] obj = (Object[]) resultsList.get(i);
				BoothInfo boothInfo = new BoothInfo();
				boothInfo.setBoothId((Long)obj[2]);
				boothInfo.setPartNo(obj[3].toString());
				boothInfo.setLocation(obj[4].toString());
				boothInfo.setVillagesCovered(obj[5].toString());
				boothInfo.setMandalName(obj[1].toString().toUpperCase());
				boothsInfo.add(boothInfo);			
			}		
		}
		return boothsInfo;
		}

	
	/*
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
	

	*/
	public Set<RegionalMappingInfoVO> getLocalBodiesInDistAndConst(Long districtId,
			Long constituencyId, String year) {
		String areaType = null;
		List<SelectOptionVO> localBodiesMappedInConst = null;
		// fetch all existing local bodies in a district
		List<SelectOptionVO> allLocalElectionBodies = getLocalElectionBodiesOfADistrict(districtId);
		// fetch mapped local bodies in a constituency
		Constituency constituency = constituencyDAO.get(constituencyId);
		areaType = constituency.getAreaType();
		RegionalMappingInfoVO regionalMappingInfoVO = null;
		Set<RegionalMappingInfoVO> finalList = new LinkedHashSet<RegionalMappingInfoVO>();
		 Set<RegionalMappingInfoVO> list = null;
		 if(areaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN) || areaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
			 localBodiesMappedInConst = getLocalElectionBodiesInConstituency(constituencyId, year);
		 if(localBodiesMappedInConst != null && localBodiesMappedInConst.size()>0)
			for(SelectOptionVO selectOption:localBodiesMappedInConst)
			 {
				regionalMappingInfoVO = new RegionalMappingInfoVO();
				regionalMappingInfoVO.setRegionId(selectOption.getId());
				regionalMappingInfoVO.setRegionName(selectOption.getName());
				regionalMappingInfoVO.setFlag(true);
				finalList.add(regionalMappingInfoVO);
			 }

		 if(allLocalElectionBodies != null && allLocalElectionBodies.size()>0)
		 {	
			list = new HashSet<RegionalMappingInfoVO>();
			for(SelectOptionVO selectOptionVO:allLocalElectionBodies)
			 {
				regionalMappingInfoVO = new RegionalMappingInfoVO();
				regionalMappingInfoVO.setRegionId(selectOptionVO.getId());
				regionalMappingInfoVO.setRegionName(selectOptionVO.getName());
				regionalMappingInfoVO.setFlag(false);
				list.add(regionalMappingInfoVO);
			 }
		 }
		 if(list != null && list.size()>0)
			 finalList.addAll(list);		
			
		return finalList;
	}

	public Set<RegionalMappingInfoVO> getWardsInLocalBodyAndConst(
			Long localBodyId, Long constituencyId, String year) {
		
		RegionalMappingInfoVO regionalMappingInfoVO = null;
		Set<RegionalMappingInfoVO> finalList = new LinkedHashSet<RegionalMappingInfoVO>();
		
		//get All wards in Local Body
		List<SelectOptionVO> allWardsInLocalBody =  getWardsInALocalElectionBody(localBodyId);
		// get All wards in a constituency
		//List<SelectOptionVO> allWardsInConstituency = getWardsInALocalElectionBody(localBodyId, constituencyId,year); 
		List<SelectOptionVO> allWardsInConstituency = new ArrayList<SelectOptionVO>(0);		
		List wardsList = null;
		
		List assemblyLocalElecBodyId = assemblyLocalElectionBodyDAO.findAssemblyLocalElectionBodyByLocalBodyAndConstituency(localBodyId, constituencyId);
		if(assemblyLocalElecBodyId != null && assemblyLocalElecBodyId.size() > 0){
			Object values = (Object)assemblyLocalElecBodyId.get(0);
			Long asmblyLocalBodyId = (Long)values;
			
			wardsList = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBody(asmblyLocalBodyId, year);
			for(int j=0;j<wardsList.size();j++)
			{
				Object[] obj = (Object[])wardsList.get(j);
				Constituency constituency = (Constituency)obj[1];
				String wardName = constituency.getLocalElectionBodyWard() != null?constituency.getLocalElectionBodyWard().getWardName().
						concat("( ").concat(constituency.getName().toUpperCase()).concat(" )"):constituency.getName().toUpperCase();
				
				allWardsInConstituency.add(new SelectOptionVO((Long)obj[0],wardName));
				
			}
			
		}
		
		if(allWardsInConstituency != null && allWardsInConstituency.size()>0)
			for(SelectOptionVO selectOption:allWardsInConstituency)
			 {
				regionalMappingInfoVO = new RegionalMappingInfoVO();
				regionalMappingInfoVO.setRegionId(selectOption.getId());
				regionalMappingInfoVO.setRegionName(selectOption.getName());
				regionalMappingInfoVO.setFlag(true);
				finalList.add(regionalMappingInfoVO);
			 }

		 if(allWardsInLocalBody != null && allWardsInLocalBody.size()>0)
		 {			
			for(SelectOptionVO selectOptionVO:allWardsInLocalBody)
			 {
				regionalMappingInfoVO = new RegionalMappingInfoVO();
				regionalMappingInfoVO.setRegionId(selectOptionVO.getId());
				regionalMappingInfoVO.setRegionName(selectOptionVO.getName());
				regionalMappingInfoVO.setFlag(false);
				finalList.add(regionalMappingInfoVO);
			 }
		 }

		return finalList;
	}
	
	public Set<RegionalMappingInfoVO> getboothsInLocalBodiesAndConst(
			Long localBodyId, Long constituencyId, String year) {
		Set<RegionalMappingInfoVO> finalList = new LinkedHashSet<RegionalMappingInfoVO>(0);
		RegionalMappingInfoVO regionalMappingInfoVO = null;
		
		// fetch booths in local election body
		List boothsList = boothDAO.findBoothsInfoForALocalElectionBodyByConstituencyAndYear(localBodyId, new Long(year), constituencyId);
		 if(boothsList.size()>0)
			{
				for(int i=0;i<boothsList.size();i++)
				{
					regionalMappingInfoVO = new RegionalMappingInfoVO();
					Object[] obj = (Object[])boothsList.get(i);
					regionalMappingInfoVO.setRegionId(new Long(obj[0].toString()));
					regionalMappingInfoVO.setRegionName(obj[1].toString());
					regionalMappingInfoVO.setVillagesCovered(obj[3].toString());
					regionalMappingInfoVO.setFlag(true);
					finalList.add(regionalMappingInfoVO);
				}
			}
		 
		//fetch booths in constituency
		List rawBoothData = boothDAO.findBoothInfoByConstituencyIdAndYear(constituencyId, new Long(year));
			if(rawBoothData.size()>0)
				for(Object[] values:(List<Object[]>)rawBoothData){
					regionalMappingInfoVO = new RegionalMappingInfoVO();
					regionalMappingInfoVO.setRegionId(Long.parseLong(values[0].toString()));
					regionalMappingInfoVO.setRegionName(values[1].toString());
					regionalMappingInfoVO.setVillagesCovered(values[2].toString());
					regionalMappingInfoVO.setFlag(false);
					finalList.add(regionalMappingInfoVO);
				}		 
		return finalList;
	}

	public Set<RegionalMappingInfoVO> getboothsInWardsAndConst(Long wardId,
			Long constituencyId, String year) {
		
		Set<RegionalMappingInfoVO> finalList = new LinkedHashSet<RegionalMappingInfoVO>(0);
		RegionalMappingInfoVO regionalMappingInfoVO = null;
		// get booths mapped to a ward
		List rawBoothData = boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(wardId, new Long(year), constituencyId);
		 if(rawBoothData.size()>0)
			{
				for(int i=0;i<rawBoothData.size();i++)
				{
					regionalMappingInfoVO = new RegionalMappingInfoVO();
					Object[] obj = (Object[])rawBoothData.get(i);
					regionalMappingInfoVO.setRegionId(new Long(obj[0].toString()));
					regionalMappingInfoVO.setRegionName(obj[1].toString());
					regionalMappingInfoVO.setVillagesCovered(obj[3].toString());
					regionalMappingInfoVO.setFlag(true);
					finalList.add(regionalMappingInfoVO);
				}
			}
		//fetch booths in constituency
			List rawBoothDataList = boothDAO.findBoothInfoByConstituencyIdAndYear(constituencyId, new Long(year));
				if(rawBoothDataList.size()>0)
					for(Object[] values:(List<Object[]>)rawBoothDataList){
						regionalMappingInfoVO = new RegionalMappingInfoVO();
						regionalMappingInfoVO.setRegionId(Long.parseLong(values[0].toString()));
						regionalMappingInfoVO.setRegionName(values[1].toString());
						regionalMappingInfoVO.setVillagesCovered(values[2].toString());
						regionalMappingInfoVO.setFlag(false);
						finalList.add(regionalMappingInfoVO);
					}		 
			return finalList;		
	}
	
	/*
	public List<SelectOptionVO> getAllRegionScopes() {
		List<SelectOptionVO> scopes = new ArrayList<SelectOptionVO>(0);
		List<RegionScopes> allScopes = null;//regionScopesDAO.getAll();
		for(RegionScopes regionScopes: allScopes)
		{
			scopes.add(new SelectOptionVO(regionScopes.getRegionScopesId(),regionScopes.getScope()));
		}
		return scopes;
	}

	public String getConstituencyAreaType(Long constituencyId) {
		
		Constituency constituency = constituencyDAO.get(constituencyId); 
		String areaType = null;
		if(constituency.getAreaType()!= null)
			areaType = constituency.getAreaType();
		return areaType;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAllRegionScopesForModule(String module,
			Long stateId) {
		if(log.isDebugEnabled())
			log.debug("Inside getAllRegionScopesForModule() method in RegionServiceDataImp service");
		List<SelectOptionVO> scopes = new ArrayList<SelectOptionVO>(0);
		try{
			List result = moduleDetailsDAO.findModuleIdByModuleName(IConstants.ADD_NEW_PROBLEM);
			Long  moduleId = Long.parseLong(result.get(0).toString());
			List allScopes = moduleRegionScopesDAO.findRegionScopesForModuleByState(moduleId, stateId);
			if(allScopes != null && allScopes.size()>0)
				for(int i = 0;i<allScopes.size();i++)
				{
					Object[] obj = (Object[])allScopes.get(i);
					scopes.add(new SelectOptionVO(Long.parseLong(obj[0].toString()),obj[1].toString()));				
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scopes;
	}
	
	public String getRegionNameByRegionId(Long regionId,String regionType)
	{
		String regionName = null;
		
		if(regionType.equalsIgnoreCase(IConstants.STATE))
		{
			State state = stateDAO.get(regionId);
			regionName = state.getStateName();
		}
		else if(regionType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			District district = districtDAO.get(regionId);
			regionName = district.getDistrictName();
		}
		else if(regionType.equalsIgnoreCase(IConstants.MLA) || regionType.equalsIgnoreCase(IConstants.MP))
		{
			Constituency constituency = constituencyDAO.get(regionId);
			regionName = constituency.getName();
		}		
		return regionName;
	}*/
	
	public List<Object> getAllAccessLocByState(List<Long> stateIds)
	{
		List<Object> returnVal = new ArrayList<Object>();
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> regionList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> assemblyList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> districtList = new ArrayList<SelectOptionVO>();
		
		List<Object[]> stList = stateDAO.getAllStatesByIds(stateIds);;
		
		List<Object[]>  parlList =  constituencyDAO.getParliamentConstisByStateIds(stateIds);
			
		List<Object[]> conList =  constituencyDAO.getAssemblyConstisByStateIds(stateIds);
			
		List<Object[]> distList = districtDAO.getDistrictsByStateId(stateIds);
		populateDataToVo(stList,stateList);
		populateDataToVo(parlList,regionList);
		populateDataToVo(conList,assemblyList);
		populateDataToVo(distList,districtList);

		returnVal.add(stateList);
		returnVal.add(districtList);
		returnVal.add(regionList);
		returnVal.add(assemblyList);
		return returnVal;
	}
	
	public List<Object> getAllAccessLocByDistrict(Long districtId)
	{
		List<Object> returnVal = new ArrayList<Object>();
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> regionList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> assemblyList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> districtList = new ArrayList<SelectOptionVO>();
		
		List<Object[]> stList = districtDAO.getStateName(districtId);
		List list = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(districtId, IConstants.DELIMITATION_YEAR);
		List<Object[]> conList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYearInADistrict(2l,districtId, IConstants.DELIMITATION_YEAR);
		List<Object[]> distList = districtDAO.getDistrictIdAndNameByDistrictId(districtId);
		populateDataToVo(stList,stateList);
		populateDataToVo((List<Object[]>)list,regionList);
		populateDataToVo(conList,assemblyList);
		populateDataToVo(distList,districtList);

		returnVal.add(stateList);
		returnVal.add(districtList);
		returnVal.add(regionList);
		returnVal.add(assemblyList);
		return returnVal;
	}
	
	private void populateDataToVo(List<Object[]> data,List<SelectOptionVO> list){
		SelectOptionVO optionVO = null;
		if(data != null && data.size() > 0)
		{
			for (Object[] params:data) {
				optionVO = new SelectOptionVO();
				optionVO.setId((Long)params[0]);
				optionVO.setName(WordUtils.capitalize(params[1].toString().toLowerCase()));
				
				list.add(optionVO);
			}
		}
	}
	public List<Object> getAllAccessLocByAssConsti(Long constiId)
	{
		List<Object> returnVal = new ArrayList<Object>();
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> regionList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> assemblyList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> districtList = new ArrayList<SelectOptionVO>();
		SelectOptionVO vo = null;
		List<Object[]> data = (List<Object[]>)constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(constiId);
		if(data != null && data.size() > 0){
			Object[] value = data.get(0);
			vo = new SelectOptionVO();
			vo.setId((Long)value[0]);
			vo.setName(WordUtils.capitalize(value[1].toString().toLowerCase()));
			assemblyList.add(vo);
			vo = new SelectOptionVO();
			vo.setId((Long)value[2]);
			vo.setName(WordUtils.capitalize(value[3].toString().toLowerCase()));
			districtList.add(vo);
			vo = new SelectOptionVO();
			vo.setId((Long)value[4]);
			vo.setName(WordUtils.capitalize(value[5].toString().toLowerCase()));
			stateList.add(vo);
		}
		 data = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentByAssembly(constiId);
		 returnVal.add(stateList);
		populateDataToVo(data,regionList);
		returnVal.add(districtList);
		returnVal.add(regionList);
		returnVal.add(assemblyList);
		return returnVal;
	}
	
	public List<Object> getAllAccessLocByParlConsti(Long constiId)
	{
		List<Object> returnVal = new ArrayList<Object>();
		List<SelectOptionVO> stateList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> regionList = new ArrayList<SelectOptionVO>(); 
		List<SelectOptionVO> assemblyList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> districtList = new ArrayList<SelectOptionVO>();
		SelectOptionVO optionVO = null;
		List<Long> assemblyIds = new ArrayList<Long>();
		List<Object[]> data = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyByParliamId(constiId);
		if(data != null && data.size() > 0){
			for (Object[] params:data) {
				optionVO = new SelectOptionVO();
				optionVO.setId((Long)params[0]);
				assemblyIds.add((Long)params[0]);
				optionVO.setName(WordUtils.capitalize(params[1].toString().toLowerCase()));
				
				assemblyList.add(optionVO);
			}
		}
		  data = constituencyDAO.getConstituencyConstituencyId(constiId);
		  
		  SelectOptionVO stateVo = new SelectOptionVO();
		  stateVo.setId((Long)data.get(0)[2]);
		  stateVo.setName(WordUtils.capitalize(data.get(0)[3].toString().toLowerCase()));
		  stateList.add(stateVo);
		  
		populateDataToVo(data,regionList);
		if(assemblyIds.size() >0){
			data = constituencyDAO.getDistrictByConstituencyId(assemblyIds);
			populateDataToVo(data,districtList);
		}
		returnVal.add(stateList);
		returnVal.add(districtList);
		returnVal.add(regionList);
		returnVal.add(assemblyList);
		return returnVal;
	}
	/*public List<SelectOptionVO> getUserStateList(String accessType,Long accessValue)
	{
		try
		{
			List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>(0);
			SelectOptionVO selectOptionVO = null;
			State state = null;
			
			if(accessType.equalsIgnoreCase(IConstants.MLA) || accessType.equalsIgnoreCase(IConstants.MP))
				state = constituencyDAO.get(accessValue).getState();
			else if(accessType.equalsIgnoreCase(IConstants.STATE))
				state = stateDAO.get(accessValue);
			else if(accessType.equalsIgnoreCase(IConstants.DISTRICT))
				state = districtDAO.get(accessValue).getState();
			
			if(state == null)
				return null;
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(state.getStateId());
			selectOptionVO.setName(state.getStateName());
			
			statesList.add(selectOptionVO);
			return statesList;
		}
		catch(Exception e)
		{
			log.error("Exception Occured In getUserStateList() And Exception is -- "+e);
			return null;
		}
	}
	
	
	public List<SelectOptionVO> getProblemTypesByRegionScopeId(Long regionScopeId)
	{
		try{
			List<Object[]> list = regionScopesProblemTypeDAO.getProblemTypesByRegionScopeId(regionScopeId);
			List<SelectOptionVO> problemTypesList =new ArrayList<SelectOptionVO>(0);;

			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(WordUtils.capitalize(params[1].toString().toLowerCase()));
					problemTypesList.add(selectOptionVO);
					Collections.sort(problemTypesList);
				}
			}
			problemTypesList.add(0,new SelectOptionVO(0L,"Select Problem Type"));
			return problemTypesList;
			
		}catch(Exception e){
			log.error("Exception Occured in getProblemTypesByRegionScopeId() method - "+e);
			return null;
		}
	}
	*/
	public List<SelectOptionVO> getAllParliamentConstituenciesForAState(
			Long electionScopeId , Long stateId) {
		List<SelectOptionVO> constsList = new ArrayList<SelectOptionVO>();
		List<Constituency> list =  constituencyDAO.getAllParliamentConstituenciesInAState(electionScopeId, stateId);
		for(Constituency constituency: list)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(constituency.getConstituencyId());
			selectOptionVO.setName(WordUtils.capitalize(constituency.getName().toLowerCase()));
			
			constsList.add(selectOptionVO);
 		}
		return constsList;
	}
	/*
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByDistrictIDs(Long districtID){
		List<Constituency> constituencies = constituencyDAO.findConstituenciesByDistrictId(districtID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(Constituency constituency : constituencies){
			result.add(new SelectOptionVO(constituency.getConstituencyId(),WordUtils.capitalize(constituency.getName().toLowerCase())));
		}
		Collections.sort(result);
		return result;
	}
	
	*/
	
	public List<SelectOptionVO> getPanchayitiesInTehsil(Long tehsilId)
	{
		log.debug("Entered into the getPanchayitiesInTehsil service method");
		List<SelectOptionVO> panchayatList = new ArrayList<SelectOptionVO>();
		
		try
		{
			List<Object[]> panchayatDetailsList = null;//panchayatDAO.getPanchayatsBymandalId(new Long(tehsilId.toString().substring(1)));
			
			
			for(Object[] obj:panchayatDetailsList)
			{
				SelectOptionVO selectOption = new SelectOptionVO();
				 selectOption.setId(new Long("2"+obj[0].toString()));
				 selectOption.setName(obj[1].toString());
				 panchayatList.add(selectOption);
				
			}
		}
		catch(Exception e)
		{
			log.error("Exception raised in getPanchayitiesInTehsil service method");
			e.printStackTrace();
			
		}
		return panchayatList;
	}
	


	public List<SelectOptionVO> getBoothsInAPanchayatByPublicationId(Long panchayatId , Long publicationId)
	{
		log.debug("Entered into the getBoothsInAPanchayatForPresentElectionYear service method");
		List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
		
		try
		{
		List<Object[]> boothDetailsList = null;//boothDAO.getBoothsInAPanchayatByPublicationId(new Long(panchayatId.toString().substring(1)),publicationId);
		
		for(Object[] obj:boothDetailsList)
		{
			 SelectOptionVO selectOption = new SelectOptionVO();
			 selectOption.setId(new Long(obj[0].toString()));
			 selectOption.setName("BOOTH - "+obj[1].toString());
			 boothsList.add(selectOption);
			
		}
		}catch(Exception e)
		{
			log.error("Exception raised in getBoothsInAPanchayatForPresentElectionYear service method");
			e.printStackTrace();
		}
		
		return boothsList;
		
		
	}
	/*
	public List<SelectOptionVO> getWardsInALocalElectionBodyByID(Long assemblyLocalBody)
	{
		List<SelectOptionVO> wards = new ArrayList<SelectOptionVO>();
		try{
		List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalBody);
		Long id = (Long)list2.get(0);
		List<Constituency> wardObjs = constituencyDAO.findWardsAndIdsInMuncipality(id);
		if(wardObjs != null && wardObjs.size() > 0)
		for(Constituency ward:wardObjs)
			wards.add(new SelectOptionVO(ward.getConstituencyId(), ward.getName()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 return wards;
		
		
	}
	
	public List<SelectOptionVO> getMandals(List<SelectOptionVO> list)
	{
	List<SelectOptionVO> resultlist = new ArrayList<SelectOptionVO>();
	try{
	if(list != null && list.size() > 0)
	{
	for(SelectOptionVO params : list)
	{
		if(params.getId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
			resultlist.add(new SelectOptionVO(params.getId(),params.getName().toString()));	
	}
	}
	return resultlist;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.error("Exception Occured in getMandals() - "+e);
		return resultlist;
	}
	
	}
	*/
	
	
	/**
	 * This method retrieves all hamlets if the location is of type Rural and all wards if the location is of type Urban 
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getHamletsOrWards(Long locationId, String year) {
		List<SelectOptionVO> regionsList = new ArrayList<SelectOptionVO>();
		String areaFlag = locationId.toString().substring(0,1);
		Long id=locationId;
		if(locationId > 1)
		id = new Long(locationId.toString().substring(1));
		
		if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE))
		{
			//fetch the wards in a municipal/corporation/greater corp region if it is partial
			//List wardsList = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(id, year);
			List wardsList = null;
			String  constituencyType = assemblyLocalElectionBodyDAO.get(id).getConstituency().getAreaType();
			if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			{
				wardsList = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBody(id, year);
			}
			else if(constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			{
				wardsList = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(id, year);
			}
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
						regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+constituency.getConstituencyId()),WordUtils.capitalize(constituency.getName().toLowerCase())));
					}
				}								
			}
			//fetch the wards in a municipal/corporation/greater corp region if it is not partial
			else if(wardsList.size()>0)
			{
				for(int j=0;j<wardsList.size();j++)
				{
					Object[] obj = (Object[])wardsList.get(j);
					Constituency constituency = (Constituency)obj[1];
					String wardName = constituency.getLocalElectionBodyWard() != null?constituency.getLocalElectionBodyWard().getWardName().
							concat("( ").concat(constituency.getName().toUpperCase()).concat(" )"):constituency.getName().toUpperCase();
					
					regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+ obj[0].toString()),WordUtils.capitalize(wardName.toLowerCase())));
				}
			}
			
		} else if(areaFlag.equalsIgnoreCase(IConstants.RURAL_TYPE))
		{
			List resultsList = hamletDAO.findHamletsByTehsilId(id);
			
			//If hamlets data is not available then go with townships
			if(resultsList == null || resultsList.size() == 0){
				resultsList = townshipDAO.findTownshipsByTehsilId(id);
			}
			
			for(int i = 0; i<resultsList.size();i++)
			{
				Object[] obj = (Object[])resultsList.get(i);
				regionsList.add(new SelectOptionVO(new Long(IConstants.RURAL_TYPE+obj[0].toString()),WordUtils.capitalize(obj[1].toString().toLowerCase())));				
			}
		}
		return regionsList;
	}
	
}
	
 