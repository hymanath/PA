package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreVolunteerDate;



public interface ITdpCadreVolunteerDateDAO extends GenericDao<TdpCadreVolunteerDate, Long>{

	public List<Object[]> getAvailableDatesForVolunteers(List<Long> valenteersIdsList);
}
