package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionObjectsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IRegionServiceData;

public class RegionServiceDataImp implements IRegionServiceData {

	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IElectionObjectsDAO electionObjectsDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	
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
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID){
		List constituencies = delimitationConstituencyDAO.getConstituenciesByDistrictID(districtID);
		List<SelectOptionVO> constituencyNames=new ArrayList<SelectOptionVO>();
		
		for(int i=0; i< constituencies.size(); i++){
			Object[] obj = (Object[])constituencies.get(i);
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(new Long(obj[0].toString()));
			objVO.setName(obj[1].toString());
			constituencyNames.add(objVO);
		}
		
		return constituencyNames;
	}
	

	@SuppressWarnings("unchecked")
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

	public List<SelectOptionVO> getStatesByCountryFromBooth(Long countryID){
		List list = boothConstituencyElectionDAO.getStatesByCountryFromBooth(countryID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	public List<SelectOptionVO> getDistrictsByStateIDFromBooth(Long stateID) {
		List list = boothConstituencyElectionDAO.getDistrictsByStateIDFromBooth(stateID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	public List<SelectOptionVO> getConstituenciesByDistrictIDFromBooth(Long districtID) {
		List list = boothConstituencyElectionDAO.getConstituenciesByDistrictIDFromBooth(districtID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	public List<SelectOptionVO> getMandalsByConstituencyIDFromBooth(Long constituencyID) {
		List list = boothConstituencyElectionDAO.getMandalsByConstituencyIDFromBooth(constituencyID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	
	public List<SelectOptionVO> getStateByParliamentConstituencyID(Long constituencyID){
		List state = constituencyDAO.getStateForParliamentConstituency(constituencyID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		if(state!=null && state.size()==1){
			Object[] object = (Object[]) state.get(0);
			result.add(new SelectOptionVO(new Long(object[0].toString()), object[1].toString()));
		}
		return result;
	}
	
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
	
}
