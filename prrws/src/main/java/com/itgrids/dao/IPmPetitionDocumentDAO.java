package com.itgrids.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmPetitionDocument;

public interface IPmPetitionDocumentDAO  extends GenericDao<PmPetitionDocument, Long> {
	public List<Object[]> getPmPetitionDocumentByPetition(Long petitionId);
	public List<Long> getPmPetitionDocumentIds(Long petitionId);
	public int updatePmpetitionDocuments(List<Long> petitiionDocIds,Long userId);
	public List<Object[]> getWorkDocuments(Set<Long> petitionIds);
}
