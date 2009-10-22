package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.service.IRegionServiceData;

public class RegionServiceDataImp implements IRegionServiceData {

	private IDistrictDAO districtDAO;
	
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
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

}
