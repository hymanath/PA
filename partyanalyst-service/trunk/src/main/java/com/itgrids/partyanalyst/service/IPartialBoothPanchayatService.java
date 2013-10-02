package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartialBoothPanchayatVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPartialBoothPanchayatService {

	public Long getPanchayatBoothDetals(Long panchayatId,Long boothId);
	
	public ResultStatus savePartialBoothPanchayaDetails(Long panchayaId,
			Long boothId,Long partialPanchayatId,String description,String partialDescription,List<Long> hamletIds);
	
	public List<PartialBoothPanchayatVO> getAllPartialBoothsInASelectedMandal(Long mandalId,Long publicationId);
	
	public ResultStatus deleteSelectedPartialPanchayat(Long partialBoothPanchayatid,Long boothId);
	
	public List<PartialBoothPanchayatVO> getSelectedPartialPanchayatDetails(Long partialBoothPanchayatid);
	
	
	public ResultStatus updatePartialBoothPanchayaDetails(Long id , Long panchayaId,
			Long boothId,Long partialPanchayatId,String description,String partialDescription,Long hamletId);
	
	public List<SelectOptionVO> getHamletsIaAPanchaya(Long panchayatId);
	
}
