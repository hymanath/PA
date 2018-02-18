package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmSubWorkCoveringLetter;

public interface IPmSubWorkCoveringLetterDAO extends GenericDao<PmSubWorkCoveringLetter, Long> {
	public List<String> getCoveringLetterDetailsByEndorsmentNo(String endorsmenNo);
	public List<Object[]> getSubWorkWiseRequiredDocumentsDetailsByPetitionId(Long petiotionId);
	public int disableExistingCoveringLettersForPetition(Long petitionId,String reporttype);
}
