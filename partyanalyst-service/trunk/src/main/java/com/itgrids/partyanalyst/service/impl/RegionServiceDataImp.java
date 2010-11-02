package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionObjectsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	private IElectionObjectsDAO electionObjectsDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	public void setElectionObjectsDAO(IElectionObjectsDAO electionObjectsDAO) {
		this.electionObjectsDAO = electionObjectsDAO;
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
	
		System.out.println("RegionservicedataImpl.......... mandals.size()-----"+mandals.size());
		List<SelectOptionVO> mandalNames=new ArrayList<SelectOptionVO>();
		
		for(Tehsil tehsil : mandals){
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(tehsil.getTehsilId());
			objVO.setName(tehsil.getTehsilName());
			mandalNames.add(objVO);
		}
		
		System.out.println("RegionservicedataImpl.......... mandalNames.size()-----"+mandalNames.size());
		
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
		List list = electionObjectsDAO.findLatestParliamentaryElectionYear(stateID);
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
				localElectionBodiesList.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString().toUpperCase()+" "+ (obj[2])));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception arised while retrieving local election bodies");
		}
		
		return localElectionBodiesList;
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
			List wardsList = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(id, year);
			if(wardsList.size() == 0)
			{			
				List<Constituency> wards = constituencyDAO.findWardsAndIdsInMuncipality(id);
				for(Constituency constituency:wards)
				{
					regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+constituency.getConstituencyId()),constituency.getName().toUpperCase()));
				}				
			}
			//fetch the wards in a municipal/corporation/greater corp region if it is not partial
			else if(wardsList.size()>0)
			{
				for(int j=0;j<wardsList.size();j++)
				{
					Object[] obj = (Object[])wardsList.get(j);
					regionsList.add(new SelectOptionVO(new Long(IConstants.URBAN_TYPE+ obj[0].toString()),obj[1].toString().toUpperCase()));
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
	
}
