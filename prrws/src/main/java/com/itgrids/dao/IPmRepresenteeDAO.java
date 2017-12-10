package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRepresentee;

public interface IPmRepresenteeDAO extends GenericDao<PmRepresentee, Long> {
	public List<Long> getExistingPetitionRepresenteeDetailsById(String voterCardNo,String adharCardNo);
	public List<Long> getExistingPetitionRepresenteeDetailsByRefId(Long refCandidateId);
}
