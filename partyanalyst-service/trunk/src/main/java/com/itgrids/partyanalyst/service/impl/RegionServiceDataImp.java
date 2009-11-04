package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IRegionServiceData;

public class RegionServiceDataImp implements IRegionServiceData {

	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyTehsilDAO constituencyTehsilDAO;

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setConstituencyTehsilDAO(
			IConstituencyTehsilDAO constituencyTehsilDAO) {
		this.constituencyTehsilDAO = constituencyTehsilDAO;
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
		List<Constituency> constituencies = constituencyDAO.getConstituenciesByDistrictID(districtID);
		
		List<SelectOptionVO> constituencyNames=new ArrayList<SelectOptionVO>();
		
		for(Constituency constituency:constituencies){
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(constituency.getConstituencyId());
			objVO.setName(constituency.getName());
			constituencyNames.add(objVO);
		}
		
		return constituencyNames;
	}
	

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getMandalsByConstituencyID(Long constituencyID){
		List mandals = constituencyTehsilDAO.getTehsilsByConstituency(constituencyID);
		System.out.println("RegionservicedataImpl.......... mandals.size()-----"+mandals.size());
		List<SelectOptionVO> mandalNames=new ArrayList<SelectOptionVO>();
		
		for(int i=0;i<mandals.size();i++){
			Object[] obj = (Object[])mandals.get(i);
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(new Long(obj[0].toString()));
			objVO.setName(obj[1].toString());
			mandalNames.add(objVO);
		}
		System.out.println("RegionservicedataImpl.......... mandalNames.size()-----"+mandalNames.size());
		
		return mandalNames;
	}
}
