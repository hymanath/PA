package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IMahaNaduService {
	public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID,Long tehsilId,Long localElecBodyId);
	
	public CadreVo searchCadreDetails(Long userId,Long constiId, String searchBy,String searchType,String sort,String sortBy,int startIndex,int maxResult);
	
	 public List<SelectOptionVO> getIncomeSources();
	
	 public List<SelectOptionVO> getCasteCategories();
	 
	 public List<SelectOptionVO> getPartyDesignations();
	 
	 public List<SelectOptionVO> getgovernmentDesignations();
	 public ResultStatus saveCadreInfoForMahaNadu(CadreVo CadreVoToSave);
	 public CadreVo getCadreCompleteInfo(Long cadreId);
	 
	 public CadreVo getDetailToPopulate(String voterIdCardNo,Long publicationId);
	 
	 public CadreVo searchVoterInfo(Long userId,Long boothId, String searchName,String searchType,String sort,String sortBy,int startIndex,int maxResult);
}
