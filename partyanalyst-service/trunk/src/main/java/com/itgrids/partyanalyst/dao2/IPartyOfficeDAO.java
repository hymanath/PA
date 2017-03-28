package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyOffice;

public interface IPartyOfficeDAO extends GenericDao<PartyOffice, Long> {
	public List<Object[]> getTotalAttendedEmployeesCadreId(Date fromDate, Date toDate);

}
