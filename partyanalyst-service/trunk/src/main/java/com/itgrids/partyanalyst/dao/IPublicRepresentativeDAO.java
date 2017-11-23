package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PublicRepresentative;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IPublicRepresentativeDAO extends GenericDao<PublicRepresentative, Long> {
	public List<Integer> getRepresentativesByPositions(Long representativeLevelId,List<Long> locationValuesList,Long positionId);
	public List<Object[]> getCandidateInfoByCandidateIds(List<Long> candidateIdsList);
	public List<Object[]> getCandidateDetailsByCandidateId(List<Long> candidateIds);
	public List<Long> getCandidateCadreDetils(Long candidateId);
	public List<UserAddress> getUserAddressForCadre(Long tdpCadreId);
	public List<Object[]> getUserAddressForCadre1(List<Long> tdpCadreIds);
	public List<Object[]> getPulicRepresentativeInfoByLocation(Long locationId,String searchType);
	public List<Object[]> getPublicRepresentativeLocationDetails(Long tdpCadreId);
	public List<Object[]> getPartyLeadersDeatails(Long stateId, List<Long> enrollmentIdsList, Long levelId,List<Long> locationIdsList,List<Long> designationIdsList,int firstIndex,int maxIndex,String fetchTypeStr);
	public List<Object[]> getLocationWiseCandidateDesignations(List<Long> condidateIds);
	public List<Object[]> getStateWiseCandidateDesignations(Long locationValue,Long locationTypeId,List<Long> representativTypeIds);
	public List<Object[]> getParticipantsCandidateDesgnination(List<Long> candidateIdsList);
}
