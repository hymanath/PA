package com.rwss.service;

import java.util.List;

import com.rwss.dto.HabitationDetailsVO;
import com.rwss.dto.InputVO;

public interface IHabitationDetailsOnClickService {

	public String getOnclickWorkDetails(InputVO inputVO);

	public String getOnclickTargetsAcheievementsDetails(InputVO inputVO);
	
	public List<HabitationDetailsVO> getHabitationDetailsByStatusByLocationType(InputVO inputVO);
	
	public List<HabitationDetailsVO> getAssetDetailsByAssetType(InputVO inputVO);
	
	public String getOnclickStrssedTargetsAcheievementsDetails(InputVO inputVO);

	public String getOnclickHabitationsupplyDetails(InputVO inputVO);
	
	public List<HabitationDetailsVO> getSchemeDetailsByTypeOfAssestName(InputVO inputVo);

	public List<HabitationDetailsVO> getWaterSourceDeatilsLocationWise(InputVO inputVo);
	
	public List<HabitationDetailsVO> getStressedHabitationDetailsByStatusByLocationType(InputVO inputVO);

}
