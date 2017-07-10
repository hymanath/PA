package com.rwss.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinHabView;

public interface IRwsMinHabViewDAO extends GenericDao<RwsMinHabView,String>{
	
	public List<Object[]> getHabitationCoverageByStatusByLocationType(InputVO inputVo);
	public List<Object[]> getLocationBasedOnSelection(InputVO inputVo);
	public Object[] gethabitationWatersupplyDetails(InputVO inputVo);
	public List<Object[]> getStressedHabitationCountLocationWise(InputVO inputVo,String type);
	public List<RwsMinHabView> getAllHabitationDetails(InputVO inputVo);
	public List<String> getAllCoverageStatus();
	public List<Object[]> getHabitationDetailsByStatusByLocationType(InputVO inputVO);
	public List<Object[]> getOnclickHabitationsupplyDetails(InputVO inputVO);
	public List<Object[]> getStressedHabitationDetailsByStatusByLocationType(InputVO inputVo);

}
