package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionDesignation;

public interface IPetitionDesignationDAO extends GenericDao<PetitionDesignation, Long> {
	public List<Object[]> getAllpetitionDesignationList();
	public List<Object[]> getAllReferredCandidateDesignationList();;
	public List<Object[]> getGivenPetitionCandidateDesignationList();
	public List<Object[]> getGivenpetitionReprDesignationsList();
}
