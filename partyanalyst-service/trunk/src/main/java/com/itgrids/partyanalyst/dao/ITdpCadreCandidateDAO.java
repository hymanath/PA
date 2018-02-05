package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCandidate;


public interface ITdpCadreCandidateDAO extends GenericDao<TdpCadreCandidate, Long>{
	
	public List<Object[]> getPublicRepresentativeDetailsByCadre(Long cadreId);
	public List<Long> getTdpCadreCandidate(Long cadreId);
	public List<Object[]> getTdpCadreCandidateIds(List<Long> finalCadreIDsList);
	public List<Object[]> getPublicRepresentaativesDetailsForCadreIdsList(List<Long> cadreIdsList);
	public List<Object[]> getPublicsRepresentaativesDetailsForCadreIdsList(List<Long> cadreIdsList);
	public List<Object[]> getPublicRepresentativeDetailsForCadreIds(List<Long> cadreIds);
	public Long getCheckCadreIdExits(Long tdpcadreId);
	public List<Object[]> getPublicRepresentativeDetailsByCadreIds(List<Long> cadreIds);
	public List<Object[]> getCandidateDetails(List<Long> cadreIds);
	public List<Object[]> getCandidateDetailsForCommittee(List<Long> cadreIds);
	public List<Object[]> getTdpCadreIdsOfCandidates(Set<Long> candidateIds);
	public List<Object[]> geTdpCadreCandidateDetailsByMemberShipIds(List<String> memberShipNos);
	
	public List<Object[]> geTdpCadreCandidateDesiganationsByCadreaIds(Set<Long> tdpCadreIds);
	public List<Object[]> nomiantionCandidateDetails(List<Long> candidateIds);
	public List<Object[]> getPublicRepresetativesInLocation(Long locationValue,Long locationTypeId,List<Long> representativTypeIds);
	public List<Long> getTdpCadreIds(List<Long> candidateIds);
}
