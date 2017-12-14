package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresenteeRefDocument;

public interface IPmRepresenteeRefDocumentDAO extends GenericDao<PmRepresenteeRefDocument, Long> {
	public List<Object[]> getPmRepresenteeRefDocumentByPetition(Long petitionId);
}
