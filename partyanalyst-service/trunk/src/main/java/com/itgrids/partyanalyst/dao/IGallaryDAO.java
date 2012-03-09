package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Gallary;

public interface IGallaryDAO extends GenericDao<Gallary, Long>{
	
	public List<Object[]> getCandidateGallaryDetail(Long candidateId,int firstResult,int maxResult,String type);
	
	public List<Object[]> getGallariesByCandidateId(Long candidateId,String contentType);
	
	public Integer deleteGallary(Long gallaryId);
	
	public List<Object[]> getCandidatesGallaryDescForUpdate(Long gallaryId , Long candidateId);
	public List<Object[]> getSpecialPageGallaryDescForUpdate(Long gallaryId );
	public List<Object> getDetailsOfVisibility(Long gallaryId);
	
}
