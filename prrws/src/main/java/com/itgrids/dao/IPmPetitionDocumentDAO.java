package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmPetitionDocument;

public interface IPmPetitionDocumentDAO  extends GenericDao<PmPetitionDocument, Long> {
	public List<Object[]> getPmPetitionDocumentByPetition(Long petitionId);
}
