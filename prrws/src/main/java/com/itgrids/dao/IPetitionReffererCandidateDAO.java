package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionReffererCandidate;

public interface IPetitionReffererCandidateDAO extends GenericDao<PetitionReffererCandidate, Long> {

	public List<Object[]> getCandidatseDetailsByDesignationAndLocation(Long designationId,Long locationLevelId,Long locationValue);
}
