package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.TdpEmail;

public interface ITdpEmailDAO extends GenericDao<TdpEmail,Integer>{
	
	public List<Object[]> getAllTdpEmailmodel();

}
