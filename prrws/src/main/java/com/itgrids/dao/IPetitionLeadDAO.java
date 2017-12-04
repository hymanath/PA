package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionLead;

public interface IPetitionLeadDAO extends GenericDao<PetitionLead, Long> {
	public List<Object[]> getPetitionLeadDetailsList();

}
