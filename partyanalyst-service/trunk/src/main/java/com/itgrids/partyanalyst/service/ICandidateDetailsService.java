/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICandidateDetailsService {
	
	public CandidateVO getCandidateDetails(Long candidateId);

	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId);
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId);
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,String type);
	
	public List<FileVO> getCandidatesPhotosInAGallary(Long gallaryId);
	
	public ResultStatus createNewGallary(GallaryVO gallaryVO);
	
	public List<FileVO> searchNewsDetails(FileVO inputs);
	
	public List<FileVO> getScopesForNewSearch();
	
	public List<FileVO> getStateDetails();
	
	public List<FileVO> getDistrictDetailsByStateId(Long stateId);
	
	public List<String> getCandidateProfileDescriptionByCandidateID(Long candidateId);
}