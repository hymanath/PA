package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommunicationMediaResponse;

public interface ICommunicationMediaResponseDAO extends GenericDao<CommunicationMediaResponse,Long>{
         
	public List<Object[]> getIVRSummaryByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getIVRDetailsByTdpCadreId(Long tdpCadreId,List<String> ivrNameList,int startIndex,int maxIndex);
	public List<Object[]> getQuestionAndoptionsByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getTotalIVRDetailsByTdpCadreId(Long tdpCadreId);
	
	public List<Object[]> getIVRDetailsByTdpCadreId1(Long tdpCadreId,int startIndex,int maxIndex);
}
