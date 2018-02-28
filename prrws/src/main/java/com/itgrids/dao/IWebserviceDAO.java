package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.model.Webservice;

public interface IWebserviceDAO extends GenericDao<Webservice,Long>{

	public List<Long> getWebserviceIdByUrl(String url);
	public List<Object[]> getWebserviceDetailsByUrl(String url);
}
