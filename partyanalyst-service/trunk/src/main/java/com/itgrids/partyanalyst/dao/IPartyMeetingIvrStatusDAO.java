package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingIvrStatus;

public interface IPartyMeetingIvrStatusDAO extends GenericDao<PartyMeetingIvrStatus, Long>{
	
	 public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate);
	 public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList);
		
}
