package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.PetitionsInputVO;
import com.itgrids.model.PmSubWorkCoveringLetter;

public interface IPmSubWorkCoveringLetterDAO extends GenericDao<PmSubWorkCoveringLetter, Long> {
	public List<String> getCoveringLetterDetailsByEndorsmentNo(String endorsmenNo);
	public List<Object[]> getSubWorkWiseRequiredDocumentsDetailsByPetitionId(Long petiotionId);
	public int disableExistingCoveringLettersForPetition(Long petitionId,String reporttype);
	public List<Object[]> getAllTypeOfDocumentsForPetition(Set<Long> petiotionIds,String reportType);
	public List<Object[]> getDocumentsDetailsForPDFDocument(PetitionsInputVO inputVO,Date startDate,Date endDate,List<Long> petitionIdsList);
}
