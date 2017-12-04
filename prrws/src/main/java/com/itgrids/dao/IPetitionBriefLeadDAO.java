package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionBriefLead;

public interface IPetitionBriefLeadDAO extends GenericDao<PetitionBriefLead, Long> {
	public List<Object[]> gePetitionBriefLeadDetailsList();

}
