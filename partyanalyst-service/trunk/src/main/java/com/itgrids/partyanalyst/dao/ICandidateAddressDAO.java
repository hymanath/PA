package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateAddress;

public interface ICandidateAddressDAO extends GenericDao<CandidateAddress, Long>{
	
	public  List getCandidateAddressDetailsByCandidateId(Long candidateId);
	public List<CandidateAddress> getCandidateAddressDetails(Long candidateId);
	
	public Object getCandidateAddressByAddressId(Long addressId);

	
}
