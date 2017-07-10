package com.rwss.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinWorksAdminView;

public interface IRwsMinWorksAdminViewDAO extends GenericDao<RwsMinWorksAdminView,String> {

	public List<Object[]> getSchemesDataByDate(InputVO inputVo);

	public List<Object[]> getSchemeWiseWorkDetails(InputVO inputVO, String WorkType);
	
	public List<Object[]> getStateLevelKPIDeatils(InputVO inputVO, String TargetType);
	
	public List<Object[]> getOnclickWorkDetails(InputVO inputVO);

	public List<Object[]> getOnclickTargetsAcheievementsDetails(InputVO inputVO);
	
	public List<Object[]> getOnclickStrssedTargetsAcheievementsDetails(InputVO inputVO);
	
	public List<Object[]> getSchemeDetailsByTypeOfAssestName(InputVO inputVo);

	public List<String> getAllAssetTypes();

	

}
