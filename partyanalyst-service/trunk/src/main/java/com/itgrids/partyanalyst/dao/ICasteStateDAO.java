package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CasteState;

public interface ICasteStateDAO extends GenericDao<CasteState, Long>{
	
	public List<Object[]> getStatewiseCastNames(Long casteCategoryGroupId);
	 public List<Object[]> getCasteNamesByAutoPopulate(Long stateId,String letters) ;
	 public List<Object[]> getAllCasteDetails();
	 public List<Object[]> getAllCasteDetailsForVoters(Long stateId);
	 
	 public List<Object[]> getAllCastesForVoters(Long stateId,Long userId);
	 
	 //public List<Object[]> getAllCasteInfoDetails();
	 
	 public CasteState getCasteStateByCasteId(Long userId, Long stateId,Long casteId, Long casteCategoryId);
	 
	 public List<Object[]> getCasteStateList();
	 
	 public List<Object[]> getCasteNamesByCasteIds(List<Long> casteIds);
	 
	 public List<Object[]> getCasteListByCasteIds(List<Long> casteIds);
	
	 public List<CasteState> gettingCasteStateBasedOnCaste(Long casteId);
	 public List<Object[]> getAllCasteInfo();
	 public List<Object[]> getAllUpdatedCasteInfo();
	 public List<Object[]> getStatewiseCastNamesByGroupId(Long casteCategoryGroupId,Long stateId);
	 public List<Object[]> getStatewisesCastNamesByGroupId(Long casteCategoryGroupId,Long stateId);
	 public List<Object[]> getAllCastesInfo();
	 public List<Object[]> getStatewisesCastNames(Long stateId);
	 public List<Object[]> getStatewiseCastNamesByCasteCategoryGroupId(List<Long> casteCategoryGroupId,Long stateId);
	 public List<Object[]> getSubGroupOfCasteDetails(Long casteCategoryGroupId);
	 public List<Object[]> getCandidateCasteGroupId(Long casteGroupId,Long casteStateId);
	 public Long getCandidateCasteId(Long casteStateId);
}
