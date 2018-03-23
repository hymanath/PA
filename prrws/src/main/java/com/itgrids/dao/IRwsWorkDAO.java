package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsWork;

public interface IRwsWorkDAO extends GenericDao<RwsWork, Long>{

	public  List<String> getWorkdetailsById(String type);

	public List<Object[]> getWorksData(Date fromDate,Date toDate, String status, String assetType, String locationType,String locationIdStr, String  districtId);

	public RwsWork getWorkdetailsByIds(String workId);

	public List<RwsWork> getWorksbyWorkIdList(List<String> workIds);

	public List<Object[]> getnotGroundedWorkList(Date fromDate, Date toDate, String assetType, String locationType, String locationIdStr, String districtId);
	
	public List<Object[]> getRwsProgramsCodeAndName();

}
