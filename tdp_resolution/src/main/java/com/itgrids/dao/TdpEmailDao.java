package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ExampleModel;
import com.itgrids.model.TdpEmailmodel;

public interface TdpEmailDao extends GenericDao<TdpEmailmodel,Integer>{
	
	//public List<Object[]> getAllNotificationType();
	public List<Object[]> getAllTdpEmailmodel();

}
