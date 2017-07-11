package com.rwss.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinWorkscompView;

public interface IRwsMinWorkscompViewDAO extends GenericDao<RwsMinWorkscompView,String> {
	
	public List<Object[]> getSchemeWiseWorkDetails(InputVO inputVO,String workType);

	public List<Object[]> getStressedKPIDeatils(InputVO inputVO,String targetAll);

	public List<Object[]> getOnclickWorkDetails(InputVO inputVO);

	public List<Object[]> getOnclickTargetsAcheievementsDetails(InputVO inputVO);

}
