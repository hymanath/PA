package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsWork;

public interface IRwsWorkDAO extends GenericDao<RwsWork, Long>{

	public  List<String> getWorkdetailsById();

	public List<Object[]> getWorksData(Date fromDate,Date toDate,String assetType, String locationType,Long locationValue);
	
	public RwsWork getWorkdetailsByIds(String workId);

}
