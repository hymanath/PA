package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Gallary;

public interface IGallaryDAO extends GenericDao<Gallary, Long>{
	
	public List<Object[]> getCandidateGallaryDetail(Long candidateId,int firstResult,int maxResult,String type);
	
	public List<Object[]> getGallariesByCandidateId(Long candidateId,String contentType);

}
