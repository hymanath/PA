package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RegistrationStatus;

public interface IRegistrationStatusDAO  extends GenericDao<RegistrationStatus,Long>{
	public List<Object[]> getAllBoothsInfo();
	public List<Object[]> getAllKnownBoothsInfo(Long constituencyId,Long publicationId);
	public List<Object[]> getAllUnKnownBoothsInfo();
	 public List<BigInteger> getAllKnownBoothsInfoByConstituency(Long constituencyId,Long publicationId);
	 public List<Object[]> getBoothsInfo(List<Long> boothIds,Long publicationId);
	 public List<BigInteger> getAllUnRecognizedBoothsInfoByConstituency(Long constituencyId,Long publicationId);
}
