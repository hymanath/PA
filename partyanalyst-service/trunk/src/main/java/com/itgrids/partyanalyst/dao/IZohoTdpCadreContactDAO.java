package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ZohoTdpCadreContact;

public interface IZohoTdpCadreContactDAO extends GenericDao<ZohoTdpCadreContact, Long>{
	public List<Long> getTdpCadreId(String zohoContactId);
} 
