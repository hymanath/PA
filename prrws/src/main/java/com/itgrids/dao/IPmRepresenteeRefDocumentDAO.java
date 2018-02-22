package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeRefDocument;

public interface IPmRepresenteeRefDocumentDAO extends GenericDao<PmRepresenteeRefDocument, Long> {
	public List<Object[]> getPmRepresenteeRefDocumentByPetition(Long petitionId);
	public List<Long> getPmRepresenteeRefDocumentIds(List<Long> representeeRefDetailsIds);
	public int updatePmPmRepresenteeRefDocumens(List<Long> representeeRefDocIds,Date updatedTime,Long userId);
	public List<Object[]> getRepresenteeRefDocuments(Set<Long> petitionIds);
}
