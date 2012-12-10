package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateAddress;
import com.itgrids.partyanalyst.model.CandidatePhone;
import com.itgrids.partyanalyst.model.PhoneType;


public interface ICandidatePhoneDAO extends GenericDao<CandidatePhone, Long>{
	public List<CandidatePhone> getCandidatePhoneDetails(Long candidateId);
	

}
