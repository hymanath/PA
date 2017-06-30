package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingMainType;

public interface IPartyMeetingMainTypeDAO extends GenericDao<PartyMeetingMainType, Long> {
public List<Object[]> getMeetingMainType();
}
