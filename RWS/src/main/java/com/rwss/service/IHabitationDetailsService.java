package com.rwss.service;

import java.util.List;

import com.rwss.dto.BasicVO;
import com.rwss.dto.InputVO;
import com.rwss.dto.LocationVO;
import com.rwss.model.RwsMinAssetView;
import com.rwss.model.RwsMinConstituencyView;
import com.rwss.model.RwsMinHabView;
import com.rwss.model.RwsMinWorksAdminView;
import com.rwss.model.RwsMinWorkscompView;

public interface IHabitationDetailsService {
	
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO inputVO);
	public String getAssetInfo(InputVO inputVO);
	public String gethabitationWatersupplyDetails(InputVO inputVo);
	public List<LocationVO> getLocationBasedOnSelection(InputVO inputVO);
	public LocationVO getStressedHabitationInfoInALocation(InputVO inputVO);
	public List<BasicVO> getSchemesDataByDate(InputVO inputVo);
	public BasicVO getLabTestDetails(InputVO inputVO);
	public List<LocationVO> getSchemeWiseWorkDetails(InputVO inputVO);
	public List<RwsMinHabView> getAllHabitationDetails(InputVO inputVO);
	public List<RwsMinConstituencyView> getAllHabitationConstitencyData();
	public List<RwsMinAssetView> getAllAssestDetails();
	public List<RwsMinWorksAdminView> getAllWorkAdminDetails();
	public List<RwsMinWorkscompView> getAllWorkComplitionDetails();
	public String getWaterSourceDeatils(InputVO inputVO);
	public String getKPIDeatils(InputVO inputVO);
	public String getStressedKPIDeatils(InputVO inputVO);
	public List<LocationVO> getWaterSourceDeatils2(InputVO inputVo);
	

}
