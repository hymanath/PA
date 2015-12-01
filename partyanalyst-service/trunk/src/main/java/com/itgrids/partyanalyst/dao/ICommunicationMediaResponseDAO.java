package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommunicationMediaResponse;

public interface ICommunicationMediaResponseDAO extends GenericDao<CommunicationMediaResponse,Long>{
         
	public List<Object[]> getIVRSummaryByTdpCadreId(Long tdpCadreId);
}
