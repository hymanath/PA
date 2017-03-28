package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreTravelInfo;



public interface ITdpCadreTravelInfoDAO extends GenericDao<TdpCadreTravelInfo, Long>{
	
	public List<TdpCadreTravelInfo> checkCustomerId(Long custId);

}
