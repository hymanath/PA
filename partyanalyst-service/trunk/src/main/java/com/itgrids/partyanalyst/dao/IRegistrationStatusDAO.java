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
	 public List<Object[]> getUnRecognizeBoothsInfo(List<Long> boothIds,Long publicationId);
	 public List<Object[]> getRecognizeBoothsInfo(List<Long> boothIds,Long publicationId);
	  public List<BigInteger> getUnrecognizeByBooths(List<Long> boothIds,Long publicationId);
	  public List<BigInteger> getAllBooths(List<Long> boothIds,Long publicationId);
	  public List<BigInteger> getRecognizeByBooths(List<Long> boothIds,Long publicationId);
	  public List<Object[]> getRecognizeByBoothsTotalVotersAndPolledVotes(List<Long> boothIds,Long publicationId);
	  public List<Object[]> getUnrecognizeByBoothsTotalVotersAndPolledVotes(List<Long> boothIds,Long publicationId);
	  public List<Object[]> getAllBoothsTotalVotersAndPolledVotes(List<Long> boothIds,Long publicationId);
}
